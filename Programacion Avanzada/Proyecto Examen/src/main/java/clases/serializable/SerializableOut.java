package clases.serializable;

import mvc.modelo.ModeloTarea;

import java.io.*;

public class SerializableOut implements Serializable {

    private ModeloTarea modelo;

    public SerializableOut(ModeloTarea modelo){
        this.modelo = modelo;
    }

    public boolean outputData(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            fos = new FileOutputStream("tareas.bin");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(modelo);
            oos.close();
            System.out.println("Serializacion OUT");
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
