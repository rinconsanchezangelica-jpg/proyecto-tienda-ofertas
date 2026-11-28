import java.util.ArrayList;
import java.util.List;
/**
 * Pruebas de GestorPedidos (crear, renombrar, eliminar, listar y cascada de
 * stock) usando los datos de ejemplo. Cada prueba imprime OK o FALLO.
 *
 * @author Sergio
 * @version v2
 */
public class TesterGestor {
    private static int fallos = 0;

    public static void main(String[] args) {
        // 1. Registrar productos en CatalogoProductos
        CatalogoProductos catalogo = new CatalogoProductos();
        for (Producto p : cargarDatosEjemplo())
            catalogo.agregar(p);
        System.out.println("Productos cargados para prueba: " + catalogo.size());

        Producto mouse     = catalogo.buscarPorNombre("Mouse Inalambrico");   // stock 12, 15%
        Producto teclado   = catalogo.buscarPorNombre("Teclado Mecanico");    // stock 1, 10%
        Producto audifonos = catalogo.buscarPorNombre("Audifonos USB-C");     // stock 0

        test_crear();
        test_stockNormal(mouse);
        test_stockUno(teclado);
        test_stockCero(audifonos);
        test_totalesYAhorro(catalogo);
        test_renombrar();
        test_eliminarYCascada(catalogo);

        System.out.println();
        System.out.println(fallos == 0 ? "TODAS LAS PRUEBAS PASARON" : "PRUEBAS FALLIDAS: " + fallos);
    }

    // ---------- pruebas ----------

    static void test_crear() {
        System.out.println("\n-- crear / buscar / listar --");
        GestorPedidos g = new GestorPedidos();
        g.crear("Compra 1");
        g.crear("Compra 2");
        check("se crean 2 pedidos", g.size() == 2);
        check("listar devuelve 2", g.listar().size() == 2);
        check("buscar ignora mayusculas", g.buscar("compra 1") != null);
        check("buscar inexistente da null", g.buscar("Nada") == null);
        check("nombre repetido lanza excepcion", lanza(() -> g.crear("compra 1")));
        check("nombre vacio lanza excepcion", lanza(() -> g.crear("   ")));
        check("nombre null lanza excepcion", lanza(() -> g.crear(null)));
        g.listar().clear();   // modificar la copia no debe afectar al gestor
        check("listar devuelve una copia", g.size() == 2);
    }

    static void test_stockNormal(Producto mouse) {
        System.out.println("\n-- regla de stock: stock normal --");
        GestorPedidos g = new GestorPedidos();
        Pedido p = g.crear("Pedido mouse");
        int antes = mouse.getStock();
        p.agregar(mouse);
        p.agregar(mouse);
        check("agregar 2 descuenta 2 del stock", mouse.getStock() == antes - 2);
        check("el pedido tiene 2 productos", p.cantidadProductos() == 2);
        check("quitar uno devuelve 1 de stock", p.quitar(mouse) && mouse.getStock() == antes - 1);
        check("quitar algo que no esta da false", !p.quitar(new Producto("Otro", "X", 1000, 0, 5)));
        p.liberar();   // dejar el stock como estaba para las demas pruebas
    }

    static void test_stockUno(Producto teclado) {
        System.out.println("\n-- regla de stock: stock = 1 --");
        GestorPedidos g = new GestorPedidos();
        Pedido p = g.crear("Pedido teclado");
        check("stock inicial es 1", teclado.getStock() == 1);
        p.agregar(teclado);
        check("la primera unidad se agrega y el stock queda en 0", teclado.getStock() == 0);
        check("la segunda unidad lanza excepcion", lanza(() -> p.agregar(teclado)));
        check("el pedido sigue con 1 producto", p.cantidadProductos() == 1);
        g.eliminar("Pedido teclado");
        check("al eliminar el pedido el stock vuelve a 1", teclado.getStock() == 1);
    }

    static void test_stockCero(Producto audifonos) {
        System.out.println("\n-- regla de stock: stock = 0 --");
        GestorPedidos g = new GestorPedidos();
        Pedido p = g.crear("Pedido audifonos");
        check("agregar con stock 0 lanza excepcion", lanza(() -> p.agregar(audifonos)));
        check("el stock sigue en 0", audifonos.getStock() == 0);
        check("el pedido sigue vacio", p.cantidadProductos() == 0);
        check("agregar null lanza excepcion", lanza(() -> p.agregar(null)));
    }

    static void test_totalesYAhorro(CatalogoProductos catalogo) {
        System.out.println("\n-- total y ahorro --");
        GestorPedidos g = new GestorPedidos();
        Pedido p = g.crear("Con totales");
        Producto mouse = catalogo.buscarPorNombre("Mouse Inalambrico");     // 85000 -15% = 72250
        Producto teclado = catalogo.buscarPorNombre("Teclado Mecanico");    // 210000 -10% = 189000
        p.agregar(mouse);
        p.agregar(mouse);
        p.agregar(teclado);
        check("total sin descuentos = 380000", casi(p.precioTotalSinDescuentos(), 380000));
        check("total con descuentos = 333500", casi(p.precioTotalConDescuentos(), 333500));
        check("ahorro = 46500", casi(p.totalDescuentos(), 46500));
        g.eliminar("Con totales");
    }

    static void test_renombrar() {
        System.out.println("\n-- renombrar --");
        GestorPedidos g = new GestorPedidos();
        g.crear("Uno");
        g.crear("Dos");
        check("renombrar devuelve true", g.renombrar("Uno", "Primero"));
        check("el nombre viejo ya no existe", g.buscar("Uno") == null);
        check("el nombre nuevo existe", g.buscar("Primero") != null);
        check("renombrar a un nombre usado lanza excepcion", lanza(() -> g.renombrar("Primero", "dos")));
        check("renombrar a vacio lanza excepcion", lanza(() -> g.renombrar("Primero", " ")));
        check("renombrar inexistente da false", !g.renombrar("Nada", "Otro"));
        check("cambiar solo mayusculas esta permitido", g.renombrar("Primero", "PRIMERO"));
        check("el pedido conserva el nombre nuevo", g.buscar("primero").getNombre().equals("PRIMERO"));
        check("sigue habiendo 2 pedidos", g.size() == 2);
    }

    static void test_eliminarYCascada(CatalogoProductos catalogo) {
        System.out.println("\n-- eliminar con cascada de stock --");
        GestorPedidos g = new GestorPedidos();
        Producto aceite = catalogo.buscarPorNombre("Aceite de Oliva 1L");   // stock 6
        Producto termo  = catalogo.buscarPorNombre("Termo Acero 750ml");    // stock 4
        int aceiteAntes = aceite.getStock();
        int termoAntes  = termo.getStock();

        Pedido p = g.crear("Para eliminar");
        p.agregar(aceite);
        p.agregar(aceite);
        p.agregar(aceite);
        p.agregar(termo);
        check("el stock bajo (aceite -3, termo -1)",
              aceite.getStock() == aceiteAntes - 3 && termo.getStock() == termoAntes - 1);

        Pedido eliminado = g.eliminar("para eliminar");
        check("eliminar devuelve el pedido", eliminado == p);
        check("el pedido ya no esta en el gestor", g.buscar("Para eliminar") == null && g.size() == 0);
        check("el stock se restauro (cascada)",
              aceite.getStock() == aceiteAntes && termo.getStock() == termoAntes);
        check("eliminar inexistente da null", g.eliminar("Nada") == null);
    }

    // ---------- datos de ejemplo ----------

    public static List<Producto> cargarDatosEjemplo() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Mouse Inalambrico", "Tienda Tech", 85000.0, 15, 12));
        productos.add(new Producto("Teclado Mecanico", "Tienda Tech", 210000.0, 10, 1));
        productos.add(new Producto("Audifonos USB-C", "Tienda Tech", 140000.0, 0, 0));
        productos.add(new Producto("Cafe Especial 500g", "Mercado Central", 38000.0, 5, 20));
        productos.add(new Producto("Aceite de Oliva 1L", "Mercado Central", 64000.0, 20, 6));
        productos.add(new Producto("Miel Organica 350g", "Mercado Central", 29000.0, 0, 8));
        productos.add(new Producto("Lampara de Escritorio", "Hogar & Deco", 95000.0, 25, 5));
        productos.add(new Producto("Organizador de Cable", "Hogar & Deco", 22000.0, 12, 15));
        productos.add(new Producto("Termo Acero 750ml", "Hogar & Deco", 72000.0, 30, 4));
        return productos;
    }

    // ---------- ayudas ----------

    static void check(String descripcion, boolean ok) {
        if (!ok) fallos++;
        System.out.println((ok ? "OK    " : "FALLO ") + descripcion);
    }

    /** true si la accion lanza una excepcion. */
    static boolean lanza(Runnable accion) {
        try {
            accion.run();
            return false;
        } catch (RuntimeException e) {
            return true;
        }
    }

    static boolean casi(double a, double b) {
        return Math.abs(a - b) < 0.01;
    }
}