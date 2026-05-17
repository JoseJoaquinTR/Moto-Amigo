package com.mycompany.motoamigopersistencia;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.Entidades.Estado;
import com.mycompany.Entidades.Repartidor;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Carmen
 */
public class RepartidorDAO implements IRepartidorDAO {

    private static RepartidorDAO instancia;

    private RepartidorDAO() {
    }

    public static synchronized RepartidorDAO getInstancia() {
        if (instancia == null) {
            instancia = new RepartidorDAO();
        }
        return instancia;
    }
    private static final String NOMBRE_COLECCION = "repartidores";

    private MongoDatabase obtenerBaseDatos() {
        return ManejadorConexiones.getInstancia().obtenerBaseDatos();
    }

    private MongoCollection<Repartidor> obtenercColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(NOMBRE_COLECCION, Repartidor.class);
    }

    @Override
    public List<Repartidor> obtenerActivos() throws PersistenciaException {
        try {
            MongoCollection<Repartidor> coleccion = obtenercColeccion(obtenerBaseDatos());
            List<Repartidor> repartidores = new LinkedList<>();
            coleccion.find(eq("estado", Estado.ACTIVO)).into(repartidores);
            return repartidores;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar repartidores activos.", ex);
        }
    }

    @Override
    public Repartidor cambiarEstado(String idRepartidor, Estado estado)
            throws PersistenciaException {

        try {
            MongoCollection<Repartidor> coleccion= obtenercColeccion(obtenerBaseDatos());

            ObjectId id = new ObjectId(idRepartidor);
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", id),
                    set("estado", estado)
            );

            if (resultado.getMatchedCount() == 0) {
                return null;
            }
            
            return coleccion.find(eq("_id", id)).first();

        } catch (MongoException ex) {
            throw new PersistenciaException(
                    "Error al cambiar estado del repartidor.",ex);
        }
    }

    @Override
    public List<Repartidor> consultarTodos() throws PersistenciaException {
        try {
            MongoCollection<Repartidor> coleccion = obtenercColeccion(obtenerBaseDatos());
            List<Repartidor> repartidores = new LinkedList<>();
            coleccion.find().into(repartidores);
            return repartidores;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar repartidores.", ex);
        }
    }

    @Override
    public List<Repartidor> buscarPorNombre(String nombreParcial) throws PersistenciaException {
        try {
            MongoCollection<Repartidor> coleccion = obtenercColeccion(obtenerBaseDatos());
            List<Repartidor> repartidores = new LinkedList<>();
            if (nombreParcial == null || nombreParcial.isBlank()) {
                coleccion.find().into(repartidores);
                return repartidores;
            }
            String patron = Pattern.quote(nombreParcial);
            coleccion.find(regex("nombre", patron, "i")).into(repartidores);
            return repartidores;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al buscar repartidores por nombre.", ex);
        }
    }

    @Override
    public Repartidor consultarPorId(String id) throws PersistenciaException {
        try {
            MongoCollection<Repartidor> coleccion = obtenercColeccion(obtenerBaseDatos());
            Document filtros = new Document().append("_id", new ObjectId(id));
            return coleccion.find(filtros).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar repartidor por id.", ex);
        }
    }
}
