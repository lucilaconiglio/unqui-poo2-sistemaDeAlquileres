package ubicacion;

import java.util.List;

import resenia.Resenia;

public class Ubicacion {
	private String tipoDeInmbueble;
	private String superficie;
	private int capacidad;
	private List<String> servicios;
	private List<String> fotos;
	private List<Resenia> resenias;
	
	public Ubicacion(String tipoDeInmbueble, String superficie, int capacidad, List<String> servicios,
			List<String> fotos, List<Resenia> resenias) {
		super();
		this.tipoDeInmbueble = tipoDeInmbueble;
		this.superficie = superficie;
		this.capacidad = capacidad;
		this.servicios = servicios;
		this.fotos = fotos;
		this.resenias = resenias;
	}
	
	public void addFoto(String foto) {
		fotos.add(foto);
	}
	
	public void removeFoto(String foto) {
		fotos.remove(foto);
	}
	
	public void addServicio(String servicio) {
		servicios.add(servicio);
	}

	public void removeServicio(String servicio) {
		servicios.remove(servicio);
	}
	
	public List<Resenia> getResenias(){
		return resenias;
	}
	
	public List<Resenia> getResenias(String resenia){
		return 	resenias.stream().filter(el-> el.equals(resenia)).toList();
	}
	
	public int getPromedioPuntaje() {
		//TODO: Ver como calcular el promedio
		return 0; 
	}
	
	public int getPromedioPuntaje(String categoria) {
		//TODO: Ver como calcular el promedio por categoria
		return 0; 
	}
	
}
