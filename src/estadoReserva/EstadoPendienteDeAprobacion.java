package estadoReserva;

import reserva.Reserva;

public class EstadoPendienteDeAprobacion implements EstadoReserva{

	@Override
	public void aceptar(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoConsolidada());
	}

	@Override
	public void rechazar(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoCancelada());
		
	}

	@Override
	public void cancear(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoCancelada());
	}
	
}
