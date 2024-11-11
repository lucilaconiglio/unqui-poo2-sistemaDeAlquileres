package ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UbicacionTest {

    private Ubicacion ubicacion;

    @BeforeEach
    public void setUp() {
        ubicacion = new Ubicacion("Argentina", "Buenos Aires", "Avenida de Mayo 1234");
    }

    @Test
    void testConstructor() {
        assertEquals("Argentina", ubicacion.getPais(), "El país debe ser 'Argentina'.");
        assertEquals("Buenos Aires", ubicacion.getCiudad(), "La ciudad debe ser 'Buenos Aires'.");
        assertEquals("Avenida de Mayo 1234", ubicacion.getDireccion(), "La dirección debe ser 'Avenida de Mayo 1234'.");
    }

    @Test
    void testSetters() {
        ubicacion.setPais("Chile");
        ubicacion.setCiudad("Santiago");
        ubicacion.setDireccion("Paseo Ahumada 456");

        assertEquals("Chile", ubicacion.getPais(), "El país debería haber sido actualizado a 'Chile'.");
        assertEquals("Santiago", ubicacion.getCiudad(), "La ciudad debería haber sido actualizada a 'Santiago'.");
        assertEquals("Paseo Ahumada 456", ubicacion.getDireccion(), "La dirección debería haber sido actualizada a 'Paseo Ahumada 456'.");
    }
}