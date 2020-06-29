package facturacion;

import clases.genericas.GenericObject;

import java.time.LocalDate;
import java.time.LocalTime;

public class Llamada extends GenericObject {

    private long numero;
    private LocalDate fecha;
    private LocalTime hora;
    private long duracion; //Duracion en segundos

    public Llamada(long numero, long duracion) {
        super(LocalDate.now());
        this.numero = numero;
        this.duracion = duracion;
        this.hora = LocalTime.now();
    }

    public long getNumero() {
        return numero;
    }

    public long getDuracion() {
        return duracion;
    }

    public LocalTime getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "Llamada: \n" +
                "Numero = " + numero + '\n' +
                "Duracion = " + duracion +" segundos"+ '\n' +
                "Fecha = " + this.get_Fecha() + '\n' +
                "Hora = " + hora + '\n';
    }
}
