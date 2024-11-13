package reserva.estadoReserva;

import reserva.Reserva;

public class EstadoCancelada implements EstadoReserva {

	@Override
	public void aceptar(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rechazar(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizarCheckOut(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Boolean estaActiva() {
		return false;
	}

	@Override
	public Boolean finalizadaExitosamente() {
		return false;
	}

	@Override
	public Boolean estaOcupada() {
		return false;
	}

	
	

}
