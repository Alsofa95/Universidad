package mvc.vista.tabla;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Tabla extends JTable {

    public Tabla(AbstractTableModel modelo) {
        super(modelo);
        setAutoCreateRowSorter(true);
    }
}
