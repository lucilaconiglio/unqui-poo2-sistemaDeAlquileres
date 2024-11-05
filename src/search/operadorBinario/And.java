package search.operadorBinario;

import java.util.List;

import publicacion.Publicacion;
import search.Search;

public class And extends OperadorBinario{

	public And(Search clausulaDerecha, Search clausulaIzquierda) {
		super(clausulaDerecha, clausulaIzquierda);
	}

	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		return null;
	}

}
