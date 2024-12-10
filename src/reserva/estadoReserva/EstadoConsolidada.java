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
		reserva.setEstadoReserva(new EstadoCancelada(reserva.calcularResarcimiento()));
	}

	@Override
	public Boolean estaActiva() {
		return true;
	}

	@Override
	public void realizarCheckOut(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoFinalizada());
		
	}

	@Override
	public Boolean finalizadaExitosamente() {
		return false;
	}

	@Override
	public Boolean estaOcupada() {
		return true;
	}

}
