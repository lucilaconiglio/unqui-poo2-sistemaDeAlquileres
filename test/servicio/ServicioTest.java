package servicio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioTest {

    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        // Configura el objeto Servicio antes de cada prueba
        servicio = new Servicio("Wi-Fi");
    }

    @Test
    void testGetConcepto() {
        // Verifica que el método getConcepto retorne el valor correcto
        assertEquals("Wi-Fi", servicio.getConcepto(), "El concepto debe ser 'Wi-Fi'");
    }

    @Test
    void testConstructor() {
        // Verifica que el constructor inicialice correctamente el concepto
        Servicio servicio2 = new Servicio("Agua Caliente");
        assertEquals("Agua Caliente", servicio2.getConcepto(), "El concepto debe ser 'Agua Caliente'");
    }

    @Test
    void testConstructorNull() {
        // Verifica que el constructor puede manejar un concepto nulo (si es un caso esperado)
        Servicio servicioNull = new Servicio(null);
        assertNull(servicioNull.getConcepto(), "El concepto debe ser null");
    }

    @Test
    void testConstructorEmptyString() {
        // Verifica que el constructor puede manejar un concepto vacío
        Servicio servicioEmpty = new Servicio("");
        assertEquals("", servicioEmpty.getConcepto(), "El concepto debe ser una cadena vacía");
    }
}