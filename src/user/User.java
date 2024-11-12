package user;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import categoria.Categoria;
import categoriasManager.CategoriasManager;
import lombok.Getter;
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
public class User implements Rankeable, Propietario, Inquilino {
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
	}

	@Override
	public int getAngiguedad() {
		return Period.between(fechaRegistro, LocalDate.now()).getYears();
	}

	@Override
	public void darDeAltaPublicacion(Sitio sitio, LocalDate checkIn, LocalDate checkOut, double precioBase,
			PoliticaDeCancelacion politicaDeCancelacion, Propietario propietario, String superficie, int capacidad,
			Ubicacion ubicacion, TipoDeInmueble tipoInmueble) {
		sitio.addPublicacion(new Publicacion(checkIn, checkOut, precioBase, politicaDeCancelacion, this, superficie,
				capacidad, ubicacion, tipoInmueble));

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
	public List<String> obetenerComentariosPorCategoria(Categoria cat) {
		return ranking.obtenerCometariosPorCategoria(cat);
	}

	@Override
	public void rankearInmueble(Resenia resenia, Publicacion publicacion) {
		if (CategoriasManager.getInstancia().obtenerCategoriasDeInmueble().equals(resenia.getCategoria())) {
			publicacion.agregarResenia(resenia);
		}
	}

	@Override
	public Ranking getRanking() {
		return ranking;
	}

	@Override
	public void rankearPropietario(Resenia resenia, Propietario propietario) {
		if (CategoriasManager.getInstancia().obtenerCategoriasDePropietario().equals(resenia.getCategoria())) {
			propietario.getRanking().agregarResenia(resenia);
		}
	}

	@Override
	public void rankearInquilino(Resenia resenia, Inquilino inquilino) {
		if (CategoriasManager.getInstancia().obtenerCategoriasDeInquilino().equals(resenia.getCategoria())) {
			inquilino.getRanking().agregarResenia(resenia);
		}
	}

	@Override
	public int vecesQueAlquiloInmueble(Publicacion publicacion) {
		// TODO: agregar contador de veces alquilado en la publi
		return 0;
	}

	@Override
	public int obtenerTotalAlquileres() {
		// TODO Auto-generated method stub
		return 0;
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
	public void reservar(Publicacion publicacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelar(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aceptar(Reserva reserva) {
		// TODO Auto-generated method stub
	}

	@Override
	public void rechazar(Reserva reserva) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Reserva> getReservasFuturas() {
		LocalDate hoy = LocalDate.now();

		// Filtrar reservas que tienen fecha de inicio mayor a hoy
		// return sitio.getReservas(this).stream().filter(reserva ->
		// reserva.getFechaInicio().isAfter(hoy)).toList(); TODO: VER DE PONER QUE EL
		// USER CONOZCA AL SITIO.
		return reservas.stream().filter(reserva -> reserva.getFechaInicio().isAfter(hoy)).toList();
	}

	@Override
	public List<Reserva> getReservasDeCiudad(String ciudad) {
		// TODO resolver mas tarde
		return null;
	}

	@Override
	public List<String> getCiudadesDondeHayReserva() {
		// TODO Auto-generated method stub
		return null;
	}

}
