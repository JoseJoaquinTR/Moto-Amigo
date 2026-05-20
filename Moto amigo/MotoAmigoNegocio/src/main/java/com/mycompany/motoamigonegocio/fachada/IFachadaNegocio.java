package com.mycompany.motoamigonegocio.fachada;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.emprendedoresdto.DireccionDTO;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.ImagenDTO;
import com.mycompany.emprendedoresdto.NegocioDTO;
import com.mycompany.emprendedoresdto.RegistroEmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.Estado;
import enums.EstatusEmprendedorDTO;
import enums.Tipo;
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
    RepartidorDTO obtenerRepartidorPorId(String idRepartidor) throws NegocioException;

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
    EmprendedorDTO obtenerEmprendedorPorId(String id) throws NegocioException;

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

    /**
     * Obtiene la lista de repartidores activos.
     *
     * @return lista de repartidores activos.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<RepartidorDTO> obtenerRepartidoresActivos() throws NegocioException;

    /**
     * Cambia el estado de un repartidor.
     *
     * @param id id del repartidor.
     * @param estado nuevo estado del repartidor.
     * @return repartidor actualizado.
     * @throws NegocioException si ocurre un error durante la actualización.
     */
    RepartidorDTO cambiarEstadoRepartidor(String id, Estado estado) throws NegocioException;

    /**
     * Obtiene un repartidor a partir de su identificador.
     *
     * @param id id del repartidor.
     * @return repartidor encontrado o null si no existe.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    RepartidorDTO consultarRepartidorPorId(String id) throws NegocioException;

    /**
     * Obtiene la lista de todos los repartidores.
     *
     * @return lista de repartidores.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<RepartidorDTO> obtenerRepartidores() throws NegocioException;

    /**
     * Busca repartidores por coincidencia de nombre.
     *
     * @param nombre nombre o parte del nombre a buscar.
     * @return lista de repartidores que coinciden.
     * @throws NegocioException si ocurre un error durante la búsqueda.
     */
    List<RepartidorDTO> buscarRepartidoresPorNombre(String nombre) throws NegocioException;

    /**
     * Obtiene la lista de motivos según el tipo indicado.
     *
     * @param tipo tipo de motivo a consultar.
     * @return lista de motivos.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<MotivoDTO> obtenerMotivos(Tipo tipo) throws NegocioException;

    /**
     * Guarda un reporte de bloqueo.
     *
     * @param dto datos del reporte de bloqueo.
     * @return reporte de bloqueo registrado.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    ReporteBloqueoDTO guardarReporteBloqueo(NuevoReporteBloqueoDTO dto) throws NegocioException;

    /**
     * Obtiene todos los reportes de bloqueo.
     *
     * @return lista de reportes de bloqueo.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<ReporteBloqueoDTO> consultarReportesBloqueos() throws NegocioException;

    /**
     * Obtiene reportes de bloqueo aplicando filtros.
     *
     * @param filtros filtros de búsqueda.
     * @return lista de reportes de bloqueo filtrados.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<ReporteBloqueoDTO> consultarReportesBloqueos(FiltrosDTO filtros) throws NegocioException;

    /**
     * Obtiene repartidores candidatos para desbloqueo masivo.
     *
     * @param filtros filtros de búsqueda.
     * @return lista de repartidores que cumplen con los filtros.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<RepartidorDTO> obtenerRepartidoresParaDesbloqueoMasivo(FiltrosDTO filtros) throws NegocioException;

    /**
     * Obtiene información de reportes de bloqueo para generar PDF.
     *
     * @param filtros filtros de búsqueda.
     * @return lista de información de reportes para PDF.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<InformacionReporteBloqueoDTO> consultarReportesBloqueoParaPDF(FiltrosDTO filtros) throws NegocioException;

    /**
     * Guarda un reporte de desbloqueo.
     *
     * @param dto datos del reporte de desbloqueo.
     * @return reporte de desbloqueo registrado.
     * @throws NegocioException si ocurre un error durante el registro.
     */
    ReporteDesbloqueoDTO guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto) throws NegocioException;

    /**
     * Obtiene todos los reportes de desbloqueo.
     *
     * @return lista de reportes de desbloqueo.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos() throws NegocioException;

    /**
     * Obtiene reportes de desbloqueo aplicando filtros.
     *
     * @param filtros filtros de búsqueda.
     * @return lista de reportes de desbloqueo filtrados.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos(FiltrosDTO filtros) throws NegocioException;

    /**
     * Obtiene información de reportes de desbloqueo para generar PDF.
     *
     * @param filtros filtros de búsqueda.
     * @return lista de información de reportes para PDF.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    List<InformacionReporteDesbloqueoDTO> consultarReportesDesbloqueoParaPDF(FiltrosDTO filtros) throws NegocioException;

    /**
     * Valida que el motivo exista
     *
     * @param motivo motivo a validar
     * @param tipo tipo del motivo
     * @return true si existe o false en caso contrario
     * @throws NegocioException si ocurre un error durante la validacion.
     */
    boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws NegocioException;

    /**
     * Incrementa en uno el número de bloqueos de un repartidor.
     *
     * @param id identificador del repartidor.
     * @return repartidor actualizado.
     * @throws NegocioException si ocurre un error durante la actualización.
     */
    RepartidorDTO incrementarNumeroBloqueos(String id) throws NegocioException;

    // CU Emprendedor
    EmprendedorDTO registrarEmprendedor(RegistroEmprendedorDTO dto) throws NegocioException;

    EmprendedorDTO actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedorDTO estatus) throws NegocioException;

    EmprendedorDTO obtenerEmprendedorPorID(String idEmprendedor) throws NegocioException;

    List<EmprendedorDTO> consultarEmprendedores() throws NegocioException;

    ReporteEmprendedoresDTO generarDatosReportePDF() throws NegocioException;

    CuentaBancariaDTO obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    ImagenDTO actualizarImagen(String idEmprendedor, ImagenDTO imagen) throws NegocioException;

    ImagenDTO obtenerImagenPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    DocumentoDTO actualizarDocumento(String idEmprendedor, DocumentoDTO documento) throws NegocioException;

    DocumentoDTO obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    CuentaUsuarioSesionDTO buscarCuenta(String correo) throws NegocioException;

    NegocioDTO registrarNegocio(String idEmprendedor, NegocioDTO dto) throws NegocioException;

    NegocioDTO obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    DireccionDTO obtenerDireccionPorIdNegocio(String idNegocio) throws NegocioException;

    DireccionDTO actualizarDireccion(String idNegocio, DireccionDTO direccion) throws NegocioException;
}
