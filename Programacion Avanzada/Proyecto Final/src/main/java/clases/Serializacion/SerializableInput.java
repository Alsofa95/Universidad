package clases.Serializacion;


import mvc.modelo.ModeloAgenda;

import java.io.*;

public class SerializableInput implements Serializable {

    public ModeloAgenda inputData(){

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ModeloAgenda modelo = null;

        try {
            fis = new FileInputStream("agenda.bin");
            ois = new ObjectInputStream(fis);
            modelo = (ModeloAgenda) ois.readObject();
            ois.close();
            System.out.println("Serializacion IN");

        } catch (FileNotFoundException e) {
            System.out.println("Iniciando nuevo archivo");
            return new ModeloAgenda();

        } catch (IOException e) {
            System.out.println("Error IO");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Error ClassNotFound");
            e.printStackTrace();

        }

        return modelo != null ? modelo : new ModeloAgenda();
    }
}
