package mvc.modelo;

import clientes.Cliente;
import facturacion.Factura;
import facturacion.Llamada;

import java.util.Collection;
import java.util.List;

public interface InterrogaModelo {

    Collection<Cliente> getClientes();
    Collection<Cliente> getClienteNif(String NIF);
    Collection<Cliente> getClientesFechas(String FechaIni,String FechaFin);
    boolean compruebaCliente(String NIF);

    List<Llamada> getLlamadas(String NIF);
    List<Llamada> getLlamadasFechas(String NIF , String FechaIni,String FechaFin);

    List<Factura> getFacturaCodigo(int codigo);
    List<Factura> getFacturaNif(String NIF);
    List getFacturaNifFechas(String NIF , String FechaIni,String FechaFin);

}
