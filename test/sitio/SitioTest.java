package sitio;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicacion.Publicacion;

import java.util.List;

class SitioTest {

    private Sitio sitio;
    private Publicacion publicacion;

    @BeforeEach
    public void setUp() {
        sitio = new Sitio();
        publicacion = mock(Publicacion.class); 
        
      
    }

    @Test
    public void testAgregarPublicacion() {
        sitio.addPublicacion(publicacion);
        
        List<Publicacion> publicaciones = sitio.getPublicaciones();
        assertEquals(1, publicaciones.size());
        assertEquals(publicacion, publicaciones.get(0));
    }

    @Test
    public void testEliminarPublicacion() {
        sitio.addPublicacion(publicacion);
        assertEquals(1, sitio.getPublicaciones().size());

        sitio.removePublicacion(publicacion);
        assertTrue(sitio.getPublicaciones().isEmpty());
    }

    @Test
    public void testAgregarServicio() {
        String servicio = "Wi-Fi";
        sitio.addServicio(servicio);
        
        List<String> servicios = sitio.getServicios();
        assertEquals(1, servicios.size());
        assertEquals(servicio, servicios.get(0));
    }

    @Test
    public void testEliminarServicio() {
        String servicio = "Wi-Fi";
        sitio.addServicio(servicio);
        assertEquals(1, sitio.getServicios().size());

        sitio.removeServicio(servicio);
        assertTrue(sitio.getServicios().isEmpty());
    }

  

    @Test
    public void testAgregarTipoDeInmueble() {
        String tipoDeInmueble = "Apartamento";
        sitio.addTipoDeInmueble(tipoDeInmueble);
        
        List<String> tiposDeInmueble = sitio.getTiposDeInmueble();
        assertEquals(1, tiposDeInmueble.size());
        assertEquals(tipoDeInmueble, tiposDeInmueble.get(0));
    }

    @Test
    public void testEliminarTipoDeInmueble() {
        String tipoDeInmueble = "Apartamento";
        sitio.addTipoDeInmueble(tipoDeInmueble);
        assertEquals(1, sitio.getTiposDeInmueble().size());

        sitio.removeTipoDeInmeble(tipoDeInmueble);
        assertTrue(sitio.getTiposDeInmueble().isEmpty());
    }

    @Test
    public void testAgregarCategoriaComun() {
        String categoria = "Familia";
        sitio.addCategoriaComun(categoria);
        
        List<String> categoriasComunes = sitio.getCategoriasComunes();
        assertEquals(1, categoriasComunes.size());
        assertEquals(categoria, categoriasComunes.get(0));
    }

    @Test
    public void testEliminarCategoriaComun() {
        String categoria = "Familia";
        sitio.addCategoriaComun(categoria);
        assertEquals(1, sitio.getCategoriasComunes().size());

        sitio.removeCategoriaComun(categoria);
        assertTrue(sitio.getCategoriasComunes().isEmpty());
    }
    
}