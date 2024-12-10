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
        boolean resultado = cancelacionGratuita.esCancelacionGratuita(fechaPrueba);
        
        // Verificamos que el resultado sea True porque la fecha de prueba(hoy) es 10 días antes (o mas)
        assertTrue(resultado);

        // Configuramos una fecha que es 8 días mas de hoy
        fechaPrueba = LocalDate.now().plusDays(8);
        resultado = cancelacionGratuita.esCancelacionGratuita(fechaPrueba);
        
        // Verificamos que el resultado sea False
        assertFalse(resultado);
    }

    @Test
    void testCalcularResarcimientoDiezDiasAntes() {
        // Establecemos una fecha para sample reserva
        LocalDate fechaInicio = LocalDate.now().plusDays(11);
        when(mockReserva.getFechaInicio()).thenReturn(fechaInicio);
        when(mockPerdiodo.getInicio()).thenReturn(fechaInicio.plusDays(10));
        double resarcimiento = cancelacionGratuita.calcularResarcimiento(mockReserva);
        
        // Verificamos que el resarcimiento sea 0.0
        assertEquals(0.0, resarcimiento);
    }

    @Test
    void testCalcularResarcimientoNoGratuito() {
        // Crear un mock de la reserva
        Reserva mockReserva = mock(Reserva.class);

        // Configurar la fecha de inicio de la reserva para que esté dentro de los 10 días (por ejemplo, 5 días)
        LocalDate fechaInicio = LocalDate.now().plusDays(5); // Dentro de los 10 días de antelación
        when(mockReserva.getFechaInicio()).thenReturn(fechaInicio);

        // Asumir que la reserva tiene 7 días de estadía y un precio total de 1000.0
        when(mockReserva.getDiasDeEstadia()).thenReturn(7);
        when(mockReserva.getValor()).thenReturn(1000.0);


        // Ejecutamos el cálculo de resarcimiento
        double resarcimiento = cancelacionGratuita.calcularResarcimiento(mockReserva);

        // Verificamos que el resarcimiento sea el costo de dos días (calculado como 1000.0 / 7 * 2)
        double valorEsperado = 1000.0 / 7 * 2;
        assertEquals(valorEsperado, resarcimiento, "El resarcimiento debería ser el valor de dos días de estadía");
    }

}