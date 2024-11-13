package user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import categoria.Categoria;
import categoriasManager.CategoriasManager;
import politicaCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;
import ranking.Ranking;
import resenia.Resenia;
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
        
        inquilino = new User("Pedro", "Lopez", 14333333, sitioSpy);
		propietario = new User("Raul", "Gutierrez",11223344, sitioSpy);

	    checkIn = LocalDate.of(2024, 11, 15);  
	    checkOut = LocalDate.of(2024, 11, 20); 
	    precioBase = 1500.0; 
	    superficie = "50m²"; 
	    capacidad = 4;       

	}
	
	/*@Test //TODO: Refactor
	void inquilinoRankeaPropietarioTest() {
	    // Crear el mock del propietario y de la clase Ranking
	    Propietario propietarioMock = mock(Propietario.class);
	    Ranking rankingMock = mock(Ranking.class);
	    List<Resenia> resenias = new ArrayList<>();
	    
	    // Configurar el mock del propietario para devolver el rankingMock
	    when(propietarioMock.getRanking()).thenReturn(rankingMock);
	    
	    // Configurar el comportamiento de getResenias en el rankingMock
	    when(rankingMock.getResenias()).thenReturn(resenias);
	    
	    // Configurar el comportamiento de agregarResenia en el rankingMock
	    doAnswer(invocation -> {
	        resenias.add(reseniaMock); // Agregar la reseña a la lista de resenias
	        return null;
	    }).when(rankingMock).agregarResenia(reseniaMock);

	    // Ejecutar el método para rankear al propietario
	    inquilino.rankearPropietario(reseniaMock, propietarioMock);

	    // Verificar que la reseña fue agregada a la lista de reseñas del propietario
	    assertTrue(rankingMock.getResenias().contains(reseniaMock));
	}*/
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
    	//inquilino.rankearUsuario(propietario, resenia1);
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
  //InquilinoRankeaPropietarioDeCategoriaNoExistente
  //UsuarioObtiene
}
