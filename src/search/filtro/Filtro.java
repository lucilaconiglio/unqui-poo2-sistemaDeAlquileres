package search.filtro;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;
import search.Search;

@Getter
@Setter
public abstract class Filtro implements Search {
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	public Filtro(LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}


	@Override
	public abstract List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones);
}
