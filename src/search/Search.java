package search;

import java.util.List;

import publicacion.Publicacion;

public interface Search {
	
	List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones);
}
