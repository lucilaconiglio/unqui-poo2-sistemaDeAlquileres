package rankeable;

import resenia.Resenia;

public interface Rankeable {
	public void agregarResenia(Resenia res);
	public double obtenerPromedioGeneral();
	public double obtenerPromedioCategoria(String cat);
}
