import clases.serializable.SerializableInput;
import clases.serializable.SerializableOut;
import mvc.controlador.ControladorTarea;
import mvc.modelo.ModeloTarea;
import mvc.vista.Vista;

public class Main {
    public static void main(String[] args) {

        ModeloTarea modelo  = new SerializableInput().inputData();
        ControladorTarea controlador = new ControladorTarea();
        Vista vista = new Vista();

        modelo.setVista(vista);
        controlador.setModelo(modelo);
        controlador.setVista(vista);

        vista.setModelo(modelo);
        vista.setControlador(controlador);

        vista.setSerOut(new SerializableOut(modelo));
        vista.creaGUI();
    }
}