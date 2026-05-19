package Paquete;

import Paquetes.PaquetesBO;
import com.mycompany.paquetesdto.EliminarPaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import Paquetes.IPaquetesBO;

/**
 *
 * @author joset
 */
public class EliminarPaquete implements IEliminarPaquete {

    private final IPaquetesBO paqueteBO;

    public EliminarPaquete() {
        this.paqueteBO = PaquetesBO.getInstancia();
    }

    @Override
    public boolean eliminar(EliminarPaqueteDTO paquete)throws PaqueteException {
        if ( paquete.getId() == null ||  paquete.getId().isBlank()) {
            throw new PaqueteException("El id del paquete a eliminar es obligatorio.");
        }
        try {
            if (paquete == null || paquete.getId()== null) {
                return false;
            }
            return paqueteBO.eliminarPaquete(paquete.getId());
        } catch (NegocioException ex) {
            throw new PaqueteException("Error al eliminar producto" ,ex);
        }
    }
}
