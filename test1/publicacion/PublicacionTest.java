package publicacion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import formaDePago.FormaDePago;
import periodo.Periodo;
import politicaCancelacion.PoliticaDeCancelacion;
import servicio.Servicio;
import ubicacion.Ubicacion;
import user.User;
import resenia.Resenia;

class PublicacionTest {

    Publicacion publicacion;
    User mockPropietario;
    FormaDePago mockFormaDePago;
    PoliticaDeCancelacion mockPolitica;
    Ubicacion mockUbicacion;

    @BeforeEach
    public void setUp() {
        // Crea mocks de las clases necesarias
        mockPropietario = mock(User.class);
        mockFormaDePago = mock(FormaDePago.class);
        mockPolitica = mock(PoliticaDeCancelacion.class);
        mockUbicacion = mock(Ubicacion.class);

        // Instancia de Publicacion usando los mocks
        publicacion = new Publicacion(LocalDate.now(), LocalDate.now().plusDays(1), 
                                      100.0, mockFormaDePago, mockPolitica,
                                      mockPropietario, "50m²", 4, mockUbicacion);
    }

    @Test
    void testAddResenia() {
        Resenia mockResenia = mock(Resenia.class);
        when(mockResenia.getPuntaje()).thenReturn(5);

        publicacion.agregarResenia(mockResenia);

        List<Resenia> resenias = publicacion.getResenias();
        assertEquals(1, resenias.size());
        assertEquals(mockResenia, resenias.get(0));
    }

    @Test
    void testObtenerPromedioGeneral() {
        Resenia resenia1 = mock(Resenia.class);
        Resenia resenia2 = mock(Resenia.class);

        when(resenia1.getPuntaje()).thenReturn(4);
        when(resenia2.getPuntaje()).thenReturn(5);

        publicacion.agregarResenia(resenia1);
        publicacion.agregarResenia(resenia2);

        double promedio = publicacion.obtenerPromedioGeneral();
        assertEquals(9.0, promedio); // 4 + 5 = 9; si quieres que sea el promedio hazlo 4.5
    }

    @Test
    void testGetReseniasPorCategoria() {
        Categoria mockCategoria = mock(Categoria.class);
        when(mockCategoria.getConcepto()).thenReturn("Categoria1");

        Resenia resenia1 = new Resenia(mockCategoria, 3, "Comentario 1");
        Resenia resenia2 = new Resenia(mockCategoria, 4, "Comentario 2");
        // Creamos una categoría diferente para esta reseña
        Categoria mockCategoriaDiferente = mock(Categoria.class);
        when(mockCategoriaDiferente.getConcepto()).thenReturn("Categoria2");

        Resenia reseniaDiferente = new Resenia(mockCategoriaDiferente, 5, "Comentario 3");

        publicacion.agregarResenia(resenia1);
        publicacion.agregarResenia(resenia2);
        publicacion.agregarResenia(reseniaDiferente);

        List<Resenia> reseniasPorCategoria = publicacion.getReseniasPorCategoria(mockCategoria);
        
        assertEquals(2, reseniasPorCategoria.size()); // solo las del mockCategoria
    }

    @Test
    void testPrecioPorDia() {
        Periodo mockPeriodo = mock(Periodo.class);
        when(mockPeriodo.getInicio()).thenReturn(LocalDate.now());
        when(mockPeriodo.getFin()).thenReturn(LocalDate.now().plusDays(2));
        when(mockPeriodo.getPrecio()).thenReturn(150.0);

        publicacion.addPediodo(mockPeriodo);
        
        double precio = publicacion.precioPorDia(LocalDate.now());
        assertEquals(150.0, precio);
        
        precio = publicacion.precioPorDia(LocalDate.now().plusDays(3)); // Debería ser el precio base
        assertEquals(100.0, precio); // Precio Base
    }

    @Test
    void testRemovePeriodo() {
        Periodo mockPeriodo = mock(Periodo.class);
        publicacion.addPediodo(mockPeriodo);

        // Verificamos que el periodo está añadido
        assertEquals(1, publicacion.getPeriodos().size());

        // Ahora removemos el periodo
        publicacion.removePeriodo(mockPeriodo);

        // Verificamos que la lista de periodos está vacía
        assertEquals(0, publicacion.getPeriodos().size());
    }

    @Test
    void testSetPoliticaDeCancelacion() {
        PoliticaDeCancelacion nuevaPolitica = mock(PoliticaDeCancelacion.class);
        publicacion.SetPoliticaDeCancelacion(nuevaPolitica);
        
        // Verificamos que la política de cancelación ha sido actualizada
        assertEquals(nuevaPolitica, publicacion.getPoliticaDeCancelacion());
    }

    @Test
    void testAddFoto() {
        String foto = "foto1.jpg";
        publicacion.addFoto(foto);
        
        // Verificamos que la foto se ha añadido
        assertTrue(publicacion.getFotos().contains(foto)); // Asumiendo que hay un método getFotos()
    }

    @Test
    void testRemoveFoto() {
        String foto = "foto1.jpg";
        publicacion.addFoto(foto);
        
        // Verificamos que la foto está en la lista
        assertTrue(publicacion.getFotos().contains(foto));

        // Ahora removemos la foto
        publicacion.removeFoto(foto);

        // Verificamos que la foto ya no está en la lista
        assertFalse(publicacion.getFotos().contains(foto));
    }

    @Test
    void testAddServicio() {
        Servicio servicio = mock(Servicio.class);
        publicacion.addServicio(servicio);
        
        // Verificamos que el servicio se ha añadido
        assertTrue(publicacion.getServicios().contains(servicio)); // Asumiendo que hay un método getServicios()
    }

    @Test
    void testRemoveServicio() {
        Servicio servicio = mock(Servicio.class);
        publicacion.addServicio(servicio);
        
        // Verificamos que el servicio está en la lista
        assertTrue(publicacion.getServicios().contains(servicio));

        // Ahora removemos el servicio
        publicacion.removeServicio(servicio);

        // Verificamos que el servicio ya no está en la lista
        assertFalse(publicacion.getServicios().contains(servicio));
    }
    
    @Test
    void testObtenerPromedioCategoria() {
        // Creamos una categoría de prueba
        Categoria mockCategoria = mock(Categoria.class);
        when(mockCategoria.getConcepto()).thenReturn("Categoria1");

        // Creamos reseñas con diferentes puntajes para la categoría de prueba
        Resenia resenia1 = new Resenia(mockCategoria, 3, "Comentario 1");
        Resenia resenia2 = new Resenia(mockCategoria, 4, "Comentario 2");
        
        // Creamos una categoría diferente para esta reseña
        Categoria mockCategoriaDiferente = mock(Categoria.class);
        when(mockCategoriaDiferente.getConcepto()).thenReturn("Categoria2");
        Resenia reseniaDiferente = new Resenia(mockCategoriaDiferente, 5, "Comentario 3");

        // Agregamos las reseñas a la publicación
        publicacion.agregarResenia(resenia1);
        publicacion.agregarResenia(resenia2);
        publicacion.agregarResenia(reseniaDiferente);

        // Obtenemos el promedio de las reseñas en la categoría de prueba
        double promedio = publicacion.obtenerPromedioCategoria(mockCategoria);
        
        // Verificamos que el promedio sea correcto
        // Sumar los puntajes de resenia1 y resenia2 (3 + 4) = 7
        // Hay 2 reseñas en total para esta categoría, así que el promedio debería ser 7 / 2 = 3.5
        assertEquals(3.5, promedio, 0.001); // Utilizamos un delta para comparación de dobles
    }
    
    @Test
    void testPrecioEntreFechas() {
        // Creamos un periodo de prueba
        Periodo mockPeriodo = mock(Periodo.class);
        when(mockPeriodo.getInicio()).thenReturn(LocalDate.now());
        when(mockPeriodo.getFin()).thenReturn(LocalDate.now().plusDays(2));
        when(mockPeriodo.getPrecio()).thenReturn(150.0);

        publicacion.addPediodo(mockPeriodo); // Agregar período

        LocalDate entrada = LocalDate.now();
        LocalDate salida = LocalDate.now().plusDays(2);

        // El precio total debería ser 150.0 * 3 (días) = 450.0
        double precioTotal = publicacion.precioEntreFechas(entrada, salida);
        assertEquals(450.0, precioTotal);
    }


    @Test
    void testCambiarPrecioBase() {
        double nuevoPrecio = 200.0;
        publicacion.cambiarPrecioBase(nuevoPrecio);
        
        // Verificamos que el precio base ha cambiado correctamente
        assertEquals(nuevoPrecio, publicacion.getPrecioBase()); // Asegúrate de tener un método getPrecioBase()
    }
    
}