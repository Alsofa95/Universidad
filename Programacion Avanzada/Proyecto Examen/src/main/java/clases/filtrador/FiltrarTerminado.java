package clases.filtrador;

import clases.Tarea;

import java.util.ArrayList;
import java.util.List;

public class FiltrarTerminado implements Filtrar{

    private boolean terminado;

    public FiltrarTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    @Override
    public List filtrar(List<Tarea> lista) {
        List<Tarea> listaFiltrada = new ArrayList<>();
        lista.forEach(tarea -> {
            if(tarea.getTerminado()==this.terminado)
                listaFiltrada.add(tarea);
        });

        return listaFiltrada;
    }
}
