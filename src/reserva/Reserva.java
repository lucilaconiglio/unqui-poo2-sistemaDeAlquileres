package reserva;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
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

	public Reserva(LocalDate fechaInicio, LocalDate fechaFin, Inquilino inquilino) {
		this.estadoReserva = new EstadoPendienteDeAprobacion();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.inquilino = inquilino;
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
	
	public boolean conflictoCon(Reserva otraReserva) {
        return (fechaInicio.isBefore(otraReserva.fechaFin) && fechaFin.isAfter(otraReserva.fechaInicio));
    }

}
