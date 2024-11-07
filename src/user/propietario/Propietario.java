package user.propietario;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import resenia.Resenia;
import user.User;

public class Propietario extends User{

	private LocalDate fechaRegistro;
	private List<Resenia> resenias;

	
	public Propietario(String nombreCompleto, String mail, int numeroDeTelefono, LocalDate fechaRegistro) {
		super(nombreCompleto, mail, numeroDeTelefono);
		this.fechaRegistro = fechaRegistro;
		this.resenias=new ArrayList<Resenia>();
	}

	public int getAntiguedad() {
		return Period.between(fechaRegistro, LocalDate.now()).getYears();
	}
	
	public List<Resenia> getReseniasPorCategoria(String categoria){
		return 	resenias.stream().filter(res-> res.getCategoria().equals(categoria)).toList();
	}

	@Override
	public void agregarResenia(Resenia res) {
		this.resenias.add(res);
	}

	@Override
	public double obtenerPromedioGeneral() {
		return resenias.stream().mapToDouble(res->res.getPuntaje()).sum();
	}
	
	@Override
	public double obtenerPromedioCategoria(String cat) {
		return getReseniasPorCategoria(cat).stream().mapToDouble(res->res.getPuntaje()).sum();
	}
	
}
