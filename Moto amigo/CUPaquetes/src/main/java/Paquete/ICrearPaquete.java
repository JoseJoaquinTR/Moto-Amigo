package Paquete;

import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;

/**
 * @author joset
 */
public interface ICrearPaquete {

    /**
     * Crea un nuevo paquete a partir de los datos proporcionados.
     *
     * @param paquete datos del paquete a crear.
     * @return el paquete creado con su id asignado, o null si ocurre un error.
     * @throws Paquete.PaqueteException
     */
    public abstract PaqueteDTO crear(NuevoPaqueteDTO paquete)throws PaqueteException;
}
