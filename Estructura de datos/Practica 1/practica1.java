package practica1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class practica1 {

    /**
     *  Método que toma dos conjuntos de enteros y separa los elementos entre aquellos que sólo aparecen una vez
     *  y aquellos que aparecen repetidos. El método modifica los conjuntos que toma como parámetros.
     * @param unicos    A la entrada un conjunto de enteros. A la sálida los elementos que solo aparecen en uno de
     *                  los conjuntos.
     * @param repetidos A la entrada un conjunto de enteros. A la salida los elementos que aparecen en ambos conjuntos.
     */

    static public void separa(Set<String> unicos, Set<String> repetidos) {
        Set<String> aux=new HashSet<String>();
        aux.addAll(repetidos); 				// (0,8,2,9)
        repetidos.retainAll(unicos); 		// (2)
        unicos.addAll(aux); 				// (6,3,2,0,8,2,9)
        unicos.removeAll(repetidos);		// (6,3,0,8,9)
    }

    /**
     *  Toma un iterador a una colección de enteros positivos y devuelve como resultado un conjunto con aquellos elementos
     *  de la colección que no son múltiplos de algún otro de la colección. Los ceros son descartados
     * @param iter  Iterador a una colección de enteros
     * @return Conjunto de de enteros.
     */

    static public Set <Integer> filtra(Iterator<Integer> iter) {
        Set<Integer> aux=new HashSet<Integer>();            //Coleccion auxiliar
        int a, b;                                     //Guardo valores de cada iterator
        boolean multiplo = false;
        while(iter.hasNext()){                        //Iterator principal
            a = iter.next();

            if(aux.size()<=0 || (a == 0)){            //Primer elemento
                if(a!=0)
                    aux.add(a);
            }else{
                Iterator<Integer> iterAux = aux.iterator();

                while(iterAux.hasNext() && !aux.contains(a)){   //Compruebo si hay elementos en la coleccion Aux
                    b = iterAux.next();                         //y si el elemento a ya se encuentra en la coleccion

                    if(a%b == 0)                                //Compruebo que no tenga ya algun multiplo dentro
                        multiplo = true;                        //de la coleccion
                    if(b%a == 0) {                              //Compruebo si mi nuevo elemento es multiplo de
                        aux.remove(b);                          //alguno que hay ya dentro, y si es asi lo borra.
                        iterAux = aux.iterator();
                    }
                }
                if(!multiplo || a==1)                           //Si no es multiplo de ninguno de la nueva coleccion
                    aux.add(a);                                 //lo añado
                multiplo = false;
            }
        }
        return aux;
    }

    /**
     * Toma una colección de conjuntos de <i>String</i> y devuelve como resultado un conjunto con aquellos <i>String </i>
     * Que aparecen en al menos dos conjuntos de la colección.
     * @param col Coleccion de conjuntos de <i>String</i>
     * @return Conjunto de <i>String</i> repetidos.
     */

    static public Set<String> repetidos(Collection<Set<String>> col) {
        Set<String> aux=new HashSet<String>();
        Set<String> resultado=new HashSet<String>();
        Iterator<Set<String>> iter = col.iterator();
        String numAux;
        int cont = 0;

        if(col.size()>1){                                   //Añado en AUX todas las posibilidades
            while(iter.hasNext()){
                aux.addAll(iter.next());
            }
            Iterator<String> iterAux = aux.iterator();

            while(iterAux.hasNext()){                       //Compruebo que aparezca en mas de un grupo
                iter = col.iterator();
                numAux=iterAux.next();
                while(iter.hasNext()){
                    if(iter.next().contains(numAux))
                        cont++;
                }
                if(cont>1){                                 //Si se ha encontrado mas de una coincidencia
                    resultado.add(numAux);                  //añado el nuevo elemento a la nueva coleccion.
                }
                cont = 0;
            }
        }

        return resultado;
    }
}