package mvc.vista;

import clientes.Cliente;
import clientes.Direccion;
import clientes.FabricaClientes;
import clientes.tipo.Empresa;
import clientes.tipo.Particular;
import mvc.controlador.Controlador;
import mvc.modelo.InterrogaModelo;
import mvc.vista.tabla.Tabla;
import mvc.vista.tabla.TablaClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collection;

import static javax.swing.BorderFactory.createTitledBorder;

public class VistaCliente extends JFrame implements InterrogaVista<Cliente>,InformaVista, VistaGeneral {

    private InterrogaModelo modelo;
    private Controlador controlador;

    /* ---- Componentes ---- */
    private JPanel jPanelBuscador;
    private JPanel jPanelCabecera;
    private JPanel jPanelContenido;
    private JPanel jPanelAltaCliente;
    private JScrollPane jScrollPane1;

    private JButton jButtonNuevo;
    private JButton jButtonTarifa;
    private JButton jButtonBorrar;
    private JButton jButtonMostrarTodo;
    private JButton jButtonBuscarFechas;
    private JButton jButtonBuscarNif;
    private JButton jButtonNuevoCliente;
    private JButton jButtonCerrarNuevoCliente;

    private JLabel  jLabelBuscar1,jLabelBuscar2;
    private JLabel jLabelAlta1,jLabelAlta2,jLabelAlta3,jLabelAlta4,jLabelAlta5,jLabelAlta6,jLabelAlta7;

    private JTextField jTextFieldFecha1,jTextFieldFecha2;
    private JTextField jTextFieldNif;
    private JTextField jTextFieldNombreNuevo,jTextFieldNifNuevo,jTextCorreoNuevo, jTextFieldPostalNuevo, jTextFieldProvNuevo, jTextFieldPoblacionNuevo,jTextFieldApellidos;

    private ButtonGroup buttonGroupTipoCliente;
    private JRadioButton jRadioButtonEmpresa;
    private JRadioButton jRadioButtonParticular;

    private Tabla tabla;
    private Escuchador escuchador;
    private Selector selector;

    FabricaClientes fab;

    /* Constructor */
    public VistaCliente(InterrogaModelo modelo,Controlador controlador){
        this.modelo = modelo;
        this.controlador = controlador;
        iniciarComponentes();
        GUI();
    }

    public void iniciarComponentes(){
        jPanelCabecera = new JPanel();
        jPanelBuscador = new JPanel();
        jPanelContenido = new JPanel();
        jPanelAltaCliente = new JPanel();

        jButtonNuevo = new JButton("Alta Cliente");
        jButtonBorrar = new JButton("Borrar Cliente");
        jButtonTarifa = new JButton("Cambiar Tarifa");
        jButtonBuscarNif = new JButton("Buscar cliente");
        jButtonBuscarFechas = new JButton("Buscar entre Fechas");
        jButtonNuevoCliente = new JButton("Agregar");
        jButtonCerrarNuevoCliente = new JButton("Cerrar");
        jButtonMostrarTodo = new JButton("Mostrar todo");

        jTextFieldNif = new JTextField();
        jTextFieldFecha1 = new JTextField();
        jTextFieldFecha2 = new JTextField();

        TablaClientes modeloTabla = new TablaClientes(this.modelo.getClientes());
        tabla = new Tabla(modeloTabla);

        jTextFieldNombreNuevo = new JTextField();
        jTextFieldNifNuevo = new JTextField();
        jTextCorreoNuevo = new JTextField();
        jTextFieldPostalNuevo = new JTextField();
        jTextFieldProvNuevo = new JTextField();
        jTextFieldPoblacionNuevo = new JTextField();
        jTextFieldApellidos = new JTextField();

        jLabelBuscar1 = new JLabel("Buscar por NIF");
        jLabelBuscar2 = new JLabel("Buscar entre fechas: (AAAA-MM-dd)");
        jLabelAlta1 = new JLabel("Nombre: ");
        jLabelAlta2 = new JLabel("NIF:");
        jLabelAlta3 = new JLabel("Correo electronico:");
        jLabelAlta4 = new JLabel("Codigo Postal: ");
        jLabelAlta5 = new JLabel("Provincia");
        jLabelAlta6 = new JLabel("Poblacion");
        jLabelAlta7 = new JLabel("Apellidos");

        jScrollPane1 = new JScrollPane();

        buttonGroupTipoCliente = new ButtonGroup();
        jRadioButtonEmpresa = new JRadioButton("Empresa");
        jRadioButtonParticular = new JRadioButton("Particular");

        escuchador = new Escuchador();
        selector = new Selector();

        fab = new FabricaClientes();
    }

    public void GUI() {
        setTitle("Menu Clientes");

        jButtonNuevo.addActionListener(escuchador);
        jButtonNuevoCliente.addActionListener(escuchador);
        jButtonCerrarNuevoCliente.addActionListener(escuchador);
        jButtonMostrarTodo.addActionListener(escuchador);
        jButtonBuscarNif.addActionListener(escuchador);
        jButtonBuscarFechas.addActionListener(escuchador);

        jButtonBorrar.addActionListener(escuchador);
        jButtonTarifa.addActionListener(escuchador);

        jRadioButtonEmpresa.addActionListener(selector);
        jRadioButtonParticular.addActionListener(selector);
        /*------------------------------Cabecera------------------------------------*/
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        jPanelCabecera.setBorder(createTitledBorder("Opciones para Clientes"));

        jPanelCabecera.add(jButtonNuevo);
        jPanelCabecera.add(jButtonBorrar);
        jPanelCabecera.add(jButtonTarifa);
        jPanelCabecera.add(jButtonMostrarTodo);

        getContentPane().add(jPanelCabecera);
        /*-------------------------------Tabla/Listado------------------------------*/
        jPanelContenido.setLayout(new BoxLayout(jPanelContenido, BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(createTitledBorder("Listado Clientes"));
        jScrollPane1.setViewportView(tabla);
        jPanelContenido.add(jScrollPane1);

        /*------------------------------------------------------------------*/
        jPanelBuscador.setBorder(createTitledBorder("Buscar Cliente"));
        jPanelBuscador.setLayout(new GridLayout(13, 0));

        jPanelBuscador.add(jLabelBuscar1);
        jPanelBuscador.add(jTextFieldNif);
        jPanelBuscador.add(jButtonBuscarNif);
        jPanelBuscador.add(jLabelBuscar2);
        jPanelBuscador.add(jTextFieldFecha1);
        jPanelBuscador.add(jTextFieldFecha2);
        jPanelBuscador.add(jButtonBuscarFechas);

        jPanelContenido.add(jPanelBuscador);
        /*------------------------------------------------------------------*/
        jPanelAltaCliente.setBorder(createTitledBorder("Alta Cliente"));
        jPanelAltaCliente.setLayout(new GridLayout(13, 0));

        jPanelAltaCliente.add(jLabelAlta1);
        jPanelAltaCliente.add(jTextFieldNombreNuevo);

        jPanelAltaCliente.add(jLabelAlta2);
        jPanelAltaCliente.add(jTextFieldNifNuevo);

        jPanelAltaCliente.add(jLabelAlta3);
        jPanelAltaCliente.add(jTextCorreoNuevo);

        jPanelAltaCliente.add(jLabelAlta4);
        jPanelAltaCliente.add(jTextFieldPostalNuevo);

        jPanelAltaCliente.add(jLabelAlta5);
        jPanelAltaCliente.add(jTextFieldProvNuevo);

        jPanelAltaCliente.add(jLabelAlta6);
        jPanelAltaCliente.add(jTextFieldPoblacionNuevo);

        buttonGroupTipoCliente.add(jRadioButtonEmpresa);
        jPanelAltaCliente.add(jRadioButtonEmpresa);

        buttonGroupTipoCliente.add(jRadioButtonParticular);
        jPanelAltaCliente.add(jRadioButtonParticular);

        jPanelAltaCliente.add(jLabelAlta7);
        jPanelAltaCliente.add(jTextFieldApellidos);

        jPanelAltaCliente.add(jButtonNuevoCliente);
        jPanelAltaCliente.add(jButtonCerrarNuevoCliente);

        jRadioButtonEmpresa.setSelected(true);
        jPanelContenido.add(jPanelAltaCliente);

        jLabelAlta7.setVisible(false);
        jTextFieldApellidos.setVisible(false);
        jPanelAltaCliente.setVisible(false);

        getContentPane().add(jPanelContenido);
        pack();
        setSize(1000, 400);
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

    /* ********* Escuchador ***********/
    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();

            System.out.println(texto);
            if(texto.equals("Agregar"))
                controlador.anyadeCliente();
            else if(texto.equals("Alta Cliente"))
                jPanelAltaCliente.setVisible(true);
            else if(texto.equals("Cerrar"))
                jPanelAltaCliente.setVisible(false);
            else if(texto.equals("Mostrar todo"))
                actualizarTabla();
            else if(texto.equals("Buscar cliente"))
                getBuscadorNif();
            else if(texto.equals("Buscar entre Fechas"))
                getBuscadorFechas();
            else if(texto.equals("Borrar Cliente"))
                controlador.borrarCliente();
            else if(texto.equals("Cambiar Tarifa"))
                controlador.cambiaTarifaCliente(getValueTarifa());
        }
    }

    class Selector implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JRadioButton seleccion = (JRadioButton) e.getSource();
            String texto = seleccion.getText();

            System.out.println(texto);
            if(texto.equals("Empresa")){
                jTextFieldApellidos.setVisible(false);
                jTextFieldApellidos.setText("");
                jLabelAlta7.setVisible(false);
            }
            else if(texto.equals("Particular")){
                jTextFieldApellidos.setVisible(true);
                jLabelAlta7.setVisible(true);
            }
        }
    }
    /* ********* Metodos ***********/

    @Override
    public Cliente getNuevoElemento() {
        String Nombre = jTextFieldNombreNuevo.getText(),
        Nif = jTextFieldNifNuevo.getText(),
        Correo = jTextCorreoNuevo.getText(),
        CodigoPost = jTextFieldPostalNuevo.getText(),
        Provincia = jTextFieldProvNuevo.getText(),
        Poblacion = jTextFieldPoblacionNuevo.getText(),
        Apellidos = jTextFieldApellidos.getText();


        if(comprobarCasillas(Nombre,Nif,Correo,CodigoPost,Provincia,Poblacion)){
            Direccion Direc = new Direccion(Integer.parseInt(CodigoPost),Provincia,Poblacion);
            jPanelAltaCliente.setVisible(false);
            this.cleanCasillas();
            if(Apellidos.equals(""))
                return fab.getEmpresa(Nombre,Nif,Direc,Correo);
            else
                return fab.getParticular(Nombre,Nif,Direc,Correo,Apellidos);
        }
        return null;
    }

    @Override
    public String currentCliente() {
        return getValueTabla();
    }

    //Buscador de clientes a partir de su NIF
    private void getBuscadorNif(){
        String NIF = jTextFieldNif.getText();
        if(NIF.length() == 9)
            actualizarTabla( modelo.getClienteNif(NIF));
        else
            JOptionPane.showMessageDialog(null, "El NIF no es correcto.");
    }

    //Buscador de clientes entre dos fechas
    private void getBuscadorFechas(){
        String FechaInicio = jTextFieldFecha1.getText();
        String FechaFin = jTextFieldFecha2.getText();

        if(isFecha(FechaInicio) && isFecha(FechaFin)){
            if(validaPeriodo(new String[]{FechaInicio,FechaFin}))
                actualizarTabla( modelo.getClientesFechas(FechaInicio,FechaFin) );
            else
                JOptionPane.showMessageDialog(null, "Las fechas no son validas.");
        }
        else
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto.");
    }

    public String getValueTabla(){
        if(tabla.getSelectedRow() >= 0 )
            return tabla.getValueAt(tabla.getSelectedRow(),2).toString();
        return "";
    }

    private double getValueTarifa(){
        String texto = JOptionPane.showInputDialog("Introduzca nueva tarifa. (X.X €/min)");
        double tarifa;
        System.out.println(texto);
        try {
            tarifa = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor de tarifa no admitido.");
            return 0;
        } catch (NullPointerException e){
            System.out.println("NullPoint");
            return 0;
        }
        return tarifa;
    }

    //Compruebo que los valores de las casillas de alta del cliente son correctos
    public boolean comprobarCasillas(String Nombre,String NIF,String Correo,String CodPost, String Provincia,String Poblacion){
        if(!Nombre.equals("") && !NIF.equals("") && !Correo.equals("") && !CodPost.equals("") && !Provincia.equals("") && !Poblacion.equals("")) {
            if(Nombre.length()>20) {
                JOptionPane.showMessageDialog(null, "El nombre excede el número de caracteres permitidos.");
                return false;
            }else{
                if(modelo.compruebaCliente(NIF)){
                    JOptionPane.showMessageDialog(null, "El NIF del cliente ya esta registrado.");
                    return false;
                }
            }
            if(NIF.length() != 9) {
                JOptionPane.showMessageDialog(null, "El NIF no es correcto.");
                return false;
            }
            if(Correo.length() > 50) {
                JOptionPane.showMessageDialog(null, "El Correo electronico excede el número de caracteres permitidos.");
                return false;
            }
            if(CodPost.length() != 5) {
                JOptionPane.showMessageDialog(null, "Codigo postal incorrecto.");
                return false;
            }else{
                try{
                    Integer.parseInt(CodPost);
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "El codigo postal tiene que ser un numero.");
                }
            }

        }else {
            JOptionPane.showMessageDialog(null, "Hay casillas vacias");
            return false;
        }
        return true;
    }

    private boolean isFecha(String dateStr) {
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

    //Limpia las casillas de los valores anteriores
    private void cleanCasillas(){
        jTextFieldNombreNuevo.setText("");
        jTextFieldNifNuevo.setText("");
        jTextCorreoNuevo.setText("");
        jTextFieldPostalNuevo.setText("");
        jTextFieldProvNuevo.setText("");
        jTextFieldPoblacionNuevo.setText("");
        jTextFieldApellidos.setText("");
        jTextFieldApellidos.setVisible(false);
        jLabelAlta7.setVisible(false);
    }

    @Override
    public void actualizarTabla() {
       tabla.setModel(new TablaClientes(modelo.getClientes()));
    }

    //Actualizo la tabla a partir de una coleccion pasada
    public void actualizarTabla(Collection collection){
        if(collection != null)
            tabla.setModel(new TablaClientes(collection));
        else
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
    }
}
