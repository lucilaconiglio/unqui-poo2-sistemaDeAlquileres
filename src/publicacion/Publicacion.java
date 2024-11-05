package publicacion;

import java.time.LocalDateTime;
import java.util.ArrayList;

import formaDePago.FormaDePago;


public class Publicacion {
	
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private double precioBase;
	private FormaDePago formaDePago;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private User propietario;
	private Inmueble inmueble;
	private List<Periodo> periodos;
	private List<Observer> suscriptores;
	private List<Reserva> reservas;
	
	public Publicacion(LocalDateTime checkIn, LocalDateTime checkOut, double precioBase,FormaDePago formaDePago,
			PoliticaDeCancelacion politicaDeCancelacion, User propietario, Inmueble inmueble) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.precioBase = precioBase;
		this.formaDePago = formaDePago;
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.propietario = propietario;
		this.inmueble = inmueble;
		this.periodos = new ArrayList<Periodo>();
		this.suscriptores = new Arraylist<Observer>();
		this.reservas = new ArrayList<Reserva>();
		
	}
	
	public LocalDateTime getCheckIn(){
		return this.checkIn;
	}
	
	public LocalDateTime getCheckOut(){
		return this.checkOut;
	}	
	
	public double precio() {
		return this.precioBase; ///esto no es el metodo final. Precio era una cuenta 
	}
	
	public void addPediodo(Periodo periodo){
		this.periodos.add(periodo);
	}
	
	public void removePeriodo(Periodo periodo){
		this.periodos.add(periodo);
	}
	
	public void addObserver(Observer ob){
		this.suscriptores.add(ob);
	}
	
	public void removeObserver (Observer ob){
		suscriptores.remove(ob);
	}
	
	public void cancelarReserva(){
		//implementar
	}
	
	public void reservaInmueble(){
		//implementar 
	}
	
	public void cambiarPrecioBase(double nuevoPrecio){
		this.precioBase=nuevoPrecio;
	}
	

}
