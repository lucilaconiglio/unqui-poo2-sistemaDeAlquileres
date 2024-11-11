package politicaCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Publicacion;
import reserva.Reserva;

class SinCancelacionTest {

    private SinCancelacion politica;
    private Publicacion publicacion;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        politica = new SinCancelacion();
        publicacion = mock(Publicacion.class);
        reserva = mock(Reserva.class);
    }

    @Test
    void testCalcularResarcimiento() {
        when(reserva.getFechaInicio()).thenReturn(LocalDate.of(2023, 11, 1));
        when(reserva.getFechaFin()).thenReturn(LocalDate.of(2023, 11, 10));
        
        when(publicacion.precioEntreFechas(any(), any())).thenReturn(200.0); 

        double resarcimiento = politica.calcularResarcimiento(publicacion, reserva);

        assertEquals(200.0, resarcimiento, "El resarcimiento debe ser igual al precio total entre las fechas.");
        
        verify(publicacion).precioEntreFechas(reserva.getFechaInicio(), reserva.getFechaFin());
    }
}