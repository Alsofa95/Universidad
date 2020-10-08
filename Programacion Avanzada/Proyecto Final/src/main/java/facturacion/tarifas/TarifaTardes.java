package facturacion.tarifas;

public class TarifaTardes extends TarifaExtra {
    private Tarifa tarifa;

    public TarifaTardes(Tarifa tarifa) {
        super(tarifa, 0.05);
        this.tarifa = tarifa;
    }
}
