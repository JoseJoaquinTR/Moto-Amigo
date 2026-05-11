package Paquete;

import Paquetes.IPaqueteBO;
import Paquetes.PaqueteBO;
import com.mycompany.motoamigodto.EliminarPaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author joset
 */
public class EliminarPaquete implements IEliminarPaquete {

    private final IPaqueteBO paqueteBO;

    public EliminarPaquete() {
        this.paqueteBO = PaqueteBO.getInstancia();
    }

    @Override
    public boolean eliminar(EliminarPaqueteDTO paquete)throws PaqueteException {
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
