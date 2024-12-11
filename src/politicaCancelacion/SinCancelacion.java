package politicaCancelacion;

import publicacion.Publicacion;
import reserva.Reserva;

public class SinCancelacion implements PoliticaDeCancelacion {

	@Override
	public double calcularResarcimiento(Reserva reserva) {
		return reserva.getValor();
	}

}
