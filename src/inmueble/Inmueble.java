package inmueble;

import java.util.List;
import lombok.Getter;
import rankeable.Rankeable;
import resenia.Resenia;
import ubicacion.Ubicacion;

@Getter
public class Inmueble implements Rankeable {
	private String tipoDeInmbueble;
	private String superficie;
	private int capacidad;
	private List<String> servicios;
	private List<String> fotos;
	private List<Resenia> resenias;
	private Ubicacion ubicacion;
	
	public Inmueble(String tipoDeInmbueble, String superficie, int capacidad, List<String> servicios,
			List<String> fotos, List<Resenia> resenias, Ubicacion ubicacion) {
		this.tipoDeInmbueble = tipoDeInmbueble;
		this.superficie = superficie;
		this.capacidad = capacidad;
		this.servicios = servicios;
		this.fotos = fotos;
		this.resenias = resenias;
		this.ubicacion = ubicacion;
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
	
	public List<Resenia> getReseniasPorCategoria(String categoria){
		return 	resenias.stream().filter(res-> res.getCategoria().equals(categoria)).toList();
	}

	@Override
	public void agregarResenia(Resenia res) {
		this.resenias.add(res);
	}

	@Override
	public double obtenerPromedioGeneral() {
		return resenias.stream().mapToDouble(res->res.getPuntaje()).sum();
	}
	
	@Override
	public double obtenerPromedioCategoria(String cat) {
		return getReseniasPorCategoria(cat).stream().mapToDouble(res->res.getPuntaje()).sum();
	}
		
}
