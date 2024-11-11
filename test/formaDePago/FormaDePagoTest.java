package formaDePago;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FormaDePagoTest {

    @Test
    void testNumeroDeFormasDePago() {
        assertEquals(3, FormaDePago.values().length);
    }

    @Test
    void testDescripcionEfectivo() {
        assertEquals("Efectivo", FormaDePago.EFECTIVO.getDescripcion());
    }

    @Test
    void testDescripcionDebito() {
        assertEquals("Débito", FormaDePago.DEBITO.getDescripcion());
    }

    @Test
    void testDescripcionCredito() {
        assertEquals("Crédito", FormaDePago.CREDITO.getDescripcion());
    }

    @Test
    void testValoresEnum() {
        assertSame(FormaDePago.EFECTIVO, FormaDePago.valueOf("EFECTIVO"));
        assertSame(FormaDePago.DEBITO, FormaDePago.valueOf("DEBITO"));
        assertSame(FormaDePago.CREDITO, FormaDePago.valueOf("CREDITO"));
    }

    @Test
    void testNoExistenOtrosValoresEnum() {
        assertThrows(IllegalArgumentException.class, () -> {
            FormaDePago.valueOf("OTRO");
        });
    }
}