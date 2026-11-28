import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Write a description of class TesterPedido here.
 * 
 * @author Erick
 * @version 1.1V
 */
public class TesterPedido {


    @Test
    void test_masStockDelPermitido () {
        Producto p1 = new Producto ("Perfume", "Falabella", 100000 , 10 , 15);
        Producto p2 = new Producto ("Camisa", "Shein", 80000 , 5 , 20);
        Producto p3 = new Producto ("Pantalon", "Levis", 120000 , 20 , 8);
        
        assertThrows (IllegalStateException.class, () -> {DetallePedido d1 = new DetallePedido(p1, 20);});
    }
    @Test
    void test_productoNormal () {
        Producto p1 = new Producto ("Perfume", "Falabella", 100000 , 10 , 15);
    
        assertDoesNotThrow(() -> {DetallePedido d1 = new DetallePedido(p1, 2 );});
    }
    @Test
    void test_productoNull_lanzaIllegalArgumentException () {
        Producto p1 = new Producto ("Perfume", "Falabella",
                100000 , 10 , 15);

        assertThrows(IllegalArgumentException.class,
                () -> {DetallePedido d1 = new DetallePedido(null , 4); });
    }
    @Test
    void test_anadirNumeroNegativo_lanzaIllegalStateException () {
        Producto p1 = new Producto ("Perfume", "Falabella",
                100000 , 10 , 15);
        assertThrows(IllegalStateException.class, () -> {DetallePedido d1 = new DetallePedido(p1 , -5);});

    }
    @Test
    void test_anadirCero_lanzaIllegalStateException () {
        Producto p1 = new Producto ("Perfume", "Falabella",
                100000 , 10 , 15);
        assertThrows(IllegalStateException.class, () -> {DetallePedido d1 = new DetallePedido(p1 , 0);});

    }







}