package politicaCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import publicacion.Publicacion;
import reserva.Reserva;

class IntermediaTest {

    private Intermedia politica;
    private Publicacion publicacion;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        politica = new Intermedia();
        publicacion = mock(Publicacion.class);
        reserva = mock(Reserva.class);
    }

    @Test
    void testCalcularResarcimientoVeinteDiasAntes() {
        when(reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(20));
        when(publicacion.precioEntreFechas(any(), any())).thenReturn(100.0); // Supongamos que el precio total es 100

        double resarcimiento = politica.calcularResarcimiento(publicacion, reserva);

        assertEquals(0.0, resarcimiento, "El resarcimiento debe ser 0.0 si la reserva es 20 días antes.");
    }

    @Test
    void testCalcularResarcimientoEntreDiecinueveYDiezDiasAntes() {
        when(reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(15));
        when(publicacion.precioEntreFechas(any(), any())).thenReturn(100.0); 

        double resarcimiento = politica.calcularResarcimiento(publicacion, reserva);

        assertEquals(50.0, resarcimiento, "El resarcimiento debe ser la mitad del precio total si la reserva está entre 19 y 10 días antes.");
    }

    @Test
    void testCalcularResarcimientoMenosDeDiezDiasAntes() {
        when(reserva.getFechaInicio()).thenReturn(LocalDate.now().plusDays(5));
        when(publicacion.precioEntreFechas(any(), any())).thenReturn(100.0); 

        double resarcimiento = politica.calcularResarcimiento(publicacion, reserva);

        assertEquals(100.0, resarcimiento, "El resarcimiento debe ser el precio total si la reserva es menos de 10 días antes.");
    }

    @Test
    void testCalcularResarcimientoHoy() {
        when(reserva.getFechaInicio()).thenReturn(LocalDate.now());
        when(publicacion.precioEntreFechas(any(), any())).thenReturn(100.0); 

        double resarcimiento = politica.calcularResarcimiento(publicacion, reserva);

        assertEquals(100.0, resarcimiento, "El resarcimiento debe ser el precio total si la reserva comienza hoy.");
    }
}