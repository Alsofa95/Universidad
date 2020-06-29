package mvc.vista;

import facturacion.Llamada;
import mvc.controlador.Controlador;
import mvc.modelo.InterrogaModelo;
import mvc.vista.tabla.Tabla;
import mvc.vista.tabla.TablaLlamada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static javax.swing.BorderFactory.createTitledBorder;

public class VistaLlamada extends JFrame implements InterrogaVista<Llamada>,InformaVista, VistaGeneral{

    private InterrogaModelo modelo;
    private Controlador controlador;

    /* ---- Componentes ---- */
    private JPanel jPanelBuscador;
    private JPanel jPanelContenido;
    private JPanel jPanelLLamadaNueva;

    private JButton jButtonBuscarFechas;
    private JButton jButtonNuevo;
    private JButton jButtonLlamadaNueva;
    private JButton jButtonCerrar;

    private JScrollPane jScrollPane1;

    private JTextField jTextFieldFecha1,jTextFieldFecha2;
    private JTextField jTextFieldNIF;
    private JTextField jTextFieldNifNuevo;
    private JTextField jTextFieldNumero,jTextFieldDuracion;

    private JLabel jLabelAlta1,jLabelAlta2,jLabelAlta3;
    private JLabel jLabelBuscar1,jLabelBuscar2, jLabelBuscar3;

    private Escuchador escuchador;
    private Tabla tabla;

    /* Constructor */
    public VistaLlamada(InterrogaModelo modelo, Controlador controlador){
        this.modelo = modelo;
        this.controlador = controlador;
        iniciarComponentes();
        GUI();
    }

    public void iniciarComponentes(){
        jPanelBuscador = new JPanel();
        jPanelContenido = new JPanel();
        jPanelLLamadaNueva = new JPanel();

        jButtonNuevo = new JButton("Alta llamada");
        jButtonBuscarFechas = new JButton("Buscar");
        jButtonLlamadaNueva = new JButton("Agregar llamada");
        jButtonCerrar = new JButton("Cerrar");

        jTextFieldFecha1 = new JTextField();
        jTextFieldFecha2 = new JTextField();
        jTextFieldNIF = new JTextField();
        jTextFieldNifNuevo = new JTextField();
        jTextFieldNumero = new JTextField();
        jTextFieldDuracion = new JTextField();

        jScrollPane1 = new JScrollPane();
        TablaLlamada modeloTabla = new TablaLlamada(new ArrayList<>());
        tabla = new Tabla(modeloTabla);

        jLabelAlta1 = new JLabel("NIF Cliente:");
        jLabelAlta2 = new JLabel("Numero de telefono");
        jLabelAlta3 = new JLabel("Duracion de la llamada");
        jLabelBuscar1 = new JLabel("Introduce NIF:");
        jLabelBuscar2 = new JLabel("[Puedes introducir tambien un periodo]");
        jLabelBuscar3 = new JLabel("Buscar entre fechas: (AAAA-MM-dd)");

        escuchador = new Escuchador();
    }

    public void GUI() {
        setTitle("Menu Llamada");

        jButtonNuevo.addActionListener(escuchador);
        jButtonBuscarFechas.addActionListener(escuchador);
        jButtonLlamadaNueva.addActionListener(escuchador);
        jButtonCerrar.addActionListener(escuchador);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        /* ****************************************************************************/
        jPanelContenido.setBorder(createTitledBorder("Listado Llamadas"));
        jPanelContenido.setLayout(new BoxLayout(jPanelContenido, BoxLayout.LINE_AXIS));

        jScrollPane1.setViewportView(tabla);
        jPanelContenido.add(jScrollPane1);

        /* ****************************************************************************/
        jPanelBuscador.setBorder(createTitledBorder("Buscar Llamada"));
        jPanelBuscador.setLayout(new GridLayout(13, 0));

        jPanelBuscador.add(jButtonNuevo);

        jPanelBuscador.add(jLabelBuscar1);
        jPanelBuscador.add(jTextFieldNIF);

        jPanelBuscador.add(jLabelBuscar2);
        jPanelBuscador.add(jLabelBuscar3);
        jPanelBuscador.add(jTextFieldFecha1);
        jPanelBuscador.add(jTextFieldFecha2);
        jPanelBuscador.add(jButtonBuscarFechas);

        jPanelContenido.add(jPanelBuscador);
        /* ****************************************************************************/
        jPanelLLamadaNueva.setBorder(createTitledBorder("Alta factura"));
        jPanelLLamadaNueva.setLayout(new java.awt.GridLayout(13, 0));

        jPanelLLamadaNueva.add(jLabelAlta1);
        jPanelLLamadaNueva.add(jTextFieldNifNuevo);

        jPanelLLamadaNueva.add(jLabelAlta2);
        jPanelLLamadaNueva.add(jTextFieldNumero);

        jPanelLLamadaNueva.add(jLabelAlta3);
        jPanelLLamadaNueva.add(jTextFieldDuracion);

        jPanelLLamadaNueva.add(jButtonLlamadaNueva);
        jPanelLLamadaNueva.add(jButtonCerrar);

        jPanelContenido.add(jPanelLLamadaNueva);

        jPanelLLamadaNueva.setVisible(false);

        /* ****************************************************************************/
        getContentPane().add(jPanelContenido);

        pack();
        setSize(800, 450);
    }

    public void creaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
    }

    public void runGUI(){
        setVisible(true);
    }

    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();

            if(texto.equals("Alta llamada"))
                jPanelLLamadaNueva.setVisible(true);
            else if(texto.equals("Buscar"))
                getBuscadorNif();
            else if(texto.equals("Agregar llamada"))
                controlador.anyadeLlamada();
            else if(texto.equals("Cerrar"))
                jPanelLLamadaNueva.setVisible(false);
        }
    }

    @Override
    public Llamada getNuevoElemento() {
        String NIF = jTextFieldNifNuevo.getText();
        String numero = jTextFieldNumero.getText();
        String duracion = jTextFieldDuracion.getText();

        if(compruebaCasillas(NIF,numero,duracion)){
            return new Llamada(Long.parseLong(numero),Long.parseLong(duracion));
        }
        return null;
    }

    @Override
    public String currentCliente() {
        return jTextFieldNifNuevo.getText();
    }

    //Buscador de clientes a partir de su NIF
    private void getBuscadorNif(){
        String NIF = jTextFieldNIF.getText();

        if(NIF.length() == 9){
            if(jTextFieldFecha1.getText().equals("") && jTextFieldFecha2.getText().equals(""))
                actualizarTabla( modelo.getLlamadas(NIF));
            else
                getBuscadorFechas(NIF);
        }
        else
            JOptionPane.showMessageDialog(null, "El NIF no es correcto.");
    }

    //Buscador de llamadas entre dos fechas
    private void getBuscadorFechas(String NIF){
        String FechaInicio = jTextFieldFecha1.getText();
        String FechaFin = jTextFieldFecha2.getText();

        if(isFecha(FechaInicio) && isFecha(FechaFin)){
            if(validaPeriodo(new String[]{FechaInicio,FechaFin}))
                actualizarTabla( modelo.getLlamadasFechas(NIF,FechaInicio,FechaFin));
            else
                JOptionPane.showMessageDialog(null, "Las fechas no son validas.");
        }
        else
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto.");
    }

    private boolean compruebaCasillas(String NIF, String numero, String duracion){

        if(!NIF.equals("") && !numero.equals("") && !duracion.equals("")){
            if(NIF.length() != 9){
                JOptionPane.showMessageDialog(null, "El NIF no es correcto.");
                return false;
            }
            if(numero.length() != 9){
                JOptionPane.showMessageDialog(null, "El numero de telefono no es correcto.");
                return false;
            }
            if(duracion.length()>20) {
                JOptionPane.showMessageDialog(null, "La duracion excede lo maximo permitido.");
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Hay casillas vacias");
            return false;
        }
        return true;
    }

    public boolean isFecha(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean validaPeriodo(String[] cadena){
        LocalDate[] fechas = {LocalDate.parse(cadena[0]),LocalDate.parse(cadena[1])};
        if(cadena[0].equals(cadena[1]) || fechas[1].isAfter(fechas[0]) || fechas[0].isBefore(fechas[1]))
            return true;
        else
            return false;
    }

    @Override
    public String getValueTabla() {
        return null;
    }

    @Override
    public void actualizarTabla() {
        tabla.setModel(new TablaLlamada(modelo.getLlamadas(currentCliente())));
    }

    @Override
    public void actualizarTabla(Collection collection) {
        if(collection != null)
            tabla.setModel(new TablaLlamada((java.util.List) collection));
        else
            tabla.setModel(new TablaLlamada(new ArrayList()));
    }
}
