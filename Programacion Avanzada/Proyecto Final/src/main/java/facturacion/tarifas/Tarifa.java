package facturacion.tarifas;

public class Tarifa {

    private double tarifa; //Precio / minuto

    public Tarifa() {
        this.tarifa = 0.15;
    }

    public Tarifa(double nuevaTarifa){
        this.tarifa = nuevaTarifa;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return tarifa + " € /min";
    }

    // todo Falta el método (abstract) al que le pasáis una llamada y devuelve el coste de esa llamada
    // todo Ese será el método que deberán sobreescribir cada una de las tarifas, y dentro de él
    // todo comprobar si son aplicables.
}
