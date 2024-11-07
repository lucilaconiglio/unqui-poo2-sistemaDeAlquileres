package search.filtro;

import java.util.List;

import publicacion.Publicacion;

public class FiltroCantidadDeHuespedes implements Filtro {

	private int cantidad;
	
	public FiltroCantidadDeHuespedes(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		return publicaciones.stream()
				            .filter(p->p.getInmueble().getCapacidad() >= cantidad)
				            .toList();
	}

}
