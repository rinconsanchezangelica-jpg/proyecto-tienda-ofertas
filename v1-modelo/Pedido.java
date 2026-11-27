import java.util.ArrayList;

/**
 * Un pedido con nombre. Guarda un producto por cada unidad pedida.
 * Regla de negocio: al agregar un producto se descuenta una unidad de su
 * stock; si no hay stock se lanza una excepcion. Al quitar un producto, o
 * al liberar todo el pedido, el stock se devuelve.
 *
 * Usa de Producto: hayStock(), descontarStock(), devolverStock(),
 * getPrecioNormal() y getPrecioFinal().
 *
 * @author Erick
 * @version 1.1v
 */
public class Pedido
{
    private String nombre;
    private ArrayList<Producto> pedido;

    public Pedido(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
        this.pedido = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del pedido no puede estar vacio");
    }

    /** Agrega una unidad del producto. Lanza excepcion si no hay stock. */
    public void agregar(Producto p) {
        if (p == null)
            throw new IllegalArgumentException("El producto no puede estar vacio");
        if (!p.hayStock())
            throw new IllegalStateException("Sin stock: " + p.getNombre());
        p.descontarStock();
        pedido.add(p);
    }

    /** Quita una unidad del producto y devuelve el stock. false si no estaba. */
    public boolean quitar(Producto p) {
        boolean estaba = pedido.remove(p);
        if (estaba) p.devolverStock();
        return estaba;
    }

    /** Vacia el pedido devolviendo todo el stock (se usa al eliminar el pedido). */
    public void liberar() {
        for (Producto p : pedido)
            p.devolverStock();
        pedido.clear();
    }

    public ArrayList<Producto> getProductos() {
        return new ArrayList<>(pedido);   // copia, para no modificar el pedido desde afuera
    }

    public int cantidadProductos() {
        return pedido.size();
    }

    public double precioTotalSinDescuentos () {
        double total = 0;
        for (Producto p : pedido) {
            total += p.getPrecioNormal();
        }
        return total;
    }

    public double precioTotalConDescuentos () {
        double total = 0;
        for (Producto p : pedido) {
            total += p.getPrecioFinal();
        }
        return total;
    }

    public double totalDescuentos () {
        return precioTotalSinDescuentos() - precioTotalConDescuentos();
    }

    @Override
    public String toString() {
        return nombre + " (" + pedido.size() + " productos) - Total: $" + precioTotalConDescuentos()
             + " - Ahorro: $" + totalDescuentos();
    }
}