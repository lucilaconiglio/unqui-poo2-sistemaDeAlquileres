package search.filtro.filtroPrecio;

import java.time.LocalDate;
import java.util.List;

import publicacion.Publicacion;
import search.filtro.filtroFecha.FiltroFecha;

public class FiltroPrecioMax extends FiltroFecha{

	public FiltroPrecioMax(LocalDate fecha) {
		super(fecha);
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		// TODO Auto-generated method stub
		return null;
	}

}
