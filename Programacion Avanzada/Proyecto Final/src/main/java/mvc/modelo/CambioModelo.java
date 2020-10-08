package mvc.modelo;

import clientes.Cliente;
import facturacion.Factura;
import facturacion.Llamada;

public interface CambioModelo {
    void anyadeCliente(Cliente cliente);
    void cambiaTarifa(String NIF, double tarifa);
    void borraCliente(String NIF);

    void anyadeLlamada(String NIF, Llamada llamada);
    void anyadeFactura(String NIF, Factura factura);
}
