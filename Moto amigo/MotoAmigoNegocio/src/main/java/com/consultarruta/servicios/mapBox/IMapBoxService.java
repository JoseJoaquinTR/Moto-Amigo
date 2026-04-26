
package com.consultarruta.servicios.mapBox;

import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;

/**
 *
 * @author calo2
 */
public interface IMapBoxService {
    
    public abstract RutaResponseDTO obtenerRuta(String origen, String destino);
    public abstract UbicacionDTO obtenerSiguienteUbicacion();
    public boolean comprobarSiFinalizoRuta();
}
