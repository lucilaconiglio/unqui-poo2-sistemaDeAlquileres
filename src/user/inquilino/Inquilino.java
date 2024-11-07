package user.inquilino;

import user.User;

import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import resenia.Resenia;
import reserva.Reserva;

public class Inquilino extends User{
	private List<Resenia> resenias;


	public Inquilino(String nombreCompleto, String mail, int numeroDeTelefono) {
		super(nombreCompleto, mail, numeroDeTelefono);
		this.resenias=new ArrayList<Resenia>();

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

	public List<Resenia> getReseniasPorCategoria(String categoria){
		return 	resenias.stream().filter(res-> res.getCategoria().equals(categoria)).toList();
	}

	@Override
	public void agregarResenia(Resenia res) {
		this.resenias.add(res);
	}

	@Override
	public double obtenerPromedioGeneral() {
		return resenias.stream().mapToDouble(res->res.getPuntaje()).sum();
	}
	
	@Override
	public double obtenerPromedioCategoria(String cat) {
		return getReseniasPorCategoria(cat).stream().mapToDouble(res->res.getPuntaje()).sum();
	}
}
