package search.filtro;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;
import search.FiltroBase;

@Getter
@Setter
public class FiltroCantidadDeHuespedes implements Filtro {

	private int cantidad; 
	
	public FiltroCantidadDeHuespedes() {
		this.cantidad = cantidad;
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones, FiltroBase filtroBase) {
		return publicaciones.stream()
							.filter(p-> p.getCapacidad() <= cantidad)
							.toList();
	}

	

}
