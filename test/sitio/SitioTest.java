package sitio;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import reserva.Reserva;
import reserva.estadoReserva.EstadoCancelada;
import reserva.estadoReserva.EstadoConsolidada;
import reserva.estadoReserva.EstadoPendienteDeAprobacion;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.inquilino.Inquilino;
import user.propietario.Propietario;


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
        when(reservaCanceladaMock.getEstadoReserva()).thenReturn(new EstadoCancelada(100.00));

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
      
        when(reservaConsolidadaMock.estaOcupada()).thenReturn(true);
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
        
        assertEquals(3, inmueblesLibres.size());
    }
    
    @Test
    void testPublicacionConSoloReservasCanceladas() {
        when(reservaPendienteMock.getEstadoReserva()).thenReturn(new EstadoCancelada(100.00)); // Configuramos la reserva para que esté cancelada
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
        when(reservaConsolidadaMock.estaOcupada()).thenReturn(true);
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaConsolidadaMock)); // Ocupado
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaCanceladaMock));   // Libre
        when(publicacionMock3.getReservas()).thenReturn(Arrays.asList(reservaPendienteMock));   // Libre

        double tasaOcupacion = sitio.tasaDeOcupacion();

        // Verifica la tasa de ocupación (1 de 3 está ocupado, debe ser aproximadamente 33.33%)
        assertEquals(33.33, tasaOcupacion, 0.1);
    }
    
    @Test
    void testObtenerReservasFuturas() {
        // Configuración del test (instancia real de Sitio)
        Sitio sitio = new Sitio(); // Usamos una instancia real de Sitio
        Inquilino inquilinoMock = mock(Inquilino.class);
        
        // Creamos las publicaciones y sus reservas mockeadas
        Publicacion publicacionMock1 = mock(Publicacion.class);
        Publicacion publicacionMock2 = mock(Publicacion.class);
        
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);
        
        // Definir fechas futuras para las reservas
        LocalDate fechaFutura1 = LocalDate.now().plusDays(1);
        LocalDate fechaFutura2 = LocalDate.now().plusDays(2);
        
        // Configurar las fechas de inicio de las reservas mockeadas
        when(reservaMock1.getFechaInicio()).thenReturn(fechaFutura1);
        when(reservaMock1.esDespuesDe(fechaFutura1)).thenReturn(true);
        when(reservaMock2.getFechaInicio()).thenReturn(fechaFutura2);
        when(reservaMock2.esDespuesDe(fechaFutura2)).thenReturn(true);
        
        // Configurar el inquilino de las reservas
        when(reservaMock1.getInquilino()).thenReturn(inquilinoMock); // Asignar el inquilino a la primera reserva
        when(reservaMock1.esInquilino(inquilinoMock)).thenReturn(true);
        when(reservaMock2.getInquilino()).thenReturn(inquilinoMock); // Asignar el inquilino a la segunda reserva
        when(reservaMock2.esInquilino(inquilinoMock)).thenReturn(true);
        
    
        // Configurar las publicaciones para devolver las reservas correspondientes
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaMock1));
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaMock2));
        
        // Añadir publicaciones al sitio
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);
        
        // Crear la lista de reservas futuras que se espera obtener
        List<Reserva> reservasFuturas = Arrays.asList(reservaMock1,reservaMock2);
        
        // Acción: Llamamos al método que estamos probando
        List<Reserva> resultado = sitio.obtenerTodasLasReservasFuturas(inquilinoMock);

        // Verificación: esperamos que el resultado contenga ambas reservas futuras
        assertEquals(reservasFuturas, resultado);
    }
    
    @Test
    void testObtenerReservasDeInquilinoEnCiudad() {
        // Configuración de los mocks
        Inquilino inquilinoMock = mock(Inquilino.class);
        //String ciudad = "Buenos Aires";
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);
        Publicacion publicacionMock = mock(Publicacion.class);

        // Mock de Ubicacion
        Ubicacion ubicacionMock = mock(Ubicacion.class);
        when(ubicacionMock.getCiudad()).thenReturn("Buenos Aires"); // Simulamos que la ciudad es Buenos Aires

        // Configurar las reservas para devolver el inquilinoMock
        when(reservaMock1.getInquilino()).thenReturn(inquilinoMock);
        when(reservaMock1.esInquilino(inquilinoMock)).thenReturn(true);
        when(reservaMock2.getInquilino()).thenReturn(inquilinoMock);
        when(reservaMock2.esInquilino(inquilinoMock)).thenReturn(true);

        // Configurar el mock de Publicacion para que devuelva el mock de Ubicacion
        when(publicacionMock.getUbicacion()).thenReturn(ubicacionMock); // Aseguramos que getUbicacion devuelve ubicacionMock
        when(publicacionMock.esDeCiudad("Buenos Aires")).thenReturn(true);
        when(publicacionMock.getReservas()).thenReturn(Arrays.asList(reservaMock1, reservaMock2)); // Reservas mockeadas

        // Añadir la publicación al Sitio
        sitio.addPublicacion(publicacionMock); // Aquí agregamos la publicación al Sitio

        // Acción: llamar al método que estamos probando
        List<Reserva> resultado = sitio.obtenerReservasDeInquilinoEnCiudad("Buenos Aires", inquilinoMock);

        // Verificación: esperamos que el resultado contenga ambas reservas
        assertEquals(2, resultado.size()); // Verificamos que la lista de reservas tenga dos elementos
        assertTrue(resultado.contains(reservaMock1)); // Verificamos que contenga reservaMock1
        assertTrue(resultado.contains(reservaMock2)); // Verificamos que contenga reservaMock2

    }
    
 
    @Test
    void testObtenerCiudadesDondeInquilinoTieneReserva() {
        Inquilino inquilinoMock = mock(Inquilino.class);

        // Crear publicaciones mockeadas
        Publicacion publicacionMock1 = mock(Publicacion.class);
        Publicacion publicacionMock2 = mock(Publicacion.class);
       
        when(publicacionMock1.getCiudad()).thenReturn("Córdoba");
        when(publicacionMock2.getCiudad()).thenReturn("Buenos Aires");
        // Crear reservas mockeadas
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);

        // Crear ubicaciones mockeadas
        Ubicacion ubicacionMock1 = mock(Ubicacion.class);
        Ubicacion ubicacionMock2 = mock(Ubicacion.class);

        // Configurar las ubicaciones para devolver ciudades
       when(ubicacionMock1.getCiudad()).thenReturn("Buenos Aires");
        when(ubicacionMock1.esDeCiudad("Buenos Aires")).thenReturn(true);
        when(ubicacionMock2.getCiudad()).thenReturn("Córdoba");
        when(ubicacionMock2.esDeCiudad("Córdoba")).thenReturn(true);

        // Configurar las reservas para devolver el inquilino y las ubicaciones correspondientes
     //   when(reservaMock1.getInquilino()).thenReturn(inquilinoMock);
        when(reservaMock1.esInquilino(inquilinoMock)).thenReturn(true);
     //   when(reservaMock2.getInquilino()).thenReturn(inquilinoMock);
        when(reservaMock2.esInquilino(inquilinoMock)).thenReturn(true);
        
        // Configurar las publicaciones para devolver las reservas y las ubicaciones
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaMock1));
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaMock2));
        when(publicacionMock1.getUbicacion()).thenReturn(ubicacionMock1);
        when(publicacionMock2.getUbicacion()).thenReturn(ubicacionMock2);
      
   

        // Añadir publicaciones al sitio
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);

        // Lista de ciudades que esperamos como resultado
        List<String> ciudadesConReserva = Arrays.asList("Córdoba","Buenos Aires");

        // Acción: Llamamos al método que estamos probando
        List<String> resultado = sitio.obtenerCiudadesDondeInquilinoTieneReserva(inquilinoMock);

        // Verificación: esperamos que el resultado contenga las ciudades correctas
        assertEquals(ciudadesConReserva, resultado);
    }
    
    @Test
    void testObtenerReservas() {
        Inquilino inquilinoMock = mock(Inquilino.class);
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);

        // Configuración de las reservas y el inquilino mockeado
        when(reservaMock1.getInquilino()).thenReturn(inquilinoMock);
        when(reservaMock1.esInquilino(inquilinoMock)).thenReturn(true);
        when(reservaMock2.getInquilino()).thenReturn(inquilinoMock);
        when(reservaMock2.esInquilino(inquilinoMock)).thenReturn(true);

        // Añadir reservas a las publicaciones
        Publicacion publicacionMock1 = mock(Publicacion.class);
        Publicacion publicacionMock2 = mock(Publicacion.class);
        
        when(publicacionMock1.getReservas()).thenReturn(Arrays.asList(reservaMock1));
        when(publicacionMock2.getReservas()).thenReturn(Arrays.asList(reservaMock2));

        // Añadir publicaciones al sitio
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);

        // Lista de reservas esperadas
        List<Reserva> reservas = Arrays.asList(reservaMock1, reservaMock2);

        // Acción: Llamamos al método que estamos probando
        List<Reserva> resultado = sitio.obtenerTodasLasReservasDe(inquilinoMock);

        // Verificación: esperamos que el resultado contenga ambas reservas
        assertEquals(reservas, resultado);
    }

    @Test
    void testCantidadDeVecesQueAlquiloInmueble() {
        // Configuración del mock de Publicacion
        Publicacion publicacionMock = mock(Publicacion.class);
        
        // Configuración del comportamiento esperado: supongamos que la publicación fue alquilada 5 veces
        when(publicacionMock.getVecesAlquilado()).thenReturn(5);
        
        // Acción: llamamos al método que estamos probando
        int vecesAlquilado = sitio.cantidadDeVecesQueAlquiloInmueble(publicacionMock);

        // Verificación: esperamos que el número de veces alquilado sea 5
        assertEquals(5, vecesAlquilado);
        
        // Verificar que se haya llamado al método getVecesAlquilado en el mock
        verify(publicacionMock).getVecesAlquilado();  
    }
    
    @Test
    void testCantidadDeVecesQueAlquiloInmuebles() {
        // Configuración de los mocks
        Propietario propietarioMock = mock(Propietario.class);
        Publicacion publicacionMock1 = mock(Publicacion.class);
        Publicacion publicacionMock2 = mock(Publicacion.class);
        Publicacion publicacionMock3 = mock(Publicacion.class); // Agregado el mock de publicacionMock3

        // Configuramos el comportamiento de las publicaciones
        when(publicacionMock1.getPropietario()).thenReturn(propietarioMock);
        when(publicacionMock1.esPropietario(propietarioMock)).thenReturn(true);
        when(publicacionMock2.getPropietario()).thenReturn(propietarioMock);
        when(publicacionMock2.esPropietario(propietarioMock)).thenReturn(true);
        when(publicacionMock3.getPropietario()).thenReturn(propietarioMock); // Mockeamos getPropietario para publicacionMock3
        when(publicacionMock3.esPropietario(propietarioMock)).thenReturn(true);
        
        when(publicacionMock1.getVecesAlquilado()).thenReturn(3);  // Esta publicación fue alquilada 3 veces
        when(publicacionMock2.getVecesAlquilado()).thenReturn(2);  // Esta publicación fue alquilada 2 veces
        when(publicacionMock3.getVecesAlquilado()).thenReturn(1);  // Esta publicación fue alquilada 1 vez (agregado un valor para publicacionMock3)

        // Creamos una lista de publicaciones
        List<Publicacion> publicaciones = Arrays.asList(publicacionMock1, publicacionMock2, publicacionMock3);

        // Agregamos las publicaciones
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);
        sitio.addPublicacion(publicacionMock3);  // Asegúrate de que publicacionMock3 está incluida correctamente

        // Acción: llamar al método que estamos probando
        int vecesAlquilado = sitio.cantidadDeVecesQueAlquiloInmuebles(propietarioMock);

        // Verificación: esperamos que el resultado sea la suma de las veces alquiladas (3 + 2 + 1 = 6)
        assertEquals(6, vecesAlquilado);

        // Verificar que se haya llamado a getPropietario() y getVecesAlquilado() en las publicaciones
       // verify(publicacionMock1).getPropietario();
        verify(publicacionMock1).esPropietario(propietarioMock);
        verify(publicacionMock1).getVecesAlquilado();
        //verify(publicacionMock2).getPropietario();
        verify(publicacionMock2).esPropietario(propietarioMock);
        verify(publicacionMock2).getVecesAlquilado();
        //verify(publicacionMock3).getPropietario();
        verify(publicacionMock3).esPropietario(propietarioMock);
        verify(publicacionMock3).getVecesAlquilado();
    }

    @Test
    void testInmueblesAlquilados() {
        // Configuración de los mocks
        Propietario propietarioMock = mock(Propietario.class);
        Publicacion publicacionMock1 = mock(Publicacion.class);
        Publicacion publicacionMock2 = mock(Publicacion.class);
        Publicacion publicacionMock3 = mock(Publicacion.class); // Publicación adicional para la prueba

        // Configuramos el comportamiento de las publicaciones
        when(publicacionMock1.getPropietario()).thenReturn(propietarioMock);
        when(publicacionMock1.esPropietario(propietarioMock)).thenReturn(true);
        when(publicacionMock2.getPropietario()).thenReturn(propietarioMock);
        when(publicacionMock2.esPropietario(propietarioMock)).thenReturn(true);
        when(publicacionMock3.getPropietario()).thenReturn(propietarioMock); // Aseguramos que el propietario es el mismo
        when(publicacionMock3.esPropietario(propietarioMock)).thenReturn(true);
        
        when(publicacionMock1.getVecesAlquilado()).thenReturn(3);  // Publicación alquilada 3 veces
        when(publicacionMock2.getVecesAlquilado()).thenReturn(0);  // Publicación no alquilada
        when(publicacionMock3.getVecesAlquilado()).thenReturn(1);  // Publicación alquilada 1 vez

        // Creamos una lista de publicaciones y las agregamos al Sitio
        List<Publicacion> publicaciones = Arrays.asList(publicacionMock1, publicacionMock2, publicacionMock3);

        // Agregamos las publicaciones al sitio
        sitio.addPublicacion(publicacionMock1);
        sitio.addPublicacion(publicacionMock2);
        sitio.addPublicacion(publicacionMock3);

        // Acción: llamar al método que estamos probando
        List<Publicacion> resultado = sitio.inmueblesAlquilados(propietarioMock);

        // Verificación: esperamos que el resultado contenga solo las publicaciones alquiladas (publicacionMock1 y publicacionMock3)
        assertEquals(2, resultado.size());  // Solo publicacionMock1 y publicacionMock3 deben ser incluidas
        assertTrue(resultado.contains(publicacionMock1));  // Debe contener publicacionMock1
        assertTrue(resultado.contains(publicacionMock3));  // Debe contener publicacionMock3
        assertFalse(resultado.contains(publicacionMock2)); // No debe contener publicacionMock2

        // Verificar que se haya llamado a getPropietario() y getVecesAlquilado() en las publicaciones
       // verify(publicacionMock1).getPropietario();
        verify(publicacionMock1).esPropietario(propietarioMock);
        verify(publicacionMock1).getVecesAlquilado();
       // verify(publicacionMock2).getPropietario();
        verify(publicacionMock2).esPropietario(propietarioMock);
        verify(publicacionMock2).getVecesAlquilado();
        //verify(publicacionMock3).getPropietario();
        verify(publicacionMock3).esPropietario(propietarioMock);
        verify(publicacionMock3).getVecesAlquilado();
    }

}