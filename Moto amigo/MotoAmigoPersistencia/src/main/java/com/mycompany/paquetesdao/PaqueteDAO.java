package com.mycompany.paquetesdao;

import Adapter.AdapterPaquete;
import Adapter.AdapterProducto;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.Entidades.Paquete;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;

/**
 *
 * @author joset
 */
public class PaqueteDAO implements IPaqueteDAO {

    private static final String NOMBRE_COLECCION = "paquetes";

    private static PaqueteDAO instancia;

    private final MongoCollection<Paquete> coleccion;

    private PaqueteDAO() {
        MongoDatabase db = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = db.getCollection(NOMBRE_COLECCION, Paquete.class);
    }

    public static synchronized PaqueteDAO getInstancia() {
        if (instancia == null) {
            instancia = new PaqueteDAO();
        }
        return instancia;
    }

    @Override
    public Paquete agregar(NuevoPaqueteDTO paquete) throws PersistenciaException {
        if (paquete == null) {
            throw new PersistenciaException("El paquete a agregar no puede ser nulo.");
        }
        try {
            Paquete entidad = AdapterPaquete.aPaqueteDTO(paquete);
            coleccion.insertOne(entidad);
            return entidad;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al agregar el paquete.", ex);
        }
    }

    @Override
    public Paquete actualizar(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id del paquete a actualizar no puede ser nulo.");
        }
        if (datosNuevos == null) {
            throw new PersistenciaException("Los datos a actualizar no pueden ser nulos.");
        }

        try {
            List<Bson> cambios = new ArrayList<>();
            if (datosNuevos.getNombre() != null) {
                cambios.add(Updates.set("nombre", datosNuevos.getNombre()));
            }
            if (datosNuevos.getTamaño() != null) {
                cambios.add(Updates.set("tamaño", AdapterPaquete.aTamañoPaquete(datosNuevos.getTamaño())));
            }
            if (datosNuevos.getProductos() != null) {
                cambios.add(Updates.set("productos",
                        AdapterPaquete.aProductosPaquete(datosNuevos.getProductos())));
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
            throw new PersistenciaException("Error al actualizar el paquete con id " + id, ex);
        }
    }

    @Override
    public boolean eliminar(String id) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id del paquete a eliminar no puede ser nulo.");
        }
        try {
            DeleteResult resultado = coleccion.deleteOne(Filters.eq("_id", AdapterPaquete.aObjectId(id)));
            return resultado.getDeletedCount() > 0;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al eliminar el paquete con id " + id, ex);
        }
    }

    @Override
    public Paquete consultarPorId(String id) throws PersistenciaException {
        if (id == null) {
            throw new PersistenciaException("El id a consultar no puede ser nulo.");
        }
        try {
            AggregateIterable<Paquete> resultado = coleccion.aggregate(Arrays.asList(
                    Aggregates.match(Filters.eq("_id", AdapterPaquete.aObjectId(id))),
                    Aggregates.lookup(
                            "productos",
                            "productos.idProducto",
                            "_id",
                            "productosResueltos"
                    )
            ));
            return resultado.first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el paquete con id " + id, ex);
        }
    }

    @Override
    public List<Paquete> consultarPorNombre(String nombre) throws PersistenciaException {
        try {
            List<Bson> pipeline = new ArrayList<>();

            if (nombre != null && !nombre.isBlank()) {
                String patron = Pattern.quote(nombre);
                pipeline.add(Aggregates.match(Filters.regex("nombre", patron, "i")));
            }

            pipeline.add(Aggregates.lookup(
                    "productos",
                    "productos.idProducto",
                    "_id",
                    "productosResueltos"
            ));
            pipeline.add(Aggregates.sort(Sorts.ascending("nombre")));

            List<Paquete> resultado = new ArrayList<>();
            for (Paquete p : coleccion.aggregate(pipeline)) {
                resultado.add(p);
            }
            return resultado;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar paquetes por nombre.", ex);
        }
    }

    @Override
    public List<Paquete> obtenerPorEmprendedor(String idEmprendedor) throws PersistenciaException {
        if (idEmprendedor == null) {
            throw new PersistenciaException("El id del emprendedor no puede ser nulo.");
        }
        try {
            AggregateIterable<Paquete> resultadoAgg = coleccion.aggregate(Arrays.asList(
                    Aggregates.match(Filters.eq("idEmprendedor", AdapterPaquete.aObjectId(idEmprendedor))),
                    Aggregates.lookup(
                            "productos",
                            "productos.idProducto",
                            "_id",
                            "productosResueltos"
                    ),
                    Aggregates.sort(Sorts.ascending("nombre"))
            ));

            List<Paquete> resultado = new ArrayList<>();
            for (Paquete p : resultadoAgg) {
                resultado.add(p);
            }
            return resultado;
        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "Error al obtener los paquetes del emprendedor " + idEmprendedor, ex);
        }
    }

}
