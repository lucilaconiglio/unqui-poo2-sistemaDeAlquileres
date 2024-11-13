package reserva.estadoReserva;

import reserva.Reserva;

public interface EstadoReserva {
	
	void aceptar(Reserva reserva);

	void rechazar(Reserva reserva);

	void cancelar(Reserva reserva);
}
