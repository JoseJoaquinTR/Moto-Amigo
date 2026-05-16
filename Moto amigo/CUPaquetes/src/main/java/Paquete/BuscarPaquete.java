
package Paquete;

import Paquetes.IPaqueteBO;
import Paquetes.PaqueteBO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author joset
 */
public class BuscarPaquete implements IBuscarPaquete {
    private final IPaqueteBO paqueteBO;

    public BuscarPaquete() {
        this.paqueteBO = PaqueteBO.getInstancia();
    }

    @Override
    public List<PaqueteDTO> buscar(String criterio)throws PaqueteException {
        try {
            return paqueteBO.buscarPaquetesPorNombre(criterio);
        } catch (NegocioException ex) {
            throw new PaqueteException("No se encontro resultados " ,ex);
            
        }
    }
}