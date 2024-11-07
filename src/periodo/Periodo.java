package periodo;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Periodo {
	
	private String concepto;
	private LocalDate inicio;
	private LocalDate fin; 
	private double precio;
	
	
	public Periodo(String concepto, LocalDate inicio, LocalDate fin, double precio) {
		this.concepto = concepto;
		this.inicio = inicio;
		this.fin = fin;
		this.precio = precio;
		
	}
	

}
