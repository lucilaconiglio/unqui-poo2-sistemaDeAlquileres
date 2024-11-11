package rankeable;

import categoria.Categoria;
import publicacion.Publicacion;
import resenia.Resenia;
import user.User;

public interface Rankeable {
	public void agregarResenia(Resenia res);
	public double obtenerPromedioGeneral();
	public double obtenerPromedioCategoria(Categoria cat);
}
