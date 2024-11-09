package observer;

import publicacion.Publicacion;

public interface Observer {

	public void notificarCancelacionInmueble(Publicacion publicacion);
	public void notificarReservaInmueble(Publicacion publicacion);
	public void notificarBajaDePrecioInmbueble(Publicacion publicacion,String msg ,double precio); //TODO: Chequear cual es el msg que deberíamos mandar!
	
} 
