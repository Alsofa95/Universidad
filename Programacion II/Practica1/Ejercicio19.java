
package ejercicio19;

import java.util.Arrays;

/**
 *
 * @author Daniel Moreno & Marc Navarro
 */
public class Ejercicio19 {

    public static void main(String[] args) {
        prueba(new int[] {1,2,3,4,5,6,7,8,9,0}, 2,new int[]{1,3,4,5,6,7,8,9,0});
        prueba(new int[] {2,22,3,33,4,44,5,55,6}, 4,new int[]{2,22,3,33,44,5,55,6});
        prueba(new int[] {0,0,1,1,2,2}, 1,new int[]{0,0,2,2});
        
    }
    public static int[] eliminarValor(int[] cadenaEnteros,int valor){
        int contador = cadenaEnteros.length;
        
        for(int i=0;i<cadenaEnteros.length;i++){
            if(cadenaEnteros[i] == valor)
                contador--;
        }
        
        int[] cadenaNueva=new int[contador];
        contador=0;
        for(int i=0;i<cadenaEnteros.length;i++){
            if(cadenaEnteros[i] != valor)
                cadenaNueva[contador++] = cadenaEnteros[i];
        }
        return cadenaNueva;
    }
    
    private static void prueba(int[] cadenaEnteros, int valor, int[] esperado) {
        int[] resultado = eliminarValor(cadenaEnteros, valor);
        System.out.println("Prueba con : "+ Arrays.toString(cadenaEnteros)+ " y valor a eliminar "+ valor);
        
        if (Arrays.equals(resultado, esperado))
        System.out.println("ok");
        else
        System.out.println("error");
    }

}
