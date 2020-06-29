package facturacion;

import clases.genericas.GenericObject;
import facturacion.tarifas.Tarifa;

import java.time.LocalDate;
import java.util.Random;

public class Factura extends GenericObject {

    private Integer codigo;
    private Tarifa tarifa;
    private LocalDate fecha;
    private LocalDate[] perido;
    private double importe;

    public Factura(Tarifa tarifa, LocalDate[] periodo) {
        super(LocalDate.now());
        this.codigo = new Random().nextInt(100000); //Codigo generado para cada factura
        this.tarifa = tarifa;
        this.perido = periodo;
    }


    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public LocalDate[] getPerido() {
        return perido;
    }

    public void setPerido(LocalDate[] perido) {
        this.perido = perido;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "Codigo de Factura : " + codigo + '\n' +
                "Tarifa aplicada = " + tarifa.toString() + '\n' +
                "Fecha emitida = " + this.get_Fecha() + '\n' +
                "Perido = Desde " + perido[0] + " hasta "+ perido[1] + '\n' +
                "Importe TOTAL = " + importe +" €"+ '\n';
    }
}
