package ejercicio11;

/**
 *
 * @author Daniel Moreno & Marc Navarro
 */
public class Ejercicio11 {

     public static void main(String[] args) {
        prueba("hola", "ola", true);
        prueba("hola", "osa", false);
        prueba("hola", "hola", true);
        prueba("hola", "", true);
    }

    public static boolean esSufijo(String s1,String s2){
        if(s1.length()>=s2.length()){
            int j = s1.length()-1;

            for(int i=s2.length()-1;i>=0;i--){

                if(s1.charAt(j) != s2.charAt(i)){
                    return false;
                }
                j--;
            }
        }else{
            return false;
        }
        return true;
    }

    private static void prueba(String s1, String s2, boolean esperado) {
        boolean resultado = esSufijo(s1, s2);
        System.out.format("Prueba con %s y %s: ", s1, s2);
        if (resultado == esperado)
            System.out.println("ok");
        else
            System.out.println("error");
    }
    
}
