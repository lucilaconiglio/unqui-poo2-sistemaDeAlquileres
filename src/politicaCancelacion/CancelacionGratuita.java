package politicaCancelacion;

import java.time.LocalDate;

import publicacion.Publicacion;
import reserva.Reserva;

public class CancelacionGratuita implements PoliticaDeCancelacion{
	
	public boolean esFechaHoyDiezDiasAntes(LocalDate fecha) {
	    LocalDate fechaLimite = LocalDate.now().plusDays(10);          

	    return fecha.isAfter(fechaLimite); // Devuelve true si la fecha es posterior a la fechaLimite
	}
	@Override
	public double calcularResarcimiento(Publicacion publi, Reserva reserva) {
		
		return esFechaHoyDiezDiasAntes(reserva.getFechaInicio())? 0.0 : publi.precioPorDia(LocalDate.now())*2;
	}

}
