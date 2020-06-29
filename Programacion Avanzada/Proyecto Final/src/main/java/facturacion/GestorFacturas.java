package facturacion;

import clases.exceptions.PeriodoException;
import clases.genericas.GenericListado;
import clientes.Cliente;
import facturacion.tarifas.FabricaTarifa;
import facturacion.tarifas.Tarifa;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class GestorFacturas implements Serializable {

    //k --> NIF , V --> Factura
    private Map<String, List<Factura>> listaFacturas;
    transient Scanner sc;

    public GestorFacturas() {
        this.listaFacturas = new HashMap<String, List<Factura>>();
    }

    private void initScanner(){
        this.sc = new Scanner(System.in);
    }

    /* --------------- EMITIR FACTURA --------------------------*/
    public void emitirFactura(Cliente cliente, List<Llamada> llamadas) throws PeriodoException {
        this.initScanner();

        LocalDate fechas[]=new LocalDate[2];
        System.out.println("Introduzca fecha de inicio: YYYY-MM-dd");
        fechas[0] = LocalDate.parse(sc.next());
        System.out.println("Introduzca fecha de fin: YYYY-MM-dd");
        fechas[1] = LocalDate.parse(sc.next());

        if(fechas[0].isAfter(fechas[1]) || fechas[1].isBefore(fechas[0])) throw new PeriodoException();

        //Empleando las clases genericas, obtengo las llamadas para el periodo deseado.
        //this.emitirFactura(cliente,new GenericListado<Llamada>().mostrar_listado(llamadas,fechas[0],fechas[1]),fechas);
    }

    public void emitirFactura(Cliente cliente, Collection<Llamada> llamadas, Factura fact){
        Factura factura=fact;
        factura.setTarifa(cliente.getTarifa());

        double importe = 0;
        FabricaTarifa ft = new FabricaTarifa();         // <-- Fabrica de Tarifas
        Tarifa tarifa;

        for(Llamada llamada : llamadas){
            tarifa = cliente.getTarifa();

            if(llamada.get_Fecha().getDayOfWeek().toString() == "SUNDAY"){
                tarifa = ft.getTarifaDomingo(tarifa);
            }
            else{
                if(llamada.getHora().isAfter(LocalTime.parse("16:00")) && llamada.getHora().isBefore(LocalTime.parse("20:00")))
                    tarifa = ft.getTarifaTardes(tarifa);
            }

            importe = importe + ((llamada.getDuracion()/60.0) * tarifa.getTarifa());
        }

        if(!listaFacturas.containsKey(cliente.getNIF()))
            listaFacturas.put(cliente.getNIF(),new ArrayList<Factura>());

        factura.setImporte((double)Math.round(importe * 100d) / 100d);
        listaFacturas.get(cliente.getNIF()).add( factura );
    }

    /* ----------- MOSTRAR FACTURA -----------------------------*/
    public List<Factura> mostrarFacturas(String NIF){
        if (listaFacturas.containsKey(NIF)){
            listaFacturas.get(NIF).forEach(factura -> {
                System.out.println(factura.toString());
            });
        }else
            System.out.println("El cliente no tiene ninguna factura");

        return listaFacturas.get(NIF);
    }

    public Factura mostrarFacturasCodigo(int codigo){
        for (List<Factura> lista : listaFacturas.values()){
            for (Factura fact : lista) {
                if(fact.getCodigo().equals(codigo)){
                    return fact;
                }
            }
        }
        return null;
    }

    public Collection<Factura> mostrarListado(String NIF, LocalDate inicio, LocalDate fin){
        return new GenericListado<Factura>().mostrar_listado(listaFacturas.get(NIF),inicio,fin);
    }
}
