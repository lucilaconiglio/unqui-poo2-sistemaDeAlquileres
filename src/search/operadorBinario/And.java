package search.operadorBinario;

import java.util.ArrayList;
import java.util.List;

import publicacion.Publicacion;
import search.Search;

public class And extends OperadorBinario {

	public And(Search clausulaDerecha, Search clausulaIzquierda) {
		super(clausulaDerecha, clausulaIzquierda);
	}

	/**
	 * Filtra una lista de publicaciones basándose en las condiciones especificadas
	 * por la cláusula AND.
	 *
	 * Este método compara las listas de publicaciones filtradas por las cláusulas
	 * izquierda y derecha de la condición AND. Retorna la intersección de estas
	 * listas, es decir, las publicaciones que cumplen con ambas condiciones.
	 *
	 * @param publicaciones Una lista de publicaciones que se va a filtrar.
	 * @return Una lista filtrada de publicaciones que cumplen con las condiciones
	 *         de la cláusula AND.
	 */
	@Override
	
	public List<Publicacion> filterPublicaciones(List<Publicacion> publicaciones) {
		
		List<Publicacion> publicacionesFiltradas = new ArrayList<>();
		
		// Se filtran las publicaciones basándose en la cláusula izquierda de la condición AND.
		List<Publicacion> primeraFiltrada = this.getClausulaIzquierda().filterPublicaciones(publicaciones);
		publicacionesFiltradas.addAll(primeraFiltrada);
		
		// Se filtran las publicaciones basándose en la cláusula derecha de la condición AND.
		List<Publicacion> segundaFiltrada = this.getClausulaDerecha().filterPublicaciones(publicaciones);

		// Se obtiene la intersección de las listas filtradas.
		List<Publicacion> interseccion = new ArrayList<>(publicacionesFiltradas);
		interseccion.retainAll(segundaFiltrada);
		/*
		 * El método retainAll nos permite eliminar todos los elementos de la lista que no le indiquemos en la colección que pasamos como parámetro al método.
		*/

		return interseccion;
	}

}
