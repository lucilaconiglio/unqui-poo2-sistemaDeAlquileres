package categoriasManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import categoria.Categoria;
import publicacion.Publicacion;
import rankeable.Rankeable;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

public class CategoriasManager {

	// SINGLETON
	private final static CategoriasManager INSTANCIA = new CategoriasManager();
	private final HashMap<Class<? extends Rankeable>, List<Categoria>> CATEGORIAS;
	// <Class<? extends Rankeable -> clase que sea hija o implemete rankeable

	public CategoriasManager() {
		this.CATEGORIAS = new HashMap<>();
	}

	public static CategoriasManager getInstancia() {
		return INSTANCIA;
	}
	
	public List<Categoria> obtenerCategoriasDeInmueble() {
		return CATEGORIAS.getOrDefault(Publicacion.class, new ArrayList<>());
	}

	public List<Categoria> obtenerCategoriasDePropietario() {
		return CATEGORIAS.getOrDefault(Propietario.class, new ArrayList<>());
	}

	public List<Categoria> obtenerCategoriasDeInquilino() {
		return CATEGORIAS.getOrDefault(Inquilino.class, new ArrayList<>());
	}
	
	public void agregarCategoria(Categoria categoria, Class<? extends Rankeable> rankeable) {
		List<Categoria> _categorias = CATEGORIAS.computeIfAbsent(rankeable, k -> new ArrayList<>());
		_categorias.add(categoria);
		
	}

}
