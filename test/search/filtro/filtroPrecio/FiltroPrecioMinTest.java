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

public class FiltroPrecioMinTest {

    private FiltroPrecioMin filtroPrecioMin;
    private Publicacion publicacion1;
    private Publicacion publicacion2;
    private Publicacion publicacion3;
    private FiltroBase filtroBase;


    @BeforeEach
    public void setUp() {
        // Inicializamos el filtro con un precio mínimo de 400
        filtroPrecioMin = new FiltroPrecioMin(400.0);
        filtroBase = new FiltroBase("Cordoba", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 10));


        // Crear mocks de publicaciones
        publicacion1 = mock(Publicacion.class);
        publicacion2 = mock(Publicacion.class);
        publicacion3 = mock(Publicacion.class);

        // Configuramos los precios entre las fechas de entrada y salida
        when(publicacion1.precioEntreFechas(any(), any())).thenReturn(500.0); // Precio dentro del rango (mayor que 400)
        when(publicacion2.precioEntreFechas(any(), any())).thenReturn(300.0); // Precio fuera del rango (menor que 400)
        when(publicacion3.precioEntreFechas(any(), any())).thenReturn(400.0); // Precio igual al mínimo
    }

    @Test
    public void testFilterPublicaciones_SoloPublicacionesValidas() {
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);

        // Aplicamos el filtro
        List<Publicacion> resultado = filtroPrecioMin.filterPublicaciones(publicaciones, filtroBase);

        // Verificamos que solo las publicaciones con precio >= 400 sean incluidas
        assertEquals(2, resultado.size()); // Esperamos que solo queden publicacion1 y publicacion3
        assertTrue(resultado.contains(publicacion1)); // Capacidad 500, mayor que 400
        assertTrue(resultado.contains(publicacion3)); // Capacidad 400, igual al mínimo
        assertFalse(resultado.contains(publicacion2)); // Capacidad 300, menor que 400
    }
}
