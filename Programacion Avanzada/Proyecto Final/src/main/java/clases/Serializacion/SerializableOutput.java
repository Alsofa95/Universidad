package clases.Serializacion;

import mvc.modelo.ModeloAgenda;

import java.io.*;

public class SerializableOutput implements Serializable {

    private ModeloAgenda modelo;

    public SerializableOutput(ModeloAgenda modelo){
        this.modelo = modelo;
    }

    public boolean outputData(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            fos = new FileOutputStream("agenda.bin");
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
