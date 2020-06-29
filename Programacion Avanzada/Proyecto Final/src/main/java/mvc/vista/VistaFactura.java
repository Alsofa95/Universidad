package mvc.vista;

import facturacion.Factura;
import mvc.controlador.Controlador;
import mvc.modelo.InterrogaModelo;
import mvc.vista.tabla.Tabla;
import mvc.vista.tabla.TablaFactura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static javax.swing.BorderFactory.createTitledBorder;

public class VistaFactura extends JFrame implements InterrogaVista<Factura>,InformaVista, VistaGeneral {

    private InterrogaModelo modelo;
    private Controlador controlador;

    /* ---- Componentes ---- */
    private JPanel jPanelBuscador;
    private JPanel jPanelContenido;
    private JPanel jPanelFacturaNueva;

    private JButton jButtonNuevo;
    private JButton jButtonBuscarFechas;
    private JButton jButtonBuscarCodigo;
    private JButton jButtonAltaFactura;
    private JButton jButtonCerrar;

    private JScrollPane jScrollPane1;

    private JTextField jTextFieldCodigo;
    private JTextField jTextFieldFecha1,jTextFieldFecha2;
    private JTextField jTextFieldNIF;
    private JTextField jTextFieldNifNuevo;
    private JTextField jTextFieldFechaNueva1,jTextFieldFechaNueva2;

    private JLabel jLabelOp1,jLabelOp2,jLabelOp3,jLabelOp4;
    private JLabel jLabelAlta1,jLabelAlta2,jLabelAlta3;

    private Escuchador escuchador;
    private Tabla tabla;

    /* Constructor */
    public VistaFactura(InterrogaModelo modelo, Controlador controlador){
        this.modelo = modelo;
        this.controlador = controlador;
        iniciarComponentes();
        GUI();
    }

    public void iniciarComponentes(){
        jScrollPane1 = new JScrollPane();

        jPanelContenido = new JPanel();
        jPanelBuscador = new JPanel();
        jPanelFacturaNueva = new JPanel();

        TablaFactura modeloTabla = new TablaFactura(new ArrayList<>());
        tabla = new Tabla(modeloTabla);

        jButtonNuevo = new JButton("Alta Factura");
        jButtonBuscarCodigo = new JButton("Buscar por codigo");
        jButtonBuscarFechas = new JButton("Buscar");
        jButtonAltaFactura = new JButton("Tramitar Factura");
        jButtonCerrar = new JButton("Cerrar");

        jTextFieldCodigo = new JTextField();
        jTextFieldNifNuevo = new JTextField();
        jTextFieldFecha1 = new JTextField();
        jTextFieldFecha2 = new JTextField();

        jTextFieldFechaNueva1 = new JTextField();
        jTextFieldFechaNueva2 = new JTextField();
        jTextFieldNIF = new JTextField();

        jLabelOp1 = new JLabel("Buscar por Codigo");
        jLabelOp2 = new JLabel("Introduce NIF: ");
        jLabelOp3 = new JLabel("[Puedes introducir tambien un periodo]");
        jLabelOp4 = new JLabel("Buscar entre fechas: (AAAA-MM-dd)");
        jLabelAlta1 = new JLabel("NIF Cliente:");
        jLabelAlta2 = new JLabel("Fecha Inicio");
        jLabelAlta3 = new JLabel("Fecha fin ");

        escuchador = new Escuchador();
    }

    public void GUI() {
        setTitle("Menu Factura");

        jButtonAltaFactura.addActionListener(escuchador);
        jButtonBuscarCodigo.addActionListener(escuchador);
        jButtonBuscarFechas.addActionListener(escuchador);
        jButtonNuevo.addActionListener(escuchador);
        jButtonCerrar.addActionListener(escuchador);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        /* ****************************************************************************/
        jPanelContenido.setBorder(createTitledBorder("Listado Facturas"));
        jPanelContenido.setLayout(new BoxLayout(jPanelContenido, BoxLayout.LINE_AXIS));

        jScrollPane1.setViewportView(tabla);
        jPanelContenido.add(jScrollPane1);

        /* ****************************************************************************/
        jPanelBuscador.setBorder(createTitledBorder("Opciones Factura"));
        jPanelBuscador.setLayout(new GridLayout(13, 0));

        jPanelBuscador.add(jButtonNuevo);

        jPanelBuscador.add(jLabelOp1);
        jPanelBuscador.add(jTextFieldCodigo);
        jPanelBuscador.add(jButtonBuscarCodigo);

        jPanelBuscador.add(jLabelOp2);
        jPanelBuscador.add(jTextFieldNIF);
        jPanelBuscador.add(jLabelOp3);
        jPanelBuscador.add(jLabelOp4);
        jPanelBuscador.add(jTextFieldFecha1);
        jPanelBuscador.add(jTextFieldFecha2);
        jPanelBuscador.add(jButtonBuscarFechas);

        jPanelContenido.add(jPanelBuscador);
        /* ****************************************************************************/
        jPanelFacturaNueva.setBorder(createTitledBorder("Alta factura"));
        jPanelFacturaNueva.setLayout(new GridLayout(13, 0));

        jPanelFacturaNueva.add(jLabelAlta1);
        jPanelFacturaNueva.add(jTextFieldNifNuevo);

        jPanelFacturaNueva.add(jLabelAlta2);
        jPanelFacturaNueva.add(jTextFieldFechaNueva1);

        jPanelFacturaNueva.add(jLabelAlta3);
        jPanelFacturaNueva.add(jTextFieldFechaNueva2);

        jPanelFacturaNueva.add(jButtonAltaFactura);

        jPanelFacturaNueva.add(jButtonCerrar);
        jPanelContenido.add(jPanelFacturaNueva);

        jPanelFacturaNueva.setVisible(false);

        getContentPane().add(jPanelContenido);
        pack();
        setSize(1000, 450);
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

            if(texto.equals("Alta Factura"))
                jPanelFacturaNueva.setVisible(true);
            else if(texto.equals("Buscar por codigo"))
                buscarFacturaCodigo();
            else if(texto.equals("Buscar"))
                getBuscadorNif();
            else if(texto.equals("Cerrar"))
                jPanelFacturaNueva.setVisible(false);
            else if(texto.equals("Tramitar Factura"))
               controlador.anyadeFactura();
        }
    }

    @Override
    public Factura getNuevoElemento() {
        String fechaInicio = jTextFieldFechaNueva1.getText();
        String fechaFin = jTextFieldFechaNueva2.getText();

        if(currentCliente().length() == 9){
            if(isFecha(fechaInicio) && isFecha(fechaFin)){
                if(validaPeriodo(new String[]{fechaInicio,fechaFin})){
                    LocalDate[] periodo = {LocalDate.parse(fechaInicio),LocalDate.parse(fechaFin)};
                    return new Factura(null,periodo);
                }else
                    JOptionPane.showMessageDialog(null, "Las fechas no son validas.");

            }else{
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
            }
        }
        return null;
    }

    @Override
    public void actualizarTabla() {
        tabla.setModel(new TablaFactura(modelo.getFacturaNif(currentCliente())));
    }

    @Override
    public void actualizarTabla(Collection collection) {
        if(collection != null)
            tabla.setModel( new TablaFactura(new ArrayList(collection)));
        else{
            tabla.setModel(new TablaFactura(new ArrayList()));
            JOptionPane.showMessageDialog(null, "Factura no encontrado.");
        }
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
    public String currentCliente() {
        return jTextFieldNifNuevo.getText();
    }

    public void buscarFacturaCodigo(){
        String texto = jTextFieldCodigo.getText();
        try{
            int codigo = Integer.parseInt(texto);
            actualizarTabla(modelo.getFacturaCodigo(codigo));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Formato del codigo no es correcto");
        }
    }

    //Buscador de clientes a partir de su NIF
    private void getBuscadorNif(){
        String NIF = jTextFieldNIF.getText();

        if(NIF.length() == 9){
            if(jTextFieldFecha1.getText().equals("") && jTextFieldFecha2.getText().equals(""))
                actualizarTabla( modelo.getFacturaNif(NIF));
            else
                getBuscadorFechas(NIF);
        }
        else
            JOptionPane.showMessageDialog(null, "El NIF no es correcto.");
    }

    //Buscador de facturas entre dos fechas
    private void getBuscadorFechas(String NIF){
        String FechaInicio = jTextFieldFecha1.getText();
        String FechaFin = jTextFieldFecha2.getText();

        if(isFecha(FechaInicio) && isFecha(FechaFin)){
            if(validaPeriodo(new String[]{FechaInicio,FechaFin}))
                actualizarTabla( modelo.getFacturaNifFechas(NIF,FechaInicio,FechaFin));
            else
                JOptionPane.showMessageDialog(null, "Las fechas no son validas.");
        }
        else
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto.");
    }

    @Override
    public String getValueTabla() {
        return null;
    }
}
