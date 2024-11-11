package resenia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import categoria.Categoria;

class ReseniaTest {

    private Resenia resenia;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = mock(Categoria.class);
        resenia = new Resenia(categoria, 4, "Buena experiencia.");
    }

    @Test
    void testConstructor() {
        assertEquals(categoria, resenia.getCategoria(), "La categoría debe ser la que se pasó al constructor.");
        assertEquals(4, resenia.getPuntaje(), "El puntaje debe ser 4.");
        assertEquals("Buena experiencia.", resenia.getComentario(), "El comentario debe ser 'Buena experiencia.'");
    }

    @Test
    void testSetters() {
        resenia.setPuntaje(5);
        resenia.setComentario("Excelente experiencia.");

        assertEquals(5, resenia.getPuntaje(), "El puntaje debería haber sido actualizado a 5.");
        assertEquals("Excelente experiencia.", resenia.getComentario(), "El comentario debería haber sido actualizado.");
    }
}