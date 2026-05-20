package com.mycompany.motoamigonegocio.fachada;

import Repartidores.IRepartidoresBO;
import Repartidores.RepartidoresBO;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.infraestructura.MapBoxService;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.emprendedoresbo.EmprendedorBO;
import com.mycompany.motoamigonegocio.EntregasBO;
import com.mycompany.motoamigonegocio.GestionRepartidores;
import com.mycompany.emprendedoresbo.IEmprendedoresBO;
import com.mycompany.motoamigonegocio.IEntregasBO;
import com.mycompany.motoamigonegocio.IGestionRepartidores;
import com.mycompany.motoamigonegocio.IIncidenteBO;
import com.mycompany.motoamigonegocio.IRutaBO;
import com.mycompany.motoamigonegocio.IUbicacionBO;
import com.mycompany.motoamigonegocio.IncidenteBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.RutaBO;
import com.mycompany.motoamigonegocio.UbicacionBO;
import com.mycompany.reportes.IMotivosBO;
import com.mycompany.reportes.IReportesBloqueoBO;
import com.mycompany.reportes.IReportesDesbloqueosBO;
import com.mycompany.reportes.MotivosBO;
import com.mycompany.reportes.ReportesBloqueoBO;
import com.mycompany.reportes.ReportesDesbloqueosBO;
import enums.Estado;
import enums.Tipo;
import java.util.List;

/**
 * Implementación de la fachada de negocio. Centraliza el acceso a los BO para
 * que los casos de uso no dependan de múltiples implementaciones.
 *
 * @author joset
 */
public class FachadaNegocio implements IFachadaNegocio {

    private final IRutaBO rutaBO;
    private final IEmprendedoresBO emprendedorBO;
    private final IRepartidoresBO repartidoresBO;
    private final IEntregasBO entregasBO;
    private final IUbicacionBO ubicacionBO;
    private final IGestionRepartidores gestionRepartidores;
    private final IIncidenteBO incidenteBO;
    private final IMotivosBO motivosBO;
    private final IReportesBloqueoBO reportesBloqueoBO;
    private final IReportesDesbloqueosBO reportesDesbloqueosBO;

    /**
     * Construye una nueva fachada con sus dependencias de negocio.
     *
     * @param rutaBO BO de rutas.
     * @param emprendedorBO BO de emprendedores.
     * @param repartidoresBO BO de repartidores.
     * @param entregasBO BO de entregas.
     * @param ubicacionBO BO de ubicaciones.
     * @param gestionRepartidores BO de gestión de repartidores y solicitudes.
     * @param incidenteBO BO de incidentes.
     * @param motivosBO BO de los motivos de Reportes bloqueo y desbloqueo
     * @param reportesBloqueoBO BO de los reportes de bloqueo
     * @param reportesDesbloqueosBO BO de los reportes de desbloqueo
     */
    public FachadaNegocio(
            IRutaBO rutaBO,
            IEmprendedoresBO emprendedorBO,
            IRepartidoresBO repartidoresBO,
            IEntregasBO entregasBO,
            IUbicacionBO ubicacionBO,
            IGestionRepartidores gestionRepartidores,
            IIncidenteBO incidenteBO,
            IMotivosBO motivosBO,
            IReportesBloqueoBO reportesBloqueoBO,
            IReportesDesbloqueosBO reportesDesbloqueosBO) {
        this.rutaBO = rutaBO;
        this.emprendedorBO = emprendedorBO;
        this.repartidoresBO = repartidoresBO;
        this.entregasBO = entregasBO;
        this.ubicacionBO = ubicacionBO;
        this.gestionRepartidores = gestionRepartidores;
        this.incidenteBO = incidenteBO;
        this.motivosBO = motivosBO;
        this.reportesBloqueoBO = reportesBloqueoBO;
        this.reportesDesbloqueosBO = reportesDesbloqueosBO;
    }

    /**
     * Crea una fachada con las implementaciones por defecto de cada BO.
     *
     * @return fachada lista para usarse.
     */
    public static FachadaNegocio crear() {
        return new FachadaNegocio(
                new RutaBO(MapBoxService.getInstance()),
                new EmprendedorBO(),
                new RepartidoresBO(),
                EntregasBO.getInstance(),
                UbicacionBO.getInstancia(),
                GestionRepartidores.getInstance(),
                IncidenteBO.getInstancia(),
                MotivosBO.getInstancia(),
                ReportesBloqueoBO.getInstancia(),
                ReportesDesbloqueosBO.getInstancia()
        );
    }

    @Override
    public Emprendedor registrarEmprendedor(EmprendedorDTO emprendedorDTO) throws NegocioException {
        return emprendedorBO.registrarEmprendedor(emprendedorDTO);
    }

    @Override
    public void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException {
        incidenteBO.registrarIncidente(incidenteDTO);
    }

    @Override
    public RutaResponseDTO calcularRuta(RutaRequestDTO rutaRequestDTO) throws NegocioException {
        return rutaBO.calcularRuta(rutaRequestDTO);
    }

    @Override
    public boolean haTerminadoRuta() throws NegocioException {
        return rutaBO.haTerminadoRuta();
    }

    @Override
    public UbicacionDTO obtenerSiguienteUbicacion() throws NegocioException {
        return rutaBO.obtenerSiguienteUbicacion();
    }

    @Override
    public RepartidorDTO obtenerRepartidorPorId(String idRepartidor) throws NegocioException {
        return repartidoresBO.consultarRepartidorPorId(idRepartidor);
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresDisponibles() throws NegocioException {
        return gestionRepartidores.obtenerRepartidoresDisponibles();
    }

    @Override
    public boolean publicarSolicitud(SolicitudEntregaDTO solicitud) throws NegocioException {
        return gestionRepartidores.publicarSolicitud(solicitud);
    }

    @Override
    public EmprendedorDTO obtenerEmprendedorPorId(String id) throws NegocioException {
        return emprendedorBO.obtenerEmprendedorPorId(id);
    }

    @Override
    public List<EntregaDTO> obtenerEntregasRepartidor(String idRepartidor) throws NegocioException {
        return entregasBO.obtenerEntregasRepartidor(idRepartidor);
    }

    @Override
    public List<EntregaDTO> obtenerEntregasEmprendedor(String idEmprendedor) throws NegocioException {
        return entregasBO.obtenerEntregasEmprendedor(idEmprendedor);
    }

    @Override
    public List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException {
        return ubicacionBO.buscarUbicacion(texto);
    }

    // BO CU Bloq
    @Override
    public List<RepartidorDTO> obtenerRepartidoresActivos() throws NegocioException {
        return repartidoresBO.obtenerRepartidoresActivos();
    }

    @Override
    public RepartidorDTO cambiarEstadoRepartidor(String id, Estado estado) throws NegocioException {
        return repartidoresBO.cambiarEstadoRepartidor(id, estado);
    }

    @Override
    public RepartidorDTO consultarRepartidorPorId(String id) throws NegocioException {
        return repartidoresBO.consultarRepartidorPorId(id);
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidores() throws NegocioException {
        return repartidoresBO.obtenerRepartidores();
    }

    @Override
    public List<RepartidorDTO> buscarRepartidoresPorNombre(String nombre) throws NegocioException {
        return repartidoresBO.buscarRepartidoresPorNombre(nombre);
    }

    @Override
    public List<MotivoDTO> obtenerMotivos(Tipo tipo) throws NegocioException {
        return motivosBO.obetnerMotivos(tipo);
    }

    @Override
    public ReporteBloqueoDTO guardarReporteBloqueo(NuevoReporteBloqueoDTO dto) throws NegocioException {
        return reportesBloqueoBO.guardarReporteBloqueo(dto);
    }

    @Override
    public List<ReporteBloqueoDTO> consultarReportesBloqueos() throws NegocioException {
        return reportesBloqueoBO.consultarReportesBloqueos();
    }

    @Override
    public List<ReporteBloqueoDTO> consultarReportesBloqueos(FiltrosDTO filtros) throws NegocioException {
        return reportesBloqueoBO.consultarReportesBloqueos(filtros);
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresParaDesbloqueoMasivo(FiltrosDTO filtros) throws NegocioException {
        return reportesBloqueoBO.obtenerRepartidoresParaDesbloqueoMasivo(filtros);
    }

    @Override
    public List<InformacionReporteBloqueoDTO> consultarReportesBloqueoParaPDF(FiltrosDTO filtros) throws NegocioException {
        return reportesBloqueoBO.consultarReportesBloqueoParaPDF(filtros);
    }

    @Override
    public ReporteDesbloqueoDTO guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto) throws NegocioException {
        return reportesDesbloqueosBO.guardarReporteDesbloqueo(dto);
    }

    @Override
    public List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos() throws NegocioException {
        return reportesDesbloqueosBO.consultarReportesDesbloqueos();
    }

    @Override
    public List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos(FiltrosDTO filtros) throws NegocioException {
        return reportesDesbloqueosBO.consultarReportesDesbloqueos(filtros);
    }

    @Override
    public List<InformacionReporteDesbloqueoDTO> consultarReportesDesbloqueoParaPDF(FiltrosDTO filtros) throws NegocioException {
        return reportesDesbloqueosBO.consultarReportesDesbloqueoParaPDF(filtros);
    }

    @Override
    public boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws NegocioException {
        return motivosBO.existeMotivo(motivo, tipo);
    }

    @Override
    public RepartidorDTO incrementarNumeroBloqueos(String id) throws NegocioException {
        return repartidoresBO.incrementarNumeroBloqueos(id);
    }

    @Override
    public List<EntregaDTO> obtenerEntregasDisponibles() throws NegocioException {

        return entregasBO.obtenerEntregasDisponibles();
    }
}
