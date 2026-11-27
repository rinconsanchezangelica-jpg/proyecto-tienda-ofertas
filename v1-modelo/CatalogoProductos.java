import java.util.ArrayList;

/**
 * Guarda y administra la lista de productos. Permite agregar, buscar
 * por id o por nombre, eliminar y listar los productos.
 * Las busquedas devuelven null cuando no encuentran nada.
 *
 * @author Angelica
 * @version 26/11/2026
 */
public class CatalogoProductos {
    private ArrayList<Producto> productos;

    public CatalogoProductos() {
        productos = new ArrayList<>();
    }

    public void agregar(Producto p) {
        if (p == null)
            throw new IllegalArgumentException("El producto no puede estar vacio");
        productos.add(p);
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos)
            if (p.getId() == id) return p;
        return null;
    }

    public Producto buscarPorNombre(String nombre) {
        if (nombre == null) return null;
        for (Producto p : productos)
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) return p;
        return null;
    }

   
    public Producto eliminar(int id) {
        Producto p = buscarPorId(id);
        if (p != null) productos.remove(p);
        return p;
    }

    public ArrayList<Producto> listar() {
        return new ArrayList<>(productos);   // copia para recorrer los productos del catalogo, para evitar modificaciones 
    }

    public int size() {
        return productos.size();
    }
}