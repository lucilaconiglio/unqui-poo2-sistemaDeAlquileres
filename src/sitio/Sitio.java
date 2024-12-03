package sitio;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import categoria.Categoria;
import categoriasManager.CategoriasManager;
import lombok.Getter;
import publicacion.Publicacion;
import rankeable.Rankeable;
import reserva.Reserva;
import reserva.estadoReserva.EstadoConsolidada;
import search.Search;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import user.User;
import user.inquilino.Inquilino;
import user.propietario.Propietario;
@Getter 
public class Sitio {

	private List<Publicacion> publicaciones;
	private List<Servicio> servicios;
	private List<TipoDeInmueble> tiposDeInmueble;
	private List<User> users;

	public Sitio() {
		this.publicaciones = new ArrayList<>();
		this.servicios = new ArrayList<>();
		this.tiposDeInmueble = new ArrayList<>();
		this.users = new ArrayList<>();
	}


	public List<Publicacion> buscarPublicaciones(Search search) {
		// Se filtran las publicaciones según los criterios de búsqueda. 
		return search.filterPublicaciones(publicaciones);
	}

	public void addPublicacion(Publicacion publicacion) {
		this.publicaciones.add(publicacion);

	}

	public void removePublicacion(Publicacion publicacion) {
		publicaciones.remove(publicacion);
	}
	
	public void addUsuario(User user) {
		users.add(user);
	}

	public void removeUsuario(User user) {
		users.remove(user);
	}
		
	// ADMINISTRACION DEL SITIO

    public void addServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

    public void removeServicio(Servicio servicio) {
        servicios.remove(servicio);
    }

    public void addTipoDeInmueble(TipoDeInmueble tiposDeInmueble) {
        this.tiposDeInmueble.add(tiposDeInmueble);
    }

    public void removeTipoDeInmeble(TipoDeInmueble _tiposDeInmueble) {
        tiposDeInmueble.remove(_tiposDeInmueble);
    }


    // Método para obtener el top-ten de inquilinos que más han alquilado en el sitio
    public List<Inquilino> topDiezInquilinos() {
        return publicaciones.stream()
                .flatMap(p -> p.getReservas().stream()) // Convierte cada lista de reservas en un stream
                .filter(r -> r.estaOcupada() || r.finalizadaExitosamente()) // Filtra las reservas concretadas
                .collect(Collectors.groupingBy(Reserva::getInquilino, Collectors.counting())) // Agrupa y cuenta por inquilino
                .entrySet().stream()
                .sorted(Map.Entry.<Inquilino, Long>comparingByValue().reversed()) // Ordena por cantidad en orden descendente
                .limit(10) // Toma los primeros diez
                .map(Map.Entry::getKey) // Extrae los inquilinos
                .collect(Collectors.toList()); // Devuelve la lista de los top 10
    }
    
 // Método para obtener todos los inmuebles libres 
    public List<Publicacion> obtenerInmueblesLibres() {
        return publicaciones.stream()
                .filter(publicacion -> publicacion.getReservas().stream()
                        .noneMatch(reserva -> reserva.estaOcupada())
                        )
                .collect(Collectors.toList());
    }

    // Método para calcular la tasa de ocupación del sitio (porcentaje de inmuebles alquilados)
    public double tasaDeOcupacion() {
        long inmueblesAlquilados = publicaciones.stream()
                .filter(publicacion -> publicacion.getReservas().stream()
                        .anyMatch(reserva -> reserva.estaOcupada()))
                .count();

        long totalInmuebles = publicaciones.size();

        return totalInmuebles > 0 ? (double) inmueblesAlquilados / totalInmuebles * 100 : 0;
    }
    
    public List<Reserva> obtenerTodasLasReservasDelSitio(){
    	return publicaciones.stream()
    			.flatMap(p -> p.getReservas().stream())
    			.toList();
    }
    
    public List<Reserva> obtenerTodasLasReservasDe(Inquilino inquilino){
    	return obtenerTodasLasReservasDelSitio()
    			.stream()
    			.filter(r -> r.esIniquilino(inquilino))
    			.toList();
    }
    
    public List<Reserva> obtenerTodasLasReservasFuturas(Inquilino inquilino){
    	return  obtenerTodasLasReservasDe(inquilino)
    			.stream()
    			.filter(r -> r.esDespuesDe(r.getFechaInicio()))
    			.toList(); 
    }
    
    public List<Reserva> obtenerReservasDeInquilinoEnCiudad(String ciudad,Inquilino inquilino){
    	/*return publicaciones.stream()
    			.filter(p -> p.getUbicacion().getCiudad().);
    	*/
    	  return publicaciones.stream()
    	            .filter(p -> p.esDeCiudad(ciudad)) // Filtra por ciudad
    	            .flatMap(p -> p.getReservas().stream()) // Descompone las reservas de cada publicación
    	            .filter(r -> r.esIniquilino(inquilino)) // Filtra por inquilino
    	            .collect(Collectors.toList()); 
    }
 
    public List<String> obtenerCiudadesDondeInquilinoTieneReserva(Inquilino inquilino) {
        return publicaciones.stream()
                .flatMap(p -> p.getReservas().stream() // Descompone las reservas de cada publicación
                        .filter(r -> r.esIniquilino(inquilino)) // Filtra por inquilino
                        .map(r -> p.getCiudad())) // Mapea la ciudad de la publicación de cada reserva
                .distinct() // Evita ciudades duplicadas
                .collect(Collectors.toList()); // Recoge los resultados en una lista
    } 
    
    public int cantidadDeVecesQueAlquiloInmueble(Publicacion publicacion) {
        return publicacion.getVecesAlquilado();
    }
    
    public int cantidadDeVecesQueAlquiloInmuebles(Propietario propietario) {
        return publicaciones.stream()
                .filter(p -> p.esPropietario(propietario))
                .mapToInt(p -> p.getVecesAlquilado())
                .sum();
    }
    
    public List<Publicacion> inmueblesAlquilados(Propietario propietario) {
        return publicaciones.stream()
                .filter(p -> p.esPropietario(propietario))
                .filter(p -> p.getVecesAlquilado() > 0)
                .toList();
    }

}
