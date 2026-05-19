package com.mycompany.emprendedoresdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.Entidades.Direccion;
import com.mycompany.Entidades.Negocio;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 *
 * @author Jesus Omar
 */
public class DireccionesDAO implements IDireccionesDAO {

    private static final String NOMBRE_COLECCION = "negocios";
    private static DireccionesDAO instancia;
    private final MongoCollection<Negocio> coleccion;

    /**
     * Constructor privado para crear una instancia de la clase solo dentro de
     * ella misma
     */
    private DireccionesDAO() {
        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = bd.getCollection(NOMBRE_COLECCION, Negocio.class);
    }

    /**
     * metodo para obtener la instancia de DireccionesDAO, si no existe accede
     * al constructor privado para crearla y la regresa
     *
     * @return
     */
    public static DireccionesDAO getInstancia() {
        if (instancia == null) {
            instancia = new DireccionesDAO();
        }
        return instancia;
    }

    /**
     * Metodo para actualizar la direccion de un negocio
     *
     * @param idNegocio id del negocio del que se modificara la direccion
     * @param direccion direccion actualizada
     * @return la nueva direccion
     * @throws PersistenciaException
     */
    @Override
    public Direccion actualizarDireccion(String idNegocio, Direccion direccion) throws PersistenciaException {
        try {
            ObjectId id = new ObjectId(idNegocio);
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", id),
                    combine(
                            set("direccion.calle", direccion.getCalle()),
                            set("direccion.numero", direccion.getNumero()),
                            set("direccion.colonia", direccion.getColonia()),
                            set("direccion.codigoPostal", direccion.getCodigoPostal())
                    )
            );
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontro el negocio");
            }
            return coleccion.find(eq("_id", id)).first().getDireccion();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar la dirección.", ex);
        }
    }

    /**
     * Metodo para obtener una direccion por el id de un Negocio
     *
     * @param idNegocio el id del negocio del que se quiere obtener la direccion
     * @return la direccion del negocio
     * @throws PersistenciaException
     */
    @Override
    public Direccion obtenerDireccionPorIdNegocio(String idNegocio) throws PersistenciaException {
        try {
            Negocio negocio = coleccion.find(
                    eq("_id", new ObjectId(idNegocio))
            ).first();
            if (negocio == null) {
                throw new PersistenciaException("Error al buscar el negocio");
            }
            return negocio.getDireccion();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al buscar la direccion", ex);
        }
    }

}
