package publicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import categoria.Categoria;
import formaDePago.FormaDePago;
import lombok.Getter;
import periodo.Periodo;
import politicaCancelacion.PoliticaDeCancelacion;
import rankeable.Rankeable;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import reserva.estadoReserva.EstadoCancelada;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.propietario.Propietario;
import observer.EventListener;

@Getter
public class Publicacion implements Rankeable{

	private LocalDate checkIn;
	private LocalDate checkOut;
	private double precioBase;
	private List<FormaDePago> formaDePago;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Propietario propietario;
	private String superficie;
	private int capacidad;
	private List<String> fotos;
	private Ubicacion ubicacion;
	private List<Periodo> periodos;
	private List<EventListener> suscriptores;

	private List<Servicio> servicios;
	private TipoDeInmueble tipoDeInmueble;
	private Ranking ranking;

	
	private List<Reserva> reservas;
    private Queue<Reserva> reservasCondicionales;
    private int vecesAlquilado;
    
	public Publicacion(LocalDate checkIn, LocalDate checkOut, double precioBase,
			PoliticaDeCancelacion politicaDeCancelacion, Propietario propietario, String superficie,int capacidad,Ubicacion ubicacion
			, TipoDeInmueble tipoDeInmueble) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.precioBase = precioBase;
		this.formaDePago = new ArrayList<>();
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.propietario = propietario;
		this.superficie = superficie;
		this.capacidad = capacidad;
		this.fotos = new ArrayList<>();
		this.ubicacion = ubicacion;
		this.periodos = new ArrayList<>();
		this.suscriptores = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.servicios = new ArrayList<>();
		this.tipoDeInmueble = tipoDeInmueble;
		this.ranking = new Ranking();
		this.reservas = new ArrayList<>();
        this.reservasCondicionales =  new LinkedList<>();
        this.vecesAlquilado = 0;
	}
	
	public void addPeriodo(Periodo periodo) {
		this.periodos.add(periodo);
	}
	
	public void removePeriodo(Periodo periodo) {
		periodos.remove(periodo);
	}
	
	public void addFoto(String foto) {
	    if (fotos.size() >= 5) {
	        throw new IllegalArgumentException("No se puede agregar más de 5 fotos.");
	    }
	    fotos.add(foto);
	}
	
	public void removeFoto(String foto) {
		fotos.remove(foto);
	}
	
	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
	}
	
	public void removeServicio(Servicio servicio) {
		servicios.remove(servicio);
	}
	
	public double precioPorDia(LocalDate fecha) {
		return periodos.stream().filter(periodo -> !fecha.isBefore(periodo.getInicio()) && 		!fecha.isAfter(periodo.getFin()))
		.findFirst().map(Periodo::getPrecio).orElse(precioBase); // Si no hay periodo específico, usa el 
		// precio base
	}
	
	public double precioEntreFechas(LocalDate entrada, LocalDate salida) {
		return entrada.datesUntil(salida.plusDays(1)) // Genera los días entre entrada y salida, inclusive
				.mapToDouble(f -> this.precioPorDia(f)) // Calcula el precio para cada día
				.sum(); // Suma todos los precios diarios
	}
	
	// STRATEGY
	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politicaDeCancelacion) {
		this.politicaDeCancelacion = politicaDeCancelacion;
	}
	
	
	// RANKING	
	
	
	
	@Override
	public void agregarResenia(Resenia res) {
		ranking.agregarResenia(res);
		
	}
	
	@Override
	public double obtenerPromedioGeneral() {
		return ranking.obtenerPromedioGeneral();
	};
	
	@Override
	public double obtenerPromedioCategoria(Categoria cat) {
		return ranking.obtenerPromedioPorCategoria(cat);
	}
	
	@Override
	public List<String> obternerComentarios() {
		return ranking.obtenerComentarios();
	}
	
	@Override
	public List<String> obtenerComentariosPorCategoria(Categoria cat) {
		return ranking.obtenerCometariosPorCategoria(cat);
	}

	
	// OBSERVER
	// RESERVA
	
	public void addSuscriptor(EventListener suscriptor) {
		suscriptores.add(suscriptor);
	}

	public void removeSuscriptor(EventListener suscriptor) {
		suscriptores.remove(suscriptor);
	}
	
	   // Recibir una reserva: Si hay conflicto, la agregamos a la lista condicional
    public void recibirReserva(Reserva reserva) {
        if (hayConflicto(reserva)) {
            reservasCondicionales.add(reserva);
        } else {
            reservas.add(reserva);
        }
    }

    // Método que verifica si hay conflicto, ignorando las reservas canceladas
    private boolean hayConflicto(Reserva nuevaReserva) {
        return reservas.stream().anyMatch(reserva -> reserva.conflictoCon(nuevaReserva));
    }
	
 // Aceptar una reserva: Cambia su estado a 'aceptada' sin moverla de la lista
    public void aceptarReserva(Reserva reserva) {
        reserva.aceptar(); // Cambiar el estado de la reserva a 'aceptada'
        notificarReservaInmueble();
        vecesAlquilado=+1;
        System.out.print(vecesAlquilado);
    }

    // Cancelar una reserva: Si está aceptada en reservas, cambia su estado a
    // 'cancelada' y no la eliminamos
    public void cancelarReserva(Reserva reserva) {
        reserva.cancelar();
        if (reservas.contains(reserva)) { // se procesa la reserva condicional solo si la reserva no es codicioal.
            if (!procesarPrimeraReservaCondicionalDisponible()) {
                notificarCancelacionInmueble();
            }
        }
    }

    private Boolean procesarPrimeraReservaCondicionalDisponible() {
        Iterator<Reserva> iterator = reservasCondicionales.iterator();
        while (iterator.hasNext()) {
            Reserva siguienteReserva = iterator.next();

            // Verificar que la reserva no esté en estado cancelado
            if (siguienteReserva.getEstadoReserva().estaActiva()) {
                reservas.add(siguienteReserva); // Moverla a la lista principal
                iterator.remove(); // Eliminar de las reservas condicionales
                return true; // Se movió una reserva condicional
            }
        }
        return false; // No se pudo mover ninguna reserva condicional
    }
	
    // Rechazar una reserva: Cambiar el estado a 'cancelada' y procesar la primera
    // reserva condicional disponible
    public void rechazarReserva(Reserva reserva) {
        reserva.rechazar(); // Cambiar el estado de la reserva a 'cancelada'
        // Verificar si la reserva NO es condicional
        if (reservas.contains(reserva)) {
            // Después de rechazar, procesamos las reservas condicionales
            procesarPrimeraReservaCondicionalDisponible();
        }
    }
	

    public void realizarCheckOut(Reserva reserva) {
    	reserva.realizarCheckOut();
    }
    
	public void bajarPrecioInmueble(double precioBase) {
		this.precioBase -= precioBase;
		notificarBajaDePrecioInmbueble();
	}

	private void notificarCancelacionInmueble() {
		suscriptores.stream()
					.forEach(s -> s.notificarCancelacionInmueble(tipoDeInmueble));
	}

	private void notificarBajaDePrecioInmbueble() {
		suscriptores.stream()
					.forEach(s -> s.notificarBajaDePrecioInmbueble(tipoDeInmueble, precioBase));
	}

	private void notificarReservaInmueble() {
		suscriptores.stream()
					.forEach(s -> s.notificarReservaInmueble(tipoDeInmueble));
	}

	
	
}
