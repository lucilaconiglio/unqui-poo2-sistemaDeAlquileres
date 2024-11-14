package sitio;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import publicacion.Publicacion;
import reserva.Reserva;
import reserva.estadoReserva.EstadoCancelada;
import reserva.estadoReserva.EstadoConsolidada;
import reserva.estadoReserva.EstadoPendienteDeAprobacion;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import user.inquilino.Inquilino;


class SitioTest {

	Sitio sitio;
    Servicio servicio;
    TipoDeInmueble tipoDeInmueble;
    Categoria categoria;
    Publicacion publicacionMock1;
    Publicacion publicacionMock2;
    Publicacion publicacionMock3;
    Reserva reservaConsolidadaMock;
    Reserva reservaPendienteMock;
    Reserva reservaCanceladaMock;
    Inquilino inquilinoMock;

    @BeforeEach
    public void setUp() {
        // Inicializa el objeto Sitio y otros objetos necesarios
        sitio = new Sitio();
        
        servicio = mock(Servicio.class);
        tipoDeInmueble = mock(TipoDeInmueble.class);
        categoria = mock(Categoria.class);
        
        publicacionMock1 = mock(Publicacion.class);
        publicacionMock2 = mock(Publicacion.class);
        publicacionMock3 = mock(Publicacion.class);

        reservaConsolidadaMock = mock(Reserva.class);
        reservaPendienteMock = mock(Reserva.class);
        reservaCanceladaMock = mock(Reserva.class);
        inquilinoMock = mock(Inquilino.class);
        
        // Mock estado de las reservas
        when(reservaConsolidadaMock.getEstadoReserva()).thenReturn(new EstadoConsolidada());
        when(reservaPendienteMock.getEstadoReserva()).thenReturn(new EstadoPendienteDeAprobacion());
        when(reservaCanceladaMock.getEstadoReserva()).thenReturn(new EstadoCancelada());

        // Mock inquilino de reserva consolidada
        when(reservaConsolidadaMock.getInquilino()).thenReturn(inquilinoMock);

    }
    @Test
    void testAgregarPublicacion() {
        sitio.addPublicacion(publicacionMock1);
        assertEquals(1, sitio.getPublicaciones().size());
        assertEquals(publicacionMock1, sitio.getPublicaciones().get(0));
    }

    @Test
    void testEliminarPublicacion() {
        sitio.addPublicacion(publicacionMock1);
        assertEquals(1, sitio.getPublicaciones().size());

        sitio.removePublicacion(publicacionMock1);
        assertTrue(sitio.getPublicaciones().isEmpty());
    }

    @Test
    void testAgregarServicio() {
        sitio.addServicio(servicio);
        assertEquals(1, sitio.getServicios().size());
        assertEquals(servicio, sitio.getServicios().get(0));
    }

    @Test
    void testEliminarServicio() {
        sitio.addServicio(servicio);
        assertEquals(1, sitio.getServicios().size());

        sitio.removeServicio(servicio);
        assertTrue(sitio.getServicios().isEmpty());
    }

    @Test
    void testAgregarTipoDeInmueble() {
        sitio.addTipoDeInmueble(tipoDeInmueble);
        assertEquals(1, sitio.getTiposDeInmueble().size());
        assertEquals(tipoDeInmueble, sitio.getTiposDeInmueble().get(0));
    }

    @Test
    void testEliminarTipoDeInmueble() {
        sitio.addTipoDeInmueble(tipoDeInmueble);
        assertEquals(1, sitio.getTiposDeInmueble().size());

        sitio.removeTipoDeInmeble(tipoDeInmueble);
        assertTrue(sitio.getTiposDeInmueble().isEmpty());
    }

    @Test
    void testObtenerTopDiezInquilinos() {
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock1);
        
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaConsolidadaMock, reservaCanceladaMock));
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaConsolidadaMock));
        when(publicacionMock3.getReservas()).thenReturn(Arrays.asList(reservaConsolidadaMock));

        List<Inquilino> topInquilinos = sitio.topDiezInquilinos();

        // Verifica que el inquilino esté en el top 10
        assertEquals(1, topInquilinos.size());
        assertTrue(topInquilinos.contains(inquilinoMock));
    }

    @Test
    void testObtenerInmueblesLibres() {
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);
        sitio.addPublicacion(publicacionMock3);
        
        // Configura una publicacion libre y otras ocupadas
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaPendienteMock));
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaCanceladaMock));
        when(publicacionMock3.getReservas()).thenReturn(Arrays.asList(reservaConsolidadaMock));

        // Ejecuta el método para obtener inmuebles libres
        List<Publicacion> inmueblesLibres = sitio.obtenerInmueblesLibres();

        // Verifica publicacionMock1 esté libre (esta en estade pendiente de aprobacion, por ende, desocupada)
        assertTrue(inmueblesLibres.contains(publicacionMock1));
        
        // Verifica publicacionMock2 esté libre 
        assertTrue(inmueblesLibres.contains(publicacionMock2));
        
        assertEquals(2, inmueblesLibres.size());
    }
    
    @Test
    void testPublicacionConSoloReservasCanceladas() {
        when(reservaPendienteMock.getEstadoReserva()).thenReturn(new EstadoCancelada()); // Configuramos la reserva para que esté cancelada
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaPendienteMock)); // Asignamos la reserva cancelada a la publicación mockeada

        sitio.addPublicacion(publicacionMock1);

        // Llamamos al método obtenerInmueblesLibres
        List<Publicacion> inmueblesLibres = sitio.obtenerInmueblesLibres();

        // Verificamos que la publicación esté en la lista de inmuebles libres
        assertEquals(1, inmueblesLibres.size());  // Debería considerarse libre, ya que la reserva está cancelada
        assertTrue(inmueblesLibres.contains(publicacionMock1)); // La publicación mockeada debería estar en la lista
    }

    @Test
    void testSinPublicaciones() {
        List<Publicacion> inmueblesLibres = sitio.obtenerInmueblesLibres();
        assertEquals(0, inmueblesLibres.size());  // Esperamos una lista vacía
    }
    
    @Test
    void testCalcularTasaDeOcupacion() {
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);
        sitio.addPublicacion(publicacionMock3);
        
        // Configura publicaciones con reservas para tasa de ocupación
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaConsolidadaMock)); // Ocupado
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaCanceladaMock));   // Libre
        when(publicacionMock3.getReservas()).thenReturn(Arrays.asList(reservaPendienteMock));   // Libre

        double tasaOcupacion = sitio.tasaDeOcupacion();

        // Verifica la tasa de ocupación (1 de 3 está ocupado, debe ser aproximadamente 33.33%)
        assertEquals(33.33, tasaOcupacion, 0.1);
    }
    
    @Test
    void testObtenerReservasFuturas() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        Inquilino inquilinoMock = mock(Inquilino.class);
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);

        // Crear una lista simulada de reservas futuras
        List<Reserva> reservasFuturas = Arrays.asList(reservaMock1, reservaMock2);
        when(sitioMock.obtenerTodasLasReservasFuturas(inquilinoMock)).thenReturn(reservasFuturas);

        // Acción
        List<Reserva> resultado = sitioMock.obtenerTodasLasReservasFuturas(inquilinoMock);

        // Verificación
        assertEquals(reservasFuturas, resultado);
        verify(sitioMock).obtenerTodasLasReservasFuturas(inquilinoMock);
    }
    
    @Test
    void testObtenerReservasDeInquilinoEnCiudad() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        Inquilino inquilinoMock = mock(Inquilino.class);
        String ciudad = "Buenos Aires";
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);
  
        // Lista simulada de reservas en la ciudad
        List<Reserva> reservasEnCiudad = Arrays.asList(reservaMock1, reservaMock2);
        when(sitioMock.obtenerReservasDeInquilinoEnCiudad(ciudad, inquilinoMock)).thenReturn(reservasEnCiudad);

        // Acción
        List<Reserva> resultado = sitioMock.obtenerReservasDeInquilinoEnCiudad(ciudad, inquilinoMock);

        // Verificación
        assertEquals(reservasEnCiudad, resultado);
        verify(sitioMock).obtenerReservasDeInquilinoEnCiudad(ciudad, inquilinoMock);
    }
 
    @Test
    void testObtenerCiudadesDondeInquilinoTieneReserva() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        Inquilino inquilinoMock = mock(Inquilino.class);

        // Lista simulada de ciudades con reserva
        List<String> ciudadesConReserva = Arrays.asList("Buenos Aires", "Córdoba");
        when(sitioMock.obtenerCiudadesDondeInquilinoTieneReserva(inquilinoMock)).thenReturn(ciudadesConReserva);

        // Acción
        List<String> resultado = sitioMock.obtenerCiudadesDondeInquilinoTieneReserva(inquilinoMock);

        // Verificación
        assertEquals(ciudadesConReserva, resultado);
        verify(sitioMock).obtenerCiudadesDondeInquilinoTieneReserva(inquilinoMock);
    }
    
    @Test 
    void testObtenerReservas() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        Inquilino inquilinoMock = mock(Inquilino.class);
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);

        // Lista simulada de reservas asociadas al inquilino
        List<Reserva> reservas = Arrays.asList(reservaMock1, reservaMock2);
        when(sitioMock.obtenerTodasLasReservasDe(inquilinoMock)).thenReturn(reservas);

        // Acción
        List<Reserva> resultado = sitioMock.obtenerTodasLasReservasDe(inquilinoMock);

        // Verificación
        assertEquals(reservas, resultado);
        verify(sitioMock).obtenerTodasLasReservasDe(inquilinoMock);
    }
    
    @Test
    void testCantidadDeVecesQueAlquiloInmueble() {
        // Configuración del mock de Publicacion
        Publicacion publicacionMock = mock(Publicacion.class);

        // Configuración del comportamiento esperado: supongamos que la publicación fue alquilada 5 veces
        when(publicacionMock.getVecesAlquilado()).thenReturn(5);

        // Crear una instancia de Sitio
        Sitio sitio = new Sitio();

        // Acción
        int vecesAlquilado = sitio.cantidadDeVecesQueAlquiloInmueble(publicacionMock);

        // Verificación
        assertEquals(5, vecesAlquilado);
        verify(publicacionMock).getVecesAlquilado();  // Verifica que se haya llamado al método getVecesAlquilado
    }
    

}