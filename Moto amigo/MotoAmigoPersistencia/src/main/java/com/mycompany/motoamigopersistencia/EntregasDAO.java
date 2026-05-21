package com.mycompany.motoamigopersistencia;

import Adapter.AdapterStringAObjectID;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import com.mycompany.Entidades.Entrega;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.nin;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;

/**
 * DAO para consultar entregas desde MongoDB.
 *
 *
 * @author Jesus Omar
 */
public class EntregasDAO implements IEntregasDAO {

    private static final String NOMBRE_COLECCION = "entregas";

    private static EntregasDAO instancia;

    private final MongoCollection<Entrega> coleccion;

    private EntregasDAO() {
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Entrega.class);
    }

    public static synchronized EntregasDAO getInstancia() {
        if (instancia == null) {
            instancia = new EntregasDAO();
        }
        return instancia;
    }

    @Override
    public List<Entrega> obtenerTodasLasEntregas() throws PersistenciaException {
        try {
            List<Entrega> entregas = new ArrayList<>();
            coleccion.find().into(entregas);
            return entregas;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todas las entregas.", ex);
        }
    }

    @Override
    public List<Entrega> obtenerEntregasRepartidor(String id) throws PersistenciaException {
        try {
            if (id == null) {
                return new ArrayList<>();
            }
            List<Entrega> entregas = new ArrayList<>();
            coleccion.find(and(
                    eq("idRepartidor", AdapterStringAObjectID.aObjectId(id)),
                    nin("estadoEntrega", "COMPLETADA", "NO COMPLETADA")
            )).into(entregas);
            return entregas;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error...", ex);
        }
    }
     @Override
    public List<Entrega> obtenerEntregasDisponibles() throws PersistenciaException {
        try {
            List<Entrega> entregas = new ArrayList<>();
            coleccion.find(eq("estadoEntrega", "DISPONIBLE")).into(entregas);
            return entregas;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar entregas disponibles.", ex);
        }
    }
    @Override
    public List<Entrega> obtenerEntregasEmprendedor(String id) throws PersistenciaException {
        try {
            List<Entrega> entregas = new ArrayList<>();

            if (id == null) {
                return entregas;
            }

            coleccion.find(eq("idEmprendedor", AdapterStringAObjectID.aObjectId(id))).into(entregas);

            return entregas;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar entregas del emprendedor.", ex);
        }
    }

    @Override
    public Entrega agregar(Entrega entrega) throws PersistenciaException {
        try {
            coleccion.insertOne(entrega);
            return entrega;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al agregar la entrega.", ex);
        }
    }
    
    @Override
    public Entrega actualizar(String idEntrega, String idRepartidor, String nuevoEstado) throws PersistenciaException {
        try {
            List<Bson> cambios = new ArrayList<>();
            if (idRepartidor != null) {
                cambios.add(Updates.set("idRepartidor", AdapterStringAObjectID.aObjectId(idRepartidor)));
            }
            if (nuevoEstado != null) {
                cambios.add(Updates.set("estadoEntrega", nuevoEstado));
            }
            if (cambios.isEmpty()) {
                return coleccion.find(eq("_id", AdapterStringAObjectID.aObjectId(idEntrega))).first();
            }
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", AdapterStringAObjectID.aObjectId(idEntrega)),
                    Updates.combine(cambios)
            );
            if (resultado.getMatchedCount() == 0) {
                return null;
            }
            return coleccion.find(eq("_id", AdapterStringAObjectID.aObjectId(idEntrega))).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar la entrega.", ex);
        }
    }
}
