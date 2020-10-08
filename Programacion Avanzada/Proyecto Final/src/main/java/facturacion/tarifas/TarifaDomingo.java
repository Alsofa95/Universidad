package facturacion.tarifas;

public class TarifaDomingo extends TarifaExtra {
    private Tarifa tarifa;

    public TarifaDomingo(Tarifa tarifa) {
        super(tarifa, 0);
        this.tarifa = tarifa;
    }
}
