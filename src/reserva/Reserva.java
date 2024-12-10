package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.Setter;
import politicaCancelacion.PoliticaDeCancelacion;
import reserva.estadoReserva.EstadoPendienteDeAprobacion;
import reserva.estadoReserva.EstadoReserva;
import user.inquilino.Inquilino;

@Getter
public class Reserva {

	@Setter
	private EstadoReserva estadoReserva;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Inquilino inquilino;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Double valor;

	public Reserva(LocalDate fechaInicio, LocalDate fechaFin, Inquilino inquilino, PoliticaDeCancelacion politicaDeCancelacion, Double valor) {
		this.estadoReserva = new EstadoPendienteDeAprobacion();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.inquilino = inquilino;
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.valor = valor;
	}

	public void aceptar() {
		estadoReserva.aceptar(this);
	}

	public void rechazar() {
		estadoReserva.rechazar(this);
	}

	public void cancelar() {
		estadoReserva.cancelar(this);
	}
	
	public void realizarCheckOut() {
		estadoReserva.realizarCheckOut(this);
	}
	
	public Boolean estaActiva() {
		return estadoReserva.estaActiva();
	}
	
	public Boolean estaOcupada() {
		return estadoReserva.estaOcupada();
	}
	
	public boolean conflictoCon(Reserva otraReserva) {
        return estaActiva() && (fechaInicio.isBefore(otraReserva.fechaFin) && fechaFin.isAfter(otraReserva.fechaInicio));
    }

	public Boolean finalizadaExitosamente() {
		return estadoReserva.finalizadaExitosamente();
	}
	
	public Boolean esInquilino(Inquilino  inquilino) {
		return this.inquilino.equals(inquilino);
	}
	
	public Boolean esDespuesDe(LocalDate fecha) {
		return fecha.isAfter(LocalDate.now());
	}
	
	public int getDiasDeEstadia() {
	    // Calcular la cantidad de días entre las fechas
	    long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
	    return (int) dias;
	}
	
	public Double calcularResarcimiento() {
		return politicaDeCancelacion.calcularResarcimiento(this);
	}

}
