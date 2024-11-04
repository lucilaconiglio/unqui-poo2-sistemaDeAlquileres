package search.operadorBinario;

import java.util.List;

import publicacion.Publicacion;
import search.Search;

public class OperadorBinario implements Search{

	private Search clausulaDerecha;
	private Search clausulaIzquierda;
	
	
	public OperadorBinario(Search clausulaDerecha, Search clausulaIzquierda) {
		this.clausulaDerecha = clausulaDerecha;
		this.clausulaIzquierda = clausulaIzquierda;
	}


	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		// TODO Auto-generated method stub
		return null;
	}


	public Search getClausulaDerecha() {
		return clausulaDerecha;
	}


	public void setClausulaDerecha(Search clausulaDerecha) {
		this.clausulaDerecha = clausulaDerecha;
	}


	public Search getClausulaIzquierda() {
		return clausulaIzquierda;
	}


	public void setClausulaIzquierda(Search clausulaIzquierda) {
		this.clausulaIzquierda = clausulaIzquierda;
	}
	
	

}
