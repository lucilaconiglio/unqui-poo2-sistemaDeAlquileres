package user;

public abstract class User {
	private String nombreCompleto;
	private String mail;
	private int numeroDeTelefono;
	
	public User(String nombreCompleto, String mail, int numeroDeTelefono) {
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNumeroDeTelefono() {
		return numeroDeTelefono;
	}

	public void setNumeroDeTelefono(int numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}
	
	public void rankearUsuario(User user /* Resenia resenia */) {
		// user.addResena(resenia)
	}
	
	// faltan los metodos de la reseña / rankeable
	
}
