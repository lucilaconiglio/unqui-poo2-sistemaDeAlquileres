package politicaCancelacion;

import publicacion.Publicacion;
import reserva.Reserva;


public class SinCancelacion implements PoliticaDeCancelacion{

	@Override
	public double calcularResarcimiento(Publicacion publi, Reserva reserva) {
		
		return publi.precioEntreFechas(reserva.getFechaInicio(),reserva.getFechaFin());
	}

}
