package facturacion;

import es.uji.www.GeneradorDatosINE;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    private GestorLlamadas gestorLlamadas;
    static GeneradorDatosINE gen = new GeneradorDatosINE();

    @BeforeEach
    public void init(){
        this.gestorLlamadas = new GestorLlamadas();
    }

    @AfterEach
    public void finish(){
        gestorLlamadas = null;
    }

    static Stream<Arguments> datosLlamadas() {
        return Stream.of(
                Arguments.of(gen.getNIF(),new Random().nextLong(),new Random().nextLong(),true),
                Arguments.of(gen.getNIF(),new Random().nextLong(),new Random().nextLong(),true),
                Arguments.of(gen.getNIF(),new Random().nextLong(),new Random().nextLong(),true),
                Arguments.of(gen.getNIF(),new Random().nextLong(),new Random().nextLong(),true),
                Arguments.of(gen.getNIF(),new Random().nextLong(),new Random().nextLong(),true)
        );
    }

    @ParameterizedTest
    @MethodSource("datosLlamadas")
    void altaLlamada(String NIF, long numero, long duracion, Boolean resultado) {
        assertEquals(gestorLlamadas.altaLlamada(NIF,numero,duracion),resultado,"Correcto: Llamada añadida");
    }
}