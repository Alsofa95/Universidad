package clases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GestorTareaTest {
    private GestorTarea gestor;

    @BeforeEach
    public void init(){
        this.gestor = new GestorTarea();
    }

    @AfterEach
    public void finish(){
        gestor = null;
    }

    static Stream<Arguments> datosTareas() {
        return Stream.of(
                Arguments.of((new Tarea("Tarea 1","Descripcion 1",true,2)),true),
                Arguments.of((new Tarea("Tarea 2","Descripcion 2",true,1)),true),
                Arguments.of((new Tarea("Tarea 3","Descripcion 3",false,3)),true)
        );
    }

    @ParameterizedTest
    @MethodSource("datosTareas")
    void anyadirTareas(Tarea tarea) {
    }

    @Test
    void borrarTarea() {
    }

    @Test
    void modificarTarea() {
    }
}