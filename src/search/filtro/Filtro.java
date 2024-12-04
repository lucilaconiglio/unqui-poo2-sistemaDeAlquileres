package search.filtro;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;
import search.FiltroBase;
import search.Search;

public interface Filtro {
	
	List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones, FiltroBase filtroBase);
}
