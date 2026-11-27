import java.util.ArrayList;

/**
 * 
 * Author: Erick
 * Version: 1.0V
 * 
 * 
 */

public class DetallePedido {
    private Producto producto;
    private int cantidad;
    
    public DetallePedido (Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException ("Tiene que agregar un producto valido");
        }
        if (cantidad >= 0 ) {
            throw new IllegalStateException ("El valor debe ser un numero valido");
        }

        this.producto = producto;
        this.cantidad = cantidad;
    }
}