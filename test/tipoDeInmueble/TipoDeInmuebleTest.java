package tipoDeInmueble;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TipoDeInmuebleTest {

    private TipoDeInmueble tipoInmueble;

    @BeforeEach
    public void setUp() {
        tipoInmueble = new TipoDeInmueble("Casa");
    }

    @Test
    void testGetConcepto() {
        assertEquals("Casa", tipoInmueble.getConcepto(), "El concepto debe ser 'Casa'");
    }

    @Test
    void testConstructor() {
        TipoDeInmueble tipoInmueble2 = new TipoDeInmueble("Departamento");
        assertEquals("Departamento", tipoInmueble2.getConcepto(), "El concepto debe ser 'Departamento'");
    }

    @Test
    void testConstructorNull() {
        TipoDeInmueble tipoInmuebleNull = new TipoDeInmueble(null);
        assertNull(tipoInmuebleNull.getConcepto(), "El concepto debe ser null");
    }

    @Test
    void testConstructorEmptyString() {
        TipoDeInmueble tipoInmuebleEmpty = new TipoDeInmueble("");
        assertEquals("", tipoInmuebleEmpty.getConcepto(), "El concepto debe ser una cadena vacía");
    }
}