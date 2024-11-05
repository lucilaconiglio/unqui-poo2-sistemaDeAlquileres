package search.filtro.filtroPrecio;

import search.filtro.Filtro;

public abstract class FiltroPrecio implements Filtro {
	
	private Double precio;

	public FiltroPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	

}
