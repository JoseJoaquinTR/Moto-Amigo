package fachada;

import Enums.EstatusEmprendedor;
import enums.Estado;
import enums.Tipo;
import com.mycompany.Entidades.*;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.motoamigopersistencia.*;
import java.util.List;

/**
 *
 * @author joset
 */
public interface IFachadaPersistencia {

    //CU Productos y Paquetes
    Producto agregarProducto(Producto producto) throws PersistenciaException;

    Producto actualizarProducto(String id, Producto datosNuevos) throws PersistenciaException;

    boolean eliminarProducto(String id) throws PersistenciaException;

    Producto consultarProductoPorId(String id) throws PersistenciaException;

    List<Producto> consultarProductosPorNombre(String criterio, String idEmprendedor) throws PersistenciaException;

    List<Producto> obtenerProductosPorEmprendedor(String idEmprendedor) throws PersistenciaException;

    Paquete agregarPaquete(Paquete paquete) throws PersistenciaException;

    Paquete actualizarPaquete(String id, Paquete datosNuevos) throws PersistenciaException;

    boolean eliminarPaquete(String id) throws PersistenciaException;

    Paquete consultarPaquetePorId(String id) throws PersistenciaException;

    List<Paquete> consultarPaquetesPorNombre(String criterio, String idEmprendedor) throws PersistenciaException;

    List<Paquete> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws PersistenciaException;

    //CU Bloquear Repartidores
    List<Repartidor> obtenerRepartidoresActivos() throws PersistenciaException;

    Repartidor cambiarEstadoRepartidor(String id, Estado estado) throws PersistenciaException;

    ReporteBloqueo guardarReporteBloqueo(NuevoReporteBloqueoDTO dto) throws PersistenciaException;

    ReporteDesbloqueo guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto) throws PersistenciaException;

    List<ReporteBloqueo> consultarReportesBloqueos() throws PersistenciaException;

    List<ReporteBloqueo> consultarReportesBloqueos(FiltrosDTO filtros) throws PersistenciaException;

    List<ReporteDesbloqueo> consultarReportesDesbloqueos() throws PersistenciaException;

    List<ReporteDesbloqueo> consultarReportesDesbloqueos(FiltrosDTO filtros) throws PersistenciaException;

    List<Motivo> obtenerMotivos(Tipo tipo);

    List<Repartidor> obtenerRepartidores() throws PersistenciaException;

    List<Repartidor> obtenerRepartidoresParaDesbloqueo(FiltrosDTO filtros) throws PersistenciaException;

    List<Repartidor> buscarRepartidor(String nombre) throws PersistenciaException;

    Repartidor consultarRepartidorPorId(String id) throws PersistenciaException;

    boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws PersistenciaException;

    Repartidor incrementarNumeroBloqueos(String id) throws PersistenciaException;

    // CU Registrar Emprendedores
    Emprendedor registrarEmprendedor(Emprendedor emprendedor) throws PersistenciaException;

    Emprendedor actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedor estatus) throws PersistenciaException;

    Emprendedor obtenerEmprendedorPorID(String idEmprendedor) throws PersistenciaException;

    List<Emprendedor> consultarEmprendedores() throws PersistenciaException;

    Negocio registrarNegocio(Negocio negocio) throws PersistenciaException;

    Negocio obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws PersistenciaException;

    CuentaUsuario buscarCuentaPorCorreo(String correo) throws PersistenciaException;

    void activarCuenta(String idEmprendedor) throws PersistenciaException;

    void desactivarCuenta(String idEmprendedor) throws PersistenciaException;

    CuentaBancaria obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws PersistenciaException;

    Imagen actualizarImagen(String idEmprendedor, Imagen imagen) throws PersistenciaException;

    Imagen obtenerImagenPorIdEmprendedor(String idEmprendedor) throws PersistenciaException;

    Documento actualizarDocumento(String idEmprendedor, Documento documento) throws PersistenciaException;

    Documento obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws PersistenciaException;

    Direccion actualizarDireccion(String idNegocio, Direccion direccion) throws PersistenciaException;

    Direccion obtenerDireccionPorIdNegocio(String idNegocio) throws PersistenciaException;
}
