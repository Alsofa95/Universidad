package mvc.vista.tabla;

import clientes.Cliente;
import clientes.tipo.Particular;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.*;

public class TablaClientes extends AbstractTableModel {

    final List<String> cabecera = Arrays.asList("Tipo","Nombre", "NIF","Correo","Codigo postal","Provincia","Poblacion","Tarifa aplicada");
    List<Cliente> datos;

    public TablaClientes(Collection<Cliente> datos) {
        this.datos = toList(datos);
    }

    public TablaClientes setDatos(final List<Cliente> datos){
        this.datos = datos;
        return this;
    }

    private List toList(Collection collection){
        return new ArrayList<Cliente>(collection);
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

        if (columna == 0) return tipoCliente(datos.get(fila));
        if (columna == 1) return datos.get(fila).getNombre();
        if (columna == 2) return datos.get(fila).getNIF();
        if (columna == 3) return datos.get(fila).getCorreo();
        if (columna == 4) return datos.get(fila).getDireccion().getCodigo();
        if (columna == 5) return datos.get(fila).getDireccion().getProvincia();
        if (columna == 6) return datos.get(fila).getDireccion().getPoblacion();
        if (columna == 7) return datos.get(fila).getTarifa().toString();
        else return "";
    }

    private String tipoCliente(Cliente cli){
       return cli.getClass().getSimpleName();
    }

    @Override
    public String getColumnName(int column) {
        return cabecera.get(column);
    }

}
