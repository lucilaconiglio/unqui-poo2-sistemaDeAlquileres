package search.filtro.filtroPrecio;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import search.filtro.Filtro;

@Getter
@Setter
public abstract class FiltroPrecio implements Filtro {
	
	private Double precio;

	public FiltroPrecio(Double precio) {
		this.precio = precio;
	}
}
