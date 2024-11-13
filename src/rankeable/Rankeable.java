package rankeable;

import java.util.List;

import categoria.Categoria;
import publicacion.Publicacion;
import ranking.Ranking;
import resenia.Resenia;
import user.User;

public interface Rankeable {
	public void agregarResenia(Resenia res);
	public Ranking getRanking();
	public double obtenerPromedioGeneral();
	public double obtenerPromedioCategoria(Categoria cat);
	public List<String> obternerComentarios();
	public List<String> obtenerComentariosPorCategoria(Categoria cat);
}
