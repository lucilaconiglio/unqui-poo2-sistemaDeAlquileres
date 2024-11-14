package reserva.estadoReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Reserva;

class EstadoFinalizadaTest {

    private EstadoFinalizada estadoFinalizada;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estadoFinalizada = new EstadoFinalizada(); // SUT
        reservaMock = mock(Reserva.class); // Mock de Reserva
    }

    @Test
    void testAceptarNoHaceNada() {
        estadoFinalizada.aceptar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testRechazarNoHaceNada() {
        estadoFinalizada.rechazar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testCancelarNoHaceNada() {
        estadoFinalizada.cancelar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testRealizarCheckOutNoHaceNada() {
        estadoFinalizada.realizarCheckOut(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testEstaActivaRetornaFalse() {
        assertFalse(estadoFinalizada.estaActiva());
    }

    @Test
    void testFinalizadaExitosamenteRetornaTrue() {
        assertTrue(estadoFinalizada.finalizadaExitosamente());
    }

    @Test
    void testEstaOcupadaRetornaFalse() {
        assertFalse(estadoFinalizada.estaOcupada());
    }
}
