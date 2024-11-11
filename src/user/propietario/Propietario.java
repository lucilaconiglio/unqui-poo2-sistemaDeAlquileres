package user.propietario;


import publicacion.Publicacion;
import resenia.Resenia;
import reserva.Reserva;
import user.User;



public interface Propietario {
	
	public void rankearInquilino(User usuario, Publicacion publicacion, Resenia resenia);
	public int getAngiguedad();
	public void aceptar(Reserva reserva);
	
}
