package mvc.vista;

import clases.Tarea;
import clases.serializable.SerializableOut;
import mvc.controlador.Controlador;
import mvc.modelo.InterrogaModelo;
import mvc.vista.tabla.Tabla;
import mvc.vista.tabla.TablaTareas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vista extends JFrame implements InterrogaVista,InformaVista{

    private transient SerializableOut serOut;

    private int seleccion = -1 ; //Elemento seleccionado de filtrar tarea
    private int seleccionNuevo = -1 ; //Elemento seleccionado de nueva tarea
    private int terminado = -1;

    private InterrogaModelo modelo;
    private Controlador controlador;

    private Escuchador escuchador;
    private SelectorNuevo selectorNuevo;
    private EscuchadorTabla escuchadorTabla;

    private ButtonGroup buttonGroupCompletadas;
    private ButtonGroup buttonGroupNuevaPrioridad;
    private ButtonGroup buttonGroupPrioridad;

    private JButton jButtonActualiza;
    private JButton jButtonBorra;
    private JButton jButtonFiltrar;
    private JButton jButtonNuevo;

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanelBusqueda;
    private JPanel jPanelCompletadas;
    private JPanel jPanelLista;
    private JPanel jPanelNuevaPrioridad;
    private JPanel jPanelNuevo;
    private JPanel jPanelOpciones2;
    private JPanel jPanelPrioridad;

    private JRadioButton jRadioButtonCC;
    private JRadioButton jRadioButtonCN;
    private JRadioButton jRadioButtonCTodas;
    private JRadioButton jRadioButtonNPAlta;
    private JRadioButton jRadioButtonNPBaja;
    private JRadioButton jRadioButtonNPNormal;
    private JRadioButton jRadioButtonPAlta;
    private JRadioButton jRadioButtonPBaja;
    private JRadioButton jRadioButtonPNormal;
    private JRadioButton jRadioButtonPTodas;

    private JCheckBox jCheckBoxCompletada;
    private JScrollPane jScrollPane1;

    private Tabla tabla;

    private JTextField jTextFieldTitulo;
    private JTextArea jTextAreaDescripcion;

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    private void iniciarComponentes() {
        buttonGroupPrioridad = new ButtonGroup();
        buttonGroupCompletadas = new ButtonGroup();
        buttonGroupNuevaPrioridad = new ButtonGroup();

        jPanelBusqueda = new JPanel();
        jPanelPrioridad = new JPanel();
        jPanelCompletadas = new JPanel();
        jPanelLista = new JPanel();
        jPanelNuevo = new JPanel();
        jPanelNuevaPrioridad = new JPanel();
        jPanelOpciones2 = new JPanel();

        jRadioButtonPAlta = new JRadioButton();
        jRadioButtonPNormal = new JRadioButton();
        jRadioButtonPBaja = new JRadioButton();
        jRadioButtonPTodas = new JRadioButton();
        jRadioButtonCC = new JRadioButton();
        jRadioButtonCN = new JRadioButton();
        jRadioButtonCTodas = new JRadioButton();
        jRadioButtonNPAlta = new JRadioButton();
        jRadioButtonNPNormal = new JRadioButton();
        jRadioButtonNPBaja = new JRadioButton();

        jButtonFiltrar = new JButton();
        jButtonNuevo = new JButton();
        jButtonActualiza = new JButton();
        jButtonBorra = new JButton();

        jScrollPane1 = new JScrollPane();

        TablaTareas modeloTabla = new TablaTareas(new ArrayList());
        tabla = new Tabla(modeloTabla);
        this.actualizaTabla();

        escuchador = new Escuchador();
        selectorNuevo = new SelectorNuevo();
        escuchadorTabla = new EscuchadorTabla();

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        jTextFieldTitulo = new JTextField();
        jTextAreaDescripcion = new JTextArea();

        jCheckBoxCompletada = new JCheckBox();
    }

    private void GUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mis tareas");

        jButtonFiltrar.addActionListener(escuchador);
        jButtonNuevo.addActionListener(escuchador);
        jButtonActualiza.addActionListener(escuchador);
        jButtonBorra.addActionListener(escuchador);

        jPanelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Tarea"));
        jPanelBusqueda.setLayout(new GridLayout(1, 3));

        jPanelPrioridad.setBorder(BorderFactory.createTitledBorder("Prioridad"));
        jPanelPrioridad.setLayout(new GridLayout(4, 1));

        jRadioButtonPAlta.setText("Alta");
        jPanelPrioridad.add(jRadioButtonPAlta);

        jRadioButtonPNormal.setText("Normal");
        jPanelPrioridad.add(jRadioButtonPNormal);

        jRadioButtonPBaja.setText("Baja");
        jPanelPrioridad.add(jRadioButtonPBaja);

        jRadioButtonPTodas.setText("Todas");
        jPanelPrioridad.add(jRadioButtonPTodas);

        buttonGroupPrioridad.add(jRadioButtonPAlta);
        buttonGroupPrioridad.add(jRadioButtonPNormal);
        buttonGroupPrioridad.add(jRadioButtonPBaja);
        buttonGroupPrioridad.add(jRadioButtonPTodas);

        jRadioButtonPTodas.setSelected(true);

        jPanelBusqueda.add(jPanelPrioridad);

        jPanelCompletadas.setBorder(BorderFactory.createTitledBorder("Completadas"));
        jPanelCompletadas.setLayout(new GridLayout(4, 1));

        jRadioButtonCC.setText("Completada");
        jPanelCompletadas.add(jRadioButtonCC);

        jRadioButtonCN.setText("No completada");
        jPanelCompletadas.add(jRadioButtonCN);

        jRadioButtonCTodas.setText("Todas");
        jPanelCompletadas.add(jRadioButtonCTodas);

        buttonGroupCompletadas.add(jRadioButtonCC);
        buttonGroupCompletadas.add(jRadioButtonCN);
        buttonGroupCompletadas.add(jRadioButtonCTodas);

        jRadioButtonCTodas.setSelected(true);

        jPanelBusqueda.add(jPanelCompletadas);

        jButtonFiltrar.setText("Aplicar filtros");
        jPanelBusqueda.add(jButtonFiltrar);

        getContentPane().add(jPanelBusqueda, BorderLayout.PAGE_START);

        /* ------ TABLA ------ */
        jPanelLista.setBorder(BorderFactory.createTitledBorder("Lista de tareas"));
        jPanelLista.setLayout(new BorderLayout());

        tabla.getSelectionModel().addListSelectionListener(escuchadorTabla);
        jScrollPane1.setViewportView(tabla);

        jPanelLista.add(jScrollPane1, BorderLayout.CENTER);

        getContentPane().add(jPanelLista, BorderLayout.CENTER);
        /* ------------ */

        jPanelNuevo.setBorder(BorderFactory.createTitledBorder("Detalle de la tarea"));
        jPanelNuevo.setLayout(new GridLayout(4, 2, 10, 10));

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Título:");
        jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
        jPanelNuevo.add(jLabel1);

        jPanelNuevo.add(jTextFieldTitulo);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Descripción:");
        jPanelNuevo.add(jLabel2);

        jTextAreaDescripcion.setPreferredSize(new Dimension(164, 20));
        jPanelNuevo.add(jTextAreaDescripcion);

        jCheckBoxCompletada.setText("Completada");
        jCheckBoxCompletada.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelNuevo.add(jCheckBoxCompletada);

        jPanelNuevaPrioridad.setBorder(BorderFactory.createTitledBorder("Prioridad"));
        jPanelNuevaPrioridad.setLayout(new GridLayout(1, 3));

        jRadioButtonNPAlta.setText("Alta");
        jPanelNuevaPrioridad.add(jRadioButtonNPAlta);

        jRadioButtonNPNormal.setText("Normal");
        jPanelNuevaPrioridad.add(jRadioButtonNPNormal);

        jRadioButtonNPBaja.setText("Baja");
        jPanelNuevaPrioridad.add(jRadioButtonNPBaja);

        buttonGroupNuevaPrioridad.add(jRadioButtonNPAlta);
        buttonGroupNuevaPrioridad.add(jRadioButtonNPNormal);
        buttonGroupNuevaPrioridad.add(jRadioButtonNPBaja);

        jRadioButtonNPAlta.addActionListener(selectorNuevo);
        jRadioButtonNPNormal.addActionListener(selectorNuevo);
        jRadioButtonNPBaja.addActionListener(selectorNuevo);

        jPanelNuevo.add(jPanelNuevaPrioridad);

        jButtonNuevo.setText("Nuevo");
        jPanelNuevo.add(jButtonNuevo);

        jPanelOpciones2.setLayout(new GridLayout(1, 2));

        jButtonActualiza.setText("Actualiza");
        jPanelOpciones2.add(jButtonActualiza);

        jButtonBorra.setText("Borra");
        jPanelOpciones2.add(jButtonBorra);

        jPanelNuevo.add(jPanelOpciones2);

        getContentPane().add(jPanelNuevo, BorderLayout.PAGE_END);

        //Funcion al cerrar la ventana
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serOut.outputData();
                System.exit(0);
            }
        });

        pack();
        setSize(500,600);
        setVisible(true);
    }

    public void creaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                iniciarComponentes();
                GUI();
            }
        });
    }

    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();

            if(texto.equals("Aplicar filtros"))
                filtrarCampos();
            else if(texto.equals("Nuevo"))
                controlador.anyadeTarea();
            else if(texto.equals("Actualiza"))
                controlador.modificaTarea();
            else if(texto.equals("Borra")){
                controlador.borraTarea();
                clearNuevo();
            }

        }
    }

    class SelectorNuevo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JRadioButton seleccion = (JRadioButton) e.getSource();
            String texto = seleccion.getText();

            if(texto.equals("Alta"))
                seleccionNuevo = 3;
            else if(texto.equals("Normal"))
                seleccionNuevo = 2;
            else if(texto.equals("Baja"))
                seleccionNuevo = 1;

        }
    }

    class EscuchadorTabla implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (tabla.getSelectedRow() > -1) {
                LocalDateTime fechaTarea = (LocalDateTime) tabla.getValueAt(tabla.getSelectedRow(), 3);
                Tarea tarea = modelo.obtenerTarea(fechaTarea);
                jTextFieldTitulo.setText(tarea.getTitulo());
                jTextAreaDescripcion.setText(tarea.getDescripcion());
                jCheckBoxCompletada.setSelected(tarea.getTerminado());

                if(tarea.getPrioridad() == 1){
                    jRadioButtonNPBaja.setSelected(true);
                    seleccionNuevo = 1;
                }
                else if(tarea.getPrioridad() == 2){
                    jRadioButtonNPNormal.setSelected(true);
                    seleccionNuevo = 2;
                }
                else{
                    jRadioButtonNPAlta.setSelected(true);
                    seleccionNuevo = 3;
                }
            }
        }
    }

    public void setSerOut(SerializableOut serOut) {
        this.serOut = serOut;
    }

    @Override
    public Tarea getElemento() {
        String titulo = jTextFieldTitulo.getText();
        String descripcion = jTextAreaDescripcion.getText();
        Boolean terminado = jCheckBoxCompletada.isSelected();

        if(this.seleccionNuevo > 0 ){
            if(!titulo.equals("") && !descripcion.equals("")){
                Tarea tarea = new Tarea(titulo,descripcion,terminado,this.seleccionNuevo);
                this.clearNuevo();
                return tarea;
            }else
                JOptionPane.showMessageDialog(null, "Faltan campos por completar.");
        }else
            JOptionPane.showMessageDialog(null, "Debe seleccionar una prioridad.");

        return null;
    }

    @Override
    public LocalDateTime getValueTabla() {
        if(tabla.getSelectedRow() >= 0 ){
            return (LocalDateTime) tabla.getValueAt(tabla.getSelectedRow(),3);
        }
        return null;
    }

    private void filtrarCampos(){
        if(jRadioButtonPAlta.isSelected()) seleccion = 3;
        else if(jRadioButtonPNormal.isSelected()) seleccion = 2;
        else if(jRadioButtonPBaja.isSelected()) seleccion = 1;
        else seleccion = 0;

        if(jRadioButtonCC.isSelected()) terminado = 2;
        else if(jRadioButtonCN.isSelected()) terminado = 1;
        else terminado = 0;

        modelo.filtraTareas(this.seleccion,this.terminado);
    }


    @Override
    public void actualizaTabla() {
        tabla.setModel(new TablaTareas(modelo.muestraTareas()));
        this.hideColumna();
    }

    @Override
    public void actualizaTabla(List<Tarea> lista) {
        tabla.setModel(new TablaTareas(lista));
        this.hideColumna();
    }


    private void hideColumna(){
        // Oculta la columna de fecha, con la que se identifica a la tarea
        // La he ocultado porque no he creido que sea necesaria para el usuario.

        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
    }

    private void clearNuevo(){
        this.seleccionNuevo = -1;
        buttonGroupNuevaPrioridad.clearSelection();
        jTextFieldTitulo.setText("");
        jTextAreaDescripcion.setText("");
        jCheckBoxCompletada.setSelected(false);
    }
}
