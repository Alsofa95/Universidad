package facturacion;

import clases.genericas.GenericListado;
import clientes.Cliente;
import clientes.GestorClientes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class GestorLlamadas implements Serializable {

    //k --> NIF , V --> Llamada
    private Map<String, List<Llamada>> listaLlamadas;
    transient Scanner sc;

    public GestorLlamadas() {
        this.listaLlamadas = new HashMap<String, List<Llamada>>();
    }

    private void initScanner(){
        this.sc = new Scanner(System.in);
    }

    /* ------------------- ALTAS llamadas --------------------------*/
    public boolean altaLlamada(String NIF, long numero, long duracion){
        if(!listaLlamadas.containsKey(NIF))
            listaLlamadas.put(NIF,new ArrayList<Llamada>());

        Llamada llamada = new Llamada(numero,duracion);
        if(listaLlamadas.get(NIF).add( llamada )){
            return true;
        }
        return false;
    }

    public boolean altaLlamada(String NIF, GestorClientes gc){
        if(gc.compruebaCliente(NIF)){
            this.initScanner();

            long numero, duracion;
            System.out.println("Introduce numero de telefono:");
            numero = sc.nextLong();
            System.out.println("Introduce duracion de la llamada en segundos");
            duracion = sc.nextLong();
            this.altaLlamada(NIF,numero,duracion);
            return true;
        }
        return false;
    }

    /* ------------------- MOSTRAR llamadas ---------------------*/
    //Devuelve las llamadas efectuadas por un cliente
    public List listarLlamadas(String NIF){
        if (listaLlamadas.containsKey(NIF))
            return listaLlamadas.get(NIF);
        else
            return null;
    }

    //Muestra las llamadas de un cliente
    public List<Llamada> mostrarLlamadas(String NIF){
        if (listaLlamadas.containsKey(NIF))
            return listaLlamadas.get(NIF);
        else
            System.out.println("El cliente no tiene ninguna llamada");
        return null;
    }

    //Mostrar listado de llamadas entre dos fechas
    public Collection<Llamada> mostrarListado(String NIF, LocalDate inicio, LocalDate fin){

        if(listaLlamadas.containsKey(NIF))
            return new GenericListado<Llamada>().mostrar_listado(listaLlamadas.get(NIF),inicio,fin);
        return null;
    }

    public boolean mostrarLlamadas(String NIF, GestorClientes gc){
        if(gc.compruebaCliente(NIF)){
            this.mostrarLlamadas(NIF);
            return true;
        }
        return false;
    }


}
