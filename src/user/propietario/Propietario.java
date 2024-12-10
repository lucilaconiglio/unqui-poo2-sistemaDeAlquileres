package user.propietario;



import publicacion.Publicacion;
import rankeable.Rankeable;
import resenia.Resenia;
import reserva.Reserva;
import user.inquilino.Inquilino;



public interface Propietario extends Rankeable{
	
	public void rankearInquilino(Resenia resenia, Inquilino inquilino, Reserva reserva);
	public int getAntiguedad();
	public void darDeAltaPublicacion(Publicacion publicacion);
}
