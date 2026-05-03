package com.mycompany.motoamigonegocio.fachada;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 * Define las operaciones expuestas por la fachada del sistema. Centraliza el
 * acceso a la lógica de negocio para evitar que la presentación o los casos de
 * uso dependan directamente de múltiples BO.
 *
 * @author joset
 */
public interface IFachadaNegocio {

    /**
     * Registra un nuevo emprendedor en el sistema.
     *
     * @param emprendedorDTO datos del emprendedor a registrar.
     * @return entidad del emprendedor registrado.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    Emprendedor registrarEmprendedor(EmprendedorDTO emprendedorDTO) throws NegocioException;

    /**
     * Registra un incidente en el sistema.
     *
     * @param incidenteDTO datos del incidente a registrar.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException;

    /**
     * Calcula una ruta en función de los datos proporcionados.
     *
     * @param rutaRequestDTO datos necesarios para calcular la ruta.
     * @return respuesta con la información de la ruta calculada.
     * @throws NegocioException si ocurre un error durante el cálculo.
     */
    RutaResponseDTO calcularRuta(RutaRequestDTO rutaRequestDTO) throws NegocioException;

    /**
     * Indica si la ruta de seguimiento ha terminado.
     *
     * @return true si la ruta ha terminado.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    boolean haTerminadoRuta() throws NegocioException;

    /**
     * Obtiene la siguiente ubicación en la ruta de seguimiento.
     *
     * @return siguiente ubicación; null si no hay más ubicaciones.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    UbicacionDTO obtenerSiguienteUbicacion() throws NegocioException;

    /**
     * Obtiene los datos de un repartidor a partir de su identificador.
     *
     * @param idRepartidor identificador del repartidor.
     * @return datos del repartidor; null si no se encuentra.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    RepartidorDTO obtenerRepartidorPorId(Long idRepartidor) throws NegocioException;

    /**
     * Obtiene la lista de repartidores actualmente disponibles.
     *
     * @return lista de repartidores disponibles.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<RepartidorDTO> obtenerRepartidoresDisponibles() throws NegocioException;

    /**
     * Publica una solicitud de entrega para que los repartidores disponibles
     * puedan recibirla.
     *
     * @param solicitud datos de la solicitud a publicar.
     * @return true si la publicación fue exitosa, false en caso contrario.
     * @throws NegocioException si ocurre un error durante la publicación.
     */
    boolean publicarSolicitud(SolicitudEntregaDTO solicitud) throws NegocioException;

    /**
     * Obtiene los datos de un emprendedor a partir de su identificador.
     *
     * @param id identificador del emprendedor.
     * @return datos del emprendedor; null si no se encuentra.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    EmprendedorDTO obtenerEmprendedorPorId(Long id) throws NegocioException;

    /**
     * Obtiene la lista de entregas asignadas a un repartidor.
     *
     * @param idRepartidor identificador del repartidor.
     * @return lista de entregas; vacía si no hay entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> obtenerEntregasRepartidor(Long idRepartidor) throws NegocioException;

    /**
     * Obtiene la lista de entregas asociadas a un emprendedor.
     *
     * @param idEmprendedor identificador del emprendedor.
     * @return lista de entregas; vacía si no hay entregas.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<EntregaDTO> obtenerEntregasEmprendedor(Long idEmprendedor) throws NegocioException;

    /**
     * Busca ubicaciones que coincidan con el texto proporcionado.
     *
     * @param texto cadena de búsqueda.
     * @return lista de ubicaciones coincidentes.
     * @throws NegocioException si ocurre un error durante la búsqueda.
     */
    List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException;
}
