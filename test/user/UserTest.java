package user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import categoria.Categoria;
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
	private Ranking rankingMock;
	private Resenia reseniaMock;
	private Categoria categoriaMock;
	private LocalDate checkIn ;
	private LocalDate checkOut;
	private double precioBase;
	private String superficie;
	private int capacidad;
	
	@BeforeEach
	public void setup() {
		//sitioMock = mock(Sitio.class);
		publicacion = mock(Publicacion.class);
		politicaDeCancelacionMock = mock(PoliticaDeCancelacion.class);
	    ubicacionMock = mock(Ubicacion.class);
	    tipoInmuebleMock = mock(TipoDeInmueble.class);
	    publicacionMock = mock(Publicacion.class);
	    rankingMock = mock(Ranking.class);
        reseniaMock = mock(Resenia.class);
        categoriaMock = mock(Categoria.class);
	    
	    
	//	when(sitioSpy.getUsers()).thenReturn(Arrays.asList(inquilino, propietario ));

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
		propietario.darDeAltaPublicacion(sitioSpy, checkIn, checkOut, precioBase, politicaDeCancelacionMock, propietario, superficie, capacidad, ubicacionMock, tipoInmuebleMock);
		
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
    	
    	String comentario = "Buen lugar";  	
    	when(resenia1.getComentario()).thenReturn(comentario);
  
    	inquilino.rankearUsuario(propietario, resenia1);
        List<String> resultado = propietario.obternerComentarios();

        assertEquals(comentario, resultado.get(0));
    
    }

    @Test
    void testObtenerComentariosPorCategoria() {
        //List<String> comentariosPorCategoria = Arrays.asList("Limpio", "Buena ubicación");
        //when(rankingMock.obtenerCometariosPorCategoria(categoriaMock)).thenReturn(comentariosPorCategoria);
    	Resenia resenia1 = mock(Resenia.class);
    	List<String> comentarioPorCategoria = Arrays.asList("Limpio");
    	when(rankingMock.obtenerCometariosPorCategoria(categoriaMock)).thenReturn(comentarioPorCategoria);
        
    	List<String> comentarioPorCategoria2 = Arrays.asList("Limpio","Buena calidad");
    	when(rankingMock.obtenerCometariosPorCategoria(categoriaMock)).thenReturn(comentarioPorCategoria2);
        
    	inquilino.rankearUsuario(propietario, resenia1);
    	
        List<String> resultado = inquilino.obetenerComentariosPorCategoria(categoriaMock);

        //assertEquals(comentarioPorCategoria, resultado);
        verify(rankingMock).obtenerCometariosPorCategoria(categoriaMock);
    }
	
    @Test
    void testRankearUsuario() {
        User inquilino2 = mock(User.class);
        Resenia reseniaMock2 = mock(Resenia.class);
        propietario.rankearUsuario(inquilino, reseniaMock2);

        verify(inquilino2).agregarResenia(reseniaMock2);
    }
    
    @Test
    void testRankearInmueble() {
        inquilino.rankearInmueble(reseniaMock, publicacionMock);

        verify(publicacionMock).agregarResenia(reseniaMock);
    }
	
}
