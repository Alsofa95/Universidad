package mvc.modelo;

import clases.GestorTarea;
import clases.Tarea;
import mvc.vista.InformaVista;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ModeloTarea implements CambioModelo,InterrogaModelo, Serializable {
    private transient InformaVista vista;
    private GestorTarea gestorTarea;

    public ModeloTarea(){
        this.gestorTarea = new GestorTarea();
    }

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }

    @Override
    public void anyadeTarea(Tarea tarea) {
        gestorTarea.anyadirTareas(tarea);
        vista.actualizaTabla();
    }

    @Override
    public void modificaTarea(LocalDateTime fechaTarea,Tarea tareaModif) {

        Tarea tarea = obtenerTarea(fechaTarea);
        if(tarea != null){
            tarea.setTitulo(tareaModif.getTitulo());
            tarea.setDescripcion(tareaModif.getDescripcion());
            tarea.setTerminado(tareaModif.getTerminado());
            tarea.setPrioridad(tareaModif.getPrioridad());

            vista.actualizaTabla();
        }
    }

    @Override
    public void borraTarea(LocalDateTime fechaTarea) {
        if(fechaTarea != null){
            gestorTarea.borrarTarea(fechaTarea);
            vista.actualizaTabla();
        }
    }

    @Override
    public List<Tarea> muestraTareas() {
        return gestorTarea.mostrarTareas();
    }

    @Override
    public void filtraTareas(int prioridad, int terminada) {
        List<Tarea> listaFiltrada = gestorTarea.filtrarTareas(prioridad,terminada);
        vista.actualizaTabla(listaFiltrada);
    }

    @Override
    public Tarea obtenerTarea(LocalDateTime fechaTarea) {
        return gestorTarea.obtenerTarea(fechaTarea);
    }
}
