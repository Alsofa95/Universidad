package mvc.modelo;

import clientes.Cliente;
import clientes.GestorClientes;
import facturacion.Factura;
import facturacion.GestorFacturas;
import facturacion.GestorLlamadas;
import facturacion.Llamada;
import mvc.vista.InformaVista;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ModeloAgenda implements CambioModelo,InterrogaModelo, Serializable {

    transient private InformaVista vistaCliente,vistaLlamada,vistaFactura;
    private GestorClientes gestorClientes;
    private GestorLlamadas gestorLlamadas;
    private GestorFacturas gestorFacturas;


    public ModeloAgenda() {
        gestorClientes = new GestorClientes();
        gestorLlamadas = new GestorLlamadas();
        gestorFacturas = new GestorFacturas();
    }

    public void setVista(InformaVista[] vista) {
        this.vistaCliente = vista[0];
        this.vistaLlamada = vista[1];
        this.vistaFactura = vista[2];
    }



    // -----------  CLIENTE  -----------------------
    @Override
    public void anyadeCliente(Cliente cliente) {
        gestorClientes.anyadirCliente(cliente);
        vistaCliente.actualizarTabla();
    }

    @Override
    public void cambiaTarifa(String NIF, double tarifa) {
        gestorClientes.cambiarTarifa(NIF,tarifa);
        vistaCliente.actualizarTabla();
    }

    @Override
    public void borraCliente(String NIF) {
        gestorClientes.borrarCliente(NIF);
        vistaCliente.actualizarTabla();
    }

    @Override
    public Collection<Cliente> getClientes() {
        return gestorClientes.mostrarListado();

    }

    @Override
    public Collection<Cliente> getClienteNif(String NIF) {
        Cliente cliente = gestorClientes.obtenerCliente(NIF);
        Collection<Cliente> col = null;
        if(cliente != null){
            col = new HashSet<Cliente>();
            col.add(cliente);
        }
        return col;
    }

    @Override
    public Collection<Cliente> getClientesFechas(String FechaIni,String FechaFin) {
        return gestorClientes.mostrarListado(LocalDate.parse(FechaIni),LocalDate.parse(FechaFin));
    }

    @Override
    public boolean compruebaCliente(String NIF){
        return gestorClientes.compruebaCliente(NIF);
    }

    // -----------  LLAMADA  ---------------------
    @Override
    public void anyadeLlamada(String NIF, Llamada llamada) {
        if(compruebaCliente(NIF))
            gestorLlamadas.altaLlamada(NIF,llamada.getNumero(),llamada.getDuracion());
        vistaLlamada.actualizarTabla();
    }

    @Override
    public List<Llamada> getLlamadas(String NIF) {
        return gestorLlamadas.mostrarLlamadas(NIF);
    }

    @Override
    public List getLlamadasFechas(String NIF , String FechaIni,String FechaFin) {
        if(compruebaCliente(NIF))
            return new ArrayList<>(gestorLlamadas.mostrarListado(NIF,LocalDate.parse(FechaIni),LocalDate.parse(FechaFin)));
        return new ArrayList();
    }



    // -----------  FACTURA  ---------------------
    @Override
    public void anyadeFactura(String NIF, Factura factura) {
        if(compruebaCliente(NIF)){
            LocalDate[] periodo = factura.getPerido();
            Collection<Llamada> listaLlamadas = gestorLlamadas.mostrarListado(NIF,periodo[0],periodo[1]);
            if(listaLlamadas.size()>0)
                gestorFacturas.emitirFactura(gestorClientes.obtenerCliente(NIF),listaLlamadas,factura);
        }
        vistaFactura.actualizarTabla();
    }

    @Override
    public List<Factura> getFacturaCodigo(int codigo) {
        List<Factura> lista = new ArrayList<>();
        Factura factura = gestorFacturas.mostrarFacturasCodigo(codigo);
        if(factura != null)
            lista.add(factura);
        return lista;
    }

    @Override
    public List<Factura> getFacturaNif(String NIF) {
        return gestorFacturas.mostrarFacturas(NIF);
    }

    @Override
    public List getFacturaNifFechas(String NIF , String FechaIni,String FechaFin) {
        if(compruebaCliente(NIF))
            return new ArrayList(gestorFacturas.mostrarListado(NIF,LocalDate.parse(FechaIni),LocalDate.parse(FechaFin)));
        return new ArrayList();
    }

}
