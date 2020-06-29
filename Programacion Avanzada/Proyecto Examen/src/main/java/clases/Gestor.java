package clases;

import java.time.LocalDateTime;
import java.util.List;

public interface Gestor {

    //Anyade una tarea a la lista
    public void anyadirTareas(Tarea tarea);

    //Borra una tarea
    public void borrarTarea(LocalDateTime fechaTarea);

    //Modifica una tarea
    public void modificarTarea(LocalDateTime fecha, int prioridad, boolean terminada);

    //Muestra listado de todas las tareas
    public List<Tarea> mostrarTareas();

    //Muestra listado de tareas filtradas
    public List<Tarea> filtrarTareas(int prioridad, int terminada);

    public Tarea obtenerTarea(LocalDateTime fechaTarea);

}
