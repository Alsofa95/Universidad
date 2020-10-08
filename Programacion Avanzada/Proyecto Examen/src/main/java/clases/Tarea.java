package clases;

import clases.genericClass.GenericObject;

public class Tarea extends GenericObject {

    private String titulo;
    private String descripcion;
    private Boolean terminado;
    private int prioridad;

    public Tarea(String titulo, String descripcion, Boolean terminado, int prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.terminado = terminado;
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTerminado() {
        return terminado;
    }

    public void setTerminado(Boolean terminado) {
        this.terminado = terminado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Tarea " + getFecha() + ":" +
                "Titulo='" + titulo + '\'' +
                "Descripcion='" + descripcion + '\'' +
                "Terminado=" + terminado +
                "Prioridad=" + prioridad;
    }
}
