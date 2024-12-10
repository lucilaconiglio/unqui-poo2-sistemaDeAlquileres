package reserva.estadoReserva;

import reserva.Reserva;

public class EstadoPendienteDeAprobacion implements EstadoReserva{

	@Override
	public void aceptar(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoConsolidada());
	}

	@Override
	public void rechazar(Reserva reserva) {
		reserva.setEstadoReserva(new EstadoCancelada(reserva.calcularResarcimiento()));
		
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
		// TODO Auto-generated method stub
		
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
