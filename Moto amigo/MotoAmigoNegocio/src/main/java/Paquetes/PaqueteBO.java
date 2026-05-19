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
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
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
public class PaqueteBO implements IPaqueteBO, IClientePaqueteBO {

    private static PaqueteBO instancia;

    private final IFachadaPersistencia fachada;
    private final AdapterPaqueteAPaqueteDTO adapterPaquete;
    private final AdapterProductoAProductoDTO adapterProducto;
    private final List<IObservadorPaqueteBO> observadores;
    private static final float PESO_MAX_CHICO = 10f;
    private static final float PESO_MAX_MEDIANO = 20f;
    private static final float PESO_MAX_GRANDE = 50f;
    private static final int PRODUCTOS_MAX_CHICO = 10;
    private static final int PRODUCTOS_MAX_MEDIANO = 25;
    private static final int PRODUCTOS_MAX_GRANDE = 50;
    private static final int CANTIDAD_MAX_POR_PRODUCTO = 50;

    private PaqueteBO() {
        this.fachada = FachadaPersistencia.getInstancia();
        this.adapterPaquete = new AdapterPaqueteAPaqueteDTO();
        this.adapterProducto = new AdapterProductoAProductoDTO();
        this.observadores = new ArrayList<>();
    }

    public static synchronized PaqueteBO getInstancia() {
        if (instancia == null) {
            instancia = new PaqueteBO();
        }
        return instancia;
    }

    @Override
    public synchronized void agregarObservador(IObservadorPaqueteBO observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public synchronized void eliminarObservador(IObservadorPaqueteBO observador) {
        if (observador != null) {
            observadores.remove(observador);
        }
    }

    @Override
    public PaqueteDTO crearPaquete(NuevoPaqueteDTO nuevoPaquete) throws NegocioException {
        validarNuevoPaquete(nuevoPaquete);
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
        if (id == null || id.isBlank()) {
            throw new NegocioException("El id del paquete a editar es obligatorio.");
        }
        if (datosNuevos == null) {
            throw new NegocioException("Los datos del paquete a editar no pueden ser nulos.");
        }
        if (datosNuevos.getProductos() != null && datosNuevos.getProductos().isEmpty()) {
            throw new NegocioException("El paquete debe tener al menos un producto.");
        }
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
        if (id == null || id.isBlank()) {
            throw new NegocioException("El id del paquete a eliminar es obligatorio.");
        }
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
    public List<PaqueteDTO> buscarPaquetesPorNombre(String nombreSimilar) throws NegocioException {
        try {
            List<Paquete> entidades = fachada.consultarPaquetesPorNombre(nombreSimilar);
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

    private void validarNuevoPaquete(NuevoPaqueteDTO nuevo) throws NegocioException {
        if (nuevo == null) {
            throw new NegocioException("El paquete a crear no puede ser nulo.");
        }
        if (nuevo.getNombre() == null || nuevo.getNombre().isBlank()) {
            throw new NegocioException("El nombre del paquete es obligatorio.");
        }
        if (nuevo.getTamaño() == null) {
            throw new NegocioException("El tamaño del paquete es obligatorio.");
        }
        if (nuevo.getProductos() == null || nuevo.getProductos().isEmpty()) {
            throw new NegocioException("El paquete debe tener al menos un producto.");
        }
        validarLimitesPaquete(nuevo);
    }

    private void notificarPaqueteCreado(PaqueteDTO paquete) {
        for (IObservadorPaqueteBO obs : new ArrayList<>(observadores)) {
            obs.paqueteCreado(paquete);
        }
    }

    private void notificarPaqueteEditado(PaqueteDTO paquete) {
        for (IObservadorPaqueteBO obs : new ArrayList<>(observadores)) {
            obs.paqueteEditado(paquete);
        }
    }

    private void notificarPaqueteEliminado(String idPaquete) {
        for (IObservadorPaqueteBO obs : new ArrayList<>(observadores)) {
            obs.paqueteEliminado(idPaquete);
        }
    }

    private void notificarProductoAgregadoAPaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaqueteBO obs : new ArrayList<>(observadores)) {
            obs.productoAgregadoAPaquete(idPaquete, producto);
        }
    }

    private void notificarProductoQuitadoDePaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaqueteBO obs : new ArrayList<>(observadores)) {
            obs.productoQuitadoDePaquete(idPaquete, producto);
        }
    }

    private void validarLimitesPaquete(NuevoPaqueteDTO nuevo) throws NegocioException {
        if (nuevo.getProductos() == null) {
            return;
        }

        float pesoMax;
        int productosMax;
        String tamañoTexto;

        switch (nuevo.getTamaño()) {
            case CHICO -> {
                pesoMax = PESO_MAX_CHICO;
                productosMax = PRODUCTOS_MAX_CHICO;
                tamañoTexto = "CHICO";
            }
            case MEDIANO -> {
                pesoMax = PESO_MAX_MEDIANO;
                productosMax = PRODUCTOS_MAX_MEDIANO;
                tamañoTexto = "MEDIANO";
            }
            case GRANDE -> {
                pesoMax = PESO_MAX_GRANDE;
                productosMax = PRODUCTOS_MAX_GRANDE;
                tamañoTexto = "GRANDE";
            }
            default -> {
                return;
            }
        }

        if (nuevo.getProductos().size() > productosMax) {
            throw new NegocioException(
                    "Un paquete " + tamañoTexto + " admite un máximo de " + productosMax + " productos distintos."
            );
        }

        float pesoTotal = 0f;
        for (ProductosPaqueteDTO pp : nuevo.getProductos()) {
            if (pp.getCantidad() > CANTIDAD_MAX_POR_PRODUCTO) {
                throw new NegocioException(
                        "La cantidad por producto no puede exceder " + CANTIDAD_MAX_POR_PRODUCTO + " unidades."
                );
            }
            pesoTotal += pp.getPesoTotal();
        }

        if (pesoTotal > pesoMax) {
            throw new NegocioException(
                    "El peso total (" + String.format("%.2f", pesoTotal) + " kg) " + "excede el máximo permitido para un paquete " + tamañoTexto
                    + " (" + pesoMax + " kg)."
            );
        }
    }

}
