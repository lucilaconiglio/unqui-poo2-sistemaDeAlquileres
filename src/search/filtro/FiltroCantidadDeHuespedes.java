package search.filtro;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;

@Getter
@Setter
public class FiltroCantidadDeHuespedes extends Filtro {

	private int cantidad; 
	
	public FiltroCantidadDeHuespedes(LocalDate fechaEntrada, LocalDate fechaSalida, int cantidad) {
		super(fechaEntrada, fechaSalida);
		this.cantidad = cantidad;
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		return publicaciones.stream()
							.filter(p-> p.getCapacidad() <= cantidad)
							.toList();
	}

	

}
