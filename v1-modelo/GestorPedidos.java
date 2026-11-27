import java.util.ArrayList;

/**
 * Administra los pedidos: crear, buscar, renombrar, eliminar y listar.
 * Los nombres de pedido son unicos (sin importar mayusculas/minusculas).
 * Al eliminar un pedido se devuelve el stock de todos sus productos (cascada).
 * Las busquedas devuelven null cuando no encuentran nada, igual que CatalogoProductos.
 *
 * @author Sergio
 * @version v1
 */
public class GestorPedidos
{
    private ArrayList<Pedido> pedidos;

    public GestorPedidos() {
        pedidos = new ArrayList<>();
    }

    /** Crea un pedido vacio. Lanza excepcion si el nombre esta vacio o ya existe. */
    public Pedido crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del pedido no puede estar vacio");
        if (buscar(nombre) != null)
            throw new IllegalArgumentException("Ya existe un pedido llamado: " + nombre.trim());
        Pedido p = new Pedido(nombre);
        pedidos.add(p);
        return p;
    }

    public Pedido buscar(String nombre) {
        if (nombre == null) return null;
        for (Pedido p : pedidos)
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) return p;
        return null;
    }

    /**
     * Cambia el nombre de un pedido. Devuelve false si no existe el pedido viejo.
     * Lanza excepcion si el nombre nuevo esta vacio o lo usa otro pedido.
     */
    public boolean renombrar(String nombreViejo, String nombreNuevo) {
        Pedido p = buscar(nombreViejo);
        if (p == null) return false;
        if (nombreNuevo == null || nombreNuevo.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del pedido no puede estar vacio");
        Pedido otro = buscar(nombreNuevo);
        if (otro != null && otro != p)    // "otro != p" deja cambiar solo mayusculas: "compra" -> "Compra"
            throw new IllegalArgumentException("Ya existe un pedido llamado: " + nombreNuevo.trim());
        p.setNombre(nombreNuevo);
        return true;
    }

    /** Elimina el pedido y devuelve el stock de sus productos. Devuelve null si no existe. */
    public Pedido eliminar(String nombre) {
        Pedido p = buscar(nombre);
        if (p == null) return null;
        p.liberar();                // cascada: el stock vuelve a los productos
        pedidos.remove(p);
        return p;
    }

    public ArrayList<Pedido> listar() {
        return new ArrayList<>(pedidos);   // copia, para no modificar la lista desde afuera
    }

    public int size() {
        return pedidos.size();
    }
}