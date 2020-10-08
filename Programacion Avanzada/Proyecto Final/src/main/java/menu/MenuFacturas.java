package menu;

import clases.exceptions.PeriodoException;
import clientes.GestorClientes;
import facturacion.GestorFacturas;
import facturacion.GestorLlamadas;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuFacturas {

    transient Scanner sc;
    GestorClientes gestorClientes;
    GestorLlamadas gestorLlamadas;
    GestorFacturas gestorFacturas;

    public MenuFacturas(GestorClientes gestorClientes, GestorLlamadas gestorLlamadas,GestorFacturas gestorFacturas,Scanner sc) {
        this.sc = sc;
        this.gestorClientes = gestorClientes;
        this.gestorLlamadas = gestorLlamadas;
        this.gestorFacturas = gestorFacturas;
        this.menu_Facturas();
    }

    private void menu_Facturas(){
        boolean subBucle = true;
        while(subBucle){
            this.opcionesFacturas();
            int seleccion = Integer.parseInt(sc.next());
            switch (seleccion){
                case 0:
                    subBucle = false;
                    break;
                case 1:
                    System.out.println("Introduce NIF del cliente para emitir factura:");
                    String NIF = sc.next();
                    if(gestorClientes.compruebaCliente(NIF)) {
                        try {
                            this.gestorFacturas.emitirFactura(gestorClientes.obtenerCliente(NIF),gestorLlamadas.listarLlamadas(NIF));
                        } catch (PeriodoException e) {
                            e.printStackTrace();
                            System.out.println("* No se pudo emitir la factura.");
                        }
                    }

                    break;
                case 2:
                    System.out.println("Introduce código de la factura:");
                    this.gestorFacturas.mostrarFacturasCodigo(sc.nextInt());
                    break;
                case 3:
                    System.out.println("Introduce NIF del cliente para mostrar todas sus facturas");
                    this.gestorFacturas.mostrarFacturas(sc.next());
                    break;
                case 4:
                    this.listarFacturas();
                    break;
            }
        }
    }

    private void opcionesFacturas(){
        System.out.println("-------------------------------------");
        System.out.println("Seleccione una opción");
        System.out.println("1.Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas.");
        System.out.println("2.Recuperar los datos de una factura a partir de su código.");
        System.out.println("3.Recuperar todas las facturas de un cliente.");
        System.out.println("4.Mostar listado de facturas de un cliente emitidas entre dos fechas.");
        System.out.println("0.Atras");
    }

    private void listarFacturas(){
        System.out.println("Introduce NIF del cliente para mostrar sus facturas:");
        String NIF = sc.next();

        LocalDate fechas[]=new LocalDate[2];
        System.out.println("Introduzca fecha de inicio: YYYY-MM-dd");
        fechas[0] = LocalDate.parse(sc.next());
        System.out.println("Introduzca fecha de fin: YYYY-MM-dd");
        fechas[1] = LocalDate.parse(sc.next());

        System.out.println("Mostrar facturas entre: ");
        System.out.println("Fecha: " + fechas[0]);
        System.out.println("Fecha: " + fechas[1]);
        System.out.println("-----------------------");

        gestorFacturas.mostrarListado(NIF,fechas[0],fechas[1]);
    }
}
