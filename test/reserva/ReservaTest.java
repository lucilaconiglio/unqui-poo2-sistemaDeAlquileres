package reserva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import publicacion.Publicacion;
import reserva.estadoReserva.EstadoCancelada;
import reserva.estadoReserva.EstadoConsolidada;
import reserva.estadoReserva.EstadoFinalizada;
import reserva.estadoReserva.EstadoPendienteDeAprobacion;
import reserva.estadoReserva.EstadoReserva;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

public class ReservaTest {

	private Inquilino inquilino; // mock
	private Propietario propietario; // mock
	private Publicacion publicacion; // mock
	
	private Reserva reserva; // SUT
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	@BeforeEach
	void setUp() {
		
		inquilino = mock(Inquilino.class);
		propietario = mock(Propietario.class);
		publicacion = mock(Publicacion.class);
		
		when(publicacion.getPropietario()).thenReturn(propietario);
		
		
		fechaInicio = LocalDate.of(2023, 11, 10);
		fechaFin = LocalDate.of(2023, 11, 20);
		reserva = new Reserva(fechaInicio, fechaFin, inquilino);
		
		when(publicacion.getReservas()).thenReturn(Arrays.asList(reserva));
		
		
	}

	@Test
	void testInicializacionReserva() {
		assertEquals(EstadoPendienteDeAprobacion.class, reserva.getEstadoReserva().getClass());
	}

	@Test
	void testAceptarReserva() {
		
		doAnswer(invocation -> {
            publicacion.aceptarReserva(reserva);
            return null;
        }).when(propietario).aceptar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.aceptar();
            return null;
        }).when(publicacion).aceptarReserva(reserva);
		
		propietario.aceptar(publicacion, reserva);
		assertEquals(EstadoConsolidada.class, reserva.getEstadoReserva().getClass());
	}

	@Test
	void testRechazarReserva() {
		
		doAnswer(invocation -> {
            publicacion.rechazarReserva(reserva);
            return null;
        }).when(propietario).rechazar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.rechazar();
            return null;
        }).when(publicacion).rechazarReserva(reserva);
		
		propietario.rechazar(publicacion, reserva);
		assertEquals(EstadoCancelada.class, reserva.getEstadoReserva().getClass());
		
	}

	@Test
	void testCancelarReservaQueEstabaAceptada() {
		
		// Primero acepto la Reserva.
		doAnswer(invocation -> {
            publicacion.aceptarReserva(reserva);
            return null;
        }).when(propietario).aceptar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.aceptar();
            return null;
        }).when(publicacion).aceptarReserva(reserva);
		
		propietario.aceptar(publicacion, reserva);
		
		// El Inquilino cancela la Reserva
		doAnswer(invocation -> {
            publicacion.cancelarReserva(reserva);
            return null;
        }).when(inquilino).cancelar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.cancelar();
            return null;
        }).when(publicacion).cancelarReserva(reserva);
		
		inquilino.cancelar(publicacion, reserva);
		assertEquals(EstadoCancelada.class, reserva.getEstadoReserva().getClass());
		
		
	}

	@Test
	void testRealizarCheckOut() {
		// Primero acepto la Reserva.
		doAnswer(invocation -> {
            publicacion.aceptarReserva(reserva);
            return null;
        }).when(propietario).aceptar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.aceptar();
            return null;
        }).when(publicacion).aceptarReserva(reserva);
		
		propietario.aceptar(publicacion, reserva);
		
		// Se ejecuta el check out
		doAnswer(invocation -> {
            reserva.realizarCheckOut();
            return null;
        }).when(publicacion).realizarCheckOut(reserva);
		
		reserva.realizarCheckOut();
		assertEquals(EstadoFinalizada.class, reserva.getEstadoReserva().getClass());
	}

	@Test
	void testEstaActiva() {
		// Cuando esta en estado pendiente de aprobacion todavia esta activa
		assertTrue(reserva.estaActiva());
	}

	@Test
	void testEstaOcupada() {
		// Para que una reserva este ocupada esta necesita estar aceptada.
		
		doAnswer(invocation -> {
            publicacion.aceptarReserva(reserva);
            return null;
        }).when(propietario).aceptar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.aceptar();
            return null;
        }).when(publicacion).aceptarReserva(reserva);
		
		propietario.aceptar(publicacion, reserva);
		
		// verificamos que este ocupada
		assertTrue(reserva.estaOcupada());
	}

	@Test
	void testConflictoConOtraReservaConConflicto() {
		LocalDate otraFechaInicio = LocalDate.of(2023, 11, 15);
		LocalDate otraFechaFin = LocalDate.of(2023, 11, 25);
		Reserva otraReserva = new Reserva(otraFechaInicio, otraFechaFin, inquilino);

		assertTrue(reserva.conflictoCon(otraReserva));
	}

	@Test
	void testConflictoConOtraReservaSinConflicto() {
		LocalDate otraFechaInicio = LocalDate.of(2023, 11, 21);
		LocalDate otraFechaFin = LocalDate.of(2023, 11, 30);
		Reserva otraReserva = new Reserva(otraFechaInicio, otraFechaFin, inquilino);

		// rechazamos la primer reserva (SUT) para que esta no genere conflicto, mas alla de que tengan 
		// fechas que conflictuen entre si
		doAnswer(invocation -> {
            publicacion.rechazarReserva(reserva);
            return null;
        }).when(propietario).rechazar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.rechazar();
            return null;
        }).when(publicacion).rechazarReserva(reserva);
		
		propietario.rechazar(publicacion, reserva);

		assertFalse(reserva.conflictoCon(otraReserva));
	}

	@Test
	void testFinalizadaExitosamente() {
		// Primero acepto la Reserva.
		doAnswer(invocation -> {
            publicacion.aceptarReserva(reserva);
            return null;
        }).when(propietario).aceptar(publicacion, reserva);
		
		doAnswer(invocation -> {
            reserva.aceptar();
            return null;
        }).when(publicacion).aceptarReserva(reserva);
		
		propietario.aceptar(publicacion, reserva);
		
		// Se ejecuta el check out
		doAnswer(invocation -> {
            reserva.realizarCheckOut();
            return null;
        }).when(publicacion).realizarCheckOut(reserva);
		
		reserva.realizarCheckOut();
		
		// verificamos que la reserva este finalizada exitosamente.
		assertTrue(reserva.finalizadaExitosamente());
	}

}
