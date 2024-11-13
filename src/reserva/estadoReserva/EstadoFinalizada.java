package reserva.estadoReserva;

import reserva.Reserva;

public class EstadoFinalizada implements EstadoReserva {

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
		// no hace nada		
	}

	@Override
	public void realizarCheckOut(Reserva reserva) {
		// no hace nada		
	}

	@Override
	public Boolean estaActiva() {
		return false;
	}

	@Override
	public Boolean finalizadaExitosamente() {
		return true;
	}

	@Override
	public Boolean estaOcupada() {
		return false;
	}

}
