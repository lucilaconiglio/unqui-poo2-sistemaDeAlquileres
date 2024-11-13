package observer;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import observer.PopUpWindow; 
import tipoDeInmueble.TipoDeInmueble;

class UsuarioMovilTest {

    private UsuarioMovil usuarioMovil;
    private PopUpWindow mockPopUpWindow;
    private TipoDeInmueble mockTipoDeInmueble;

    @BeforeEach
    public void setUp() {
        // Crea un mock para PopUpWindow
        mockPopUpWindow = mock(PopUpWindow.class);
        // Crea un mock para TipoDeInmueble
        mockTipoDeInmueble = mock(TipoDeInmueble.class);
        
        // Inicializa la clase UsuarioMovil con el mock de PopUpWindow
        usuarioMovil = new UsuarioMovil(mockPopUpWindow);
        
        // Configura el comportamiento del mockTipoDeInmueble
        when(mockTipoDeInmueble.toString()).thenReturn("Departamento");
    }

    @Test
    void testNotificarCancelacionInmueble() {
        // llama al método a probar
        usuarioMovil.notificarCancelacionInmueble(mockTipoDeInmueble);
        
        // Verifica que el método popUp fue llamado con el mensaje correcto
        String expectedMensaje = "El/la Departamento que te interesa se ha liberado! Corre a reservarlo!";
        verify(mockPopUpWindow).popUp(expectedMensaje, "Green", 15);
    }
}