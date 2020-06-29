package clientes;

import clases.genericas.GenericListado;
import facturacion.GestorFacturas;
import facturacion.tarifas.Tarifa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class GestorClientes implements Serializable {

    //k --> NIF , V --> Cliente
    private Map<String,Cliente> listaClientes;
    GestorFacturas gestorFacturas;
    transient Scanner sc;

    public GestorClientes() {
        this.listaClientes = new HashMap<String, Cliente>();
        this.gestorFacturas = new GestorFacturas();
    }

    private void initScanner(){
        this.sc = new Scanner(System.in);
    }

    //Comprueba que exista el cliente
    public boolean compruebaCliente(String NIF){
        if (listaClientes.containsKey(NIF)) {
            return true;
        }
            return false;
    }

    // Anyade un cliente
    public boolean anyadirCliente(Cliente cliente){
        if (listaClientes.get(cliente.getNIF()) == null) { //   Compruebo que el cliente no exista ya en la ListaClientes
            listaClientes.put(cliente.getNIF(), cliente);  //   Añado el cliente nuevo con su clave NIF
            mostrarCliente(cliente.getNIF());
            return true;
        }
        return false;
    }

    // Borra un cliente a partir de su NIF
    public boolean borrarCliente(String NIF){
        if (compruebaCliente(NIF)) {
            listaClientes.remove(NIF);              //Si existe lo borro
            System.out.println("Cliente borrado con exito.");
            return true;
        }
        System.out.println("No se pudo borrar el cliente.");
        return false;
    }

    // Recuperar un cliente a partir de su NIF
    public Cliente obtenerCliente(String NIF){
        if (compruebaCliente(NIF)) {
           return listaClientes.get(NIF);
        }
        return null;
    }

    /* --------------------- MOSTRAR ------------------------------- */
    //Mostrar datos cliente a partir de su NIF
    public void mostrarCliente(String NIF){
        if (compruebaCliente(NIF))
            System.out.println(listaClientes.get(NIF).toString());
    }

    //Mostrar todos los clientes
    public Collection<Cliente> mostrarListado(){
        System.out.println("Listado de clientes:");
        if (listaClientes.size()>0) {       //Compruebo que exista el cliente
            listaClientes.forEach((NIF,Cliente) -> System.out.println("NIF: " + NIF + ": Nombre: " + Cliente.getNombre()));
        }
        else
            System.out.println("Cartera de clientes vacia.");
        return listaClientes.values();
    }

    //Mostrar listado clientes entre dos fechas
    public Collection mostrarListado(LocalDate inicio, LocalDate fin){

        System.out.println("Listado de clientes:");
        Collection<Cliente> clientes = new GenericListado<Cliente>().mostrar_listado(listaClientes.values(),inicio,fin);
        clientes.forEach(cliente -> {
            System.out.println(cliente.toString());
        });
        return clientes;
    }

    /* ------------------------- TARIFA --------------------------- */
    //Cambiar la tarifa del cliente
    public boolean cambiarTarifa(String NIF){
        this.initScanner();
        System.out.println("Introduzca nueva tarifa (€ / min): ");

        if((this.cambiarTarifa(NIF,sc.nextDouble())) != null )
            return true;
        else
            return false;
    }

    public Tarifa cambiarTarifa(String NIF, double nuevaTarifa){
        if (compruebaCliente(NIF)) {
            Cliente cliente = listaClientes.get(NIF);
            cliente.getTarifa().setTarifa(nuevaTarifa);
            System.out.println("Nueva tarifa: " + cliente.getTarifa());
            return cliente.getTarifa();
        }
        return null;
    }

}