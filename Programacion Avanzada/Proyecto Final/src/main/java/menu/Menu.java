package menu;

import clientes.GestorClientes;
import facturacion.GestorFacturas;
import facturacion.GestorLlamadas;

import java.io.Serializable;
import java.util.Scanner;

public class Menu implements Serializable {

    private boolean bucle;
    transient Scanner sc;
    GestorClientes gestorClientes;
    GestorLlamadas gestorLlamadas;
    GestorFacturas gestorFacturas;


    public Menu(){
        gestorClientes = new GestorClientes();
        gestorLlamadas = new GestorLlamadas();
        gestorFacturas = new GestorFacturas();
    }

    private void initScanner(){
        this.sc = new Scanner(System.in);
    }

    public void menu(){
        bucle=true;
        this.initScanner();

        while(bucle){
            this.opciones();
            int seleccion = sc.nextInt();
            switch (seleccion){
                case 0:
                    bucle = false;
                    break;
                case 1:
                        new MenuCliente(gestorClientes,sc);
                    break;
                case 2:
                        new MenuLlamada(gestorClientes,gestorLlamadas,sc);
                    break;
                case 3:
                        new MenuFacturas(gestorClientes,gestorLlamadas,gestorFacturas,sc);
                    break;
            }
        }
    }

    private void opciones(){
        System.out.println("-------------------------------------");
        System.out.println("Seleccione una opción");
        System.out.println("1.Gestion Clientes");
        System.out.println("2.Gestion Llamadas");
        System.out.println("3.Gestion Facturas");
        System.out.println("0.Exit");
    }

}
