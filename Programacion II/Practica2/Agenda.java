package ejercicio210;

import java.util.Arrays;

/**
 *
 * @author Daniel Moreno
 */
public class Agenda {

    private Tarea[] tareas;
    private int cantidad = 0;
    
    public Agenda() {
        tareas=new Tarea[8];
    }
    
    public void añadir(Tarea tarea){
        cantidad++;
        
        if(cantidad < tareas.length) {
            Tarea tareaTemp;
            
            for (int i = 0; i < cantidad - 1; i++) {
                if (tareas[i].getFecha().compareTo(tarea.getFecha()) > 0) {
                    tareaTemp = tareas[i];  //Guardo la tarea de la posicion i
                    tareas[i] = tarea;      //Meto la tarea nueva
                    tarea = tareaTemp;      //Meto la tarea guardada en la variable tarea para meterse en el bucle de nuevo
                }
            }
            tareas[cantidad - 1] = tarea;   //La ultima tarea se guarda en la ultima posicion del array
            
        }else {
            Tarea[] nuevaLista = new Tarea[this.tareas.length * 2];
            boolean centinela = true;
            
            for (int i = 0, j = 0; i < cantidad; i++) {
                if (centinela && (i == cantidad - 1 || tareas[j].getFecha().compareTo(tarea.getFecha()) > 0)) { //Si esta en la utlima posicion o la fecha de la tarea es mayor
                        nuevaLista[i] = tarea;
                        centinela = false;
                } else {
                        nuevaLista[i] = tareas[j++];
                }
            }
            tareas = nuevaLista;
        }
    }
    
    public int cantidad(){
        return tareas.length;
    }
    
    public Tarea[] consultar(Fecha fechaConsulta){
        Tarea[] busqueda;
        int contador=0;                 /*Numero de elementos que coinciden con la fecha */
        
        for(int i=0;i<cantidad-1;i++){
            if(this.tareas[i].getFecha().compareTo(fechaConsulta)== 0)
                contador++;
        }
        
        busqueda=new Tarea[contador];   /*Array con el numero de elementos de la busqueda */
        
        for(int i=0,j=0;i<cantidad-1;i++){
            if(this.tareas[i].getFecha().compareTo(fechaConsulta)== 0)
                busqueda[j++]=tareas[i];
        }
        return busqueda;
    } 
    
    public void borrarPasadas(Fecha fecha){
        int primerElement = 0,ultimoElement = 0;
        boolean centinela = true;
        
        for (int i = 0; i < this.cantidad; i++) {
            if (tareas[i].getFecha().compareTo(fecha) >= 0) {
                    centinela=false;
                    ultimoElement=i;
            }else{
                if(centinela)
                    primerElement=i;
                tareas[i] = null;
            }  
        }
        this.cantidad = ultimoElement-primerElement;
        
        for (int i = 0; i < this.cantidad; i++) {
            tareas[i] = tareas[primerElement+i+1];
            tareas[primerElement+i+1]=null;
        }
        System.out.println(Arrays.deepToString(tareas));
    }
    
    public void borrar(){
        this.borrarPasadas(new Fecha(0,0,0).hoy());
    }
    
    public String toString(){
        String cadenaVuelta = "";
		for (int i = 0; i < this.tareas.length; i++) {
                    if(tareas[i] != null)
			cadenaVuelta = cadenaVuelta + this.tareas[i].toString() + "\n";
		}
        return cadenaVuelta;
    }
}
