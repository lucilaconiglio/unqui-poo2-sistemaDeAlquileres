package sitio;

import java.util.List;
import java.util.ArrayList;


import categoria.Categoria;
import publicacion.Publicacion;
import search.Search;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import user.User;
import user.inquilino.Inquilino;

public class Sitio {

	private List<Publicacion> publicaciones;
	private List<Servicio> servicios;
	private List<TipoDeInmueble> tiposDeInmueble;
	private List<Categoria> categoriasPropietario;
	private List<Categoria> categoriasInquilino;
	private List<Categoria> categoriaInmueble;
	private Search search;
	private List<User> users;

	public Sitio() {
		this.publicaciones = new ArrayList<Publicacion>();
		this.servicios = new ArrayList<Servicio>();
		this.tiposDeInmueble = new ArrayList<TipoDeInmueble>();
		this.categoriasPropietario = new ArrayList<Categoria>();
		this.categoriasInquilino = new ArrayList<Categoria>();
		this.categoriaInmueble = new ArrayList<Categoria>();
		this.users = new ArrayList<User>();
	}

	/**
	 * Busca y filtra las publicaciones disponibles según los criterios
	 * proporcionados en el objeto de búsqueda.
	 *
	 * @param search Objeto de búsqueda que contiene los criterios de búsqueda para
	 *               las publicaciones.
	 * @return Lista de publicaciones filtradas según los criterios de búsqueda.
	 */
	
	public List<Publicacion> buscarPublicaciones(Search search) {
		// Se filtran las publicaciones según los criterios de búsqueda.
		return search.filterPublicaciones(publicaciones);
	}

	public List<Publicacion> getPublicaciones() {
		return this.publicaciones;
	}

	public void addPublicacion(Publicacion publicacion) {
		this.publicaciones.add(publicacion);

	}

	public void removePublicacion(Publicacion publicacion) {
		publicaciones.remove(publicacion);
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
	}

	public void removeServicio(Servicio servicio) {
		servicios.remove(servicio);
	}

	public List<TipoDeInmueble> getTiposDeInmueble() {
		return this.tiposDeInmueble;
	}

	public void addTipoDeInmueble(TipoDeInmueble tiposDeInmueble) {
		this.tiposDeInmueble.add(tiposDeInmueble);
	}

	public void removeTipoDeInmeble(TipoDeInmueble _tiposDeInmueble) {
		tiposDeInmueble.remove(_tiposDeInmueble);
	}


	public List<Categoria> getCategoriaPropietario() {
		return this.categoriasPropietario;
	}

	public void addCategoriaPropietario(Categoria cp) {
		this.categoriasPropietario.add(cp);
	}

	public void removeCategoriaPropietario(Categoria cp) {
		categoriasPropietario.remove(cp);
	}

	public List<Categoria> getCategoriasInquilino() {
		return this.categoriasInquilino;
	}

	public void addCategoriaInquilino(Categoria ci) {
		this.categoriasInquilino.add(ci);
	}

	public void removeCategoriaInquilino(Categoria ci) {
		categoriasInquilino.remove(ci);
	}

	public List<Categoria> getCategoriasInmueble() {
		return this.categoriaInmueble;
	}

	public void addCategoriaInmueble(Categoria ci) {
		this.categoriaInmueble.add(ci);
	}

	public void removeCategoriaInmueble(Categoria ci) {
		categoriaInmueble.remove(ci);
	}

	public List<Inquilino> topTenInquilinos() {
		return null;
	}// implementar y revisar tipado


	public double tasaOcupacion() {
		return 0;
	}// implementar y revisar tipado

}
