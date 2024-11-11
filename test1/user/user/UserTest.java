package user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Publicacion;
import sitio.Sitio;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

class UserTest {
	private Inquilino inquilino;
	private Propietario propietario;
	private Sitio sitioMock;
	private Publicacion publicacion;
	
	@BeforeEach
	public void setup() {
		sitioMock = mock(Sitio.class);
		publicacion = mock(Publicacion.class);
		
		
		inquilino = new User("Pedro", "Lopez", 11433333, sitioMock);
		propietario = new User("Raul", "Gutierrez",11223344, sitioMock);
		
		
	}
	
	//testChequeaSiEstaDeAltaEnElSistema
	@Test
	void elUsuarioSeDaDeAltaEnElSistema() {
		 sitioMock.addUsuario( (User)inquilino );
		 assertEquals(sitioMock.getUsuarios().size(), 1 );
	}
	
	@Test
	void elUsuarioSeDaDeBajaDelSistema() {
		 sitioMock.removeUsuario( (User)inquilino);
		 assertEquals(sitioMock.getUsuarios().size(), 0);
	}
	
	@Test
	void elPropietarioPuedeRealizarUnaPublicacion() {
	//	propietario.crearPublicacion();
		
	//	sitioMock.getPublicaciones().contains()
	}
	
	

	//testPuedeRealizarUnaPublicacionElPropietario
	//testInquilinoPuedeRealizarUnaBusqueda
	//TestUnUsuarioBuscReseniasDeUnaPublicacion
	//TestUnInquilinoRealizaUnaReserva
	//
	
	
}
