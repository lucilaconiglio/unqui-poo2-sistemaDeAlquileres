package user.inquilino;

import java.time.LocalDate;
import java.util.List;

import formaDePago.FormaDePago;
import publicacion.Publicacion;
import rankeable.Rankeable;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
import user.propietario.Propietario;

public interface Inquilino extends Rankeable {

	public void rankearInmueble(Resenia resenia, Publicacion publicacion);
    public void reservar(Publicacion publicacion, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaDePago);
    public void cancelar(Publicacion publicacion, Reserva reserva);
	public void rankearPropietario(Resenia resenia, Propietario propietario);
	public List<Reserva> obtenerReservas(Sitio sitio);
	public List<Reserva> obtenerReservasFuturas(Sitio sitio);
	public List<Reserva> obtenerReservasDeInquilinoEnCiudad(String ciudad, Sitio sitio);
	public List<String> obtenerCiudadesDondeInquilinoTieneReserva(Sitio sitio);
}
