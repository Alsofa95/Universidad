package clientes;

import java.io.Serializable;

public class Direccion implements Serializable {

    private int codigo;
    private String provincia;
    private String poblacion;

    public Direccion(int codigo, String provincia, String poblacion) {
        this.codigo = codigo;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    @Override
    public String toString() {
        return "CP - "+ codigo + " - " + provincia + " - " + poblacion;
    }
}
