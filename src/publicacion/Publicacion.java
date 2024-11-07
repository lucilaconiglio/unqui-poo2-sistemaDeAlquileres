package publicacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import formaDePago.FormaDePago;
import inmueble.Inmueble;
import lombok.Getter;
import periodo.Periodo;
import reserva.Reserva;
import user.User;
import observer.Observer;

@Getter
public class Publicacion {

	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private double precioBase;
	private FormaDePago formaDePago;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private User propietario;
	private Inmueble inmueble;
	private List<Periodo> periodos;
	private List<Observer> suscriptores;
	private List<Reserva> reservas;

	public Publicacion(LocalDateTime checkIn, LocalDateTime checkOut, double precioBase, FormaDePago formaDePago,
			PoliticaDeCancelacion politicaDeCancelacion, User propietario, Inmueble inmueble) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.precioBase = precioBase;
		this.formaDePago = formaDePago;
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.propietario = propietario;
		this.inmueble = inmueble;
		this.periodos = new ArrayList<Periodo>();
		this.suscriptores = new ArrayList<Observer>();
		this.reservas = new ArrayList<Reserva>();

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

	public void addPediodo(Periodo periodo) {
		this.periodos.add(periodo);
	}

	public void removePeriodo(Periodo periodo) {
		this.periodos.add(periodo);
	}

	public void addObserver(Observer ob) {
		this.suscriptores.add(ob);
	}

	public void removeObserver(Observer ob) {
		suscriptores.remove(ob);
	}

	public void cancelarReserva() {
		// implementar
	}

	public void reservaInmueble() {
		// implementar
	}

	public void cambiarPrecioBase(double nuevoPrecio) {
		this.precioBase = nuevoPrecio;
	}
}
