package observer;

import publicacion.Publicacion;
import tipoDeInmueble.TipoDeInmueble;

public class SitioExterno implements EventListener {
	
	private HomePagePublisher homePagePublisher;

	public SitioExterno(HomePagePublisher homePagePublisher) {
		this.homePagePublisher = homePagePublisher;
	}

	@Override
	public void notificarCancelacionInmueble(TipoDeInmueble tipoDeInmueble) {
		
	}

	@Override
	public void notificarReservaInmueble(TipoDeInmueble tipoDeInmueble) {
		
	}

	@Override
	public void notificarBajaDePrecioInmbueble(TipoDeInmueble tipoDeInmueble, Double precio) {
		String mensaje = "No te pierdas esta oferta: Un inmueble" + tipoDeInmueble + "a tan sólo " + precio.toString() + "pesos";
		homePagePublisher.publish(mensaje);
	}


	
	
}
