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
    private ArrayList <DetallePedido> pedido = new ArrayList<DetallePedido>();
    
    public DetallePedido (Producto producto, int cantidad) {
        if (producto == null ) {
            throw new IllegalArgumentException ("Tiene que agregar un producto valido");
        }
        else if (cantidad <= 0 ) {
            throw new IllegalStateException ("El valor debe ser un numero valido");
        }
        else if (cantidad > producto.getStock()) {
            throw new IllegalStateException("No puede elegir mas que el stock del producto.");
        }
        else {
        this.producto = producto;
        this.cantidad = cantidad;
        }
    }

    public Producto getProducto () {
        return this.producto;
    }

    public int getCantidadProducto() {
        return this.cantidad;
    }





}