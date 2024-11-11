/*package user.inquilino;

import user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;

public class Inquilino_LEGACY extends User{
	private List<Resenia> resenias;
	private List<Reserva> reservas;


	public Inquilino_LEGACY(String nombreCompleto, String mail, int numeroDeTelefono) {
		super(nombreCompleto, mail, numeroDeTelefono, new Sitio());
		this.resenias=new ArrayList<Resenia>();
		this.reservas=new ArrayList<Reserva>();
		//this.sitio = sitio;

	}

	public List<Reserva> getReservasFuturas() {
        LocalDate hoy = LocalDate.now();
        
        // Filtrar reservas que tienen fecha de inicio mayor a hoy
        return reservas.stream().filter(reserva -> reserva.getFechaInicio().isAfter(hoy)).toList();
    }

	
	public List<Reserva> getReservasDeCiudad(String ciudad){
		return null;
	}
	
	public List<String> getCiudadesDondeHayReserva(){
		return null;
	}
	
	public void rankeatInmueble(Inmueble inmueble ) {
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
*/