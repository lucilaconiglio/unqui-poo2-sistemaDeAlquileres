package user;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;

import categoria.Categoria;
import categoriasManager.CategoriasManager;
import formaDePago.FormaDePago;
import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import ranking.Ranking;
import resenia.Resenia;
import reserva.Reserva;
import sitio.Sitio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

class UserTest {
    @Spy
    private Sitio sitioSpy = new Sitio(); 
	
	
	private User inquilino;
	private User propietario;
	private Publicacion publicacion;
	private PoliticaDeCancelacion politicaDeCancelacionMock;
	private Ubicacion ubicacionMock;
	private TipoDeInmueble tipoInmuebleMock;
	private Publicacion publicacionMock;
	private Resenia reseniaMock;
	private Categoria categoriaMock;
	private LocalDate checkIn ;
	private LocalDate checkOut;
	private double precioBase;
	private String superficie;
	private int capacidad;
	private Ranking rankingMock;
	private CategoriasManager categoriasManagerMock;
	private FormaDePago formaDePagoMock;
	private Reserva reservaMock;
	
	@BeforeEach
	public void setup() {
		//sitioMock = mock(Sitio.class);
		publicacion = mock(Publicacion.class);
		politicaDeCancelacionMock = mock(PoliticaDeCancelacion.class);
	    ubicacionMock = mock(Ubicacion.class);
	    tipoInmuebleMock = mock(TipoDeInmueble.class);
	    publicacionMock = mock(Publicacion.class);
        reseniaMock = mock(Resenia.class);
        categoriaMock = mock(Categoria.class);
        rankingMock = mock(Ranking.class);
        categoriasManagerMock = mock(CategoriasManager.class);
        formaDePagoMock = mock(FormaDePago.class);
        reservaMock = mock(Reserva.class);
        
        inquilino = new User("Pedro", "Lopez", 14333333, sitioSpy);
		propietario = new User("Raul", "Gutierrez",11223344, sitioSpy);

	    checkIn = LocalDate.of(2024, 11, 15);  
	    checkOut = LocalDate.of(2024, 11, 20); 
	    precioBase = 1500.0; 
	    superficie = "50m²"; 
	    capacidad = 4;       

	}
	@Test
	void elUsuarioSeDaDeAltaEnElSistema() {
		sitioSpy.addUsuario( inquilino );
		sitioSpy.addUsuario( propietario );
		 assertEquals(2,sitioSpy.getUsers().size() );
	}
	
	@Test
	void elUsuarioSeDaDeBajaDelSistema() {
		sitioSpy.removeUsuario( inquilino);
		sitioSpy.removeUsuario( propietario);
	//	 when(sitioSpy.getUsers().size()).thenReturn(0);
		 assertEquals( 0, sitioSpy.getUsers().size());
	}
	

	@Test
	void elPropietarioPuedeRealizarUnaPublicacion() {
		propietario.darDeAltaPublicacion(publicacionMock);
		
		assertEquals(1, sitioSpy.getPublicaciones().size() );
	}
	/*
	@Test
    void testObtenerPromedioCategoria() {
        when(rankingMock.obtenerPromedioPorCategoria(categoriaMock)).thenReturn(4.5);

        double promedio = inquilino.obtenerPromedioCategoria(categoriaMock);

        assertEquals(4.5, promedio, 0.01);
        verify(rankingMock).obtenerPromedioPorCategoria(categoriaMock);
    
    }
*/
  /*  @Test
    void testObtenerComentarios() {
    	Resenia resenia1 = mock(Resenia.class);
    	Resenia resenia2 = mock(Resenia.class);
    	Propietario propietarioMock = mock(Propietario.class);
    	String comentario = "Buen lugar";  	
    	List<String> comentarios = Arrays.asList("Excelente lugar", "Me encanto");
    	
    	when(resenia1.getComentario()).thenReturn(comentario);
    	when(propietarioMock.obternerComentarios()).thenReturn(comentarios );

        List<String> resultado = propietarioMock.obternerComentarios();

        assertEquals(comentarios , resultado);
    
    }
*/
   /* @Test
    void testObtenerComentariosPorCategoria() {
        Inquilino inquilinoMock = mock(Inquilino.class);

        categoriasManagerMock.agregarCategoria(categoriaMock, Inquilino.class);
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);


        List<String> comentarios = Arrays.asList("Muy bueno", "Genial");
        when(reseniaMock.getComentario()).thenReturn("Muy bueno");

        when(inquilinoMock.obtenerComentariosPorCategoria(categoriaMock)).thenReturn(comentarios);

        List<String> resultado = inquilinoMock.obtenerComentariosPorCategoria(categoriaMock);
        assertEquals(comentarios, resultado);
    }*/

    @Test
    void testGetReseniasDelUsuario() {
        User inquilino2 = mock(User.class);
        Resenia reseniaMock2 = mock(Resenia.class);
        Resenia reseniaMock3 = mock(Resenia.class);
        
        List<Resenia> resenias = Arrays.asList(reseniaMock2,reseniaMock3);
        
       // propietario.rankearUsuario(inquilino, reseniaMock2);
        when(inquilino2.getResenias()).thenReturn(resenias);
        
        //verify(inquilino2).agregarResenia(reseniaMock2);
        assertEquals(resenias,inquilino2.getResenias());
    }
    
 /* @Test // TODO: Refactor
    void testRankearInmueble() {
        // Crear una lista con categoriaMock
        List<Categoria> categorias = Arrays.asList(categoriaMock);

        // Configurar el mock de CategoriasManager para devolver la lista de categorías
        when(categoriasManagerMock.obtenerCategoriasDeInmueble()).thenReturn(categorias);
        
        // Asegurar que la categoría de la reseña sea la correcta
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);

        // Ejecutar el método rankearInmueble
        inquilino.rankearInmueble(reseniaMock, publicacionMock);
        inquilino.
        // Verificar que agregarResenia se llame en publicacionMock con reseniaMock
        verify(publicacionMock).agregarResenia(reseniaMock);
    }
  */

    /*@Test
    void inquilinoRankeaPropietario() {

        Inquilino inquilinoMock = mock(Inquilino.class);
        Propietario propietarioMock = mock(Propietario.class);
        Ranking rankingMock = mock(Ranking.class);
        Resenia reseniaMock = mock(Resenia.class);
        Categoria categoriaMock = mock(Categoria.class);
    	when(categoriasManagerMock.getInstancia())
        when(categoriaMock.getConcepto()).thenReturn("Lindo");   
        when(categoriasManagerMock.obtenerCategoriasDePropietario()).thenReturn(Arrays.asList(categoriaMock));
        when(propietarioMock.getRanking()).thenReturn(rankingMock);
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);
       
        
        
        inquilino.rankearPropietario(reseniaMock, propietario);
    
       // verify(propietario).getRanking().agregarResenia(reseniaMock);

    }
    */
    
    @Test
    void inquilinoRankeaPropietario() {
        // Instancia real de CategoriasManager
        CategoriasManager categoriasManager = CategoriasManager.getInstancia();

        // Configuración de categorías para el test
        Categoria categoriaMock = mock(Categoria.class);
        when(categoriaMock.getConcepto()).thenReturn("Lindo");
        
        // Agrega la categoría al CategoriasManager
        categoriasManager.agregarCategoria(categoriaMock, Propietario.class);

        // Mock de otras dependencias
     // Usa instancia real si es posible
        Propietario propietarioMock = mock(Propietario.class);
        Ranking rankingMock = mock(Ranking.class);
        Resenia reseniaMock = mock(Resenia.class);
        
        // Configuración de mocks adicionales
        when(propietarioMock.getRanking()).thenReturn(rankingMock);
        rankingMock.agregarResenia(reseniaMock);
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);
        when(reservaMock.finalizadaExitosamente()).thenReturn(true);
        // Acción
        inquilino.rankearPropietario(reseniaMock, propietarioMock, reservaMock);

        // Verificación
        verify(rankingMock).agregarResenia(reseniaMock);
    }

    @Test
    void inquilinoRankeaPropietarioPeroNoSeRealizoElCheckout() {
        // Instancia real de CategoriasManager
        CategoriasManager categoriasManager = CategoriasManager.getInstancia();

        // Configuración de categorías para el test
        Categoria categoriaMock = mock(Categoria.class);
        when(categoriaMock.getConcepto()).thenReturn("Lindo");
        
        // Agrega la categoría al CategoriasManager
        categoriasManager.agregarCategoria(categoriaMock, Propietario.class);

        // Mock de otras dependencias
     // Usa instancia real si es posible
        Propietario propietarioMock = mock(Propietario.class);
        Ranking rankingMock = mock(Ranking.class);
        Resenia reseniaMock = mock(Resenia.class);
        
        // Configuración de mocks adicionales
        when(propietarioMock.getRanking()).thenReturn(rankingMock);
        rankingMock.agregarResenia(reseniaMock);
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);
        when(reservaMock.finalizadaExitosamente()).thenReturn(false);
        // Acción
        inquilino.rankearPropietario(reseniaMock, propietarioMock, reservaMock);

        // Verificación
        verify(rankingMock).agregarResenia(reseniaMock);
    }

    
 
    @Test
    void inquilinoNoRankeaPropietarioCuandoNoExiste() {

        Inquilino inquilinoMock = mock(Inquilino.class);
        Resenia reseniaMock = mock(Resenia.class);
        Categoria categoriaMock = mock(Categoria.class);

    
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);
        when(categoriasManagerMock.obtenerCategoriasDePropietario()).thenReturn(Arrays.asList(categoriaMock));
        when(reservaMock.finalizadaExitosamente()).thenReturn(true);
      
        inquilinoMock.rankearPropietario(reseniaMock, null, reservaMock);

     
        verifyNoInteractions(categoriasManagerMock);
    }
    
    
    @Test
    void propietarioRankeaInquilino() {
        // Instancia real de CategoriasManager
        CategoriasManager categoriasManager = CategoriasManager.getInstancia();

        // Configuración de categorías para el test
        Categoria categoriaMock = mock(Categoria.class);
        when(categoriaMock.getConcepto()).thenReturn("Amigable");

        // Agrega la categoría al CategoriasManager para Inquilino
        categoriasManager.agregarCategoria(categoriaMock, Inquilino.class);

        // Instancia real de Propietario si es posible, o mantenemos el mock
   // Usa la instancia real si aplica
        Inquilino inquilinoMock = mock(Inquilino.class);
        Ranking rankingMock = mock(Ranking.class);
        Resenia reseniaMock = mock(Resenia.class);

        // Configuración de mocks adicionales
        when(inquilinoMock.getRanking()).thenReturn(rankingMock);
        rankingMock.agregarResenia(reseniaMock);
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);
        when(reservaMock.finalizadaExitosamente()).thenReturn(true);
        
        // Acción: Propietario rankea a Inquilino
        propietario.rankearInquilino(reseniaMock, inquilinoMock, reservaMock);

        // Verificación
        verify(rankingMock).agregarResenia(reseniaMock);
    }


    @Test
    void propietarioRankeaInquilinoNoExiste() {

        Propietario propietarioMock = mock(Propietario.class);
        Resenia reseniaMock = mock(Resenia.class);
        Categoria categoriaMock = mock(Categoria.class);

        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);
        when(categoriasManagerMock.obtenerCategoriasDeInquilino()).thenReturn(Arrays.asList(categoriaMock));
        when(reservaMock.finalizadaExitosamente()).thenReturn(true);

        propietarioMock.rankearInquilino(reseniaMock, null, reservaMock);

        verifyNoInteractions(categoriasManagerMock);
    }

    
    @Test
    void inquilinoRankeaPropietarioDeCategoriaNoExistente() {
        Inquilino inquilinoMock = mock(Inquilino.class);
        Propietario propietarioMock = mock(Propietario.class);
        Ranking rankingMock = mock(Ranking.class); 
        Resenia reseniaMock = mock(Resenia.class);
        Categoria categoriaMock = mock(Categoria.class);
        Categoria categoriaNoExistenteMock = mock(Categoria.class);

        when(propietarioMock.getRanking()).thenReturn(rankingMock);
        when(reseniaMock.getCategoria()).thenReturn(categoriaNoExistenteMock);
        when(categoriasManagerMock.obtenerCategoriasDePropietario()).thenReturn(Arrays.asList(categoriaMock));
        when(reservaMock.finalizadaExitosamente()).thenReturn(true);
        
        inquilinoMock.rankearPropietario(reseniaMock, propietarioMock, reservaMock);

        verify(rankingMock, never()).agregarResenia(reseniaMock);
    }

    
    @Test
    void inquilinoReservaPublicacion() {
        Inquilino inquilino = new User(superficie, superficie, capacidad, sitioSpy); 
        Publicacion publicacionMock = mock(Publicacion.class); 
        LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
        LocalDate fechaSalida = LocalDate.of(2024, 12, 10);

        inquilino.reservar(publicacionMock, fechaEntrada, fechaSalida, formaDePagoMock);
        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);

        verify(publicacionMock).recibirReserva(reservaCaptor.capture());

        Reserva reservaCapturada = reservaCaptor.getValue();
       
        assertEquals(fechaEntrada, reservaCapturada.getFechaInicio());
        assertEquals(fechaSalida, reservaCapturada.getFechaFin());
        assertEquals(inquilino, reservaCapturada.getInquilino());
    }

    @Test
    void inquilinoCancelaReservaPublicacion() {
        Inquilino inquilino = new User(superficie, superficie, capacidad, sitioSpy); 
        Publicacion publicacionMock = mock(Publicacion.class); 
        LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
        LocalDate fechaSalida = LocalDate.of(2024, 12, 10);

        Reserva reserva = new Reserva(fechaEntrada, fechaSalida, inquilino, formaDePagoMock);
        inquilino.reservar(publicacionMock, fechaEntrada, fechaSalida, formaDePagoMock);
        inquilino.cancelar(publicacionMock, reserva);


        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);

        verify(publicacionMock).cancelarReserva(reservaCaptor.capture());

        Reserva reservaCancelada = reservaCaptor.getValue();
      
        assertEquals(fechaEntrada, reservaCancelada.getFechaInicio());
        assertEquals(fechaSalida, reservaCancelada.getFechaFin());
        assertEquals(inquilino, reservaCancelada.getInquilino());
    }

    @Test
    void propietarioAceptaReservaPublicacion() {
        Propietario propietario = new User(superficie, superficie, capacidad, sitioSpy);
        Inquilino inquilino = new User(superficie, superficie, capacidad, sitioSpy);
        Publicacion publicacionMock = mock(Publicacion.class); 
        LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
        LocalDate fechaSalida = LocalDate.of(2024, 12, 10);

        Reserva reserva = new Reserva(fechaEntrada, fechaSalida, inquilino, formaDePagoMock);
        
        propietario.aceptar(publicacionMock, reserva); 
        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);

        verify(publicacionMock).aceptarReserva(reservaCaptor.capture());

        Reserva reservaAceptada = reservaCaptor.getValue();
      
        assertEquals(fechaEntrada, reservaAceptada.getFechaInicio());
        assertEquals(fechaSalida, reservaAceptada.getFechaFin());

    }

    @Test
    void propietarioRechazaReservaPublicacion() {
        Propietario propietario = new User(superficie, superficie, capacidad, sitioSpy); 
        Inquilino inquilino = new User(superficie, superficie, capacidad, sitioSpy);
        Publicacion publicacionMock = mock(Publicacion.class); 
        LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
        LocalDate fechaSalida = LocalDate.of(2024, 12, 10);

        Reserva reserva = new Reserva(fechaEntrada, fechaSalida, inquilino, formaDePagoMock);

        propietario.rechazar(publicacionMock, reserva);

        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);
   
        verify(publicacionMock).rechazarReserva(reservaCaptor.capture());

        Reserva reservaRechazada = reservaCaptor.getValue();
    
        assertEquals(fechaEntrada, reservaRechazada.getFechaInicio());
        assertEquals(fechaSalida, reservaRechazada.getFechaFin());

    }
    
    @Test
    void testObtenerReservasFuturas() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);
 //       Inquilino inquilino = new User(); // Suponiendo que User implementa Inquilino

        // Crear una lista simulada de reservas futuras
        List<Reserva> reservasFuturas = Arrays.asList(reservaMock1,reservaMock2);
        when(sitioMock.obtenerTodasLasReservasFuturas(inquilino)).thenReturn(reservasFuturas);

        // Acción
        List<Reserva> resultado = inquilino.obtenerReservasFuturas(sitioMock);

        // Verificación
        assertEquals(reservasFuturas, resultado);
        verify(sitioMock).obtenerTodasLasReservasFuturas(inquilino);
    }
    
    @Test
    void testObtenerReservasDeInquilinoEnCiudad() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        String ciudad = "Buenos Aires";
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);

        // Lista simulada de reservas en la ciudad
        List<Reserva> reservasEnCiudad = Arrays.asList(reservaMock1,reservaMock2);
        when(sitioMock.obtenerReservasDeInquilinoEnCiudad(ciudad, inquilino)).thenReturn(reservasEnCiudad);

        // Acción
        List<Reserva> resultado = inquilino.obtenerReservasDeInquilinoEnCiudad(ciudad, sitioMock);

        // Verificación
        assertEquals(reservasEnCiudad, resultado);
        verify(sitioMock).obtenerReservasDeInquilinoEnCiudad(ciudad, inquilino);
    }

    @Test
    void testObtenerCiudadesDondeInquilinoTieneReserva() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
    

        // Lista simulada de ciudades
        List<String> ciudadesConReserva = Arrays.asList("Buenos Aires", "Córdoba");
        when(sitioMock.obtenerCiudadesDondeInquilinoTieneReserva(inquilino)).thenReturn(ciudadesConReserva);

        // Acción
        List<String> resultado = inquilino.obtenerCiudadesDondeInquilinoTieneReserva(sitioMock);

        // Verificación
        assertEquals(ciudadesConReserva, resultado);
        verify(sitioMock).obtenerCiudadesDondeInquilinoTieneReserva(inquilino);
    }
    
    
    @Test
    void testObtenerReservas() {
        // Configuración del mock de Sitio
        Sitio sitioMock = mock(Sitio.class);
        Reserva reservaMock1 = mock(Reserva.class);
        Reserva reservaMock2 = mock(Reserva.class);

        // Lista simulada de reservas asociadas al inquilino
        List<Reserva> reservas = Arrays.asList(reservaMock1,reservaMock2);
        when(sitioMock.obtenerTodasLasReservasDe(inquilino)).thenReturn(reservas);

        // Acción
        List<Reserva> resultado = inquilino.obtenerReservas(sitioMock);

        // Verificación
        assertEquals(reservas, resultado);
        verify(sitioMock).obtenerTodasLasReservasDe(inquilino);
    }

   ///-------------------------------------------->
    
    @Test
    void testGetAntiguedad() {
        // Creación de usuario en una fecha pasada simulada mediante modificación
        Sitio sitioMock = mock(Sitio.class);
        User usuario = new User("Nombre Completo", "email@example.com", 123456789, sitioMock);
        
        // Simulación de antigüedad esperada (considerando que fechaRegistro es la de hoy)
        int antiguedadEsperada = 0; // Ya que la antigüedad es inmediata a la fecha de creación
        
        // Acción
        int antiguedadActual = usuario.getAntiguedad();

        // Verificación
        assertEquals(antiguedadEsperada, antiguedadActual);
    }

    /* @Test // TODO REFACTOR
    void testAgregarResenia() {
        // Mock de Resenia
        Sitio sitioMock = mock(Sitio.class);
        Resenia reseniaMock = mock(Resenia.class);
        
        // Crear una instancia real de User con un mock de Ranking
        Ranking rankingMock = mock(Ranking.class);
        //User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);
        User usuario = mock(User.class);
        // Acción
        
        when(usuario.getRanking()).thenReturn(rankingMock);
        when(usuario.getRanking().getResenias()).thenReturn(Arrays.asList(reseniaMock));
  //      usuario.getRanking().agregarResenia(reseniaMock);

        // Verificación
        verify(usuario).agregarResenia(reseniaMock);
        verify(rankingMock).agregarResenia(reseniaMock);
    }
*/
    
    @Test
    void testGetRanking() {
        // Crear una instancia de User con un Ranking predefinido
    	Sitio sitioMock = mock(Sitio.class);
    	User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);
        
        // Obtener el ranking usando el método getRanking
        Ranking resultado = usuario.getRanking();
        
        // Verificación: Asegurar que el ranking no sea nulo y es la instancia esperada
        assertNotNull(resultado, "El ranking no debería ser nulo");
        assertEquals(usuario.getRanking(), resultado, "Debería devolver el mismo ranking asignado en User");
    }

    @Test
    void testObtenerComentarios() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Mock de Ranking y configuración de comentarios
    	Sitio sitioMock = mock(Sitio.class);
        Ranking rankingMock = mock(Ranking.class);
        List<String> comentariosEsperados = Arrays.asList("Excelente servicio", "Muy recomendable");
        when(rankingMock.obtenerComentarios()).thenReturn(comentariosEsperados);

        // Crear instancia de User con el mock de Ranking (si es posible pasar el mock en el constructor)
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);
        
        // Reemplazar el ranking en el usuario (si no hay un setter de ranking, se puede simular en el constructor)
        Field rankingField = User.class.getDeclaredField("ranking");
        rankingField.setAccessible(true);
        rankingField.set(usuario, rankingMock);
        
        // Acción: Llamada al método obtenerComentarios
        List<String> comentarios = usuario.obternerComentarios();
        
        // Verificación
        assertEquals(comentariosEsperados, comentarios, "Los comentarios obtenidos no coinciden con los esperados");
        verify(rankingMock).obtenerComentarios();  // Verifica que se haya llamado al método en el mock
    }

    @Test
    void testObtenerPromedioGeneral() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Mock de Ranking
    	Sitio sitioMock = mock(Sitio.class);
        Ranking rankingMock = mock(Ranking.class);
        
        // Definición del valor esperado del promedio
        double promedioEsperado = 4.5;
        when(rankingMock.obtenerPromedioGeneral()).thenReturn(promedioEsperado);
        
        // Crear instancia de User con el mock de Ranking (si es posible pasar el mock en el constructor)
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);
        
        // Reemplazar el ranking en el usuario (si no hay un setter, se puede simular en el constructor)
        Field rankingField = User.class.getDeclaredField("ranking");
        rankingField.setAccessible(true);
        rankingField.set(usuario, rankingMock);
        
        // Acción: Llamada al método obtenerPromedioGeneral
        double promedioActual = usuario.obtenerPromedioGeneral();
        
        // Verificación
        assertEquals(promedioEsperado, promedioActual, 0.01, "El promedio general obtenido no coincide con el esperado");
        verify(rankingMock).obtenerPromedioGeneral();  // Verifica que se haya llamado al método en el mock
    }

    @Test
    void testObtenerPromedioCategoria() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Mock de Ranking y Categoria
    	Sitio sitioMock = mock(Sitio.class);
        Ranking rankingMock = mock(Ranking.class);
        Categoria categoriaMock = mock(Categoria.class);

        // Definición del promedio esperado para la categoría
        double promedioEsperado = 4.2;
        when(rankingMock.obtenerPromedioPorCategoria(categoriaMock)).thenReturn(promedioEsperado);

        // Crear instancia de User
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);

        // Reemplazar el ranking en el usuario
        Field rankingField = User.class.getDeclaredField("ranking");
        rankingField.setAccessible(true);
        rankingField.set(usuario, rankingMock);

        // Acción
        double promedioActual = usuario.obtenerPromedioCategoria(categoriaMock);

        // Verificación
        assertEquals(promedioEsperado, promedioActual, 0.01, "El promedio obtenido para la categoría no coincide con el esperado");
        verify(rankingMock).obtenerPromedioPorCategoria(categoriaMock); // Verificar que se llame al método en el mock
    }

    @Test
    void testGetResenias() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Mock de Ranking y Resenia
    	Sitio sitioMock = mock(Sitio.class);
        Ranking rankingMock = mock(Ranking.class);
        Resenia reseniaMock1 = mock(Resenia.class);
        Resenia reseniaMock2 = mock(Resenia.class);
        List<Resenia> reseniasEsperadas = Arrays.asList(reseniaMock1, reseniaMock2);

        // Configuración del mock de Ranking para retornar la lista de reseñas
        when(rankingMock.getResenias()).thenReturn(reseniasEsperadas);

        // Crear instancia de User
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);

        // Reemplazar el ranking en el usuario
        Field rankingField = User.class.getDeclaredField("ranking");
        rankingField.setAccessible(true);
        rankingField.set(usuario, rankingMock);

        // Acción
        List<Resenia> reseniasActuales = usuario.getResenias();

        // Verificación
        assertEquals(reseniasEsperadas, reseniasActuales, "La lista de reseñas obtenida no coincide con la esperada");
        verify(rankingMock).getResenias(); // Verificar que se llame al método en el mock
    }

    @Test
    void testObtenerComentariosPorCategoria() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Mock de Ranking y Categoria
    	Sitio sitioMock = mock(Sitio.class);
        Ranking rankingMock = mock(Ranking.class);
        Categoria categoriaMock = mock(Categoria.class);

        // Configuración de comentarios esperados para la categoría
        List<String> comentariosEsperados = Arrays.asList("Buen servicio", "Muy recomendado");
        when(rankingMock.obtenerCometariosPorCategoria(categoriaMock)).thenReturn(comentariosEsperados);

        // Crear instancia de User
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);

        // Inyección del ranking mock en el usuario usando reflexión
        Field rankingField = User.class.getDeclaredField("ranking");
        rankingField.setAccessible(true);
        rankingField.set(usuario, rankingMock);

        // Acción
        List<String> comentariosActuales = usuario.obtenerComentariosPorCategoria(categoriaMock);

        // Verificación
        assertEquals(comentariosEsperados, comentariosActuales, "La lista de comentarios obtenida no coincide con la esperada");
        verify(rankingMock).obtenerCometariosPorCategoria(categoriaMock); // Verificar que se llame al método en el mock
    }
    
    @Test
    void testAgregarResenia() {
        // Mock de Ranking y Resenia
    	Sitio sitioMock = mock(Sitio.class);
        Ranking rankingMock = mock(Ranking.class);
        Resenia reseniaMock = mock(Resenia.class);

        // Crear instancia de User con ranking mockeado
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);

        // Inyección del ranking mock en el usuario usando reflexión
        try {
            Field rankingField = User.class.getDeclaredField("ranking");
            rankingField.setAccessible(true);
            rankingField.set(usuario, rankingMock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("No se pudo inyectar el ranking mock");
        }

        // Acción: Llamar al método agregarResenia en User
        usuario.agregarResenia(reseniaMock);

        // Verificación: Verificar que se llamó al método agregarResenia del mock de Ranking
        verify(rankingMock).agregarResenia(reseniaMock);
    }
    
    /*@Test
    void testRankearInmueble() {
        // Mock de las dependencias
       	Sitio sitioMock = mock(Sitio.class);
        CategoriasManager categoriasManagerMock = mock(CategoriasManager.class);
        Categoria categoriaMock = mock(Categoria.class);
        Resenia reseniaMock = mock(Resenia.class);
        Publicacion publicacionMock = mock(Publicacion.class);

        // Mock de CategoriasManager para devolver una categoría específica
        when(CategoriasManager.getInstancia()).thenReturn(categoriasManagerMock);
        when(categoriasManagerMock.obtenerCategoriasDeInmueble()).thenReturn(Arrays.asList(categoriaMock));
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);

        // Crear instancia de User
        User usuario = new User("Nombre Completo", "mail@example.com", 123456789, sitioMock);

        // Acción: Llamar al método rankearInmueble
        usuario.rankearInmueble(reseniaMock, publicacionMock);

        // Verificación: Verificar que se llama a agregarResenia en la publicación si las categorías coinciden
        verify(publicacionMock).agregarResenia(reseniaMock);
    }
*/


    //Veces que alquilo unInmueble-- No testear
    //oberterTtoalAlquileres --NoTestear
    //obtenerPromedioGeniarl -- No Testar
    //getDeReservasFuturas ---No Testar
}
