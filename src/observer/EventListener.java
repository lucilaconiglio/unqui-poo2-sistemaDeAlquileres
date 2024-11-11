package observer;

import tipoDeInmueble.TipoDeInmueble;

public interface EventListener {

	public void notificarCancelacionInmueble(TipoDeInmueble tipoDeInmueble);
	public void notificarReservaInmueble(TipoDeInmueble tipoDeInmueble);
	public void notificarBajaDePrecioInmbueble(TipoDeInmueble tipoDeInmueble, Double precio); //TODO: Chequear cual es el msg que deberíamos mandar!
	
} 
