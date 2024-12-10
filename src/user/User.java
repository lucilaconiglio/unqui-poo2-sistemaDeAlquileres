package user;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import categoria.Categoria;
import categoriasManager.CategoriasManager;
import lombok.Getter;
import publicacion.Publicacion;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
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
	public void agregarResenia(Resenia res) {
		ranking.agregarResenia(res);
	}

	@Override
	public double obtenerPromedioGeneral() {
		return ranking.obtenerPromedioGeneral();
	}

	public List<Resenia> getResenias() {
		return ranking.getResenias();
	}
}
