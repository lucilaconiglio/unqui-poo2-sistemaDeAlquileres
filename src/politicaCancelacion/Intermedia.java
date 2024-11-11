package politicaCancelacion;

import java.time.LocalDate;

import publicacion.Publicacion;
import reserva.Reserva;

public class Intermedia implements PoliticaDeCancelacion {

    @Override
    public double calcularResarcimiento(Publicacion publi, Reserva reserva) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicioReserva = reserva.getFechaInicio();
        double precioTotal = publi.precioEntreFechas(fechaInicioReserva, reserva.getFechaFin());

        if (esVeinteDiasAntes(fechaActual, fechaInicioReserva)) {
            return 0.0;
        } else if (estaEntreDiecinueveYDiezDiasAntes(fechaActual, fechaInicioReserva)) {
            return precioTotal / 2;
        } else {
            return precioTotal;
        }
    }

    private boolean esVeinteDiasAntes(LocalDate fechaActual, LocalDate fechaInicioReserva) {
        return fechaActual.isEqual(fechaInicioReserva.minusDays(20));
    }

    private boolean estaEntreDiecinueveYDiezDiasAntes(LocalDate fechaActual, LocalDate fechaInicioReserva) {
        LocalDate fecha19DiasAntes = fechaInicioReserva.minusDays(19);
        LocalDate fecha10DiasAntes = fechaInicioReserva.minusDays(10);
        return (fechaActual.isAfter(fecha19DiasAntes) && fechaActual.isBefore(fecha10DiasAntes)) ||
               fechaActual.isEqual(fecha19DiasAntes) || fechaActual.isEqual(fecha10DiasAntes);
    }
}