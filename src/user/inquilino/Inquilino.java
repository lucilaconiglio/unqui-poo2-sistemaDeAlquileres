package user.inquilino;

import java.time.LocalDate;
import java.util.List;

import formaDePago.FormaDePago;
import publicacion.Publicacion;
import rankeable.Rankeable;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import user.propietario.Propietario;

public interface Inquilino extends Rankeable {

	public void rankearInmueble(Resenia resenia, Publicacion publicacion);
	public List<Reserva> getReservasFuturas();
	public List<Reserva> getReservasDeCiudad(String ciudad);
	public List<String> getCiudadesDondeHayReserva();
    public void reservar(Publicacion publicacion, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaDePago);
    public void cancelar(Publicacion publicacion, Reserva reserva);
	public void rankearPropietario(Resenia resenia, Propietario propietario);

}
