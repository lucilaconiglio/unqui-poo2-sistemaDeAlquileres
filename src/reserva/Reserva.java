package reserva;

import java.time.LocalDate;

import reserva.estadoReserva.EstadoPendienteDeAprobacion;
import reserva.estadoReserva.EstadoReserva;

public class Reserva {

	private EstadoReserva estadoReserva;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	//private Inquilino inquilino;
	
	public Reserva(LocalDate fechaInicio, LocalDate fechaFin/*, Inquilino inquilino*/) {
		this.estadoReserva = new EstadoPendienteDeAprobacion();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		//this.inquilino = inquilino;
	}
	
	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}
	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	} 
	
	
}
