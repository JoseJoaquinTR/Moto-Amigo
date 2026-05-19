package fachada;

import Enums.EstatusEmprendedor;
import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.Entidades.Direccion;
import com.mycompany.Entidades.Documento;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.Entidades.Imagen;
import enums.Estado;
import com.mycompany.Entidades.Motivo;
import com.mycompany.Entidades.Negocio;
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
import com.mycompany.emprendedoresdao.CuentasBancariasDAO;
import com.mycompany.emprendedoresdao.CuentasDAO;
import com.mycompany.emprendedoresdao.DireccionesDAO;
import com.mycompany.emprendedoresdao.DocumentosDAO;
import com.mycompany.emprendedoresdao.EmprendedoresDAO;
import com.mycompany.emprendedoresdao.ICuentasBancariasDAO;
import com.mycompany.emprendedoresdao.ICuentasDAO;
import com.mycompany.emprendedoresdao.IDireccionesDAO;
import com.mycompany.emprendedoresdao.IDocumentosDAO;
import com.mycompany.emprendedoresdao.IEmprendedoresDAO;
import com.mycompany.emprendedoresdao.IImagenesDAO;
import com.mycompany.emprendedoresdao.INegociosDAO;
import com.mycompany.emprendedoresdao.ImagenesDAO;
import com.mycompany.emprendedoresdao.NegociosDAO;
import com.mycompany.motoamigopersistencia.IMotivosDAO;
import com.mycompany.motoamigopersistencia.IReportesBloqueosDAO;
import com.mycompany.motoamigopersistencia.IReportesDesbloqueosDAO;
import com.mycompany.paquetesdao.PaquetesDAO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.productosdao.ProductosDAO;
import com.mycompany.motoamigopersistencia.*;
import java.util.List;
import com.mycompany.motoamigopersistencia.IRepartidoresDAO;
import com.mycompany.paquetesdao.IPaquetesDAO;
import com.mycompany.productosdao.IProductosDAO;

/**
 *
 * @author joset
 */
public class FachadaPersistencia implements IFachadaPersistencia {

    private static FachadaPersistencia instancia;

    private final IProductosDAO productoDAO;
    private final IPaquetesDAO paqueteDAO;
    private final IRepartidoresDAO repartidoresDAO;
    private final IReportesBloqueosDAO bloqueosDAO;
    private final IReportesDesbloqueosDAO desbloqueosDAO;
    private final IMotivosDAO motivosDAO;
    // CU Registrar Emprendedores
    private final IEmprendedoresDAO emprendedoresDAO;
    private final INegociosDAO negociosDAO;
    private final ICuentasDAO cuentasDAO;
    private final ICuentasBancariasDAO cuentasBancariasDAO;
    private final IImagenesDAO imagenesDAO;
    private final IDocumentosDAO documentosDAO;
    private final IDireccionesDAO direccionesDAO;

    private FachadaPersistencia() {
        this.productoDAO = ProductosDAO.getInstancia();
        this.paqueteDAO = PaquetesDAO.getInstancia();
        this.repartidoresDAO = RepartidoresDAO.getInstancia();
        this.bloqueosDAO = ReportesBloqueosDAO.getInstancia();
        this.desbloqueosDAO = ReportesDesbloqueosDAO.getInstancia();
        this.motivosDAO = MotivosDAO.getInstancia();
        // CU Registrar Emprendedores
        this.emprendedoresDAO = EmprendedoresDAO.getInstancia();
        this.negociosDAO = NegociosDAO.getInstancia();
        this.cuentasDAO = CuentasDAO.getInstancia();
        this.cuentasBancariasDAO = CuentasBancariasDAO.getInstancia();
        this.imagenesDAO = ImagenesDAO.getInstancia();
        this.documentosDAO = DocumentosDAO.getInstancia();
        this.direccionesDAO = DireccionesDAO.getInstancia();
    }

    public static synchronized FachadaPersistencia getInstancia() {
        if (instancia == null) {
            instancia = new FachadaPersistencia();
        }
        return instancia;
    }

    @Override
    public Producto agregarProducto(Producto producto) throws PersistenciaException {
        return productoDAO.agregar(producto);
    }

    @Override
    public Producto actualizarProducto(String id, Producto datosNuevos) throws PersistenciaException {
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
    public List<Producto> consultarProductosPorNombre(String nombreSimilar, String idEmprendedor) throws PersistenciaException {
        return productoDAO.consultarPorNombre(nombreSimilar, idEmprendedor);
    }

    @Override
    public List<Producto> obtenerProductosPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        return productoDAO.obtenerPorEmprendedor(idEmprendedor);
    }

    @Override
    public Paquete agregarPaquete(Paquete paquete) throws PersistenciaException {
        return paqueteDAO.agregar(paquete);
    }

    @Override
    public Paquete actualizarPaquete(String id, Paquete datosNuevos) throws PersistenciaException {
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
    public List<Paquete> consultarPaquetesPorNombre(String criterio, String idEmprendedor) throws PersistenciaException {
        return paqueteDAO.consultarPorNombre(criterio, idEmprendedor);
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

    // CU Registrar Emprendedores
    @Override
    public Emprendedor registrarEmprendedor(Emprendedor emprendedor) throws PersistenciaException {
        return emprendedoresDAO.registrarEmprendedor(emprendedor);
    }

    @Override
    public Emprendedor actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedor estatus) throws PersistenciaException {
        return emprendedoresDAO.actualizarEmprendedor(idEmprendedor, estatus);
    }

    @Override
    public Emprendedor obtenerEmprendedorPorID(String idEmprendedor) throws PersistenciaException {
        return emprendedoresDAO.obtenerEmprendedorPorID(idEmprendedor);
    }

    @Override
    public List<Emprendedor> consultarEmprendedores() throws PersistenciaException {
        return emprendedoresDAO.consultarEmprendedores();
    }

    @Override
    public Negocio registrarNegocio(Negocio negocio) throws PersistenciaException {
        return negociosDAO.registrarNegocio(negocio);
    }

    @Override
    public Negocio obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        return negociosDAO.obtenerNegocioPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public CuentaUsuario buscarCuentaPorCorreo(String correo) throws PersistenciaException {
        return cuentasDAO.buscarCuenta(correo);
    }

    @Override
    public void activarCuenta(String idEmprendedor) throws PersistenciaException {
        cuentasDAO.activarCuenta(idEmprendedor);
    }

    @Override
    public void desactivarCuenta(String idEmprendedor) throws PersistenciaException {
        cuentasDAO.desactivarCuenta(idEmprendedor);
    }

    @Override
    public CuentaBancaria obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        return cuentasBancariasDAO.obtenerCuentaBancariaPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public Imagen actualizarImagen(String idEmprendedor, Imagen imagen) throws PersistenciaException {
        return imagenesDAO.actualizarImagen(idEmprendedor, imagen);
    }

    @Override
    public Imagen obtenerImagenPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        return imagenesDAO.obtenerImagenPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public Documento actualizarDocumento(String idEmprendedor, Documento documento) throws PersistenciaException {
        return documentosDAO.actualizarDocumentoEmprendedor(idEmprendedor, documento);
    }

    @Override
    public Documento obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        return documentosDAO.obtenerDocumentoPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public Direccion actualizarDireccion(String idNegocio, Direccion direccion) throws PersistenciaException {
        return direccionesDAO.actualizarDireccion(idNegocio, direccion);
    }

    @Override
    public Direccion obtenerDireccionPorIdNegocio(String idNegocio) throws PersistenciaException {
        return direccionesDAO.obtenerDireccionPorIdNegocio(idNegocio);
    }

}
