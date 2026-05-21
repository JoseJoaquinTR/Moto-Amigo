package com.mycompany.emprendedoresdao;

import Enums.EstatusEmprendedor;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.LinkedList;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class EmprendedoresDAO implements IEmprendedoresDAO {

    private static final String NOMBRE_COLECCION = "emprendedores";
    private static EmprendedoresDAO instancia;
    private final MongoCollection<Emprendedor> coleccion;

    private EmprendedoresDAO() {
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Emprendedor.class);
    }

    public static EmprendedoresDAO getInstancia() {
        if (instancia == null) {
            instancia = new EmprendedoresDAO();
        }
        return instancia;
    }

    /**
     * Resgistra un nuevo emprendedor en la base de datos
     *
     * @param emprendedor el emprendedor que se registrara
     * @return el emprendedor que se registro
     * @throws PersistenciaException
     */
    @Override
    public Emprendedor registrarEmprendedor(Emprendedor emprendedor) throws PersistenciaException {
        try {
            InsertOneResult resultado = coleccion.insertOne(emprendedor);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo resgitrar al emprendedor");
            }
            return emprendedor;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al registrar al emprendedor", ex);
        }
    }

    /**
     * Actualiza el estatus de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor a actualizar
     * @param estatus el nuevo estatus del emprendedor
     * @return el emprendedor que se actualizo
     * @throws PersistenciaException
     */
    @Override
    public Emprendedor actualizarEmprendedor(String idEmprendedor, EstatusEmprendedor estatus) throws PersistenciaException {
        try {
            ObjectId id = new ObjectId(idEmprendedor);
            UpdateResult resultado = coleccion.updateOne(eq("_id", id), set("estatus", estatus));
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontro el emprendedor");
            }
            return coleccion.find(eq("_id", id)).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar el estatus del emprendedor", ex);
        }
    }

    /**
     * permite consultar a un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor a buscar
     * @return el emprendedor que coincida con el id
     * @throws PersistenciaException
     */
    @Override
    public Emprendedor obtenerEmprendedorPorID(String idEmprendedor) throws PersistenciaException {
        try {
            return coleccion.find(eq("_id", new ObjectId(idEmprendedor))).first();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al obtener el emprendedor", ex);
        }
    }

    /**
     * Te regresa la lista de emprendedores existentes, y aparte te permite
     * buscar por filtros
     *
     * @param nombre
     * @param rfc
     * @return la lista de emprendedores que estan en la base de datos
     * @throws PersistenciaException
     */
    @Override
    public List<Emprendedor> consultarEmprendedores(String nombre, String rfc, EstatusEmprendedor estatus) throws PersistenciaException {
        try {
            List<Bson> filtros = new LinkedList<>();

            if (nombre != null && !nombre.isBlank()) {
                filtros.add(Filters.regex("nombre", nombre, "i"));
            }
            if (rfc != null && !rfc.isBlank()) {
                filtros.add(Filters.regex("rfc", rfc, "i"));
            }
            if (estatus != null) {
                filtros.add(Filters.eq("estatus", estatus.toString()));
            }

            List<Emprendedor> resultado = new LinkedList<>();
            if (filtros.isEmpty()) {
                coleccion.find().into(resultado);
            } else {
                coleccion.find(Filters.and(filtros)).into(resultado);
            }
            return resultado;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar los emprendedores", ex);
        }
    }

}
