package resenia;

import categoria.Categoria;

public class Resenia {
	
	private Categoria categoria;
	private int puntaje;
	private String comentario;
	
	public Resenia(Categoria categoria, int puntaje, String comentario) {
		if (puntaje < 1 || puntaje > 5) {
			throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5.");
		}
		this.categoria = categoria;
		this.puntaje = puntaje;
		this.comentario = comentario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		if (puntaje < 1 || puntaje > 5) {
			throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5.");
		}
		this.puntaje = puntaje;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}