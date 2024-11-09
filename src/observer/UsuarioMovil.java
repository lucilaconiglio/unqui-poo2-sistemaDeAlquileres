package observer;

import publicacion.Publicacion;

public class UsuarioMovil implements Observer{
	private PopUpWindow popUpWindow;

	public UsuarioMovil(PopUpWindow popUpWindow) {
		super();
		this.popUpWindow = popUpWindow;
	}

	@Override
	public void notificarCancelacionInmueble(Publicacion publicacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarReservaInmueble(Publicacion publicacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarBajaDePrecioInmbueble(Publicacion publicacion, String msg, double precio) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
