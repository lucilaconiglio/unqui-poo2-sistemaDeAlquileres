package observer;

import publicacion.Publicacion;

public class SitioExterno implements Observer{
	private HomePagePublisher homePagePublisher;

	public SitioExterno(HomePagePublisher homePagePublisher) {
		super();
		this.homePagePublisher = homePagePublisher;
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
