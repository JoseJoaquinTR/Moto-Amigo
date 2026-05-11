package Paquete;

import Paquetes.IPaqueteBO;
import Paquetes.PaqueteBO;
import com.mycompany.motoamigodto.NuevoPaqueteDTO;
import com.mycompany.motoamigodto.PaqueteDTO;
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
            throw new PaqueteException("Error al crear producto" ,ex);
        }
    }
}
