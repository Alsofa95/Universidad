package clases.filtrador;

import clases.Tarea;

import java.util.ArrayList;
import java.util.List;

public class FiltrarPrioridad implements Filtrar {

    private int prioridad;

    public FiltrarPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public List filtrar(List<Tarea> lista) {
        List<Tarea> listaFiltrada = new ArrayList<>();
        lista.forEach(tarea -> {
            if(tarea.getPrioridad()==this.prioridad)
                listaFiltrada.add(tarea);
        });

        return listaFiltrada;
    }
}
