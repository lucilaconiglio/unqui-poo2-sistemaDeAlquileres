package user.inquilino;

import java.time.LocalDate;
import java.util.List;

import publicacion.Publicacion;
import resenia.Resenia;
import reserva.Reserva;
import user.propietario.Propietario;

public interface Inquilino {

	public void rankearInmueble(Resenia resenia, Publicacion publicacion);
	public List<Reserva> getReservasFuturas();
	public List<Reserva> getReservasDeCiudad(String ciudad);
	public List<String> getCiudadesDondeHayReserva();
	public void reservar(Publicacion publicacion, LocalDate fechaEntrada, LocalDate fechaSalida);
	public void cancelar(Reserva reserva);

}
