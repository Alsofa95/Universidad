package clientes.tipo;

import clientes.Cliente;
import clientes.Direccion;

public class Particular extends Cliente {
    private String apellidos;

    public Particular(String nombre, String NIF, Direccion direccion, String correo, String apellidos) {
        super(nombre, NIF, direccion, correo);
        this.apellidos = apellidos;
    }
}
