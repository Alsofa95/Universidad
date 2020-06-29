package mvc.modelo;

import clases.Tarea;

import java.time.LocalDateTime;

public interface CambioModelo {

    public void anyadeTarea(Tarea tarea);
    public void modificaTarea(LocalDateTime fechaTarea,Tarea tareaModif);
    public void borraTarea(LocalDateTime fechaTarea);

}
