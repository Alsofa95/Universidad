
import clases.Serializacion.SerializableInput;
import clases.Serializacion.SerializableOutput;
import mvc.controlador.ControladorAgenda;
import mvc.modelo.ModeloAgenda;
import mvc.vista.Vista;

public class Main {
    public static void main(String[] args) {

        ModeloAgenda modelo  = new SerializableInput().inputData();
        ControladorAgenda controlador = new ControladorAgenda();
        Vista vista = new Vista(modelo,controlador);

        modelo.setVista(vista.getInformantes());
        controlador.setModelo(modelo);
        controlador.setVista(vista.getInterrogantes());

        vista.creaGUI();
        vista.setSerializable(new SerializableOutput(modelo));
    }
}
