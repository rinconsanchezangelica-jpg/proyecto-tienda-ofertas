/**
 * Prueba la clase CatalogoProductos: agregar, buscar, eliminar
 * y verificar que listar devuelve una copia.
 *
 * @author Angelica
 * @version 26/11/2026
 */
public class TesterCatalogo {

    public static void main(String[] args) {
        CatalogoProductos cat = new CatalogoProductos();

        // Crear productos de prueba
        Producto a = new Producto("Audifonos", "TiendaX", 100000, 20, 3);
        Producto b = new Producto("Mouse", "TiendaY", 50000, 0, 1);
        Producto c = new Producto("Teclado", "TiendaX", 80000, 15, 0);

        // Prueba 1: agregar
        cat.agregar(a);
        cat.agregar(b);
        cat.agregar(c);
        if (cat.size() == 3) {
            System.out.println("Agregar: OK");
        } else {
            System.out.println("Agregar: FALLO");
        }

        // Prueba 2: buscar por id
        if (cat.buscarPorId(b.getId()) == b) {
            System.out.println("Buscar por id: OK");
        } else {
            System.out.println("Buscar por id: FALLO");
        }

        // Prueba 3: buscar por nombre 
        if (cat.buscarPorNombre("mouse") == b) {
            System.out.println("Buscar por nombre: OK");
        } else {
            System.out.println("Buscar por nombre: FALLO");
        }

        // Prueba 4: buscar algo que no existe
        if (cat.buscarPorNombre("Monitor") == null) {
            System.out.println("Buscar inexistente: OK");
        } else {
            System.out.println("Buscar inexistente: FALLO");
        }

        // Prueba 5: agregar null debe lanzar excepcion
        try {
            cat.agregar(null);
            System.out.println("Agregar null: FALLO");
        } catch (IllegalArgumentException e) {
            System.out.println("Agregar null: OK");
        }

        // Prueba 6: eliminar
        Producto eliminado = cat.eliminar(b.getId());
        if (eliminado == b && cat.size() == 2) {
            System.out.println("Eliminar: OK");
        } else {
            System.out.println("Eliminar: FALLO");
        }

        // Prueba 7: listar devuelve una copia, y si se vacia no se arriesga la originss
        cat.listar().clear();
        if (cat.size() == 2) {
            System.out.println("Listar (copia): OK");
        } else {
            System.out.println("Listar (copia): FALLO");
        }
    }
}