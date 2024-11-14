package reserva.estadoReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Reserva;


class EstadoPendienteDeAprobacionTest {

    private EstadoPendienteDeAprobacion estadoPendienteDeAprobacion;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        estadoPendienteDeAprobacion = new EstadoPendienteDeAprobacion(); // SUT
        reservaMock = mock(Reserva.class); // Mock de Reserva
    }

    @Test
    void testAceptarCambiaEstadoAConsolidada() {
        estadoPendienteDeAprobacion.aceptar(reservaMock);
        verify(reservaMock).setEstadoReserva(isA(EstadoConsolidada.class));
    }

    @Test
    void testRechazarCambiaEstadoACancelada() {
        estadoPendienteDeAprobacion.rechazar(reservaMock);
        verify(reservaMock).setEstadoReserva(isA(EstadoCancelada.class));
    }

    @Test
    void testCancelarCambiaEstadoACancelada() {
        estadoPendienteDeAprobacion.cancelar(reservaMock);
        verify(reservaMock).setEstadoReserva(isA(EstadoCancelada.class));
    }

    @Test
    void testEstaActivaRetornaTrue() {
        assertTrue(estadoPendienteDeAprobacion.estaActiva(), "Una reserva en estado pendiente de aprobación debería estar activa");
    }

    @Test
    void testFinalizadaExitosamenteRetornaTrue() {
        assertTrue(estadoPendienteDeAprobacion.finalizadaExitosamente(), "Una reserva en estado pendiente de aprobación debería considerarse finalizada exitosamente");
    }

    @Test
    void testEstaOcupadaRetornaFalse() {
        assertFalse(estadoPendienteDeAprobacion.estaOcupada(), "Una reserva en estado pendiente de aprobación no debería estar ocupada");
    }
}
