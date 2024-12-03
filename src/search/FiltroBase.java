package search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;
import search.filtro.Filtro;

@Getter
@Setter
public class FiltroBase implements Search {

	private String ciudad;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private List<Filtro> filtros;

	public FiltroBase(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.ciudad = ciudad;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.filtros = new ArrayList<>();
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {

	    // Filtrar primero por ciudad
	    List<Publicacion> result = publicaciones.stream()
	            .filter(p -> p.getUbicacion().esDeCiudad(ciudad)).toList();
	    
	    // Aplicar los filtros adicionales
	    for (Search filtro : filtros) {
	        result = filtro.filterPublicaciones(result);  // Aplicar cada filtro a las publicaciones filtradas
	    }

	    return result;
	}

	public void addFiltro(Filtro filtro) {
		filtro.setFechaEntrada(fechaEntrada);
		filtro.setFechaSalida(fechaSalida);
		filtros.add(filtro);
	}

	public void removeFiltro(Filtro filtro) {
		filtros.remove(filtro);
	}
	

}
