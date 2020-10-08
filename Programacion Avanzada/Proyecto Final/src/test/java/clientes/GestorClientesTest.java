package clientes;

import clientes.tipo.Empresa;
import es.uji.www.GeneradorDatosINE;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GestorClientesTest {

    private GestorClientes gestorClientes;
    static GeneradorDatosINE gen = new GeneradorDatosINE();

    @BeforeEach
    public void init(){
        this.gestorClientes = new GestorClientes();
    }

    @AfterEach
    public void finish(){
        gestorClientes = null;
    }

    static Stream<Arguments> datosClientes() {
        return Stream.of(
                Arguments.of((new Cliente(gen.getNombre(),gen.getNIF(),new Direccion(12345,gen.getProvincia(),gen.getPoblacion(gen.getProvincia())),"correo@dominio.com")),true),
                Arguments.of((new Cliente(gen.getNombre(),gen.getNIF(),new Direccion(12345,gen.getProvincia(),gen.getPoblacion(gen.getProvincia())),"correo@dominio.com")),true),
                Arguments.of((new Cliente(gen.getNombre(),gen.getNIF(),new Direccion(12345,gen.getProvincia(),gen.getPoblacion(gen.getProvincia())),"correo@dominio.com")),true)
        );
    }

    @ParameterizedTest
    @MethodSource("datosClientes")
    void anyadirCliente(Cliente cliente, boolean resultado) {
        assertEquals(gestorClientes.anyadirCliente(cliente), resultado,"Correcto: Cliente añadido");
    }

    @ParameterizedTest
    @MethodSource("datosClientes")
    void borrarCliente(Cliente cliente,Boolean resultado) {
        this.anyadirCliente(cliente,resultado);
        assertEquals(gestorClientes.borrarCliente(cliente.getNIF()),resultado,"Cliente borrado");
    }

    @ParameterizedTest
    @MethodSource("datosClientes")
    void cambiarTarifa(Cliente cliente, Boolean resultado) {
        this.anyadirCliente(cliente,resultado);
        assertEquals(gestorClientes.cambiarTarifa(cliente.getNIF(),0.10).getTarifa(),cliente.getTarifa().getTarifa(),"Tarifa modificada");
    }
}