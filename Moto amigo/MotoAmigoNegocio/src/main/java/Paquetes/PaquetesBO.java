package Paquetes;

import Adapter.AdapterPaqueteAPaqueteDTO;
import Adapter.AdapterProductoAProductoDTO;
import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class PaquetesBO implements IPaquetesBO, IClientePaquetesBO {

    private static PaquetesBO instancia;

    private final IFachadaPersistencia fachada;
    private final AdapterPaqueteAPaqueteDTO adapterPaquete;
    private final AdapterProductoAProductoDTO adapterProducto;
    private final List<IObservadorPaquetesBO> observadores;

    private PaquetesBO() {
        this.fachada = FachadaPersistencia.getInstancia();
        this.adapterPaquete = new AdapterPaqueteAPaqueteDTO();
        this.adapterProducto = new AdapterProductoAProductoDTO();
        this.observadores = new ArrayList<>();
    }

    public static synchronized PaquetesBO getInstancia() {
        if (instancia == null) {
            instancia = new PaquetesBO();
        }
        return instancia;
    }

    @Override
    public synchronized void agregarObservador(IObservadorPaquetesBO observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public synchronized void eliminarObservador(IObservadorPaquetesBO observador) {
        if (observador != null) {
            observadores.remove(observador);
        }
    }

    @Override
    public PaqueteDTO crearPaquete(NuevoPaqueteDTO nuevoPaquete) throws NegocioException {
        try {
            Paquete creado = fachada.agregarPaquete(nuevoPaquete);
            PaqueteDTO dto = adapterPaquete.adaptar(creado);
            notificarPaqueteCreado(dto);
            return dto;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear el paquete.", ex);
        }
    }

    @Override
    public PaqueteDTO editarPaquete(String id, EditarPaqueteDTO datosNuevos) throws NegocioException {
        try {
            Paquete actualizado = fachada.actualizarPaquete(id, datosNuevos);
            PaqueteDTO dto = adapterPaquete.adaptar(actualizado);
            if (dto != null) {
                notificarPaqueteEditado(dto);
            }
            return dto;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar el paquete.", ex);
        }
    }

    @Override
    public boolean eliminarPaquete(String id) throws NegocioException {
        try {
            boolean eliminado = fachada.eliminarPaquete(id);
            if (eliminado) {
                notificarPaqueteEliminado(id);
            }
            return eliminado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el paquete.", ex);
        }
    }

    @Override
    public PaqueteDTO consultarPaquetePorId(String id) throws NegocioException {
        if (id == null || id.isBlank()) {
            throw new NegocioException("El id del paquete a consultar es obligatorio.");
        }
        try {
            Paquete encontrado = fachada.consultarPaquetePorId(id);
            return adapterPaquete.adaptar(encontrado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el paquete.", ex);
        }
    }

    @Override
    public List<PaqueteDTO> buscarPaquetesPorNombre(String criterio, String idEmprendedor) throws NegocioException {
        try {
            List<Paquete> entidades = fachada.consultarPaquetesPorNombre(criterio, idEmprendedor);
            List<PaqueteDTO> resultado = new ArrayList<>();
            for (Paquete p : entidades) {
                resultado.add(adapterPaquete.adaptar(p));
            }
            return resultado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar paquetes por nombre.", ex);
        }
    }

    @Override
    public List<PaqueteDTO> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio.");
        }
        try {
            List<Paquete> entidades = fachada.obtenerPaquetesPorEmprendedor(idEmprendedor);
            List<PaqueteDTO> resultado = new ArrayList<>();
            for (Paquete p : entidades) {
                resultado.add(adapterPaquete.adaptar(p));
            }
            return resultado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener los paquetes del emprendedor.", ex);
        }
    }

    @Override
    public PaqueteDTO agregarProductoAPaquete(String idPaquete, ProductoDTO producto, int cantidad)
            throws NegocioException {
        if (idPaquete == null || idPaquete.isBlank()) {
            throw new NegocioException("El id del paquete es obligatorio.");
        }
        if (producto == null || producto.getId() == null || producto.getId().isBlank()) {
            throw new NegocioException("El producto a agregar es inválido.");
        }
        if (cantidad <= 0) {
            throw new NegocioException("La cantidad debe ser mayor a 0.");
        }

        try {
            Paquete actual = fachada.consultarPaquetePorId(idPaquete);
            if (actual == null) {
                throw new NegocioException("No existe un paquete con id " + idPaquete);
            }

            String idNuevoProducto = producto.getId();
            List<ProductosPaquete> nuevaLista = new ArrayList<>();
            for (ProductosPaquete pp : actual.getProductos()) {
                if (!pp.getIdProducto().equals(idNuevoProducto)) {
                    nuevaLista.add(pp);
                }
            }
            float pesoTotal = producto.getPeso() * cantidad;
            nuevaLista.add(new ProductosPaquete(idNuevoProducto, cantidad, pesoTotal));

            EditarPaqueteDTO edicion = new EditarPaqueteDTO();
            edicion.setProductos(adapterPaquete.crearProductoDTOID(nuevaLista));

            Paquete actualizado = fachada.actualizarPaquete(idPaquete, edicion);
            PaqueteDTO dto = adapterPaquete.adaptar(actualizado);

            notificarProductoAgregadoAPaquete(idPaquete, producto);
            if (dto != null) {
                notificarPaqueteEditado(dto);
            }
            return dto;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al agregar producto al paquete.", ex);
        }
    }

    @Override
    public PaqueteDTO quitarProductoDePaquete(String idPaquete, String idProducto)
            throws NegocioException {
        if (idPaquete == null || idPaquete.isBlank()) {
            throw new NegocioException("El id del paquete es obligatorio.");
        }
        if (idProducto == null || idProducto.isBlank()) {
            throw new NegocioException("El id del producto a quitar es obligatorio.");
        }

        try {
            Paquete actual = fachada.consultarPaquetePorId(idPaquete);
            if (actual == null) {
                throw new NegocioException("No existe un paquete con id " + idPaquete);
            }

            ProductoDTO productoQuitado = null;
            Producto encontrado = fachada.consultarProductoPorId(idProducto);
            if (encontrado != null) {
                productoQuitado = adapterProducto.adaptar(encontrado);
            }

            List<ProductosPaquete> nuevaLista = new ArrayList<>();
            for (ProductosPaquete pp : actual.getProductos()) {
                if (!pp.getIdProducto().equals(idProducto)) {
                    nuevaLista.add(pp);
                }
            }

            EditarPaqueteDTO edicion = new EditarPaqueteDTO();
            edicion.setProductos(adapterPaquete.crearProductoDTOID(nuevaLista));

            Paquete actualizado = fachada.actualizarPaquete(idPaquete, edicion);
            PaqueteDTO dto = adapterPaquete.adaptar(actualizado);

            if (productoQuitado != null) {
                notificarProductoQuitadoDePaquete(idPaquete, productoQuitado);
            }
            if (dto != null) {
                notificarPaqueteEditado(dto);
            }
            return dto;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al quitar producto del paquete.", ex);
        }
    }

    private void notificarPaqueteCreado(PaqueteDTO paquete) {
        for (IObservadorPaquetesBO obs : new ArrayList<>(observadores)) {
            obs.paqueteCreado(paquete);
        }
    }

    private void notificarPaqueteEditado(PaqueteDTO paquete) {
        for (IObservadorPaquetesBO obs : new ArrayList<>(observadores)) {
            obs.paqueteEditado(paquete);
        }
    }

    private void notificarPaqueteEliminado(String idPaquete) {
        for (IObservadorPaquetesBO obs : new ArrayList<>(observadores)) {
            obs.paqueteEliminado(idPaquete);
        }
    }

    private void notificarProductoAgregadoAPaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaquetesBO obs : new ArrayList<>(observadores)) {
            obs.productoAgregadoAPaquete(idPaquete, producto);
        }
    }

    private void notificarProductoQuitadoDePaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaquetesBO obs : new ArrayList<>(observadores)) {
            obs.productoQuitadoDePaquete(idPaquete, producto);
        }
    }

}
