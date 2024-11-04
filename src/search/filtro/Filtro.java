package search.filtro;

import java.util.List;

import publicacion.Publicacion;
import search.Search;

public interface Filtro extends Search{
	List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones);
}
