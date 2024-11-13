package sitio;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
                .filter(r -> r.getEstadoReserva().estaOcupada() || r.getEstadoReserva().finalizadaExitosamente()) // Filtra las reservas concretadas
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
                        .noneMatch(reserva -> reserva.getEstadoReserva().estaOcupada())
                        )
                .collect(Collectors.toList());
    }

    // Método para calcular la tasa de ocupación del sitio (porcentaje de inmuebles alquilados)
    public double tasaDeOcupacion() {
        long inmueblesAlquilados = publicaciones.stream()
                .filter(publicacion -> publicacion.getReservas().stream()
                        .anyMatch(reserva -> reserva.getEstadoReserva().estaOcupada()))
                .count();

        long totalInmuebles = publicaciones.size();

        return totalInmuebles > 0 ? (double) inmueblesAlquilados / totalInmuebles * 100 : 0;
    }
    
    
}
