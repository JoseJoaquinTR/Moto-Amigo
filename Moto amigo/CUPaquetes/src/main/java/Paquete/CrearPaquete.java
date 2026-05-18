package Paquete;

import Paquetes.IPaqueteBO;
import Paquetes.PaqueteBO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author joset
 */
public class CrearPaquete implements ICrearPaquete {

    private final IPaqueteBO paqueteBO;

    public CrearPaquete() {
        this.paqueteBO = PaqueteBO.getInstancia();
    }

    @Override
    public PaqueteDTO crear(NuevoPaqueteDTO paquete)throws PaqueteException{
        try {
            return paqueteBO.crearPaquete(paquete);
        } catch (NegocioException ex) {
            throw new PaqueteException(ex.getMessage(),ex);
        }
    }
}
