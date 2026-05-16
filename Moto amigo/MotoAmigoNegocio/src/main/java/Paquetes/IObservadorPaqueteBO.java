
package Paquetes;

import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;

/**
 * 
 * @author joset
 */
public interface IObservadorPaqueteBO {

    /**
     * Notifica que se creó un paquete.
     *
     * @param paquete paquete recién creado.
     */
    void paqueteCreado(PaqueteDTO paquete);

    /**
     * Notifica que se editó un paquete.
     *
     * @param paquete paquete editado.
     */
    void paqueteEditado(PaqueteDTO paquete);

    /**
     * Notifica que se eliminó un paquete.
     *
     * @param idPaquete identificador del paquete eliminado.
     */
    void paqueteEliminado(String idPaquete);

    /**
     * Notifica que se agregó un producto a un paquete.
     *
     * @param idPaquete identificador del paquete.
     * @param producto producto agregado.
     */
    void productoAgregadoAPaquete(String idPaquete, ProductoDTO producto);

    /**
     * Notifica que se quitó un producto de un paquete.
     *
     * @param idPaquete identificador del paquete.
     * @param producto producto quitado.
     */
    void productoQuitadoDePaquete(String idPaquete, ProductoDTO producto);
}
