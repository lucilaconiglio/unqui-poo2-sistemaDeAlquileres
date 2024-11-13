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
        when(categoria.getConcepto()).thenReturn("Categoria1");  // Puedes configurar el mock si necesitas acceso a métodos de Categoria.
        resenia = new Resenia(categoria, 4, "Buena experiencia.");
    }

    @Test
    void testConstructor() {
        // Probamos que el constructor funcione correctamente
        assertEquals(categoria, resenia.getCategoria(), "La categoría debe ser la que se pasó al constructor.");
        assertEquals(4, resenia.getPuntaje(), "El puntaje debe ser 4.");
        assertEquals("Buena experiencia.", resenia.getComentario(), "El comentario debe ser 'Buena experiencia.'");
    }

    @Test
    void testConstructorPuntajeInvalido() {
        // Probamos que el constructor lanza una excepción para un puntaje no válido
        assertThrows(IllegalArgumentException.class, () -> {
            new Resenia(categoria, 0, "Comentario inválido.");  // Puntaje menor que 1
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Resenia(categoria, 6, "Comentario inválido.");  // Puntaje mayor que 5
        });
    }

    @Test
    void testSetters() {
        resenia.setPuntaje(5);
        resenia.setComentario("Excelente experiencia.");

        assertEquals(5, resenia.getPuntaje(), "El puntaje debería haber sido actualizado a 5.");
        assertEquals("Excelente experiencia.", resenia.getComentario(), "El comentario debería haber sido actualizado.");
    }

    @Test
    void testSetPuntajeInvalido() {
        // Probamos que el setter lanza una excepción para un puntaje inválido
        assertThrows(IllegalArgumentException.class, () -> {
            resenia.setPuntaje(0);  // Puntaje menor que 1
        });
        assertThrows(IllegalArgumentException.class, () -> {
            resenia.setPuntaje(6);  // Puntaje mayor que 5
        });
    }
}