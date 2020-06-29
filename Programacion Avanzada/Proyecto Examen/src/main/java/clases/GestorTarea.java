package clases;

import clases.filtrador.Filtrador;
import clases.filtrador.FiltrarPrioridad;
import clases.filtrador.FiltrarTerminado;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GestorTarea implements Gestor, Serializable {
    private Map<LocalDateTime,Tarea> listadoTareas;

    public GestorTarea() {
        listadoTareas = new TreeMap<>();
    }

    @Override
    public void anyadirTareas(Tarea tarea) {
        listadoTareas.put(tarea.getFecha(), tarea);
    }

    @Override
    public void borrarTarea(LocalDateTime fechaTarea) {
        listadoTareas.remove(fechaTarea);
    }

    @Override
    public void modificarTarea(LocalDateTime fecha, int prioridad, boolean terminada) {
        Tarea modificada = listadoTareas.get(fecha);
        modificada.setPrioridad(prioridad);
        modificada.setTerminado(terminada);
    }

    @Override
    public List mostrarTareas() {
        return new ArrayList(listadoTareas.values());
    }

    @Override
    public List<Tarea> filtrarTareas(int prioridad, int terminada) {
        Filtrador filtrador = new Filtrador(this.mostrarTareas());
        List<Tarea> nuevaLista = new ArrayList<>();

        if(prioridad == 0 && terminada == 0)
            nuevaLista = this.mostrarTareas();
        else{
            if(prioridad>0){
                filtrador.setFiltro(new FiltrarPrioridad(prioridad));
                nuevaLista = filtrador.getFiltrado();
            }
            if(terminada>0){
                if(terminada == 2)                      // OPCION -> Terminado
                    filtrador.setFiltro(new FiltrarTerminado(true));
                else if(terminada == 1)                 // OPCION -> No Terminado
                    filtrador.setFiltro(new FiltrarTerminado(false));
                nuevaLista = filtrador.getFiltrado();
            }
        }
        return nuevaLista;
    }

    @Override
    public Tarea obtenerTarea(LocalDateTime fechaTarea) {
        if(fechaTarea != null && listadoTareas.containsKey(fechaTarea) )
            return listadoTareas.get(fechaTarea);
        else
            return null;
    }


}
