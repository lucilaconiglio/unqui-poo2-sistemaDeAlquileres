package categoriasManager;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import publicacion.Publicacion;
import user.inquilino.Inquilino;
import user.propietario.Propietario;

class CategoriasManagerTest {
    
    private CategoriasManager categoriasManager;

    @BeforeEach
    public void setUp() {
        categoriasManager = CategoriasManager.getInstancia();
    }

    @Test
    void testSingleton() {
        CategoriasManager otraInstancia = CategoriasManager.getInstancia();
        assertSame(categoriasManager, otraInstancia, "Deberían ser la misma instancia");
    }

    @Test
    void testAgregarCategoria() {
        // Crea una categoría de prueba
        Categoria categoria = new Categoria("Categoria Test");

        // Agrega la categoría a una publicación
        categoriasManager.agregarCategoria(categoria, Publicacion.class);
        
        // Verifica que la categoría se ha agregado correctamente
        assertEquals(1, categoriasManager.obtenerCategoriasDeInmueble().size(), "Debería haber una categoría para Publicación.");
        assertTrue(categoriasManager.obtenerCategoriasDeInmueble().contains(categoria), "La categoría debería estar en la lista.");
    }
    
    @Test
    void testObtenerCategoriasDePropietario() {
        // Crea una categoría de prueba
        Categoria categoria = new Categoria("Categoria Propietario");

        // Agrega la categoría a un propietario
        categoriasManager.agregarCategoria(categoria, Propietario.class);
        
        // Verifica que la categoría se ha agregado correctamente
        assertEquals(1, categoriasManager.obtenerCategoriasDePropietario().size(), "Debería haber una categoría para Propietario.");
        assertTrue(categoriasManager.obtenerCategoriasDePropietario().contains(categoria), "La categoría debería estar en la lista.");
    }

    @Test
    void testObtenerCategoriasDeInquilino() {
        // Crea una categoría de prueba
        Categoria categoria = new Categoria("Categoria Inquilino");

        // Agrega la categoría a un inquilino
        categoriasManager.agregarCategoria(categoria, Inquilino.class);
        
        // Verifica que la categoría se ha agregado correctamente
        assertEquals(1, categoriasManager.obtenerCategoriasDeInquilino().size(), "Debería haber una categoría para Inquilino.");
        assertTrue(categoriasManager.obtenerCategoriasDeInquilino().contains(categoria), "La categoría debería estar en la lista.");
    }

    @Test
    void testObtenerCategoriasNoExistentes() {
        // Verifica que se devuelva una lista vacía para una categoría que no ha sido agregada
        assertTrue(categoriasManager.obtenerCategoriasDeInmueble().isEmpty(), "La lista de categorías de Publicación debe estar vacía.");
        assertTrue(categoriasManager.obtenerCategoriasDePropietario().isEmpty(), "La lista de categorías de Propietario debe estar vacía.");
        assertTrue(categoriasManager.obtenerCategoriasDeInquilino().isEmpty(), "La lista de categorías de Inquilino debe estar vacía.");
    }
}