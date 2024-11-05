package user.propietario;

import java.time.LocalDate;
import java.time.Period;

import user.User;

public class Propietario extends User{

	private LocalDate fechaRegistro;
	
	public Propietario(String nombreCompleto, String mail, int numeroDeTelefono, LocalDate fechaRegistro) {
		super(nombreCompleto, mail, numeroDeTelefono);
		this.fechaRegistro = fechaRegistro;
	}

	public int getAntiguedad() {
		return Period.between(fechaRegistro, LocalDate.now()).getYears();
	}
	
	//  faltan metodos del ranking
}
