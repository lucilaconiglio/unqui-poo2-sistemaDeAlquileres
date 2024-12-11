package politicaCancelacion;

import java.time.LocalDate;

import publicacion.Publicacion;
import reserva.Reserva;

public class CancelacionGratuita implements PoliticaDeCancelacion{

	@Override
	public double calcularResarcimiento(Reserva reserva) {
	    // Si la fecha está dentro de los 10 días previos, no hay resarcimiento
	    return esCancelacionGratuita(reserva.getFechaInicio()) 
	        ? 0.0 
	        : calcularCostoPorDosDias(reserva.getDiasDeEstadia(), reserva.getValor());
	}

	public double calcularCostoPorDosDias(int diasTotales, double valorTotal) {
	    // Calcular el valor de dos días de estadía
	    double valorPorDia = valorTotal / diasTotales;
	    return valorPorDia * 2;
	}

	
	public boolean esCancelacionGratuita(LocalDate fecha) {
	    LocalDate fechaLimite = LocalDate.now().plusDays(10);          
	    return fecha.isAfter(fechaLimite); // Devuelve true si la fecha es posterior a la fechaLimite
	}

}
