package sitio;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Sitio {
	
	private List<Publicacion> publicaciones;
	private List<String> servicios; 
	private List<String> tiposDeInmueble; 
	private List<String> categoriasComunes;
	private List<String> categoriasPropietario;
	private List<String> categoriasInquilino;
	private List<String> categoriaInmueble;
	private Map<Rankeable, List<String>> categorias;
	private Search search;
	private List<User> users; 
	
	public Sitio() {
		this.publicaciones= new ArrayList<Publicacion>();
		this.servicios= new ArrayList<String>();
		this.tiposDeInmueble= new ArrayList<String>();
		this.categoriasComunes= new ArrayList<String>();
		this.categoriasPropietario= new ArrayList<String>();
		this.categoriasInquilino= new ArrayList<String>();
		this.categoriaInmueble= new ArrayList<String>();
		this.categorias = new Map<Rankeable, List<String>>();
		this.search = new Search();
		this.users = new ArrayList<User>();
	}
	
	public List<Publicacion> buscarPublicaciones(Search search) {
		
	}//ver como lo implementamos 
	
	public List<Publicacion> getPublicaciones(){
		return this.publicaciones;
	}
	
	public void addPublicacion(Publicacion publicacion) {
		this.publicaciones.add(publicacion);
		
	}
	
	public void removePublicacion(Publicacion publicacion) {
		publicaciones.remove(publicacion);
	}
	
	public List<String> getServicios(){
		return this.servicios;
	}
	
	public void addServicio(String servicio) {
		this.servicios.add(servicio);	
	}
	
	public void removeServicio(String servicio) {
		servicios.remove(servicio);
	}
	
	public List<String> getTiposDeInmueble(){
		return this.tiposDeInmueble;
	}
	
	public void addTipoDeInmueble(String tiposDeInmueble) {
		this.tiposDeInmueble.add(tiposDeInmueble);	
	}
	
	public void removeTipoDeInmeble(String _tiposDeInmueble) {
		tiposDeInmueble.remove(_tiposDeInmueble);
	}
	
	public List<String> getCategoriasComunes(){
		return this.categoriasComunes;
	}
	
	public void addCategoriaComun(String categoriaComun){
		this.categoriasComunes.add(categoriaComun);
	}
	
	public void removeCategoriaComun(String categoriaComun) {
		categoriasComunes.remove(categoriaComun);
	}
	
	public List<String> getCategoriaPropietario(){
		return this.categoriasPropietario;
	}
	
	public void addCategoriaPropietario(String cp){
		this.categoriasPropietario.add(cp);
	}
	
	public void removeCategoriaPropietario(String cp){
		categoriasPropietario.remove(cp);
	}
	
	public List<String> getCategoriasInquilino(){
		return this.categoriasInquilino;
	}
	
	public void addCategoriaInquilino(String ci){
		this.categoriasInquilino.add(ci);
	}
	
	public void removeCategoriaInquilino(String ci) {
		categoriasInquilino.remove(ci);
	}
	
	public List<String> getCategoriasInmueble(){
		return this.categoriaInmueble;
	}
	
	public void addCategoriaInmueble(String ci) {
		this.categoriaInmueble.add(ci);
	}
	
	public void removeCategoriaInmueble(String ci) {
		categoriaInmueble.remove(ci);
	}
	
	public List<Inquilino> topTenInquilinos(){
		
	}//implementar
	
	public List<Inmueble> inmueblesLibres(){
		
	}//implementar
	
	public double tasaOcupacion() {
		
	}//implementar
	
}
