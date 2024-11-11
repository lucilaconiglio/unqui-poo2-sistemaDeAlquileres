package observer;

import publicacion.Publicacion;
import tipoDeInmueble.TipoDeInmueble;

public class UsuarioMovil implements EventListener{
	
	private PopUpWindow popUpWindow;

	public UsuarioMovil(PopUpWindow popUpWindow) {
		this.popUpWindow = popUpWindow;
	}

	@Override
	public void notificarCancelacionInmueble(TipoDeInmueble tipoDeInmueble) {
		String mensaje = "El/la " + tipoDeInmueble + " que te interesa se ha liberado! Corre a reservarlo!";
		popUpWindow.popUp(mensaje, "Green", 15);
		
	}

	@Override
	public void notificarReservaInmueble(TipoDeInmueble tipoDeInmueble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarBajaDePrecioInmbueble(TipoDeInmueble tipoDeInmueble, Double precio) {
		// TODO Auto-generated method stub
		
	}


	
}
