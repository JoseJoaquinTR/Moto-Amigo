package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso para calcular una ruta entre dos puntos.
 *
 * @author calo2
 */
public interface IConsultarRuta {

    /**
     * Calcula la ruta correspondiente a la solicitud indicada.
     *
     * @param rutaDTO datos de origen y destino para el cálculo.
     * @return ruta calculada.
     * @throws NegocioException si ocurre un error durante el cálculo.
     */
    RutaResponseDTO consultarRuta(RutaRequestDTO rutaDTO) throws NegocioException;
}
