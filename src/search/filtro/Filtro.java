package search.filtro;

import java.util.List;

import publicacion.Publicacion;
import search.FiltroBase;

public interface Filtro {
	
	List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones, FiltroBase filtroBase);
}
