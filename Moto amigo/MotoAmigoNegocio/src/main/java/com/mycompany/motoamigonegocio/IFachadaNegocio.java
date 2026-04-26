
package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;


/**
 * Define las operaciones expuestas por la fachada del sistema.
 * Centraliza el acceso a la lógica de negocio para evitar que la
 * presentación o los casos de uso dependan directamente de múltiples BO.
 * 
 * @author joset
 */
public interface IFachadaNegocio {

    /**
     * Registra un nuevo emprendedor en el sistema.
     *
     * @param emprendedorDTO datos del emprendedor a registrar.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    void registrarEmprendedor(EmprendedorDTO emprendedorDTO) throws NegocioException;

    /**
     * Registra un incidente en el sistema.
     *
     * @param incidenteDTO datos del incidente a registrar.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    public void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException;

    /**
     * Calcula una ruta en función de los datos proporcionados.
     *
     * @param rutaRequestDTO datos necesarios para calcular la ruta.
     * @return respuesta con la información de la ruta calculada.
     * @throws NegocioException si ocurre un error durante el cálculo.
     */
    public RutaResponseDTO calcularRuta(RutaRequestDTO rutaRequestDTO) throws NegocioException;
}
