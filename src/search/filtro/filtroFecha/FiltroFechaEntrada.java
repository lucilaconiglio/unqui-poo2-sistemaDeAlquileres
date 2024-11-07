package search.filtro.filtroFecha;

import java.time.LocalDate;
import java.util.List;

import publicacion.Publicacion;

public class FiltroFechaEntrada extends FiltroFecha{

	public FiltroFechaEntrada(LocalDate fecha) {
		super(fecha);
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		return null;
	}

}
