package com.mycompany.emprendedoresdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class CuentasDAO implements ICuentasDAO {

    private static final String NOMBRE_COLECCION = "emprendedores";
    private static CuentasDAO instancia;
    private final MongoCollection<Emprendedor> coleccion;

    /**
     * Constructor privado para que solo se pueda usar dentro de CuentasDAO
     */
    private CuentasDAO() {
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Emprendedor.class);
    }

    /**
     * Te devuelve la instancia de CuentasDAO, si todavia no hay una creada la
     * crea y te la regresa
     *
     * @return la instancia de CuentasDAO
     */
    public static CuentasDAO getInstancia() {
        if (instancia == null) {
            instancia = new CuentasDAO();
        }
        return instancia;
    }

    @Override
    public CuentaUsuario buscarCuenta(String correo) throws PersistenciaException {
        try {
            Emprendedor emprendedor = coleccion.find(eq("cuentaUsuario.correo", correo)).first();
            return emprendedor.getCuentaUsuario();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al buscar la cuenta", ex);
        }
    }

    /**
     * Metodo para activar la cuenta de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor al que se le activara la
     * cuenta
     * @throws PersistenciaException
     */
    @Override
    public void activarCuenta(String idEmprendedor) throws PersistenciaException {
        try {
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", new ObjectId(idEmprendedor)),
                    set("cuentaUsuario.activa", true)
            );
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontro el emprendedor");
            }
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al activar la cuenta", ex);
        }
    }

    /**
     * Metodo para desactivar la cuenta de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor al que se le desactivara la
     * cuenta
     * @throws PersistenciaException
     */
    @Override
    public void desactivarCuenta(String idEmprendedor) throws PersistenciaException {
        try {
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", new ObjectId(idEmprendedor)),
                    set("cuentaUsuario.activa", false)
            );
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontro el emprendedor");
            }
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al desactivar la cuenta", ex);
        }
    }

}
