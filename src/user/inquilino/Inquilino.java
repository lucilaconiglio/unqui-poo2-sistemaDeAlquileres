package user.inquilino;


import publicacion.Publicacion;
import rankeable.Rankeable;
import resenia.Resenia;
import reserva.Reserva;
import user.propietario.Propietario;

public interface Inquilino extends Rankeable {

	public void rankearInmueble(Resenia resenia, Publicacion publicacion, Reserva reserva);
	public void rankearPropietario(Resenia resenia, Propietario propietario, Reserva reserva);	//public List<Reserva> obtenerReservasDeInquilinoEnCiudad(String ciudad, Sitio sitio);
}
