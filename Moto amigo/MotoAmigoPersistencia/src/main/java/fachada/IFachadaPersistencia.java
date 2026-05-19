package fachada;

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
    Producto agregarProducto(NuevoProductoDTO producto) throws PersistenciaException;

    Producto actualizarProducto(String id, EditarProductoDTO datosNuevos) throws PersistenciaException;

    boolean eliminarProducto(String id) throws PersistenciaException;

    Producto consultarProductoPorId(String id) throws PersistenciaException;

    List<Producto> consultarProductosPorNombre(String nombreSimilar) throws PersistenciaException;

    List<Producto> obtenerProductosPorEmprendedor(String idEmprendedor) throws PersistenciaException;

    Paquete agregarPaquete(NuevoPaqueteDTO paquete) throws PersistenciaException;

    Paquete actualizarPaquete(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException;

    boolean eliminarPaquete(String id) throws PersistenciaException;

    Paquete consultarPaquetePorId(String id) throws PersistenciaException;

    List<Paquete> consultarPaquetesPorNombre(String nombre) throws PersistenciaException;

    List<Paquete> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws PersistenciaException;

    
    //CU Bloquear Repartidores
    List<Repartidor> obtenerRepartidoresActivos() throws PersistenciaException;

    Repartidor cambiarEstadoRepartidor(String id, Estado estado)throws PersistenciaException;

    ReporteBloqueo guardarReporteBloqueo(NuevoReporteBloqueoDTO dto) throws PersistenciaException;

    ReporteDesbloqueo guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto)throws PersistenciaException;

    List<ReporteBloqueo> consultarReportesBloqueos()throws PersistenciaException;
    
    List<ReporteBloqueo> consultarReportesBloqueos(FiltrosDTO filtros)throws PersistenciaException;

    List<ReporteDesbloqueo> consultarReportesDesbloqueos()throws PersistenciaException;
    
    List<ReporteDesbloqueo> consultarReportesDesbloqueos(FiltrosDTO filtros)throws PersistenciaException;

    List<Motivo> obtenerMotivos(Tipo tipo);

    List<Repartidor> obtenerRepartidores()throws PersistenciaException;

    List<Repartidor> obtenerRepartidoresParaDesbloqueo(FiltrosDTO filtros)throws PersistenciaException;
    
    List<Repartidor> buscarRepartidor(String nombre) throws PersistenciaException;
    
    Repartidor consultarRepartidorPorId(String id) throws PersistenciaException;

    boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws PersistenciaException;
    
    Repartidor incrementarNumeroBloqueos(String id) throws PersistenciaException;
}
