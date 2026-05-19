
package Paquete;

import Paquetes.PaquetesBO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;
import Paquetes.IPaquetesBO;

/**
 *
 * @author joset
 */
public class BuscarPaquete implements IBuscarPaquete {
    private final IPaquetesBO paqueteBO;

    public BuscarPaquete() {
        this.paqueteBO = PaquetesBO.getInstancia();
    }

    @Override
    public List<PaqueteDTO> buscar(String criterio,String idEmprendedor)throws PaqueteException {
        try {
            if(criterio == null || idEmprendedor == null  ){
                throw new PaqueteException("No se puede mandar un criterio o id nulo " );
            }
            return paqueteBO.buscarPaquetesPorNombre(criterio,idEmprendedor);
        } catch (NegocioException ex) {
            throw new PaqueteException("No se encontro resultados " ,ex);
            
        }
    }
}