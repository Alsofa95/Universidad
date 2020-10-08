package mvc.controlador;

import clientes.Cliente;
import facturacion.Factura;
import facturacion.Llamada;
import mvc.modelo.CambioModelo;
import mvc.vista.InterrogaVista;
import java.io.Serializable;


public class ControladorAgenda implements Controlador, Serializable {
    private CambioModelo modelo;
    private InterrogaVista vistaCliente,vistaLlamada,vistaFacturas;


    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista[] vista) {
        this.vistaCliente = vista[0];
        this.vistaLlamada = vista[1];
        this.vistaFacturas = vista[2];
    }

    public void anyadeCliente() {   //Recoge los datos de nuevo cliente
        Cliente cliente = (Cliente) vistaCliente.getNuevoElemento();
        if(cliente !=null)
            modelo.anyadeCliente(cliente);
    }


    public void anyadeLlamada() {   //Recoge los datos de nueva llamada
        Llamada llamada = (Llamada) vistaLlamada.getNuevoElemento();
        if(llamada!=null)
            modelo.anyadeLlamada(vistaLlamada.currentCliente(),llamada);
    }


    public void anyadeFactura() {   //Recoge los datos de nueva factura
        Factura factura = (Factura) vistaFacturas.getNuevoElemento();
        if(factura !=null)
            modelo.anyadeFactura(vistaFacturas.currentCliente(),factura);
    }

    @Override
    public void borrarCliente() {
        String NIF = vistaCliente.getValueTabla();
        if(NIF.length() == 9)
            modelo.borraCliente(NIF);
    }

    @Override
    public void cambiaTarifaCliente(double tarifa) {
        String NIF = vistaCliente.getValueTabla();
        if(NIF.length() == 9 && tarifa>0)
            modelo.cambiaTarifa(NIF,tarifa);
    }


}
