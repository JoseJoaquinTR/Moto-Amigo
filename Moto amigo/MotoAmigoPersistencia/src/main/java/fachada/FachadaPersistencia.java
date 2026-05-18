package fachada;

import enums.Estado;
import com.mycompany.Entidades.Motivo;
import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.Entidades.ReporteDesbloqueo;
import enums.Tipo;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.motoamigopersistencia.IMotivosDAO;
import com.mycompany.motoamigopersistencia.IReportesBloqueosDAO;
import com.mycompany.motoamigopersistencia.IReportesDesbloqueosDAO;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.paquetesdao.IPaqueteDAO;
import com.mycompany.productosdao.IProductoDAO;
import com.mycompany.paquetesdao.PaqueteDAO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.productosdao.ProductoDAO;
import com.mycompany.motoamigopersistencia.*;
import java.util.List;
import com.mycompany.motoamigopersistencia.IRepartidoresDAO;

/**
 *
 * @author joset
 */
public class FachadaPersistencia implements IFachadaPersistencia {

    private static FachadaPersistencia instancia;

    private final IProductoDAO productoDAO;
    private final IPaqueteDAO paqueteDAO;
    private final IRepartidoresDAO repartidoresDAO;
    private final IReportesBloqueosDAO bloqueosDAO;
    private final IReportesDesbloqueosDAO desbloqueosDAO;
    private final IMotivosDAO motivosDAO;

    private FachadaPersistencia() {
        this.productoDAO = ProductoDAO.getInstancia();
        this.paqueteDAO = PaqueteDAO.getInstancia();
        this.repartidoresDAO = RepartidoresDAO.getInstancia();
        this.bloqueosDAO = ReportesBloqueosDAO.getInstancia();
        this.desbloqueosDAO = ReportesDesbloqueosDAO.getInstancia();
        this.motivosDAO = MotivosDAO.getInstancia();
    }

    public static synchronized FachadaPersistencia getInstancia() {
        if (instancia == null) {
            instancia = new FachadaPersistencia();
        }
        return instancia;
    }

    @Override
    public Producto agregarProducto(NuevoProductoDTO producto) throws PersistenciaException {
        return productoDAO.agregar(producto);
    }

    @Override
    public Producto actualizarProducto(String id, EditarProductoDTO datosNuevos) throws PersistenciaException {
        return productoDAO.actualizar(id, datosNuevos);
    }

    @Override
    public boolean eliminarProducto(String id) throws PersistenciaException {
        return productoDAO.eliminar(id);
    }

    @Override
    public Producto consultarProductoPorId(String id) throws PersistenciaException {
        return productoDAO.consultarPorId(id);
    }

    @Override
    public List<Producto> consultarProductosPorNombre(String nombreSimilar) throws PersistenciaException {
        return productoDAO.consultarPorNombre(nombreSimilar);
    }

    @Override
    public List<Producto> obtenerProductosPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        return productoDAO.obtenerPorEmprendedor(idEmprendedor);
    }

    @Override
    public Paquete agregarPaquete(NuevoPaqueteDTO paquete) throws PersistenciaException {
        return paqueteDAO.agregar(paquete);
    }

    @Override
    public Paquete actualizarPaquete(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException {
        return paqueteDAO.actualizar(id, datosNuevos);
    }

    @Override
    public boolean eliminarPaquete(String id) throws PersistenciaException {
        return paqueteDAO.eliminar(id);
    }

    @Override
    public Paquete consultarPaquetePorId(String id) throws PersistenciaException {
        return paqueteDAO.consultarPorId(id);
    }

    @Override
    public List<Paquete> consultarPaquetesPorNombre(String nombre) throws PersistenciaException {
        return paqueteDAO.consultarPorNombre(nombre);
    }

    @Override
    public List<Paquete> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        return paqueteDAO.obtenerPorEmprendedor(idEmprendedor);
    }

    //CU bloquear repartidores
    @Override
    public List<Repartidor> obtenerRepartidoresActivos() throws PersistenciaException {
        return repartidoresDAO.obtenerActivos();
    }

    @Override
    public Repartidor cambiarEstadoRepartidor(String id, Estado estado)
            throws PersistenciaException {
        return repartidoresDAO.cambiarEstado(id, estado);
    }

    @Override
    public ReporteBloqueo guardarReporteBloqueo(NuevoReporteBloqueoDTO dto)
            throws PersistenciaException {
        return bloqueosDAO.guardarReporte(dto);
    }

    @Override
    public ReporteDesbloqueo guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto)
            throws PersistenciaException {
        return desbloqueosDAO.guardarReporte(dto);
    }

    @Override
    public List<ReporteBloqueo> consultarReportesBloqueos()
            throws PersistenciaException {
        return bloqueosDAO.consultarTodos();
    }

    @Override
    public List<ReporteDesbloqueo> consultarReportesDesbloqueos()
            throws PersistenciaException {
        return desbloqueosDAO.consultarTodos();
    }

    @Override
    public List<Motivo> obtenerMotivos(Tipo tipo) {
        return motivosDAO.obtenerMotivos(tipo);
    }

    @Override
    public List<Repartidor> obtenerRepartidores()
            throws PersistenciaException {
        return repartidoresDAO.consultarTodos();
    }

    @Override
    public List<Repartidor> obtenerRepartidoresParaDesbloqueo(FiltrosDTO filtros)
            throws PersistenciaException {
        return bloqueosDAO.obtenerRepartidoresParaDesbloqueoMasivo(filtros);
    }

    @Override
    public List<ReporteBloqueo> consultarReportesBloqueos(FiltrosDTO filtros) throws PersistenciaException {
        return bloqueosDAO.consultarConFiltros(filtros);
    }

    @Override
    public List<ReporteDesbloqueo> consultarReportesDesbloqueos(FiltrosDTO filtros) throws PersistenciaException {
        return desbloqueosDAO.consultarConFiltros(filtros);
    }

    @Override
    public List<Repartidor> buscarRepartidor(String nombre) throws PersistenciaException {
        return repartidoresDAO.buscarPorNombre(nombre);
    }

    @Override
    public Repartidor consultarRepartidorPorId(String id) throws PersistenciaException {
        return repartidoresDAO.consultarPorId(id);
    }

    @Override
    public boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws PersistenciaException {
        return motivosDAO.existeMotivo(motivo, tipo);
    }

    @Override
    public Repartidor incrementarNumeroBloqueos(String id) throws PersistenciaException {
        return repartidoresDAO.incrementarNumeroBloqueos(id);
    }

}
