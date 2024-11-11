package ranking;

import java.util.ArrayList;
import java.util.List;

import categoria.Categoria;
import resenia.Resenia;

public class Ranking {
	private List<Resenia> resenias;
	
	public Ranking() {
		this.resenias = new ArrayList<>();
	}
	
	public void agregarResenia(Resenia resenia) {
		resenias.add(resenia);
	}
	
	public List<Resenia> getResenias() {
		return resenias;
	}
	
	public double obtenerPromedioGeneral() {
		return resenias.stream().mapToDouble(res->res.getPuntaje()).sum();
	}
	
	public double obtenerPromedioPorCategoria(Categoria cat) {
		List<Resenia> reseniasPorCategoria = obtenerReseniasPorCategoria(cat);
	    double sumaPuntajes = reseniasPorCategoria.stream()
	        .mapToDouble(res->res.getPuntaje()).sum();
	    
	    return reseniasPorCategoria.isEmpty() ? 0.0 : sumaPuntajes / reseniasPorCategoria.size();
	}
	
	public List<Resenia> obtenerReseniasPorCategoria(Categoria categoria){
		return 	resenias.stream().filter(res-> res.getCategoria().getConcepto().equals(categoria.getConcepto())).toList();
	}
	
	public List<String> obtenerComentarios(){
		return resenias.stream().map(resenia ->resenia.getComentario()).toList();
	}
	
	public List<String> obtenerCometariosPorCategoria(Categoria cat){
		return obtenerReseniasPorCategoria(cat).stream().map(resenia -> resenia.getComentario()).toList();
	}
	

}
