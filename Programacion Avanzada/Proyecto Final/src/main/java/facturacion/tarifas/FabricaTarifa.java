package facturacion.tarifas;

public class FabricaTarifa {

    public Tarifa getTarifaDomingo(Tarifa tarifa){
        return new TarifaDomingo(tarifa);
    }
    public Tarifa getTarifaTardes(Tarifa tarifa){
        return new TarifaTardes(tarifa);
    }
}
