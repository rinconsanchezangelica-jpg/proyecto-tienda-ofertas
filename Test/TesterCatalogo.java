import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba la clase CatalogoProductos con JUnit 5: agregar, buscar,
 * eliminar y verificar que listar devuelve una copia.
 *
 * @author Angelica
 * @version 26/11/2026
 */
public class TesterCatalogo {

    private CatalogoProductos cat;
    private Producto a;
    private Producto b;
    private Producto c;

    @BeforeEach
    void setUp() {
        cat = new CatalogoProductos();
        a = new Producto("Audifonos", "TiendaX", 100000, 20, 3);
        b = new Producto("Mouse", "TiendaY", 50000, 0, 1);
        c = new Producto("Teclado", "TiendaX", 80000, 15, 0);
        cat.agregar(a);
        cat.agregar(b);
        cat.agregar(c);
    }

    @Test
    void agregar() {
        assertEquals(3, cat.size());
    }

    @Test
    void buscarPorId() {
        assertSame(b, cat.buscarPorId(b.getId()));
    }

    @Test
    void buscarPorNombre() {
        assertSame(b, cat.buscarPorNombre("mouse"));
    }

    @Test
    void buscarInexistente() {
        assertNull(cat.buscarPorNombre("Monitor"));
    }

    @Test
    void agregarNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> cat.agregar(null));
    }

    @Test
    void eliminar() {
        Producto eliminado = cat.eliminar(b.getId());
        assertSame(b, eliminado);
        assertEquals(2, cat.size());
    }

    @Test
    void listarDevuelveCopia() {
        cat.listar().clear();
        assertEquals(3, cat.size());
    }
}
