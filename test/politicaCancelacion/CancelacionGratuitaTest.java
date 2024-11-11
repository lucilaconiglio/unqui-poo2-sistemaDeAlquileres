package politicaCancelacion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Publicacion;
import reserva.Reserva;
import periodo.Periodo;

class CancelacionGratuitaTest {

    CancelacionGratuita cancelacionGratuita;
    Publicacion mockPublicacion;
    Reserva mockReserva;
    Periodo mockPerdiodo;

    @BeforeEach
    public void setUp() {
        cancelacionGratuita = new CancelacionGratuita();
        mockPublicacion = mock(Publicacion.class);
        mockReserva = mock(Reserva.class);
        mockPerdiodo = mock(Periodo.class);
    }

    @Test
    void testEsFechaHoyDiezDiasAntes() {
        // Configuramos una fecha que es 10 días después de hoy
        LocalDate fechaPrueba = LocalDate.now().plusDays(11);
        boolean resultado = cancelacionGratuita.esFechaHoyDiezDiasAntes(fechaPrueba);
        
        // Verificamos que el resultado sea True porque la fecha de prueba(hoy) es 10 días antes (o mas)
        assertTrue(resultado);

        // Configuramos una fecha que es 8 días mas de hoy
        fechaPrueba = LocalDate.now().plusDays(8);
        resultado = cancelacionGratuita.esFechaHoyDiezDiasAntes(fechaPrueba);
        
        // Verificamos que el resultado sea False
        assertFalse(resultado);
    }

    @Test
    void testCalcularResarcimientoDiezDiasAntes() {
        // Establecemos una fecha para sample reserva
        LocalDate fechaInicio = LocalDate.now().plusDays(11);
        when(mockReserva.getFechaInicio()).thenReturn(fechaInicio);
        when(mockPerdiodo.getInicio()).thenReturn(fechaInicio.plusDays(10));
        when(mockPublicacion.getPrecioBase()).thenReturn(10.00);
        double resarcimiento = cancelacionGratuita.calcularResarcimiento(mockPublicacion, mockReserva);
        
        // Verificamos que el resarcimiento sea 0.0
        assertEquals(0.0, resarcimiento);
    }

    @Test
    void testCalcularResarcimientoNoDiezDiasAntes() {
        // Establecemos una fecha para la reserva que NO es 10 días antes
        LocalDate fechaInicio = LocalDate.now().plusDays(5); // No es 10 días antes
        when(mockReserva.getFechaInicio()).thenReturn(fechaInicio);
        //when(mockPerdiodo.getInicio()).thenReturn(fechaInicio.plusDays(10));
        when(mockPublicacion.precioPorDia(LocalDate.now())).thenReturn(100.0);

        double resarcimiento = cancelacionGratuita.calcularResarcimiento(mockPublicacion, mockReserva);
        
        // Verificamos que el resarcimiento sea 200.0 (2 días de precio)
        assertEquals(200.0, resarcimiento);
    }
}