package reserva;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.estadoReserva.EstadoCancelada;
import reserva.estadoReserva.EstadoConsolidada;
import reserva.estadoReserva.EstadoPendienteDeAprobacion;
import user.inquilino.Inquilino;

public class ReservaTest {

private Inquilino inquilinoMock;
    
    @BeforeEach
    void setUp() {
        inquilinoMock = mock(Inquilino.class);
    }
    
	
    @Test
    void testEstadoInicialReserva() {
        // Instanciamos la reserva
        Reserva reserva = new Reserva(LocalDate.now(), LocalDate.now().plusDays(5), inquilinoMock);

        // Verificamos que la reserva esté en el estado de Pendiente de Aprobación
        assertTrue(reserva.getEstadoReserva() instanceof EstadoPendienteDeAprobacion);
    }

    @Test
    void testRechazarReserva() {
        // Instanciamos la reserva
        Reserva reserva = new Reserva(LocalDate.now(), LocalDate.now().plusDays(5), inquilinoMock);

        // Rechazamos la reserva
        reserva.rechazar();

        // Verificamos que el estado de la reserva haya cambiado a EstadoCancelada
        assertTrue(reserva.getEstadoReserva() instanceof EstadoCancelada);
    }
    
    @Test
    void testAceptarReserva() {
        // Instanciamos la reserva
        Reserva reserva = new Reserva(LocalDate.now(), LocalDate.now().plusDays(5), inquilinoMock);
        
        // Aceptamos la reserva
        reserva.aceptar();
        
        // Verificamos que el estado de la reserva haya cambiado a EstadoConsolidada
        assertTrue(reserva.getEstadoReserva() instanceof EstadoConsolidada);
    }
    
    @Test
    void testCancelarReserva() {
        // Instanciamos la reserva
        Reserva reserva = new Reserva(LocalDate.now(), LocalDate.now().plusDays(5), inquilinoMock);

        reserva.aceptar();
        // Cancelamos la reserva
        reserva.cancelar();

        // Verificamos que el estado de la reserva haya cambiado a EstadoCancelada
        assertTrue(reserva.getEstadoReserva() instanceof EstadoCancelada);
    }
    
}
