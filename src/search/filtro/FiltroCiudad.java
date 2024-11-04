package search.filtro;

import java.util.List;

import publicacion.Publicacion;

public class FiltroCiudad implements Filtro {

	private String ciudad;
	
	
	public FiltroCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		// TODO Auto-generated method stub
		return null;
	}

}
