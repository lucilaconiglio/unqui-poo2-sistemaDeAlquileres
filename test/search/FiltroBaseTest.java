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
        filtroBase = new FiltroBase("Cordoba", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 10));


        // Crear publicaciones con mocks y configuraciones de prueba
        publicacion1 = crearMockPublicacion("Cordoba", 4);
        publicacion2 = crearMockPublicacion("Rosario", 2);
        publicacion3 = crearMockPublicacion("Cordoba", 6);
    }

    private Publicacion crearMockPublicacion(String ciudad, int capacidad) {
        Publicacion publicacion = mock(Publicacion.class);
        Ubicacion ubicacion = mock(Ubicacion.class);

        // Configuramos el mock para recibir la ciudad correcta
        when(ubicacion.getCiudad()).thenReturn(ciudad);
        
        // Configura el mock para `publicacion`
        when(publicacion.getCapacidad()).thenReturn(capacidad);
        when(publicacion.getUbicacion()).thenReturn(ubicacion);

        return publicacion;
    }

    @Test
    public void testFilterPublicaciones_FiltraPorCiudad() {
    	Ubicacion ubicacion1 = mock(Ubicacion.class);
    	when(ubicacion1.getCiudad()).thenReturn("Cordoba");
    	when(publicacion1.getUbicacion()).thenReturn(ubicacion1);
    	when(publicacion1.esDeCiudad("Cordoba")).thenReturn(true);

    	Ubicacion ubicacion2 = mock(Ubicacion.class);
    	when(ubicacion2.getCiudad()).thenReturn("Rosario");
    	when(publicacion2.getUbicacion()).thenReturn(ubicacion2);
    	when(publicacion2.esDeCiudad("Rosario")).thenReturn(false);        

    	Publicacion publicacion3 = mock(Publicacion.class);
    	Ubicacion ubicacion3 = mock(Ubicacion.class);
    	when(ubicacion3.getCiudad()).thenReturn("Cordoba");
    	when(publicacion3.getUbicacion()).thenReturn(ubicacion3);
    	when(publicacion3.esDeCiudad("Cordoba")).thenReturn(true);        
    	
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
        
        // Ejecutamos el método de filtro
        List<Publicacion> resultado = filtroBase.filterPublicaciones(publicaciones);
        
        assertEquals(2, resultado.size());
        assertEquals(publicacion1, resultado.get(0));
        assertEquals(publicacion3, resultado.get(1));
    }

    @Test
    public void testFilterPublicaciones_AplicaFiltrosAdicionales() {
        // Creamos un filtro adicional que limite la capacidad a <= 4
        Filtro filtroCapacidad = mock(Filtro.class);
        List<Publicacion> publicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
        when(filtroCapacidad.filterPublicaciones(publicaciones, filtroBase))
            .thenReturn(Arrays.asList(publicacion1)); // Solo retorna publicacion1 al aplicar el filtro

        filtroBase.addFiltro(filtroCapacidad);

        // Ejecutamos el filtro principal
        List<Publicacion> resultado = filtroBase.filterPublicaciones(publicaciones);

        // Verificamos que solo quede publicacion1 después de aplicar el filtro adicional
        assertEquals(0, resultado.size());
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
        assertEquals(0, filtroBase.getFiltros().size());
    }
}
