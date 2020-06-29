package mvc.vista.tabla;

import facturacion.Llamada;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class TablaLlamada extends AbstractTableModel {

    final List<String> cabecera = Arrays.asList("Fecha llamada","Hora","Numero","Duracion");
    List<Llamada> datos;

    public TablaLlamada(List datos) {
        this.datos = datos;
    }

    public TablaLlamada setDatos(final List<Llamada> datos){
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

        if (columna == 0) return datos.get(fila).get_Fecha();
        if (columna == 1) return datos.get(fila).getHora();
        if (columna == 2) return datos.get(fila).getNumero();
        if (columna == 3) return datos.get(fila).getDuracion()/360 +" h | " + datos.get(fila).getDuracion()/60 + " min | " + datos.get(fila).getDuracion() % 60 + " seg" ;
        else return "";
    }

    @Override
    public String getColumnName(int column) {
        return cabecera.get(column);
    }
}
