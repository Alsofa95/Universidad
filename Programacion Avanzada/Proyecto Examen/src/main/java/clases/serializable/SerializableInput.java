package clases.serializable;

import mvc.modelo.ModeloTarea;

import java.io.*;

public class SerializableInput implements Serializable {

    public ModeloTarea inputData(){

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ModeloTarea modelo = null;

        try {
            fis = new FileInputStream("tareas.bin");
            ois = new ObjectInputStream(fis);
            modelo = (ModeloTarea) ois.readObject();
            ois.close();
            System.out.println("Serializable IN");
        } catch (FileNotFoundException e) {
            System.out.println("Iniciando nuevo archivo");
        } catch (IOException e) {
            System.out.println("Error IO");
        } catch (ClassNotFoundException e) {
            System.out.println("Error ClassNotFound");
        }

        return modelo != null ? modelo : new ModeloTarea();
    }
}
