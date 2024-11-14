package periodo;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PeriodoTest {

    private Periodo periodo;

    @BeforeEach
    public void setUp() {
        periodo = new Periodo("Temporada Alta", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31), 150.0);
    }

    @Test
    void testConstructor() {
        assertEquals("Temporada Alta", periodo.getConcepto(), "El concepto debe ser 'Temporada Alta'");
        assertEquals(LocalDate.of(2023, 12, 1), periodo.getInicio(), "La fecha de inicio debe ser el 1 de diciembre de 2023");
        assertEquals(LocalDate.of(2023, 12, 31), periodo.getFin(), "La fecha de fin debe ser el 31 de diciembre de 2023");
        assertEquals(150.0, periodo.getPrecio(), "El precio debe ser 150.0");
    }

}
