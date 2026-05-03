package com.mycompany.motoamigonegocio.fachada;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.infraestructura.MapBoxService;
import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.EmprendedorBO;
import com.mycompany.motoamigonegocio.EntregasBO;
import com.mycompany.motoamigonegocio.GestionRepartidores;
import com.mycompany.motoamigonegocio.IEmprendedoresBO;
import com.mycompany.motoamigonegocio.IEntregasBO;
import com.mycompany.motoamigonegocio.IGestionRepartidores;
import com.mycompany.motoamigonegocio.IIncidenteBO;
import com.mycompany.motoamigonegocio.IRepartidorBO;
import com.mycompany.motoamigonegocio.IRutaBO;
import com.mycompany.motoamigonegocio.IUbicacionBO;
import com.mycompany.motoamigonegocio.IncidenteBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.RepartidorBO;
import com.mycompany.motoamigonegocio.RutaBO;
import com.mycompany.motoamigonegocio.UbicacionBO;
import java.util.List;

/**
 * Implementación de la fachada de negocio. Centraliza el acceso a los BO
 * para que los casos de uso no dependan de múltiples implementaciones.
 *
 * @author joset
 */
public class FachadaNegocio implements IFachadaNegocio {

    private final IRutaBO rutaBO;
    private final IEmprendedoresBO emprendedorBO;
    private final IRepartidorBO repartidorBO;
    private final IEntregasBO entregasBO;
    private final IUbicacionBO ubicacionBO;
    private final IGestionRepartidores gestionRepartidores;
    private final IIncidenteBO incidenteBO;

    /**
     * Construye una nueva fachada con sus dependencias de negocio.
     *
     * @param rutaBO BO de rutas.
     * @param emprendedorBO BO de emprendedores.
     * @param repartidorBO BO de repartidores.
     * @param entregasBO BO de entregas.
     * @param ubicacionBO BO de ubicaciones.
     * @param gestionRepartidores BO de gestión de repartidores y solicitudes.
     * @param incidenteBO BO de incidentes.
     */
    public FachadaNegocio(IRutaBO rutaBO,
                          IEmprendedoresBO emprendedorBO,
                          IRepartidorBO repartidorBO,
                          IEntregasBO entregasBO,
                          IUbicacionBO ubicacionBO,
                          IGestionRepartidores gestionRepartidores,
                          IIncidenteBO incidenteBO) {
        this.rutaBO = rutaBO;
        this.emprendedorBO = emprendedorBO;
        this.repartidorBO = repartidorBO;
        this.entregasBO = entregasBO;
        this.ubicacionBO = ubicacionBO;
        this.gestionRepartidores = gestionRepartidores;
        this.incidenteBO = incidenteBO;
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
                new RepartidorBO(),
                EntregasBO.getInstance(),
                UbicacionBO.getInstancia(),
                GestionRepartidores.getInstance(),
                IncidenteBO.getInstancia()
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
    public RepartidorDTO obtenerRepartidorPorId(Long idRepartidor) throws NegocioException {
        return repartidorBO.obtenerRepartidorPorId(idRepartidor);
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
    public EmprendedorDTO obtenerEmprendedorPorId(Long id) throws NegocioException {
        return emprendedorBO.obtenerEmprendedorPorId(id);
    }

    @Override
    public List<EntregaDTO> obtenerEntregasRepartidor(Long idRepartidor) throws NegocioException {
        return entregasBO.obtenerEntregasRepartidor(idRepartidor);
    }

    @Override
    public List<EntregaDTO> obtenerEntregasEmprendedor(Long idEmprendedor) throws NegocioException {
        return entregasBO.obtenerEntregasEmprendedor(idEmprendedor);
    }

    @Override
    public List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException {
        return ubicacionBO.buscarUbicacion(texto);
    }
}
