package reserva.estadoReserva;

import reserva.Reserva;

public class EstadoConsolidada implements EstadoReserva{

	@Override
	public void aceptar(Reserva reserva) {
		// no hace nada
	}

	@Override
	public void rechazar(Reserva reserva) {
		// no hace nada
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoCancelada());
	}

}
