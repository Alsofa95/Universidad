package clases.filtrador;

import clases.Tarea;

import java.util.List;

public class Filtrador {

    private List<Tarea> lista;
    private Filtrar filtro;

    public Filtrador(List<Tarea> lista) {
        this.lista = lista;
    }

    public List<Tarea> getLista() {
        return lista;
    }

    public void setFiltro(Filtrar filtro) {
        this.filtro = filtro;
    }

    public List<Tarea> getFiltrado(){
        this.lista = filtro.filtrar(this.lista);
        return this.lista;
    }
}
