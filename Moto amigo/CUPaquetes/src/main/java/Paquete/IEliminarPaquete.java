
package Paquete;

import com.mycompany.paquetesdto.EliminarPaqueteDTO;

/**
 *
 * @author joset
 */
public interface IEliminarPaquete {

    /**
     * Elimina un paquete identificado por el DTO proporcionado.
     *
     * @param paquete datos del paquete a eliminar (contiene su id).
     * @return true si se eliminó exitosamente, false en caso contrario.
     */
    boolean eliminar(EliminarPaqueteDTO paquete)throws PaqueteException;
}
