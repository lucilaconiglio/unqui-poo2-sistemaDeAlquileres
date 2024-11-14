package user.propietario;


import java.time.LocalDate;
import java.util.List;

import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import rankeable.Rankeable;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.User;
import user.inquilino.Inquilino;



public interface Propietario extends Rankeable{
	
	public void rankearInquilino(Resenia resenia, Inquilino inquilino);
	public int getAntiguedad();
	public void aceptar(Publicacion publicacion, Reserva reserva);
    public void rechazar(Publicacion publicacion, Reserva reserva);
	public int vecesQueAlquiloInmueble(Publicacion publicacion,Sitio sitio);
	public int obtenerTotalAlquileres(Sitio sitio);
	public List<Publicacion> historialDeInmueblesAlquilados(Sitio sitio);
	// TODO> PASARLE UNA ISNTANCIA DE PUBLICACION.
	/*public void darDeAltaPublicacion(Sitio sitio, LocalDate checkIn, LocalDate checkOut, double precioBase,
			PoliticaDeCancelacion politicaDeCancelacion, Propietario propietario, String superficie, int capacidad,
			Ubicacion ubicacion,TipoDeInmueble tipoDeInmueble);
	*/
	public void darDeAltaPublicacion(Publicacion publicacion);
	//obtenerHistorialAlquileres --> devuelve lista de reservas historico
}
