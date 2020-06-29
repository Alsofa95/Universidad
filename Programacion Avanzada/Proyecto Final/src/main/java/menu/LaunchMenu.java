package menu;

import clases.Serializacion.SerializableInput;
import clases.Serializacion.SerializableOutput;

public class LaunchMenu {
    Menu menu;

    public LaunchMenu() {
        System.out.println("* Cargando datos");

        this.menu.menu();

        System.out.println("* Guardando datos");

    }
}
