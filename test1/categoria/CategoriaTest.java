package categoria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria("Residencial");
    }

    @Test
    void testGetConcepto() {
        assertEquals("Residencial", categoria.getConcepto(), "El concepto debe ser 'Residencial'");
    }

    @Test
    void testConstructor() {
        Categoria categoria2 = new Categoria("Comercial");
        assertEquals("Comercial", categoria2.getConcepto(), "El concepto debe ser 'Comercial'");
    }

    @Test
    void testConstructorNull() {
        Categoria categoriaNull = new Categoria(null);
        assertNull(categoriaNull.getConcepto(), "El concepto debe ser null");
    }

    @Test
    void testConstructorEmptyString() {
        Categoria categoriaEmpty = new Categoria("");
        assertEquals("", categoriaEmpty.getConcepto(), "El concepto debe ser una cadena vacía");
    }
}