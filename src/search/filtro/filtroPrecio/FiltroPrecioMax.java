package search.filtro.filtroPrecio;

import java.time.LocalDate;
import java.util.List;

import publicacion.Publicacion;

public class FiltroPrecioMax extends FiltroPrecio {

	public FiltroPrecioMax(Double precio, LocalDate fechaEntrada, LocalDate fechaSalida) {
		super(fechaSalida, fechaEntrada, precio);
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		return publicaciones.stream()
				.filter(p -> p.precioEntreFechas(this.getFechaEntrada(), this.getFechaSalida()) <= this.getPrecio())
				.toList();
	}

}
