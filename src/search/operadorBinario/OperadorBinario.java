package search.operadorBinario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import publicacion.Publicacion;
import search.Search;

@Getter
@Setter
public abstract class OperadorBinario implements Search{

	private Search clausulaDerecha;
	private Search clausulaIzquierda;
	
	
	public OperadorBinario(Search clausulaDerecha, Search clausulaIzquierda) {
		this.clausulaDerecha = clausulaDerecha;
		this.clausulaIzquierda = clausulaIzquierda;
	}


	@Override
	public abstract List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones);
}
