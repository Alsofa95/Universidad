package facturacion.tarifas;

public abstract class TarifaExtra extends Tarifa {
    private Tarifa tarifa;

    public TarifaExtra(Tarifa tarifa, double precioExtra) {
        super(precioExtra);
        this.tarifa = tarifa;
    }

    @Override
    public double getTarifa() {
        return super.getTarifa();
    }
}
