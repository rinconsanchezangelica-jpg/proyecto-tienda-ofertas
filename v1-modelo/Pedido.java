import java.util.ArrayList;

/**
 * Write a description of class Pedido here.
 * 
 * @author Erick
 * @version 1.0v
 */
public class Pedido
{
    ArrayList <Producto> pedido;
    
    

    
    public Pedido(ArrayList <Producto> productos) {
        if (productos.size() == 0 ) {
            throw new IllegalStateException("Su pedido no tiene ningun producto.");
        }
        this.pedido = productos;
        
    }

    public int precioTotalSinDescuentos () {
        int total = 0;
        for (Producto p : pedido) {
            total += p.getPrecioNormal();
        }
        return total;
    }


    public int precioTotalConDescuentos () {
        int total = 0;
        for (Producto p : pedido) {
            total += p.getPrecioFinal();
        }
        return total;
    }

    public int totalDescuentos () {
        return precioTotalSinDescuentos() - precioTotalConDescuentos(); 
    }


    
   
}