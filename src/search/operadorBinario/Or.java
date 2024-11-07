package search.operadorBinario;

import java.util.List;
import java.util.stream.Stream;

import publicacion.Publicacion;
import search.Search;

public class Or extends OperadorBinario {

	public Or(Search clausulaDerecha, Search clausulaIzquierda) {
		super(clausulaDerecha, clausulaIzquierda);
	}

	/**
	 * Filtra una lista de publicaciones basándose en las condiciones especificadas
	 * por la cláusula OR.
	 *
	 * Este método combina los resultados de filtrar las publicaciones según las
	 * cláusulas izquierda y derecha de la condición OR. La lista resultante contiene
	 * únicamente publicaciones distintas.
	 *
	 * @param publicaciones Una lista de publicaciones que se va a filtrar.
	 * @return Una lista filtrada de publicaciones que cumplen con las condiciones de la
	 *         cláusula OR.
	 */


	@Override
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		// Se filtran las publicaciones basándose en la cláusula izquierda de la condición OR.
		List<Publicacion> clausulaIzquierda = this.getClausulaIzquierda().filterPublicaciones(publicaciones);

		// Se filtran  las publicaciones basándose en la cláusula derecha de la condición OR.
		List<Publicacion> clausulaDerecha = this.getClausulaDerecha().filterPublicaciones(publicaciones);

		// Se combinan los resultados de las cláusulas izquierda y derecha, asegurando
		// que las publicaciones sean distintas.
		return Stream.concat(clausulaIzquierda.stream(), clausulaDerecha.stream()).distinct().toList();
	}

}
