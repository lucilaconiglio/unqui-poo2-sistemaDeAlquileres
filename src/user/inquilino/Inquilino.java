package user.inquilino;

import java.util.List;

import resenia.Resenia;
import reserva.Reserva;

public interface Inquilino {

	public void rankearInmueble(Resenia resenia);
	public List<Reserva> getReservasFuturas();
	public List<Reserva> getReservasDeCiudad(String ciudad);
	public List<String> getCiudadesDondeHayReserva();
	public void reservar(Reserva reserva);
	public void cancelar(Reserva reserva);

}
