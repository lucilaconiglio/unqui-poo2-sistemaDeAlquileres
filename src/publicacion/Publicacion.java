package publicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import categoria.Categoria;
import formaDePago.FormaDePago;
import lombok.Getter;
import periodo.Periodo;
import politicaCancelacion.PoliticaDeCancelacion;
import rankeable.Rankeable;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
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
	private List<Reserva> reservas;
	private List<Servicio> servicios;
	private TipoDeInmueble tipoDeInmueble;
	private Ranking ranking;

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
	}
	
	public void addPeriodo(Periodo periodo) {
		this.periodos.add(periodo);
	}
	
	public void removePeriodo(Periodo periodo) {
		periodos.remove(periodo);
	}
	
	public void addFoto(String foto) {
		// TODO: agregar limite de 5 fotos. devolver exepcion cuando se quiere agregar de mas.
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
		return periodos.stream().filter(periodo -> !fecha.isBefore(periodo.getInicio()) && !fecha.isAfter(periodo.getFin()))
				.findFirst().map(Periodo::getPrecio).orElse(precioBase); // Si no hay periodo específico, usa el precio
		// base
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
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return politicaDeCancelacion;
	}
	
	
	// RANKING	
	
	@Override
	public Ranking getRanking() {
		return ranking;
		
	}
	
	
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
	public List<String> obetenerComentariosPorCategoria(Categoria cat) {
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
	
	public void cancelarReserva(Reserva reserva) {
		reserva.cancelar();
		notificarCancelacionInmueble();
	}
	

	public void aceptarReserva(Reserva reserva) {
		reserva.aceptar();
		notificarReservaInmueble();
	}
	
	public void rechazarReserva(Reserva reserva) {
		reserva.rechazar();
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
