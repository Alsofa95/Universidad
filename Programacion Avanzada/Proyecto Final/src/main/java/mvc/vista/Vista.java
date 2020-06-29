package mvc.vista;

import clases.Serializacion.SerializableOutput;
import mvc.controlador.Controlador;
import mvc.modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Vista extends JFrame implements VistaGeneral{

    private InterrogaModelo modelo;
    private Controlador controlador;
    private SerializableOutput serializable;

    private VistaCliente vc;
    private VistaLlamada vl;
    private VistaFactura vf;

    private JButton jButtonClientes,jButtonFacturas,jButtonLlamadas;
    private JPanel jPanelSeleccion;

    Escuchador escuchador;

    public Vista(InterrogaModelo modelo, Controlador controlador) {
        this.modelo = modelo;
        this.controlador = controlador;
        vc = new VistaCliente(modelo,controlador);
        vl = new VistaLlamada(modelo,controlador);
        vf = new VistaFactura(modelo,controlador);
        iniciarComponentes();
    }

    public InterrogaVista[] getInterrogantes() {
        InterrogaVista[] vistas = {vc,vl,vf};
        return vistas;
    }
    public InformaVista[] getInformantes(){
        InformaVista[] vistas = {vc,vl,vf};
        return vistas;
    }

    public void iniciarComponentes() {
        jPanelSeleccion = new JPanel();
        jButtonClientes = new JButton("Clientes");
        jButtonLlamadas = new JButton("Llamadas");
        jButtonFacturas = new JButton("Facturas");
        escuchador = new Escuchador();

    }

    public void GUI(){
        setTitle("Agenda/Cartera clientes");
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanelSeleccion.setLayout(new java.awt.GridLayout(3, 2));
        jPanelSeleccion.add(new JLabel("- Menu de gestion de clientes. (Añadir clientes,Borrar cliente...) "));
        jButtonClientes.setFont(new Font("Tahoma", 0, 18));
        jButtonClientes.addActionListener(escuchador);
        jPanelSeleccion.add(jButtonClientes);

        jPanelSeleccion.add(new JLabel("- Menu de gestion de llamadas. (Alta nuevas llamadas, buscar llamadas..) "));
        jButtonLlamadas.setFont(new Font("Tahoma", 0, 18));
        jButtonLlamadas.addActionListener(escuchador);
        jPanelSeleccion.add(jButtonLlamadas);


        jPanelSeleccion.add(new JLabel("- Menu de gestion de facturas. (Alta de facturas, buscar facturas...) "));
        jButtonFacturas.setFont(new Font("Tahoma", 0, 18));
        jButtonFacturas.addActionListener(escuchador);
        jPanelSeleccion.add(jButtonFacturas);

        getContentPane().add(jPanelSeleccion);

        /* Anyadimos funcion al cerrar el programa */
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serializable.outputData();
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    public void creaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI();
            }
        });
    }

    public void setSerializable(SerializableOutput serializable){
        this.serializable = serializable;
    }

    class Escuchador implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();

            if(texto.equals("Clientes"))
                vc.creaGUI();
            else if(texto.equals("Llamadas"))
                vl.creaGUI();
            else if(texto.equals("Facturas"))
                vf.creaGUI();
        }
    }
}
