package mvc.vista.tabla;

import clases.Tarea;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class TablaTareas extends AbstractTableModel {

    final List<String> cabecera = Arrays.asList("Terminada","Prioridad","Descripcion","Fecha Creacion");
    List<Tarea> datos;

    public TablaTareas(List datos) {
        this.datos = datos;
    }

    public TablaTareas setDatos(final List<Tarea> datos){
        this.datos = datos;
        return this;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return cabecera.size();
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        if (columna == 0) return datos.get(fila).getTerminado();
        if (columna == 1) return this.getPrioridad(datos.get(fila).getPrioridad());
        if (columna == 2) return datos.get(fila).getDescripcion();
        if (columna == 3) return datos.get(fila).getFecha();
        else return "";
    }

    @Override
    public String getColumnName(int column) {
        return cabecera.get(column);
    }

    private String getPrioridad(int prioridad){
        if(prioridad == 1)
            return "Baja";
        else if(prioridad == 2)
            return "Normal";
        else
            return "Alta";
    }
}
