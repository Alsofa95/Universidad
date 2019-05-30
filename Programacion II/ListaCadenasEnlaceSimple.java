package ejercicio2;
import java.util.Arrays;

public class ListaCadenasEnlaceSimple implements ListaCadenas{
    
    private static class Nodo{
        String cadena;
        Nodo siguiente;

        public Nodo(String cadena,Nodo siguiente){
            this.cadena=cadena;
            this.siguiente=siguiente;
        }
     
    }
    /*.............................................*/
    private int talla;          //Numero de elementos
    private Nodo primerNodo;    //Nodo cabecera
    
    public ListaCadenasEnlaceSimple(){
            this.talla=0;
            this.primerNodo=null;
    }
    
    public boolean add(String s) {
        Nodo newNodo = new Nodo(s,null);
        
        if (isEmpty()) {           
            this.primerNodo=newNodo;
            ++talla;
        }
        else{                      
            Nodo aux=this.primerNodo;
            while(aux.siguiente != null){         //Recorro los nodos
                aux = aux.siguiente;
            }
            aux.siguiente=newNodo;              //Añado el nuevo Nodo
            ++talla;                            //Incremento contador
        }
        return true;
    }
    
    public void add(int i,String s){
        if (i<0 || i > size()){
            throw new IndexOutOfBoundsException();
        }else{
            if(i == 0)
                primerNodo = new Nodo(s, primerNodo);       
            else{
                Nodo aux = primerNodo;                //Recorro los nodos
                for(int j=0 ; j<i-1 ; j++){              
                    aux = aux.siguiente;
                }
                aux.siguiente = new Nodo(s, aux.siguiente);
            }
            ++talla;
        }
    }
    
    public void clear() {
        primerNodo=null;
        talla=0;
    }
    
    public String get(int i) {
            if (i<0 || i >= size()){
                throw new IndexOutOfBoundsException();
            }
            else{
                Nodo aux=primerNodo;
                for(int j=0 ; j<i ; j++){
                    aux=aux.siguiente;
                }
                return aux.cadena;
            }
    }
    
    public int indexOf(String s){
        Nodo aux=primerNodo;
        int contador = 0;
        
        while(aux.siguiente != null){
            if(aux.cadena.equals(s)){
                 return contador;
            }
            aux=aux.siguiente;
            contador++;
        }
        return -1;
    }
    
    public int lastIndexOf(String s){
        Nodo aux=primerNodo;
        int contador = 0;
        int lastIndex = -1;     //Ultimo elemento encotrado
        
        while(aux.siguiente != null){
            if(aux.cadena == s)
                lastIndex=contador;
            
            aux=aux.siguiente;
            contador++;
        }
        return lastIndex;
    }
    
    public boolean isEmpty() { 
        if (this.primerNodo == null)
            return true;
        else
            return false;
    }
    
    public String remove(int i){
        if(i<0 || i>=size()){
                throw new IndexOutOfBoundsException();
        }else{
            String borrada;
            Nodo aux = primerNodo;
            
            if(i == 0){                                          // Primer nodo
                borrada=aux.cadena;
                primerNodo=primerNodo.siguiente;
            }else{
                for(int j=0 ; j<i-1 ; j++){                     //Recorro nodos una posicion menos
                    aux=aux.siguiente;
                }
                borrada = aux.siguiente.cadena;                 //Guardo la cadena de nodo a borrar
                    
                if(i == size()-1){                              // Ultimo nodo
                    aux.siguiente = null;
                }else{
                    aux.siguiente = aux.siguiente.siguiente;     //Desplazo 1 posicion los nodos
                }
            }
            
            --talla;
            return borrada;
        }
    }
    
    public boolean remove(String s){
        int index = indexOf(s);
        
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }
    
    public String set(int i, String s){
        if(i<0 || i>=size())
                throw new IndexOutOfBoundsException();
        else{
            Nodo aux=primerNodo;
            String cadenaCambiada;    
            for(int j=0 ; j<i ; j++){
                aux=aux.siguiente;
            }
            cadenaCambiada = aux.cadena;
            aux.cadena = s;
            
            return cadenaCambiada;
        }
    }
    
    public int size() {
        return this.talla;
    }
    
    public String toString(){
        String[] cadena;
        Nodo aux=primerNodo;
        
        if(!isEmpty()){
            cadena=new String[size()];
            
            for(int j=0 ; j<cadena.length ; j++ ){
                cadena[j]=aux.cadena;
                aux=aux.siguiente;
            }
        }
        else{
            cadena=new String[0];
        }
         return Arrays.toString(cadena);
    }
    
    
}
