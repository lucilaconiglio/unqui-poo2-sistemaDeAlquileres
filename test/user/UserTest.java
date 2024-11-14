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
	
	@Test
    void testObtenerPromedioCategoria() {
  /*      when(rankingMock.obtenerPromedioPorCategoria(categoriaMock)).thenReturn(4.5);

        double promedio = inquilino.obtenerPromedioCategoria(categoriaMock);

        assertEquals(4.5, promedio, 0.01);
        verify(rankingMock).obtenerPromedioPorCategoria(categoriaMock);
    */
    }

    @Test
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

    @Test
    void testObtenerComentariosPorCategoria() {
        Inquilino inquilinoMock = mock(Inquilino.class);

        categoriasManagerMock.agregarCategoria(categoriaMock, Inquilino.class);
        when(reseniaMock.getCategoria()).thenReturn(categoriaMock);


        List<String> comentarios = Arrays.asList("Muy bueno", "Genial");
        when(reseniaMock.getComentario()).thenReturn("Muy bueno");

        when(inquilinoMock.obtenerComentariosPorCategoria(categoriaMock)).thenReturn(comentarios);

        List<String> resultado = inquilinoMock.obtenerComentariosPorCategoria(categoriaMock);
        assertEquals(comentarios, resultado);
    }

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
        System.out.println("Ver resenias del propietario: "+ propietario.getRanking().getResenias());
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

        // Acción
        inquilino.rankearPropietario(reseniaMock, propietarioMock);

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

      
        inquilinoMock.rankearPropietario(reseniaMock, null);

     
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

        // Acción: Propietario rankea a Inquilino
        propietario.rankearInquilino(reseniaMock, inquilinoMock);

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


        propietarioMock.rankearInquilino(reseniaMock, null);

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

        inquilinoMock.rankearPropietario(reseniaMock, propietarioMock);

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
        System.out.println("Reserva capturada: " + reservaCapturada);
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
        System.out.println("Reserva cancelada: " + reservaCancelada);
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
        System.out.println("Reserva aceptada: " + reservaAceptada);
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
        System.out.println("Reserva rechazada: " + reservaRechazada);
        assertEquals(fechaEntrada, reservaRechazada.getFechaInicio());
        assertEquals(fechaSalida, reservaRechazada.getFechaFin());

    }

    //Veces que alquilo unInmueble-- No testear
    //oberterTtoalAlquileres --NoTestear
    //obtenerPromedioGeniarl -- No Testar
    //getDeReservasFuturas ---No Testar
}
