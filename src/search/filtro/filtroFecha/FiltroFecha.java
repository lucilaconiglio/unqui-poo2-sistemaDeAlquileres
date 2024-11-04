package search.filtro.filtroFecha;

import java.time.LocalDate;

import search.filtro.Filtro;

public abstract class FiltroFecha implements Filtro {

	private LocalDate fecha;
	
	public FiltroFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


}
