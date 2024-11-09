package sitio;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import publicacion.Publicacion;
import rankeable.Rankeable;
import search.Search;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import user.User;

import java.util.ArrayList;
import java.util.List;

class SitioTest {

    Sitio sitio;
    Publicacion publicacion;
    Servicio servicio;
    TipoDeInmueble tipoDeInmueble;
    Categoria categoria;

    @BeforeEach
    public void setUp() {
        // Inicializa el objeto Sitio y otros objetos necesarios
        sitio = new Sitio();
        publicacion = mock(Publicacion.class);
        servicio = mock(Servicio.class);
        tipoDeInmueble = mock(TipoDeInmueble.class);
        categoria = mock(Categoria.class);
    }

    @Test
    void testAgregarPublicacion() {
        sitio.addPublicacion(publicacion);
        assertEquals(1, sitio.getPublicaciones().size());
        assertEquals(publicacion, sitio.getPublicaciones().get(0));
    }

    @Test
    void testEliminarPublicacion() {
        sitio.addPublicacion(publicacion);
        assertEquals(1, sitio.getPublicaciones().size());

        sitio.removePublicacion(publicacion);
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
    void testAgregarCategoriaPropietario() {
        sitio.addCategoriaPropietario(categoria);
        assertEquals(1, sitio.getCategoriaPropietario().size());
        assertEquals(categoria, sitio.getCategoriaPropietario().get(0));
    }

    @Test
    void testEliminarCategoriaPropietario() {
        sitio.addCategoriaPropietario(categoria);
        assertEquals(1, sitio.getCategoriaPropietario().size());

        sitio.removeCategoriaPropietario(categoria);
        assertTrue(sitio.getCategoriaPropietario().isEmpty());
    }

    @Test
    void testAgregarCategoriaInquilino() {
        sitio.addCategoriaInquilino(categoria);
        assertEquals(1, sitio.getCategoriasInquilino().size());
        assertEquals(categoria, sitio.getCategoriasInquilino().get(0));
    }

    @Test
    void testEliminarCategoriaInquilino() {
        sitio.addCategoriaInquilino(categoria);
        assertEquals(1, sitio.getCategoriasInquilino().size());

        sitio.removeCategoriaInquilino(categoria);
        assertTrue(sitio.getCategoriasInquilino().isEmpty());
    }

    @Test
    void testAgregarCategoriaInmueble() {
        sitio.addCategoriaInmueble(categoria);
        assertEquals(1, sitio.getCategoriasInmueble().size());
        assertEquals(categoria, sitio.getCategoriasInmueble().get(0));
    }

    @Test
    void testEliminarCategoriaInmueble() {
        sitio.addCategoriaInmueble(categoria);
        assertEquals(1, sitio.getCategoriasInmueble().size());

        sitio.removeCategoriaInmueble(categoria);
        assertTrue(sitio.getCategoriasInmueble().isEmpty());
    }


}