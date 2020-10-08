package clases.exceptions;

public class NifException extends Exception {

    public NifException(){
        super("El tamanyo del NIF no puede ser superior, ni ser inferior a 9 caracteres.");
    }
}
