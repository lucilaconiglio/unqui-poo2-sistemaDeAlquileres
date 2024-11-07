package search.filtro.filtroFecha;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import search.filtro.Filtro;

@Getter
@Setter
public abstract class FiltroFecha implements Filtro {

	private LocalDate fecha;
	
	public FiltroFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
