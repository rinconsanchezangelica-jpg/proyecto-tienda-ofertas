/**
 * Prueba la clase Producto: calculo del precio final, validaciones
 * del constructor y manejo del stock.
 *
 * @author Angelica
 * @version 26/11/2026
 */
public class TesterProducto {

    public static void main(String[] args) {
        Producto a = new Producto("Audifonos", "TiendaX", 100000, 20, 3);
        Producto b = new Producto("Mouse", "TiendaY", 50000, 0, 1);

        // Prueba 1: IDs consecutivos
        if (b.getId() == a.getId() + 1) {
            System.out.println("IDs consecutivos: OK");
        } else {
            System.out.println("IDs consecutivos: FALLO");
        }

        // Prueba 2: precio final y ahorro
        if (a.getPrecioFinal() == 80000 && a.getAhorroUnitario() == 20000) {
            System.out.println("Precio final y ahorro: OK");
        } else {
            System.out.println("Precio final y ahorro: FALLO");
        }

        // Prueba 3: producto sin descuento
        if (b.getAhorroUnitario() == 0) {
            System.out.println("Sin descuento: OK");
        } else {
            System.out.println("Sin descuento: FALLO");
        }

        // Prueba 4: nombre vacio debe lanzar excepcion
        try {
            new Producto("  ", "TiendaX", 1000, 10, 5);
            System.out.println("Nombre vacio: FALLO");
        } catch (RuntimeException e) {
            System.out.println("Nombre vacio: OK");
        }

        // Prueba 5: precio negativo debe lanzar excepcion
        try {
            new Producto("Cable", "TiendaX", -5, 10, 5);
            System.out.println("Precio negativo: FALLO");
        } catch (RuntimeException e) {
            System.out.println("Precio negativo: OK");
        }

        // Prueba 6: descuento mayor a 90 debe lanzar excepcion
        try {
            new Producto("Cable", "TiendaX", 1000, 91, 5);
            System.out.println("Descuento 91: FALLO");
        } catch (RuntimeException e) {
            System.out.println("Descuento 91: OK");
        }

        // Prueba 7: stock negativo debe lanzar excepcion
        try {
            new Producto("Cable", "TiendaX", 1000, 10, -1);
            System.out.println("Stock negativo: FALLO");
        } catch (RuntimeException e) {
            System.out.println("Stock negativo: OK");
        }

        // Prueba 8: descontar y devolver stock
        a.descontarStock();
        if (a.getStock() == 2) {
            System.out.println("Descontar stock: OK");
        } else {
            System.out.println("Descontar stock: FALLO");
        }
        a.devolverStock();
        if (a.getStock() == 3) {
            System.out.println("Devolver stock: OK");
        } else {
            System.out.println("Devolver stock: FALLO");
        }

        // Prueba 9: descontar con stock 0 debe lanzar excepcion
        b.descontarStock();   // b tenia stock 1, ahora queda en 0
        try {
            b.descontarStock();
            System.out.println("Descontar sin stock: FALLO");
        } catch (RuntimeException e) {
            System.out.println("Descontar sin stock: OK");
        }
    }
}