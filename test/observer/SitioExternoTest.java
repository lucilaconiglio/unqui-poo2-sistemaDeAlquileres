package observer;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import observer.HomePagePublisher; 
import tipoDeInmueble.TipoDeInmueble;

class SitioExternoTest {

    private SitioExterno sitioExterno;
    private HomePagePublisher mockHomePagePublisher;
    private TipoDeInmueble mockTipoDeInmueble;

    @BeforeEach
    public void setUp() {
        // Crea un mock para HomePagePublisher
        mockHomePagePublisher = mock(HomePagePublisher.class);
        // Crea un mock para TipoDeInmueble
        mockTipoDeInmueble = mock(TipoDeInmueble.class);

        // Inicializa la clase SitioExterno con el mock de HomePagePublisher
        sitioExterno = new SitioExterno(mockHomePagePublisher);
    }

    @Test
    void testNotificarBajaDePrecioInmbueble() {
        double precioInmueble = 100.0;
        when(mockTipoDeInmueble.toString()).thenReturn("Departamento"); // Configura el comportamiento del mock
        
        // Llama al método a probar
        sitioExterno.notificarBajaDePrecioInmbueble(mockTipoDeInmueble, precioInmueble);
        
        // Verifica que el método publish en homePagePublisher fue llamado con el mensaje correcto
        String expectedMensaje = "No te pierdas esta oferta: Un inmuebleDepartamentoa tan sólo 100.0pesos";
        verify(mockHomePagePublisher).publish(expectedMensaje);
    }
}