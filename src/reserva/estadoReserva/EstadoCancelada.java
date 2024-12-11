package reserva.estadoReserva;

import lombok.Getter;
import politicaCancelacion.PoliticaDeCancelacion;
import reserva.Reserva;

@Getter
public class EstadoCancelada implements EstadoReserva {

	private double resarcimiento;
	
	public EstadoCancelada(double resarcimiento){
		this.resarcimiento = resarcimiento; 
	}
	
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
