package user.propietario;


import java.time.LocalDate;

import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.User;
import user.inquilino.Inquilino;



public interface Propietario {
	
	public void rankearInquilino(Resenia resenia, Inquilino inquilino);
	public int getAngiguedad();
	public void aceptar(Reserva reserva);
	public void rechazar(Reserva reserva);
	public int vecesQueAlquiloInmueble(Publicacion publicacion);
	public int obtenerTotalAlquileres();
	public Ranking getRanking();
	
	// TODO> PASARLE UNA ISNTANCIA DE PUBLICACION.
	public void darDeAltaPublicacion(Sitio sitio, LocalDate checkIn, LocalDate checkOut, double precioBase,
			PoliticaDeCancelacion politicaDeCancelacion, Propietario propietario, String superficie, int capacidad,
			Ubicacion ubicacion,TipoDeInmueble tipoDeInmueble);
	//obtenerHistorialAlquileres --> devuelve lista de reservas historico
}
