package estadoReserva;

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
	public void cancear(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoCancelada());
	}

}
