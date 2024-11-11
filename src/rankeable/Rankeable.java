package rankeable;

import java.util.List;

import categoria.Categoria;
import publicacion.Publicacion;
import resenia.Resenia;
import user.User;

public interface Rankeable {
	public void agregarResenia(Resenia res);
	public double obtenerPromedioGeneral();
	public double obtenerPromedioCategoria(Categoria cat);
	public List<String> obternerComentarios();
	public List<String> obetenerComentariosPorCategoria(Categoria cat);
}
