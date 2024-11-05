package periodo;

import java.time.LocalDateTime;

public class Periodo {
	
	private LocalDateTime inicio;
	private LocalDateTime fin; 
	private double precio;
	
	public Periodo(LocalDateTime inicio, LocalDateTime fin, double precio) {
		this.inicio = inicio;
		this.fin = fin;
		this.precio = precio;
		
	}
	
	public LocalDateTime getIncio() {
		return this.inicio;
	}
	
	public LocalDateTime getFinal() {
		return this.fin;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	

}
