package com.mycompany.emprendedoresdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mycompany.Entidades.Negocio;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import static com.mongodb.client.model.Filters.*;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class NegociosDAO implements INegociosDAO {

    private static final String NOMBRE_COLECCION = "negocios";
    private static NegociosDAO instancia;
    private final MongoCollection<Negocio> coleccion;

    /**
     * Constructor privado para que solo se pueda acceder a el desde la clase, y
     * para que solo hay una instancia
     *
     * @return
     */
    private NegociosDAO() {
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Negocio.class);
    }

    /**
     * Metodo para obtener la instancia de esta DAO, si no hay ninguna creada
     * accede al constructor privado y la regresa
     *
     * @return la instancia de la DAO
     */
    public static NegociosDAO getInstancia() {
        if (instancia == null) {
            instancia = new NegociosDAO();
        }
        return instancia;
    }

    /**
     * Metodo para registrar un nuevo negocio
     *
     * @param negocio el negocio que sera registrado
     * @return el negocio registrado
     * @throws PersistenciaException
     */
    @Override
    public Negocio registrarNegocio(Negocio negocio) throws PersistenciaException {
        try {
            InsertOneResult resultado = coleccion.insertOne(negocio);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo registrar el negocio");
            }
            return negocio;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al registrar el negocio");
        }
    }

    /**
     * Metodo para obtener un negocio por el id del emprendedor que esta dentro
     * de la coleccion
     *
     * @param idEmprendedor el id del emprendedor que esta dentro del negocio
     * @return el negocio que contenga el idEmprendedor que se envio
     * @throws PersistenciaException
     */
    @Override
    public Negocio obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        try {
            return coleccion.find(eq("idEmprendedor", new ObjectId(idEmprendedor))).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al obtener el negocio");
        }
    }

}
