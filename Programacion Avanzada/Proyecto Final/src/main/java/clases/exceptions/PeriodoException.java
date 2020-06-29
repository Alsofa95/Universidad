package clases.exceptions;

public class PeriodoException extends Exception {

    public PeriodoException(){
        super("El periodo introducido es incorrecto, la fecha de inicio no puede ser posterior a la fecha de fin.");
    }
}
