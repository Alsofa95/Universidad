package clientes;
import clases.genericas.GenericObject;
import facturacion.tarifas.Tarifa;

import java.time.LocalDate;


public class Cliente extends GenericObject {

    private String nombre;
    private String NIF;
    private Direccion direccion;
    private String correo;
    private Tarifa tarifa;


    public Cliente(String nombre, String NIF, Direccion direccion, String correo) {

        super(LocalDate.now());
        this.nombre = nombre;
        this.NIF = NIF;
        this.direccion = direccion;
        this.correo = correo;
        this.tarifa =  new Tarifa();
    }

    public String getNombre() {
        return nombre;
    }

    public String getNIF() {
        return this.NIF;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }


    @Override
    public String toString() {
        return "Cliente : \n" +
                "Nombre = " + nombre + '\n' +
                "NIF = " + NIF + '\n' +
                "Direccion = " + direccion.toString() + '\n' +
                "Correo = " + correo + '\n' +
                "Tarifa = " + tarifa.toString() + '\n'+
                "Fecha de creacion = " + this.get_Fecha();
    }
}


