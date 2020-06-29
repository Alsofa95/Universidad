package clases.genericClass;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GenericObject implements Serializable {

    private LocalDateTime Fecha;

    public GenericObject() {
        Fecha = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }
}
