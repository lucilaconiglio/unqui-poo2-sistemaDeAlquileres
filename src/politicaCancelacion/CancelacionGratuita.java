package politicaCancelacion;

import java.time.LocalDate;

import publicacion.Publicacion;
import reserva.Reserva;

public class CancelacionGratuita implements PoliticaDeCancelacion{
	
	public boolean esFechaHoyDiezDiasAntes(LocalDate fecha) {
	    LocalDate fechaHoy = LocalDate.now();
	    LocalDate fechaLimite = fechaHoy.minusDays(10); // Esta es la fecha límite (10 días antes de hoy)
	   
	    return fecha.isAfter(fechaLimite) || fecha.isEqual(fechaLimite);
	}
	@Override
	public double calcularResarcimiento(Publicacion publi, Reserva reserva) {
		
		return esFechaHoyDiezDiasAntes(reserva.getFechaInicio())? 0.0 : publi.precioPorDia(LocalDate.now())*2;
	}

}
