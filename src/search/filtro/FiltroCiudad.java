package search.filtro;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;

@Getter
@Setter
public class FiltroCiudad implements Filtro {

	private String ciudad;
	
	public FiltroCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		return publicaciones.stream()
							.filter(p-> p.getInmueble().getUbicacion().getCiudad().toUpperCase().equals(ciudad.toUpperCase()))
							.toList();
	}

}
