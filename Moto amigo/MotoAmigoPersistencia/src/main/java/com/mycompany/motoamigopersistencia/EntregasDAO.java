package com.mycompany.motoamigopersistencia;

import Adapter.AdapterStringAObjectID;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Entrega;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

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
            List<Entrega> entregas = new ArrayList<>();

            if (id == null) {
                return entregas;
            }

            coleccion.find(eq("idRepartidor", AdapterStringAObjectID.aObjectId(id))).into(entregas);

            return entregas;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar entregas del repartidor.", ex);
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
}
