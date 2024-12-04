package search.filtro.filtroPrecio;

import java.time.LocalDate;
import java.util.List;

import publicacion.Publicacion;
import search.FiltroBase;

public class FiltroPrecioMax extends FiltroPrecio {

	public FiltroPrecioMax(Double precio) {
		super(precio);
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones, FiltroBase filtroBase) {
		return publicaciones.stream()
				.filter(p -> p.precioEntreFechas(filtroBase.getFechaEntrada(), filtroBase.getFechaSalida()) <= this.getPrecio())
				.toList();
	}

}
