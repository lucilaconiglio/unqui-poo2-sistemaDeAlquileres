package reserva.estadoReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Reserva;

class EstadoCanceladaTest {

    private EstadoCancelada estadoCancelada;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estadoCancelada = new EstadoCancelada(00.00); // SUT
        reservaMock = mock(Reserva.class); // Mock de Reserva
    }

    @Test
    void testAceptarNoHaceNada() {
        estadoCancelada.aceptar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testRechazarNoHaceNada() {
        estadoCancelada.rechazar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testCancelarNoHaceNada() {
        estadoCancelada.cancelar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testRealizarCheckOutNoHaceNada() {
        estadoCancelada.realizarCheckOut(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testEstaActivaRetornaFalse() {
    	assertFalse(estadoCancelada.estaActiva());
    }

    @Test
    void testFinalizadaExitosamenteRetornaFalse() {
        assertFalse(estadoCancelada.finalizadaExitosamente());
    }

    @Test
    void testEstaOcupadaRetornaFalse() {
        assertFalse(estadoCancelada.estaOcupada());
    }
}