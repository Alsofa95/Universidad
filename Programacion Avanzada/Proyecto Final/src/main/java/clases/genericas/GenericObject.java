package clases.genericas;

import clases.GestorFecha;

import java.io.Serializable;
import java.time.LocalDate;

public class GenericObject implements GestorFecha, Serializable {

    private LocalDate fecha;

    public GenericObject(LocalDate fecha) {
        this.fecha = fecha;
    }


    @Override
    public LocalDate get_Fecha() {
        return this.fecha;
    }
}
