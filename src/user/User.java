package user;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import categoria.Categoria;
import publicacion.Publicacion;
import rankeable.Rankeable;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

public  class User implements Rankeable,Propietario,Inquilino {
	private String nombreCompleto;
	private String mail;
	private LocalDate fechaRegistro;
	private int numeroDeTelefono;
	private List<Resenia> resenias;
	private List<Reserva> reservas;
	private Sitio sitio;

	public User(String nombreCompleto, String mail, int numeroDeTelefono, Sitio sitio) {
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.numeroDeTelefono = numeroDeTelefono;
		this.sitio = sitio;
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

	public List<Resenia> getResenias(){
		return resenias;
	};
	
	public List<Resenia> getResenia(Resenia reseniaBuscada){
		return (List<Resenia>) resenias.stream().filter(resenia-> resenia.equals(reseniaBuscada));//Rearmar esto para que devuelve una única resenia
	}

	@Override
	public void rankearInmueble(Resenia resenia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reserva> getReservasFuturas() {
		   LocalDate hoy = LocalDate.now();
	        
	        // Filtrar reservas que tienen fecha de inicio mayor a hoy
		     //return sitio.getReservas(this).stream().filter(reserva -> reserva.getFechaInicio().isAfter(hoy)).toList(); TODO: VER DE PONER QUE EL USER CONOZCA AL SITIO.
	        return reservas.stream().filter(reserva -> reserva.getFechaInicio().isAfter(hoy)).toList();
	}
	
	@Override
	public List<Reserva> getReservasDeCiudad(String ciudad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCiudadesDondeHayReserva() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reservar(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rankearInquilino(User usuario, Publicacion publicacion, Resenia resenia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAngiguedad() {
		return Period.between(fechaRegistro, LocalDate.now()).getYears();
	}

	@Override
	public void aceptar(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarResenia(Resenia res) {
		// TODO Auto-generated method stub
		this.resenias.add(res);
	}

	@Override
	public double obtenerPromedioGeneral() {
		// TODO Auto-generated method stub
		return resenias.stream().mapToDouble(res->res.getPuntaje()).sum();
	}

	public List<Resenia> getReseniasPorCategoria(String categoria){
		return 	resenias.stream().filter(res-> res.getCategoria().equals(categoria)).toList();
	}


	@Override
	public double obtenerPromedioCategoria(Categoria cat) {
		// TODO Auto-generated method stub
		return 0;
	};

}
