package ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UbicacionTest {

    private Ubicacion ubicacion;

    @BeforeEach
    void setUp() {
        ubicacion = new Ubicacion("Argentina", "Buenos Aires", "Av. 9 de Julio 1234");
    }

    @Test
    void testGetPais() {
        // Verifica que el país se haya asignado correctamente
        assertEquals("Argentina", ubicacion.getPais(), "El país debe ser 'Argentina'");
    }

    @Test
    void testGetCiudad() {
        // Verifica que la ciudad se haya asignado correctamente
        assertEquals("Buenos Aires", ubicacion.getCiudad(), "La ciudad debe ser 'Buenos Aires'");
    }

    @Test
    void testGetDireccion() {
        // Verifica que la dirección se haya asignado correctamente
        assertEquals("Av. 9 de Julio 1234", ubicacion.getDireccion(), "La dirección debe ser 'Av. 9 de Julio 1234'");
    }

    @Test
    void testConstructorNullValues() {
        // Verifica que el constructor maneje correctamente los valores nulos
        Ubicacion ubicacionNull = new Ubicacion(null, null, null);
        assertNull(ubicacionNull.getPais(), "El país debe ser null");
        assertNull(ubicacionNull.getCiudad(), "La ciudad debe ser null");
        assertNull(ubicacionNull.getDireccion(), "La dirección debe ser null");
    }

    @Test
    void testConstructorEmptyValues() {
        // Verifica que el constructor maneje correctamente los valores vacíos
        Ubicacion ubicacionEmpty = new Ubicacion("", "", "");
        assertEquals("", ubicacionEmpty.getPais(), "El país debe ser una cadena vacía");
        assertEquals("", ubicacionEmpty.getCiudad(), "La ciudad debe ser una cadena vacía");
        assertEquals("", ubicacionEmpty.getDireccion(), "La dirección debe ser una cadena vacía");
    }
}
