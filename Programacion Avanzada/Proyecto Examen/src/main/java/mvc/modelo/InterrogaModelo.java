package mvc.modelo;

import clases.Tarea;

import java.time.LocalDateTime;
import java.util.List;

public interface InterrogaModelo {

    //Devuelve listado de todas las tareas
    public List<Tarea> muestraTareas();

    //Listado de tareas filtradas
    public void filtraTareas(int prioridad, int terminada);

    //  Devuelve informacion de la tarea,
    //  debe existir ya que la fecha la obtengo directamente
    //  de la tabla de tareas.
    public Tarea obtenerTarea(LocalDateTime fechaTarea);
}
