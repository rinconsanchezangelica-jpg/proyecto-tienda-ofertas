import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class TesterGestor here.
 * 
 * @author Sergio 
 * @version v1
 */
public class TesterGestor {

    public static void main(String[] args) {
        List<Producto> productosEjemplo = cargarDatosEjemplo();

        // Aquí continúas con las pruebas de CatalogoProductos, Pedido y GestorPedidos:
        // 1. Registrar productos en CatalogoProductos
        // 2. Crear pedidos y probar stock = 0 y stock = 1
        // 3. Renombrar y listar pedidos
        // 4. Eliminar un pedido y verificar restauración de stock en cascada
        System.out.println("Productos cargados para prueba: " + productosEjemplo.size());
    }

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
}