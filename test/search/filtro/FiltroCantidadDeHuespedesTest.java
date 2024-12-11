package search.filtro;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import publicacion.Publicacion;
import search.FiltroBase;

public class FiltroCantidadDeHuespedesTest {

    private FiltroCantidadDeHuespedes filtro;
    private Publicacion publicacion1;
    private Publicacion publicacion2;
    private Publicacion publicacion3;
    private FiltroBase filtroBase;


    @BeforeEach
    public void setUp() {
        filtroBase = new FiltroBase("Cordoba", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 10));
        filtro = new FiltroCantidadDeHuespedes(4);

        // Crear publicaciones con mocks
        publicacion1 = crearMockPublicacion(3); // Capacidad 3
        publicacion2 = crearMockPublicacion(4); // Capacidad 4
        publicacion3 = crearMockPublicacion(5); // Capacidad 5
    }

    private Publicacion crearMockPublicacion(int capacidad) {
        Publicacion publicacion = mock(Publicacion.class);
        when(publicacion.getCapacidad()).thenReturn(capacidad);
        return publicacion;
    }

    @Test
    public void testFilterPublicaciones_CapacidadMenorOIgualA4() {
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);

        // Ejecutamos el filtro
        List<Publicacion> resultado = filtro.filterPublicaciones(publicaciones, filtroBase);

        // Verificamos que solo las publicaciones con capacidad <= 4 sean incluidas
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(publicacion1)); // Capacidad 3
        assertTrue(resultado.contains(publicacion2)); // Capacidad 4
        assertFalse(resultado.contains(publicacion3)); // Capacidad 5
    }

    @Test
    public void testFilterPublicaciones_SoloPublicacionesValidas() {
        // Cambiamos el filtro para que solo permita hasta 2 personas
        filtro = new FiltroCantidadDeHuespedes(2);
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);

        // Ejecutamos el filtro
        List<Publicacion> resultado = filtro.filterPublicaciones(publicaciones, filtroBase);

        // Verificamos que ninguna publicacion sea incluida
        assertEquals(0, resultado.size());
    }
}
