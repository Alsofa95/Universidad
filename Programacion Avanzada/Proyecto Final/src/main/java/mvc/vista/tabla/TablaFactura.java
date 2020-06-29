package mvc.vista.tabla;

import facturacion.Factura;
import facturacion.Llamada;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TablaFactura extends AbstractTableModel {

    final List<String> cabecera = Arrays.asList("Fecha Factura","Codigo","Importe","Fecha Inicio","Fecha Fin","Tarifa");
    List<Factura> datos;

    public TablaFactura(List datos) {
        this.datos = datos;
    }

    public TablaFactura setDatos(final List<Factura> datos){
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
        LocalDate[] periodo = datos.get(fila).getPerido();

        if (columna == 0) return datos.get(fila).get_Fecha();
        if (columna == 1) return datos.get(fila).getCodigo();
        if (columna == 2) return datos.get(fila).getImporte() + " €";
        if (columna == 3) return periodo[0];
        if (columna == 4) return periodo[1];
        if (columna == 5) return datos.get(fila).getTarifa();
        else return "";
    }



    @Override
    public String getColumnName(int column) {
        return cabecera.get(column);
    }
}
