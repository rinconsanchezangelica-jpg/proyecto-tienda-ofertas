/**
 * Representa un producto de una tienda, con su precio, descuento y stock.
 * Cada producto recibe un ID automatico al crearse. Si algun dato es
 * invalido (nombre vacio, precio menor o igual a 0, descuento fuera de
 * 0 a 90 o stock negativo) se lanza una excepcion.
 *
 * @author Angelica
 * @version 26/11/2026
 */
public class Producto {
    private static int contador = 0;  

    private int id;
    private String nombre;
    private String tienda;
    private double precioNormal;
    private int descuento;             // porcentaje entre 0 y 90
    private int stock;

    public Producto(String nombre, String tienda, double precioNormal, int descuento, int stock) {
        // Primero se valida todo; el ID se asigna solo si los datos son validos
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (tienda == null || tienda.trim().isEmpty())
            throw new IllegalArgumentException("La tienda no puede estar vacia");
        if (precioNormal <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        if (descuento < 0 || descuento > 90)
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 90");
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");

        this.id = ++contador;
        this.nombre = nombre.trim();
        this.tienda = tienda.trim();
        this.precioNormal = precioNormal;
        this.descuento = descuento;
        this.stock = stock;
    }

    public int getId() { 
        return id; }
    public String getNombre() { 
        return nombre; }
    public String getTienda() { 
        return tienda; }
    public double getPrecioNormal() { 
        return precioNormal; }
    public int getDescuento() { 
        return descuento; }
    public int getStock() { 
        return stock; }

    public double getPrecioFinal() {
        return precioNormal * (1 - descuento / 100.0);
    }

    public double getAhorroUnitario() {
        return precioNormal - getPrecioFinal();
    }

    public boolean hayStock() {
        return stock > 0;
    }

    public void descontarStock() {
        if (stock == 0)
            throw new IllegalStateException("Sin stock: " + nombre);
        stock--;
    }

    public void devolverStock() {
        stock++;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " - " + tienda
             + " - $" + precioNormal + " (-" + descuento + "%) = $" + getPrecioFinal()
             + " - Stock: " + stock;
    }
}