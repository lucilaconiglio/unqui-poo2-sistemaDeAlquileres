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
import resenia.Resenia;
import reserva.Reserva;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.User;
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
	private List<Resenia> resenias;
	private Ubicacion ubicacion;
	private List<Periodo> periodos;
	private List<EventListener> suscriptores;
	private List<Reserva> reservas;
	private List<Servicio> servicios;
	private TipoDeInmueble tipoDeInmueble;

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
		this.resenias = new ArrayList<>();
		this.ubicacion = ubicacion;
		this.periodos = new ArrayList<>();
		this.suscriptores = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.servicios = new ArrayList<>();
		this.tipoDeInmueble = tipoDeInmueble;
	}
	
	public void addPediodo(Periodo periodo) {
		this.periodos.add(periodo);
	}
	
	public void removePeriodo(Periodo periodo) {
		periodos.remove(periodo);
	}
	
	public void SetPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion=politica;
	}
	
	public void addFoto(String foto) {
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
	
	public List<Resenia> getReseniasPorCategoria(Categoria categoria){
		return 	resenias.stream().filter(res-> res.getCategoria().getConcepto().equals(categoria.getConcepto())).toList();
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
	public double obtenerPromedioCategoria(Categoria cat) {
		List<Resenia> reseniasPorCategoria = getReseniasPorCategoria(cat);
	    double sumaPuntajes = reseniasPorCategoria.stream()
	        .mapToDouble(res->res.getPuntaje()).sum();
	    
	    return reseniasPorCategoria.isEmpty() ? 0.0 : sumaPuntajes / reseniasPorCategoria.size();
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

	
	public void addSuscriptor(EventListener suscriptor) {
		suscriptores.add(suscriptor);
	}

	public void removeSuscriptor(EventListener suscriptor) {
		suscriptores.remove(suscriptor);
	}
	
	public void cancelarReserva(Reserva reserva) {
		//reserva.cancelar();
		notificarCancelacionInmueble();
	}
	

	public void reservaInmueble() {
		// new Reserva()
		notificarReservaInmueble();
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
