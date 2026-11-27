import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba la clase Producto con JUnit 5: calculo del precio final,
 * validaciones del constructor y manejo del stock.
 *
 * @author Angelica
 * @version 26/11/2026
 */
public class ProductoTest {

    private Producto a;
    private Producto b;

    @BeforeEach
    void setUp() {
        a = new Producto("Audifonos", "TiendaX", 100000, 20, 3);
        b = new Producto("Mouse", "TiendaY", 50000, 0, 1);
    }

    @Test
    void idsConsecutivos() {
        assertEquals(a.getId() + 1, b.getId());
    }

    @Test
    void precioFinalYAhorro() {
        assertEquals(80000, a.getPrecioFinal());
        assertEquals(20000, a.getAhorroUnitario());
    }

    @Test
    void productoSinDescuento() {
        assertEquals(0, b.getAhorroUnitario());
    }

    @Test
    void nombreVacioLanzaExcepcion() {
        assertThrows(RuntimeException.class,
                () -> new Producto("  ", "TiendaX", 1000, 10, 5));
    }

    @Test
    void precioNegativoLanzaExcepcion() {
        assertThrows(RuntimeException.class,
                () -> new Producto("Cable", "TiendaX", -5, 10, 5));
    }

    @Test
    void descuentoMayorA90LanzaExcepcion() {
        assertThrows(RuntimeException.class,
                () -> new Producto("Cable", "TiendaX", 1000, 91, 5));
    }

    @Test
    void stockNegativoLanzaExcepcion() {
        assertThrows(RuntimeException.class,
                () -> new Producto("Cable", "TiendaX", 1000, 10, -1));
    }

    @Test
    void descontarStock() {
        a.descontarStock();
        assertEquals(2, a.getStock());
    }

    @Test
    void devolverStock() {
        a.descontarStock();
        a.devolverStock();
        assertEquals(3, a.getStock());
    }

    @Test
    void descontarSinStockLanzaExcepcion() {
        b.descontarStock();   // b tenia stock 1, ahora queda en 0
        assertThrows(RuntimeException.class, () -> b.descontarStock());
    }
}
