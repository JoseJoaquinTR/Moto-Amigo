package com.mycompany.paquetesdao;

import Adapter.AdapterPaquete;
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
public class PaquetesDAO implements IPaquetesDAO {

    private static final String NOMBRE_COLECCION = "paquetes";

    private static PaquetesDAO instancia;

    private final MongoCollection<Paquete> coleccion;

    private PaquetesDAO() {
        MongoDatabase db = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = db.getCollection(NOMBRE_COLECCION, Paquete.class);
    }

    public static synchronized PaquetesDAO getInstancia() {
        if (instancia == null) {
            instancia = new PaquetesDAO();
        }
        return instancia;
    }

    @Override
    public Paquete agregar(NuevoPaqueteDTO paquete) throws PersistenciaException {
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
        try {
            DeleteResult resultado = coleccion.deleteOne(Filters.eq("_id", AdapterPaquete.aObjectId(id)));
            return resultado.getDeletedCount() > 0;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al eliminar el paquete con id " + id, ex);
        }
    }

    @Override
    public Paquete consultarPorId(String id) throws PersistenciaException {
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
    public List<Paquete> consultarPorNombre(String criterio, String idEmprendedor) throws PersistenciaException {
        try {
            List<Bson> pipeline = new ArrayList<>();

            if (criterio != null && !criterio.isBlank()) {
                pipeline.add(Aggregates.match(Filters.and(
                        Filters.regex("nombre", Pattern.quote(criterio), "i"),
                        Filters.eq("idEmprendedor", AdapterPaquete.aObjectId(idEmprendedor))
                )));
            } else {
                pipeline.add(Aggregates.match(
                        Filters.eq("idEmprendedor", AdapterPaquete.aObjectId(idEmprendedor))
                ));
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
