package Ejercicio24;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio24 {
/**
 *
 * @author Daniel Moreno & Marc Navarro
 */

    public static void main(String[] args) throws FileNotFoundException {
        String[] equipos ={"CDEF_Logroño","Levante_UD_SAD","Valencia_Féminas_CF"} ;
        prueba(equipos, "equipos.txt", new String[][] {{"-","2","X"},{"-","-","X"},{"1","-","-"}});

    }
    public static String[][] crearMatrizResultados(String[] equipos, String Fichero) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(Fichero));    
        String resultados[][]=new String[equipos.length][equipos.length];
        
        for(int i=0; i<equipos.length;i++){ 
            for(int j=0; j<equipos.length;j++){
                resultados[i][j]="-";
            }
        }
        
        if(sc.hasNextLine()){
            while(sc.hasNextLine()) {
                String[] lectura=(sc.nextLine()).split(" ");
                resultados[identificaEquipo(equipos, lectura[0])]
                        [identificaEquipo(equipos, lectura[2])]=identificaResultado(lectura);
            }
            sc.close();
        }
        return resultados;
    }
    public static int identificaEquipo(String[] equipos,String lectura){
        for(int i=0;i<equipos.length;i++){
            if(equipos[i].equals(lectura)){
                return i;
            }
        }
        return -1;
    }
    public static String identificaResultado(String[] lectura){
        int resLocal = Integer.parseInt(lectura[1]);
        int resVisitante = Integer.parseInt(lectura[3]);
        if(resLocal>resVisitante){
            return "1";
        }else{
            if(resLocal<resVisitante){
                return "2";
            }else{
                if(resLocal == resVisitante){
                    return "X";
                }  
            }
        }
        return "-";
    }
    
    private static void prueba(String[] cadenaEquipos, String nombreFichero, String[][] esperado) throws FileNotFoundException {
        String[][] resultados=crearMatrizResultados(cadenaEquipos,nombreFichero);
        System.out.println("Prueba con : "+ Arrays.toString(cadenaEquipos)+ " y fichero "+ nombreFichero);
        
        if (Arrays.deepEquals(resultados, esperado))
            System.out.println("ok");
        else
            System.out.println("error");
    }
}