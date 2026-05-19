
package com.mycompany.productosdao;

import Adapter.AdapterPaquete;
import Adapter.AdapterProducto;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.Entidades.Producto;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;

/**
 *
 * @author joset
 */
public class ProductosDAO implements IProductosDAO {

    private static final String NOMBRE_COLECCION = "productos";

    private static ProductosDAO instancia;

    private final MongoCollection<Producto> coleccion;

    private ProductosDAO() {
        MongoDatabase db = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = db.getCollection(NOMBRE_COLECCION, Producto.class);
    }

    public static synchronized ProductosDAO getInstancia() {
        if (instancia == null) {
            instancia = new ProductosDAO();
        }
        return instancia;
    }

    @Override
    public Producto agregar(NuevoProductoDTO producto) throws PersistenciaException {

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

            if (cambios.isEmpty()) {
                return coleccion.find(Filters.eq("_id", AdapterPaquete.aObjectId(id))).first();
            }

            UpdateResult resultado = coleccion.updateOne(
                    Filters.eq("_id", AdapterPaquete.aObjectId(id)),
                    Updates.combine(cambios)
            );

            if (resultado.getMatchedCount() == 0) {
                return null;
            }

            return coleccion.find(Filters.eq("_id", AdapterPaquete.aObjectId(id))).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar el producto con id " + id, ex);
        }
    }

    @Override
    public boolean eliminar(String id) throws PersistenciaException {

        try {
            DeleteResult resultado = coleccion.deleteOne(Filters.eq("_id", AdapterPaquete.aObjectId(id)));
            return resultado.getDeletedCount() > 0;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al eliminar el producto con id " + id, ex);
        }
    }

    @Override
    public Producto consultarPorId(String id) throws PersistenciaException {

        try {
            return coleccion.find(Filters.eq("_id", AdapterPaquete.aObjectId(id))).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el producto con id " + id, ex);
        }
    }

    @Override
    public List<Producto> consultarPorNombre(String criterio,String idEmprendedor) throws PersistenciaException {
        try {
            List<Producto> resultado = new ArrayList<>();
            if (criterio == null || criterio.isBlank()) {
                for (Producto p : coleccion.find()) {
                    resultado.add(p);
                }
                return resultado;
            }
            String patron = Pattern.quote(criterio);
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

        try {
            List<Producto> resultado = new ArrayList<>();
            for (Producto p : coleccion.find(Filters.eq("idEmprendedor", AdapterPaquete.aObjectId(idEmprendedor)))) {
                resultado.add(p);
            }
            return resultado;
        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "Error al obtener los productos del emprendedor " + idEmprendedor, ex);
        }
    }

}
