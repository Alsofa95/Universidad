package clientes;

import clientes.tipo.Empresa;
import clientes.tipo.Particular;

public class FabricaClientes {

    public FabricaClientes() {
    }

    public Cliente getParticular(String nombre, String NIF, Direccion direccion, String correo, String apellidos){
        return new Particular(nombre, NIF, direccion, correo,apellidos);
    }
    public Cliente getEmpresa(String nombre, String NIF, Direccion direccion, String correo){
        return new Empresa(nombre, NIF, direccion, correo);
    }

}
