package search.filtro.filtroPrecio;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import search.filtro.Filtro;

@Getter
@Setter
public abstract class FiltroPrecio extends Filtro {
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Double precio;

	public FiltroPrecio(LocalDate fechaEntrada, LocalDate fechaSalida, Double precio) {
		super(fechaEntrada, fechaSalida);
		this.precio = precio;
	}
}
