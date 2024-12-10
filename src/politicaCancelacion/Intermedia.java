package politicaCancelacion;

import java.time.LocalDate;

import publicacion.Publicacion;
import reserva.Reserva;

public class Intermedia implements PoliticaDeCancelacion {

    @Override
    public double calcularResarcimiento(Reserva reserva) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicioReserva = reserva.getFechaInicio();
        double precioTotal = reserva.getValor();

        if (esConAnticipacion(fechaActual, fechaInicioReserva)) {
            return 0.0; // Cancelación gratuita hasta 20 días antes
        } else if (esCancelacionIntermedia(fechaActual, fechaInicioReserva)) {
            return precioTotal / 2; // 50 % del precio total
        } else {
            return precioTotal; // 100 % del precio total
        }
    }

    private boolean esConAnticipacion(LocalDate fechaActual, LocalDate fechaInicioReserva) {
        // Cancelación gratuita hasta 20 días antes
        return fechaActual.isBefore(fechaInicioReserva.minusDays(20));
    }

    private boolean esCancelacionIntermedia(LocalDate fechaActual, LocalDate fechaInicioReserva) {
        // Cancelación intermedia: entre 19 y 10 días antes (inclusive)
        LocalDate fecha19DiasAntes = fechaInicioReserva.minusDays(19);
        LocalDate fecha10DiasAntes = fechaInicioReserva.minusDays(10);
        return (fechaActual.isEqual(fecha19DiasAntes) || fechaActual.isEqual(fecha10DiasAntes)) ||
               (fechaActual.isAfter(fecha19DiasAntes) && fechaActual.isBefore(fecha10DiasAntes));
    }


}