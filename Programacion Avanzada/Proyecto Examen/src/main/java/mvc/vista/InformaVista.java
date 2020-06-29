package mvc.vista;

import clases.Tarea;

import java.util.List;

public interface InformaVista {

    public void actualizaTabla();
    public void actualizaTabla(List<Tarea> lista);

}
