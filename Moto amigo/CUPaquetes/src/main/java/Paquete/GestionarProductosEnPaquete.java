
package Paquete;

import com.mycompany.productosdto.ProductoDTO;

/**
 *
 * @author joset
 */
public class GestionarProductosEnPaquete implements IGestionarProductosEnPaquete {

    // TODO:  IPaqueteBO .
    public GestionarProductosEnPaquete() {
    }

    @Override
    public boolean agregarProducto(String idPaquete, ProductoDTO producto) {
        // TODO: PaqueteBO.
        return false;
    }

    @Override
    public boolean quitarProducto(String idPaquete, ProductoDTO producto) {
        // TODO: PaqueteBO.
        return false;
    }

    @Override
    public float calcularPeso(String idPaquete) {
        // TODO: PaqueteBO.
        return 0.0f;
    }
}
