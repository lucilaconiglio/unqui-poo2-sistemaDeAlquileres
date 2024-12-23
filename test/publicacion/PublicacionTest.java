package publicacion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import observer.EventListener;
import periodo.Periodo;
import politicaCancelacion.PoliticaDeCancelacion;
import ranking.Ranking;
import servicio.Servicio;
import tipoDeInmueble.TipoDeInmueble;
import ubicacion.Ubicacion;
import user.User;
import user.propietario.Propietario;
import resenia.Resenia;
import reserva.Reserva;
import reserva.estadoReserva.EstadoPendienteDeAprobacion;

class PublicacionTest {

    private Publicacion publicacion;
    private Propietario mockPropietario;
    private PoliticaDeCancelacion mockPolitica;
    private TipoDeInmueble mockTipoDeInmueble;
    private Ubicacion mockUbicacion;
    private EventListener suscriptorMock;
    private Ranking mockRanking;
    private Reserva reservaMock1;
    private Reserva reservaMock2;
	private List<Ranking> listRanking;
 
    @BeforeEach
    public void setUp() {
        mockPropietario = mock(User.class);
        mockTipoDeInmueble = mock(TipoDeInmueble.class);
        mockPolitica = mock(PoliticaDeCancelacion.class);
        mockUbicacion = mock(Ubicacion.class);
        suscriptorMock = mock(EventListener.class);
        mockRanking = mock(Ranking.class);
        
        publicacion = new Publicacion(LocalDate.now(), LocalDate.now().plusDays(1), 
                                      100.0, mockPolitica,
                                      mockPropietario, "50m²", 4, mockUbicacion, mockTipoDeInmueble);
        
        publicacion.addSuscriptor(suscriptorMock);
        reservaMock1 = mock(Reserva.class);
        reservaMock2 = mock(Reserva.class);
        when(publicacion.getCiudad()).thenReturn("Cordoba");
        //when(publicacion.esPropietario(mockPropietario)).thenReturn(true);
        // Configura estado inicial de reservas mockeadas
        when(reservaMock1.getEstadoReserva()).thenReturn(new EstadoPendienteDeAprobacion());
        when(reservaMock2.getEstadoReserva()).thenReturn(new EstadoPendienteDeAprobacion());
    }
    
    @Test
    void testGetRanking() {
        // Asegúrate de que el ranking devuelto no sea nulo
        assertNotNull(publicacion.getRanking());
        
        List<Ranking> listRanking;
        listRanking= new ArrayList<>();
        listRanking.add(publicacion.getRanking());

        assertFalse(listRanking.isEmpty());
    }


    @Test
    void testAddResenia() {
        Resenia resenia = mock(Resenia.class);

        // Agrega la reseña usando el método de Publicacion
        publicacion.agregarResenia(resenia);

        assertEquals(1, publicacion.obternerComentarios().size());
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
        assertEquals(4.5, promedio); 
    }

    @Test
    void testPrecioPorDia() {
        // Configuración de un periodo que cubre el rango de fechas
        Periodo mockPeriodo = mock(Periodo.class);
        when(mockPeriodo.getInicio()).thenReturn(LocalDate.now());
        when(mockPeriodo.getFin()).thenReturn(LocalDate.now().plusDays(2));
        when(mockPeriodo.getPrecio()).thenReturn(150.0);

        publicacion.addPeriodo(mockPeriodo);

        // Test cuando la fecha está en el rango del periodo
        double precio = publicacion.precioPorDia(LocalDate.now());
        assertEquals(150.0, precio);

        // Test cuando la fecha está fuera del rango del periodo
        precio = publicacion.precioPorDia(LocalDate.now().plusDays(3)); // Debería ser el precio base
        assertEquals(100.0, precio); // Precio Base
    }

    @Test
    void testPrecioPorDiaSinPeriodos() {
        // Prueba cuando no hay periodos agregados a la publicación
        double precio = publicacion.precioPorDia(LocalDate.now());
        assertEquals(100.0, precio); // Cuando no hay periodos, debe devolver el precioBase
    }


    @Test
    void testRemovePeriodo() {
        Periodo mockPeriodo = mock(Periodo.class);
        publicacion.addPeriodo(mockPeriodo);

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
        publicacion.setPoliticaDeCancelacion(nuevaPolitica);
        
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
    void testAddMasCincoFotos() {
        // Agregar hasta 5 fotos
        publicacion.addFoto("foto1.jpg");
        publicacion.addFoto("foto2.jpg");
        publicacion.addFoto("foto3.jpg");
        publicacion.addFoto("foto4.jpg");
        publicacion.addFoto("foto5.jpg");

        // Verificar que las 5 fotos han sido agregadas correctamente
        assertTrue(publicacion.getFotos().contains("foto1.jpg"));
        assertTrue(publicacion.getFotos().contains("foto2.jpg"));
        assertTrue(publicacion.getFotos().contains("foto3.jpg"));
        assertTrue(publicacion.getFotos().contains("foto4.jpg"));
        assertTrue(publicacion.getFotos().contains("foto5.jpg"));

        // Llamar a addFoto con una sexta foto y verificar la excepción
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            publicacion.addFoto("foto6.jpg");
        });

        // Verificar el mensaje de la excepción
        assertEquals("No se puede agregar más de 5 fotos.", exception.getMessage());
    }
    
    @Test
    void testObtenerComentariosPorCategoria() {
        // Crear una categoría de prueba
        Categoria mockCategoria1 = mock(Categoria.class);
        when(mockCategoria1.getConcepto()).thenReturn("Categoria1");
        Categoria mockCategoria2= mock(Categoria.class);
        when(mockCategoria2.getConcepto()).thenReturn("Categoria2"); 

        // Crear reseñas y agregarlas a la publicación
        Resenia resenia1 = new Resenia(mockCategoria1, 4, "Comentario de prueba 1 con Categoria1.");
        Resenia resenia2 = new Resenia(mockCategoria1, 5, "Comentario de prueba 2 con Categoria1.");
        Resenia reseniaDiferente = new Resenia(mockCategoria2, 3, "Comentario de otra categoría.");

        // Agregamos las reseñas a la publicación
        publicacion.agregarResenia(resenia1);
        publicacion.agregarResenia(resenia2);
        publicacion.agregarResenia(reseniaDiferente);

        // Verificar que los comentarios de la categoría correcta son devueltos
        List<String> comentarios = publicacion.obtenerComentariosPorCategoria(mockCategoria1);
        
        // Aseguramos que devuelva los comentarios esperados
        assertEquals(2, comentarios.size());
        assertTrue(comentarios.contains("Comentario de prueba 1 con Categoria1."));
        assertTrue(comentarios.contains("Comentario de prueba 2 con Categoria1."));
    }
    
    @Test
    void testAddSuscriptor() {
        // Crear un mock de EventListener
        EventListener mockSuscriptor = mock(EventListener.class);

        // Agregar el suscriptor
        publicacion.addSuscriptor(mockSuscriptor);

        // Verificar que el suscriptor ha sido agregado
        assertTrue(publicacion.getSuscriptores().contains(mockSuscriptor));
    }
    
    @Test
    void testRemoveSuscriptor() {
        // Crear un mock de EventListener
        EventListener mockSuscriptor = mock(EventListener.class);

        // Agregar el suscriptor primero
        publicacion.addSuscriptor(mockSuscriptor);

        // Asegurarnos de que el suscriptor está en la lista
        assertTrue(publicacion.getSuscriptores().contains(mockSuscriptor));

        // Ahora eliminar el suscriptor
        publicacion.removeSuscriptor(mockSuscriptor);

        // Verificar que el suscriptor ha sido eliminado
        assertFalse(publicacion.getSuscriptores().contains(mockSuscriptor));
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

        publicacion.addPeriodo(mockPeriodo); // Agregar período

        LocalDate entrada = LocalDate.now();
        LocalDate salida = LocalDate.now().plusDays(2);

        // El precio total debería ser 150.0 * 3 (días) = 450.0
        double precioTotal = publicacion.precioEntreFechas(entrada, salida);
        assertEquals(450.0, precioTotal);
    }


    @Test
    void testBajarPrecio() {
        double descuento = 20.0;
        publicacion.bajarPrecioInmueble(descuento);
        
        // Verificamos que el precio base ha cambiado correctamente
        assertEquals(80.0, publicacion.getPrecioBase());  // 100 - 20 = 80
    }
    
    @Test
    public void testCancelarReserva_NotificaCancelacionInmueble() {
        // Crea un mock de Reserva si es necesario
        Reserva reserva = mock(Reserva.class);

        // Llama al método cancelarReserva en Publicacion
        publicacion.recibirReserva(reserva);
        publicacion.cancelarReserva(reserva);

        // Verifica que el método notificarCancelacionInmueble fue llamado en el
        // suscriptor
        verify(suscriptorMock).notificarCancelacionInmueble(mockTipoDeInmueble);
    }

    @Test
    public void testBajarPrecioInmueble_NotificaBajaDePrecio() {
        // Define un nuevo precio para reducir el precio base
        double descuento = 20.0;

        // Llama a bajarPrecioInmueble
        publicacion.bajarPrecioInmueble(descuento);

        // Verifica que el método notificarBajaDePrecioInmbueble fue llamado en el suscriptor con el nuevo precio
        verify(suscriptorMock).notificarBajaDePrecioInmbueble(mockTipoDeInmueble, 80.0); // 100 - 20 = 80
    }

    @Test
    public void testReservaInmueble_NotificaReservaInmueble() {
        // Llama al método reservaInmueble
    	Reserva reserva = mock(Reserva.class);
        publicacion.aceptarReserva(reserva);

        // Verifica que el método notificarReservaInmueble fue llamado en el suscriptor
        verify(suscriptorMock).notificarReservaInmueble(mockTipoDeInmueble);
    }
    
    
    @Test
    void testRecibirReservaSinConflicto() {

        // Ejecutar el método
        publicacion.recibirReserva(reservaMock1);

        // Verificar que la reserva fue agregada a reservas
        assertTrue(publicacion.getReservas().contains(reservaMock1));
        assertFalse(publicacion.getReservasCondicionales().contains(reservaMock1));
    }

    
    @Test
    void testRecibirReservaConConflicto() {
        // Simula conflicto entre reservaMock1 y reservaMock2
        when(reservaMock1.conflictoCon(reservaMock2)).thenReturn(true);
        when(reservaMock2.conflictoCon(reservaMock1)).thenReturn(true);

        // Ejecuta el flujo de reservas
        publicacion.recibirReserva(reservaMock1); // Agregar reservaMock1 sin conflicto
        publicacion.recibirReserva(reservaMock2); // reservaMock2 tiene conflicto, debería ir a reservasCondicionales

        // Verifica que reservaMock1 esté en reservas y reservaMock2 en reservasCondicionales
        assertTrue(publicacion.getReservas().contains(reservaMock1));
        assertFalse(publicacion.getReservasCondicionales().contains(reservaMock1));
        
        assertTrue(publicacion.getReservasCondicionales().contains(reservaMock2));
        assertFalse(publicacion.getReservas().contains(reservaMock2));
    }
    
    @Test
    void testCancelarReservaYMoverCondicionalALaReserva() {
        // Simula conflicto entre reservaMock1 y reservaMock2
        when(reservaMock1.conflictoCon(reservaMock2)).thenReturn(true); // reservaMock1 está en conflicto con reservaMock2
        when(reservaMock2.conflictoCon(reservaMock1)).thenReturn(true); // reservaMock2 está en conflicto con reservaMock1
        when(reservaMock2.estaActiva()).thenReturn(true);
        
        // Ejecuta el flujo de reservas
        publicacion.recibirReserva(reservaMock1); // reservaMock1 irá a reservasCondicionales debido al conflicto con reservaMock2
        publicacion.recibirReserva(reservaMock2); // reservaMock2 irá a reservasCondicionales debido al conflicto con reservaMock1

        // Verifica que ambas reservas estén en reservas condicionales
        assertTrue(publicacion.getReservas().contains(reservaMock1));
        assertFalse(publicacion.getReservasCondicionales().contains(reservaMock1));


        // Ahora, cancelar reservaMock2
        publicacion.cancelarReserva(reservaMock1); // Llamamos a cancelarReserva para cambiar su estado a cancelado


        // La reservaMock1 fue cancelada,  y reservaMock2 debería pasar a reservas activas
        // Verifica que reservaMock2 ya no esté en reservas condicionales
        assertFalse(publicacion.getReservasCondicionales().contains(reservaMock2)); 
        assertTrue(publicacion.getReservas().contains(reservaMock2)); // reservaMock1 debe pasar a reservas activas

   }
    @Test
    void testRechazarReservaYProcesarCondicional() {
        // Simula conflicto entre reservaMock1 y reservaMock2
        when(reservaMock1.conflictoCon(reservaMock2)).thenReturn(true); // reservaMock1 está en conflicto con reservaMock2
        when(reservaMock2.conflictoCon(reservaMock1)).thenReturn(true); // reservaMock2 está en conflicto con reservaMock1
        when(reservaMock2.estaActiva()).thenReturn(true);
        
        // Ejecuta el flujo de reservas
        publicacion.recibirReserva(reservaMock1); // reservaMock1 irá a reservas
        publicacion.recibirReserva(reservaMock2); // reservaMock2 irá a reservasCondicionales debido al conflicto con reservaMock1

        // Verifica que ambas reservas estén sus respectivas listas
        assertTrue(publicacion.getReservas().contains(reservaMock1));
        assertTrue(publicacion.getReservasCondicionales().contains(reservaMock2));


        // Ahora, rechazar reservaMock1
        publicacion.rechazarReserva(reservaMock1); // Llamamos a rechazarReserva para cambiar su estado a cancelado
    
        // Verifica que la reservaMock2 haya sido procesada y movida a reservas activas
        assertFalse(publicacion.getReservasCondicionales().contains(reservaMock2)); // reservaMock2 no debe estar en reservas condicionales
       
        // Verifica que reservaMock2 haya pasado de condicionales a reservas activas
        assertFalse(publicacion.getReservasCondicionales().contains(reservaMock2)); // reservaMock2 ya no debe estar en condicionales
        assertTrue(publicacion.getReservas().contains(reservaMock2)); // reservaMock2 debe pasar a reservas activas
    }
    
    @Test
    void testEsDeCiudad() {
        // Verifica que el país se haya asignado correctamente
        assertFalse(publicacion.esDeCiudad("Cordoba"));
    }
    
    @Test
    void testEsPropietario() {
        // Verifica que el país se haya asignado correctamente
        assertTrue(publicacion.esPropietario(mockPropietario));
    }
    
    

}
