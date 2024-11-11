package user.propietario;

import java.time.LocalDate;
import java.util.List;

import formaDePago.FormaDePago;
import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import resenia.Resenia;
import reserva.Reserva;
import ubicacion.Ubicacion;
import user.User;


public interface Propietario {
	
	public void rankearInquilino(User usuario, Publicacion publicacion, Resenia resenia);
	public int getAngiguedad();
	public void aceptar(Reserva reserva);
	public void crearPublicacion(LocalDate checkIn, LocalDate checkOut, double precioBase, FormaDePago formaDePago,
			PoliticaDeCancelacion politicaDeCancelacion, Propietario propietario , String superficie,int capacidad,Ubicacion ubicacion);
}
