package politicaCancelacion;

import publicacion.Publicacion;
import reserva.Reserva;

public interface PoliticaDeCancelacion {
	
	public double calcularResarcimiento(Publicacion publi, Reserva reserva);


}
