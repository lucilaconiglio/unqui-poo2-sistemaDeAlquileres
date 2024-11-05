package observer;

import inmueble.Inmueble;
import publicacion.Publicacion;

public interface Observer {

	public void notificarCancelacionInmueble(Publicacion publicacion, Inmueble inmueble);
	public void notificarReservaInmueble(Publicacion publicacion ,Inmueble inmueble);
	public void notificarBajaDePrecioInmbueble(Publicacion publicacion,String msg ,double precio); //TODO: Chequear cual es el msg que deberíamos mandar!
	
} 
