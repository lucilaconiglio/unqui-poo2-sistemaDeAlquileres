package search.filtro.filtroPrecio;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Publicacion;
import search.FiltroBase;

public class FiltroPrecioMaxTest {

    private FiltroPrecioMax filtroPrecioMax;
    private Publicacion publicacion1;
    private Publicacion publicacion2;
    private Publicacion publicacion3;
    private FiltroBase filtroBase;


    @BeforeEach
    public void setUp() {
        // Inicializamos el filtro con un precio máximo de 500
        filtroPrecioMax = new FiltroPrecioMax(500.0);
        filtroBase = new FiltroBase("Cordoba", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 10));


        // Crear mocks de publicaciones
        publicacion1 = mock(Publicacion.class);
        publicacion2 = mock(Publicacion.class);
        publicacion3 = mock(Publicacion.class);

        // Configuramos los precios entre las fechas de entrada y salida
        when(publicacion1.precioEntreFechas(any(), any())).thenReturn(300.0); // Precio dentro del rango
        when(publicacion2.precioEntreFechas(any(), any())).thenReturn(600.0); // Precio fuera del rango
        when(publicacion3.precioEntreFechas(any(), any())).thenReturn(450.0); // Precio dentro del rango
    }

    @Test
    public void testFilterPublicaciones_SoloPublicacionesValidas() {
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);

        // Aplicamos el filtro
        List<Publicacion> resultado = filtroPrecioMax.filterPublicaciones(publicaciones, filtroBase);

        // Verificamos que solo las publicaciones con precio <= 500 sean incluidas
        assertEquals(2, resultado.size()); // Esperamos que solo queden publicacion1 y publicacion3
        assertTrue(resultado.contains(publicacion1)); // precio 300
        assertTrue(resultado.contains(publicacion3)); // precio 450
        assertFalse(resultado.contains(publicacion2)); // Capacidad 600, fuera del rango
    }
}
