package user;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import categoria.Categoria;
import categoriasManager.CategoriasManager;
import formaDePago.FormaDePago;
import lombok.Getter;
import lombok.Setter;
import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import rankeable.Rankeable;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

@Getter
public class User implements Propietario, Inquilino {
	private String nombreCompleto;
	private String mail;
	private LocalDate fechaRegistro;
	private int numeroDeTelefono;
	private List<Reserva> reservas;
	private Sitio sitio;
	private Ranking ranking;

	public User(String nombreCompleto, String mail, int numeroDeTelefono, Sitio sitio) {
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.numeroDeTelefono = numeroDeTelefono;
		this.sitio = sitio;
		this.ranking = new Ranking();
		this.fechaRegistro = LocalDate.now();
	}

	@Override
	public int getAntiguedad() {
		return Period.between(fechaRegistro, LocalDate.now()).getYears();
	}

	@Override
	public void darDeAltaPublicacion(Publicacion publicacion) {
		sitio.addPublicacion(publicacion);

	}
	// RANKING

	@Override
	public double obtenerPromedioCategoria(Categoria cat) {
		return ranking.obtenerPromedioPorCategoria(cat);
	}

	@Override
	public List<String> obternerComentarios() {
		return ranking.obtenerComentarios();
	}

	@Override
	public List<String> obtenerComentariosPorCategoria(Categoria cat) {
		return ranking.obtenerCometariosPorCategoria(cat);
	}

	@Override
	public Ranking getRanking() {
		return ranking;
	}
	
	@Override
	public void rankearInmueble(Resenia resenia, Publicacion publicacion, Reserva reserva) {
		if (reserva.finalizadaExitosamente() && CategoriasManager.getInstancia().obtenerCategoriasDeInmueble().equals(resenia.getCategoria())) {
			publicacion.agregarResenia(resenia);
		}
	}


	@Override
	public void rankearPropietario(Resenia resenia, Propietario propietario, Reserva reserva) {
		
		if (reserva.finalizadaExitosamente() && CategoriasManager.getInstancia().obtenerCategoriasDePropietario().equals(resenia.getCategoria())) {
			propietario.getRanking().agregarResenia(resenia);
		}
	}

	@Override
	public void rankearInquilino(Resenia resenia, Inquilino inquilino, Reserva reserva) {
		if (reserva.finalizadaExitosamente() && CategoriasManager.getInstancia().obtenerCategoriasDeInquilino().equals(resenia.getCategoria())) {
			inquilino.getRanking().agregarResenia(resenia);
		}
	}

	@Override
	public int vecesQueAlquiloInmueble(Publicacion publicacion,Sitio sitio) {
		// TODO: agregar contador de veces alquilado en la publi
		return sitio.cantidadDeVecesQueAlquiloInmueble(publicacion);
	}

	@Override
	public int obtenerTotalAlquileres(Sitio sitio) {
		// TODO Auto-generated method stub
		return sitio.cantidadDeVecesQueAlquiloInmuebles(this);
	}

	@Override
	public void agregarResenia(Resenia res) {
		ranking.agregarResenia(res);

	}

	@Override
	public double obtenerPromedioGeneral() {
		return ranking.obtenerPromedioGeneral();
	};

	public List<Resenia> getResenias() {
		return ranking.getResenias();
	}
	// RESERVA


	 @Override
	    public void reservar(Publicacion publicacion, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaDePago) {
	        Reserva reserva = new Reserva(fechaEntrada, fechaSalida, this, formaDePago);
	        publicacion.recibirReserva(reserva);
	    }

    @Override
    public void cancelar(Publicacion publicacion, Reserva reserva) {
        publicacion.cancelarReserva(reserva);

    }

    @Override
    public void aceptar(Publicacion publicacion, Reserva reserva) {
        publicacion.aceptarReserva(reserva);
    }

    @Override
    public void rechazar(Publicacion publicacion, Reserva reserva) {
        publicacion.rechazarReserva(reserva);
    }


	@Override
	public List<Reserva>  obtenerReservas(Sitio sitio) {
			
		return sitio.obtenerTodasLasReservasDe(this);
	}


	@Override
	public List<Reserva> obtenerReservasDeInquilinoEnCiudad(String ciudad, Sitio sitio) {
		// TODO Auto-generated method stub
		return sitio.obtenerReservasDeInquilinoEnCiudad(ciudad, this);
	}

	@Override
	public List<String> obtenerCiudadesDondeInquilinoTieneReserva(Sitio sitio) {
		return sitio.obtenerCiudadesDondeInquilinoTieneReserva(this);
	}


	@Override
	public List<Reserva> obtenerReservasFuturas(Sitio sitio) {
		return sitio.obtenerTodasLasReservasFuturas(this);
	}

	@Override
	public List<Publicacion> historialDeInmueblesAlquilados(Sitio sitio) {
		// TODO Auto-generated method stub
		return sitio.inmueblesAlquilados(this);
	}


}
