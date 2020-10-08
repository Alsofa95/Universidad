package facturacion;

import facturacion.tarifas.Tarifa;
import facturacion.tarifas.TarifaDomingo;
import facturacion.tarifas.TarifaTardes;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TarifasTest {

    @Test
    public void testTarifaTarde() {
        Tarifa tarifa = new Tarifa(0.15);
        tarifa = new TarifaTardes(tarifa);
        assertThat(tarifa.getTarifa(), is(0.05));
    }

    @Test
    public void testTarifaDomingos() {
        Tarifa tarifa = new Tarifa(0.15);
        tarifa = new TarifaDomingo(tarifa);
        assertThat(tarifa.getTarifa(), is(0.0));
    }

    @Test
    public void testCambiosTarifas() {
        Tarifa tarifa = new Tarifa(0.15);
        tarifa = new TarifaTardes(tarifa);
        tarifa = new TarifaDomingo(tarifa);
        assertThat(tarifa.getTarifa(), is(0.0));
    }
}
