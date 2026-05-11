
package com.mycompany.motoamigopersistencia;

import Adapter.AdapterProducto;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.Entidades.Producto;
import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.NuevoProductoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;

/**
 *
 * @author joset
 */
public class ProductoDAO implements IProductoDAO {

    private static final String NOMBRE_COLECCION = "productos";

    private static ProductoDAO instancia;

    private final MongoCollection<Producto> coleccion;

    private ProductoDAO() {
        MongoDatabase db = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = db.getCollection(NOMBRE_COLECCION, Producto.class);
    }

    public static synchronized ProductoDAO getInstancia() {
        if (instancia == null) {
            instancia = new ProductoDAO();
        }
        return instancia;
    }

    @Override
    public Producto agregar(NuevoProductoDTO producto) throws PersistenciaException {
        if (producto == null) {
            throw new PersistenciaException("El producto a agregar no puede ser nulo.");
        }
        try {
            Producto entidad = AdapterProducto.aProductoDTO(producto);
            coleccion.insertOne(entidad);
            return entidad;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al agregar el producto.", ex);
        }
    }

    @Override
    public Producto actualizar(String id, EditarProductoDTO datosNuevos) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id del producto a actualizar no puede ser nulo.");
        }
        if (datosNuevos == null) {
            throw new PersistenciaException("Los datos a actualizar no pueden ser nulos.");
        }

        try {
            List<Bson> cambios = new ArrayList<>();
            if (datosNuevos.getNombre() != null) {
                cambios.add(Updates.set("nombre", datosNuevos.getNombre()));
            }
            if (datosNuevos.getPeso() > 0) {
                cambios.add(Updates.set("peso", datosNuevos.getPeso()));
            }
            if (datosNuevos.getUnidad() != null) {
                cambios.add(Updates.set("unidad", AdapterProducto.aTipoUnidad(datosNuevos.getUnidad())));
            }
            if (datosNuevos.getPrecio() > 0) {
                cambios.add(Updates.set("precio", datosNuevos.getPrecio()));
            }
            if (datosNuevos.getImagen() != null) {
                cambios.add(Updates.set("imagen", datosNuevos.getImagen()));
            }
            if (datosNuevos.getIdEmprendedor() != null) {
                cambios.add(Updates.set("idEmprendedor", datosNuevos.getIdEmprendedor()));
            }

            if (cambios.isEmpty()) {
                return coleccion.find(Filters.eq("_id", id)).first();
            }

            UpdateResult resultado = coleccion.updateOne(
                    Filters.eq("_id", id),
                    Updates.combine(cambios)
            );

            if (resultado.getMatchedCount() == 0) {
                return null;
            }

            return coleccion.find(Filters.eq("_id", id)).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar el producto con id " + id, ex);
        }
    }

    @Override
    public boolean eliminar(String id) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id del producto a eliminar no puede ser nulo.");
        }
        try {
            DeleteResult resultado = coleccion.deleteOne(Filters.eq("_id", id));
            return resultado.getDeletedCount() > 0;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al eliminar el producto con id " + id, ex);
        }
    }

    @Override
    public Producto consultarPorId(String id) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id a consultar no puede ser nulo.");
        }
        try {
            return coleccion.find(Filters.eq("_id", id)).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el producto con id " + id, ex);
        }
    }

    @Override
    public List<Producto> consultarPorNombre(String nombreSimilar) throws PersistenciaException {
        try {
            List<Producto> resultado = new ArrayList<>();
            if (nombreSimilar == null || nombreSimilar.isBlank()) {
                for (Producto p : coleccion.find()) {
                    resultado.add(p);
                }
                return resultado;
            }
            String patron = Pattern.quote(nombreSimilar);
            for (Producto p : coleccion.find(Filters.regex("nombre", patron, "i"))) {
                resultado.add(p);
            }
            return resultado;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar productos por nombre.", ex);
        }
    }

    @Override
    public List<Producto> obtenerPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        if (idEmprendedor == null) {
            throw new PersistenciaException("El id del emprendedor no puede ser nulo.");
        }
        try {
            List<Producto> resultado = new ArrayList<>();
            for (Producto p : coleccion.find(Filters.eq("idEmprendedor", idEmprendedor))) {
                resultado.add(p);
            }
            return resultado;
        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "Error al obtener los productos del emprendedor " + idEmprendedor, ex);
        }
    }

}
