package mvc.controlador;

import clases.Tarea;
import mvc.modelo.CambioModelo;
import mvc.vista.InterrogaVista;

import java.time.LocalDateTime;

public class ControladorTarea implements Controlador {

    private CambioModelo modelo;
    private InterrogaVista vista;

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    @Override
    public void anyadeTarea() {
        Tarea tarea = vista.getElemento();
        if(tarea != null)
            modelo.anyadeTarea(tarea);
    }

    @Override
    public void borraTarea() {
        modelo.borraTarea(vista.getValueTabla());
    }

    @Override
    public void modificaTarea() {
        Tarea tarea = vista.getElemento();
        LocalDateTime fechaTarea = vista.getValueTabla();
        if(tarea != null && fechaTarea != null)
            modelo.modificaTarea(fechaTarea,tarea);
    }
}
