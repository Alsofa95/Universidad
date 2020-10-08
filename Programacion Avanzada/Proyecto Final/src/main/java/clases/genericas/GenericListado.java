package clases.genericas;

import java.time.LocalDate;
import java.util.*;

public class GenericListado<T> {


    public Collection<T> mostrar_listado(Collection<? extends GenericObject> c, LocalDate fecha_ini, LocalDate fecha_fin) {

        Collection<T> result = new HashSet<>();

        c.forEach( generic -> {
            LocalDate fecha = generic.get_Fecha();
            if(fecha.isEqual(fecha_ini) || fecha.isEqual(fecha_fin) || (fecha.isAfter(fecha_ini) && fecha.isBefore(fecha_fin)))
                result.add((T) generic);
        });

        return result;
    }

}
