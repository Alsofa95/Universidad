package mvc.controlador;

public interface Controlador {
    void anyadeCliente();
    void anyadeLlamada();
    void anyadeFactura();

    void borrarCliente();
    void cambiaTarifaCliente(double tarifa);
}
