package menu;

import clientes.FabricaClientes;
import clases.exceptions.NifException;
import clientes.Cliente;
import clientes.Direccion;
import clientes.GestorClientes;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuCliente {
    transient Scanner sc;
    GestorClientes gestorClientes;

    public MenuCliente(GestorClientes gestorClientes,Scanner sc) {
        this.sc = sc;
        this.gestorClientes = gestorClientes;
        menu_Clientes();
    }

    private void menu_Clientes(){
        boolean subBucle = true;
        while(subBucle){
            this.opcionesClientes();
            int seleccion = Integer.parseInt(sc.next());
            switch (seleccion){
                case 0:
                    subBucle = false;
                    break;
                case 1:
                    Cliente cliente = null;
                    try {
                        cliente = this.añadirCliente();
                        gestorClientes.anyadirCliente(cliente);
                    } catch (NifException e) {
                        e.printStackTrace();
                        System.out.println("* No se pudo añadir el cliente.");
                    }
                    break;
                case 2:
                    System.out.println("Introduce NIF del cliente a borrar:");
                    try {
                        gestorClientes.borrarCliente(this.solicitarNif());
                    } catch (NifException e) {
                        e.printStackTrace();
                        System.out.println("* No se pudo borrar el cliente.");
                    }
                    break;
                case 3:
                    System.out.println("Introduce NIF del cliente para modificar Tarifa:");
                    gestorClientes.cambiarTarifa(sc.next());
                    break;
                case 4:
                    System.out.println("Introduce NIF del cliente para visualizar sus datos:");
                    try {
                        gestorClientes.mostrarCliente(this.solicitarNif());
                    } catch (NifException e) {
                        e.printStackTrace();
                        System.out.println("* No se pudo modificar tarifa.");
                    }
                    break;
                case 5:
                    gestorClientes.mostrarListado();
                    break;
                case 6:
                    //Muestra un listado de clientes que fueron de alta entre 2 fechas
                    this.listadoClientes();
                    break;

            }

        }
    }

    private void opcionesClientes(){
        System.out.println("-------------------------------------");
        System.out.println("Seleccione una opción");
        System.out.println("1.Dar de alta un nuevo cliente");
        System.out.println("2.Borrar cliente");
        System.out.println("3.Cambiar la tarifa de un cliente");
        System.out.println("4.Recuperar los datos de un cliente a partir de su NIF");
        System.out.println("5.Recuperar el listado de todos los clientes");
        System.out.println("6.Mostrar listado de clientes dados de altas entre dos fechas.");
        System.out.println("0.Atras");
    }

    public Cliente añadirCliente() throws NifException {
        System.out.println("A continuación introduzca los datos del cliente");
        String[] data = this.credencialesCliente(); // NIF, Nombre y Correo del cliente
        Direccion direccion = this.nuevaDireccion(); // Direccion del cliente

        System.out.println("Tipo de cliente : 1.Particular \t 2.Empresa");
        int tipoCliente = Integer.parseInt(sc.next());

        FabricaClientes fc = new FabricaClientes();                 //<-- Fabrica Clientes

        switch (tipoCliente){
            case 1 :
                System.out.println("Introduzca los Apellidos del cliente:");
                String Apellidos = sc.next();
                return fc.getParticular(
                        data[0],
                        data[1],
                        direccion,
                        data[2],
                        Apellidos);
            case 2:
                return fc.getEmpresa(
                        data[0],
                        data[1],
                        direccion,
                        data[2]);
            default:
                System.out.println("No es una opción valida");
        }
        return null;
    }

    private String[] credencialesCliente() throws NifException { //NIF, Nombre, Correo.
        String[] dataCliente=new String[3];
        System.out.println("Introduzca Nombre del cliente:");
        dataCliente[0]=sc.next(); //1.Nombre

        System.out.println("Introduzca NIF del cliente:");
        dataCliente[1]=this.solicitarNif(); //0.NIF

        System.out.println("Introduzca Correo electronico del cliente:");
        dataCliente[2]=sc.next(); //2.Correo electronico

        return dataCliente;
    }

    private Direccion nuevaDireccion(){
        int CodigoPostal = 0;
        String Provincia,Poblacion;

        System.out.println("Direccion del cliente:");
        while(String.valueOf(CodigoPostal).length() != 5) {
            System.out.println("Introduzca Codigo postal valido:");
            CodigoPostal = sc.nextInt();
        }
        System.out.println("Introduzca provincia:");
        Provincia = sc.next();
        System.out.println("Introduzca población:");
        Poblacion = sc.next();

        return new Direccion(CodigoPostal,Provincia,Poblacion);
    }

    private void listadoClientes(){
        LocalDate fechas[]=new LocalDate[2];
        System.out.println("Introduzca fecha de inicio: YYYY-MM-dd");
        fechas[0] = LocalDate.parse(sc.next());
        System.out.println("Introduzca fecha de fin: YYYY-MM-dd");
        fechas[1] = LocalDate.parse(sc.next());

        System.out.println("Mostrar clientes entre: ");
        System.out.println("Fecha: " + fechas[0]);
        System.out.println("Fecha: " + fechas[1]);
        System.out.println("-----------------------");
        gestorClientes.mostrarListado(fechas[0],fechas[1]);
    }

    private String solicitarNif() throws NifException {
        String NIF = sc.next();
            if(NIF.length() != 9) throw new NifException();

        return NIF;
    }

}
