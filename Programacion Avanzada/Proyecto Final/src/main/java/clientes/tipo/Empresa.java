package clientes.tipo;

import clientes.Cliente;
import clientes.Direccion;

public class Empresa extends Cliente {

    public Empresa(String nombre, String NIF, Direccion direccion, String correo) {
        super(nombre, NIF, direccion, correo);
    }


}
