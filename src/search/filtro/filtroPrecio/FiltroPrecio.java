package search.filtro.filtroPrecio;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import search.filtro.Filtro;

@Getter
@Setter
public abstract class FiltroPrecio implements Filtro {
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Double precio;

	public FiltroPrecio(Double precio, LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.precio = precio;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}
}
