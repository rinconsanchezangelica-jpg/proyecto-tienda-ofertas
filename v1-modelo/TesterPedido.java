import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Write a description of class TesterPedido here.
 * 
 * @author Erick
 * @version 1.0V
 */
public class TesterPedido {
    public static void main (String [] args) {
        test_masStockDelPermitido();
        test_productoNormal();
    }

    static void test_masStockDelPermitido () {
        Producto p1 = new Producto ("Perfume", "Falabella", 100000 , 10 , 15);
        Producto p2 = new Producto ("Camisa", "Shein", 80000 , 5 , 20);
        Producto p3 = new Producto ("Pantalon", "Levis", 120000 , 20 , 8);
        
        //Lanza una excepcion al querer crear agregar mas stock del que tiene un producto
        DetallePedido d1 = new DetallePedido(p1, 20);
    }
    static void test_productoNormal () {
        Producto p1 = new Producto ("Perfume", "Falabella", 100000 , 10 , 15);
    
        DetallePedido d1 = new DetallePedido(p1, 2 );
    }






}