package user.inquilino;

import user.User;

import java.util.List;

import inmueble.Inmueble;
import reserva.Reserva;

public class Inquilino extends User{

	public Inquilino(String nombreCompleto, String mail, int numeroDeTelefono) {
		super(nombreCompleto, mail, numeroDeTelefono);
	}

	public List<Reserva> getReservasFuturas(){
		return null;
	}
	
	public List<Reserva> getReservasDeCiudad(String ciudad){
		return null;
	}
	
	public List<String> getCiudadesDondeHayReserva(){
		return null;
	}
	
	public void rankeatInmueble(Inmueble inmueble /*Resenia resenia*/) {
		// inmueble.agregarResenia(resenia)
	}
	
	//  faltan metodos del ranking
}
