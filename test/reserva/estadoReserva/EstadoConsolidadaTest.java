package reserva.estadoReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Reserva;

class EstadoConsolidadaTest {

    private EstadoConsolidada estadoConsolidada;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estadoConsolidada = new EstadoConsolidada(); // SUT
        reservaMock = mock(Reserva.class); // Mock de Reserva
    }

    @Test
    void testAceptarNoHaceNada() {
        estadoConsolidada.aceptar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testRechazarNoHaceNada() {
        estadoConsolidada.rechazar(reservaMock);
        verifyNoInteractions(reservaMock);
    }

    @Test
    void testCancelarCambiaEstadoACancelada() {
        estadoConsolidada.cancelar(reservaMock);
        verify(reservaMock).setEstadoReserva(any(EstadoCancelada.class));
    }

    @Test
    void testRealizarCheckOutCambiaEstadoAFinalizada() {
        estadoConsolidada.realizarCheckOut(reservaMock);
        verify(reservaMock).setEstadoReserva(any(EstadoFinalizada.class));
    }

    @Test
    void testEstaActivaRetornaTrue() {
        assertTrue(estadoConsolidada.estaActiva());
    }

    @Test
    void testFinalizadaExitosamenteRetornaFalse() {
        assertFalse(estadoConsolidada.finalizadaExitosamente());
    }

    @Test
    void testEstaOcupadaRetornaTrue() {
        assertTrue(estadoConsolidada.estaOcupada());
    }
}
