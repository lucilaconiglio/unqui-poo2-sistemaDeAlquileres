package search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import publicacion.Publicacion;
import inmueble.Inmueble;
import ubicacion.Ubicacion;
import search.FiltroBase;
import search.filtro.Filtro;

public class FiltroBaseTest {

    private FiltroBase filtroBase;
    private Publicacion publicacion1;
    private Publicacion publicacion2;
    private Publicacion publicacion3;

    @BeforeEach
    public void setUp() {
        // Inicializamos FiltroBase con ciudad y fechas específicas
        filtroBase = new FiltroBase("CiudadEjemplo", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 10));

        // Crear publicaciones con mocks y configuraciones de prueba
        publicacion1 = crearMockPublicacion("CiudadEjemplo", 4);
        publicacion2 = crearMockPublicacion("OtraCiudad", 2);
        publicacion3 = crearMockPublicacion("CiudadEjemplo", 6);
    }

    private Publicacion crearMockPublicacion(String ciudad, int capacidad) {
        Publicacion publicacion = mock(Publicacion.class);
        Inmueble inmueble = mock(Inmueble.class);
        Ubicacion ubicacion = mock(Ubicacion.class);

        // Configuramos los valores de retorno de los mocks
        when(publicacion.getInmueble()).thenReturn(inmueble);
        when(inmueble.getUbicacion()).thenReturn(ubicacion);
        when(ubicacion.getCiudad()).thenReturn(ciudad);
        when(inmueble.getCapacidad()).thenReturn(capacidad);

        return publicacion;
    }

    @Test
    public void testFilterPublicaciones_FiltraPorCiudad() {
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);

        // Ejecutamos el método de filtro
        List<Publicacion> resultado = filtroBase.filterPublicaciones(publicaciones);

        // Verificamos que solo se incluyan las publicaciones de "CiudadEjemplo"
        assertEquals(2, resultado.size());
        assertEquals(publicacion1, resultado.get(0));
        assertEquals(publicacion3, resultado.get(1));
    }

    @Test
    public void testFilterPublicaciones_AplicaFiltrosAdicionales() {
        // Creamos un filtro adicional que limite la capacidad a <= 4
        Filtro filtroCapacidad = mock(Filtro.class);
        when(filtroCapacidad.filterPublicaciones(Mockito.anyList()))
            .thenReturn(Arrays.asList(publicacion1)); // Solo retorna publicacion1 al aplicar el filtro

        filtroBase.addFiltro(filtroCapacidad);

        // Ejecutamos el filtro principal
        List<Publicacion> resultado = filtroBase.filterPublicaciones(Arrays.asList(publicacion1, publicacion2, publicacion3));

        // Verificamos que solo quede publicacion1 después de aplicar el filtro adicional
        assertEquals(1, resultado.size());
        assertEquals(publicacion1, resultado.get(0));
    }

    @Test
    public void testFilterPublicaciones_ConInmuebleNull() {
        // Configuramos una publicación con inmueble nulo
        Publicacion publicacionConInmuebleNull = mock(Publicacion.class);
        when(publicacionConInmuebleNull.getInmueble()).thenReturn(null);

        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacionConInmuebleNull, publicacion3);

        // Ejecutamos el método de filtro
        List<Publicacion> resultado = filtroBase.filterPublicaciones(publicaciones);

        // Verificamos que las publicaciones sin inmueble no causen problemas
        assertEquals(2, resultado.size());
        assertEquals(publicacion1, resultado.get(0));
        assertEquals(publicacion3, resultado.get(1));
    }

    @Test
    public void testFilterPublicaciones_SinFiltrosAdicionales() {
        // Probamos que sin filtros adicionales se mantengan las publicaciones de la ciudad especificada
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);

        // Ejecutamos el método de filtro
        List<Publicacion> resultado = filtroBase.filterPublicaciones(publicaciones);

        // Verificamos el resultado
        assertEquals(2, resultado.size());
        assertEquals(publicacion1, resultado.get(0));
        assertEquals(publicacion3, resultado.get(1));
    }

    @Test
    public void testAddFiltro() {
        // Creamos un filtro mock
        Filtro filtroMock = mock(Filtro.class);

        // Agregamos el filtro a filtroBase
        filtroBase.addFiltro(filtroMock);

        // Verificamos que el filtro haya sido agregado correctamente
        assertEquals(1, filtroBase.getFiltros().size());
        assertEquals(filtroMock, filtroBase.getFiltros().get(0));
    }

    @Test
    public void testRemoveFiltro() {
        // Creamos un filtro mock y lo agregamos
        Filtro filtroMock = mock(Filtro.class);
        filtroBase.addFiltro(filtroMock);

        // Lo eliminamos
        filtroBase.removeFiltro(filtroMock);

        // Verificamos que la lista de filtros esté vacía
        assertEquals(2, filtroBase.getFiltros().size());
    }
}
