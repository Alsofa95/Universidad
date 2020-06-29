package menu;

import clientes.GestorClientes;
import facturacion.GestorLlamadas;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuLlamada {

    transient Scanner sc;
    GestorClientes gestorClientes;
    GestorLlamadas gestorLlamadas;

    public MenuLlamada(GestorClientes gestorClientes, GestorLlamadas gestorLlamadas,Scanner sc) {
        this.sc = sc;
        this.gestorClientes = gestorClientes;
        this.gestorLlamadas = gestorLlamadas;

        this.menu_Llamadas();
    }


    private void menu_Llamadas(){
        boolean subBucle = true;
        while(subBucle){
            this.opcionesLlamadas();
            int seleccion = Integer.parseInt(sc.next());
            switch (seleccion){
                case 0:
                    subBucle = false;
                    break;
                case 1:
                    System.out.println("Introduce NIF del cliente para alta de llamada:");
                    gestorLlamadas.altaLlamada(sc.next(),gestorClientes);
                    break;
                case 2:
                    System.out.println("Introduce NIF del cliente para mostrar sus llamadas:");
                    gestorLlamadas.mostrarLlamadas(sc.next(),gestorClientes);
                    break;
                case 3:
                    this.listarLlamadas();
                    break;
            }

        }
    }

    private void opcionesLlamadas(){
        System.out.println("-------------------------------------");
        System.out.println("Seleccione una opción");
        System.out.println("1.Dar de alta una llamada.");
        System.out.println("2.Listar todas las llamadas de un cliente.");
        System.out.println("3.Listar todas las llamadas de un cliente entre dos fechas.");
        System.out.println("0.Atras");
    }

    // Debo comprobar que el NIF exista !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void listarLlamadas(){
        System.out.println("Introduce NIF del cliente para mostrar sus llamadas:");
        String NIF = sc.next();

        LocalDate fechas[]=new LocalDate[2];
        System.out.println("Introduzca fecha de inicio: YYYY-MM-dd");
        fechas[0] = LocalDate.parse(sc.next());
        System.out.println("Introduzca fecha de fin: YYYY-MM-dd");
        fechas[1] = LocalDate.parse(sc.next());

        System.out.println("Mostrar llamadas entre: ");
        System.out.println("Fecha: " + fechas[0]);
        System.out.println("Fecha: " + fechas[1]);
        System.out.println("-----------------------");

        gestorLlamadas.mostrarListado(NIF,fechas[0],fechas[1]);
    }
}
