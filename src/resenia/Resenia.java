package resenia;

public class Resenia {
	
	private String categoria;
	private int puntaje;
	private String comentario;
	
	public Resenia(String categoria, int puntaje, String comentario) {
		super();
		this.categoria = categoria;
		this.puntaje = puntaje;
		this.comentario = comentario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	 
}
