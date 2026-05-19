package com.mycompany.emprendedoresdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;

/**
 *
 * @author Jesus Omar
 */
public class CuentasBancariasDAO implements ICuentasBancariasDAO {

    private static final String NOMBRE_COLECCION = "emprendedores";
    private static CuentasBancariasDAO instancia;
    private final MongoCollection<Emprendedor> coleccion;

    /**
     * Constructor privado para acceder a el solo desde la clase
     */
    private CuentasBancariasDAO() {
        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = bd.getCollection(NOMBRE_COLECCION, Emprendedor.class);
    }

    /**
     * Regresa la instancia de CuentasBancariasDAO, si no existe llama al
     * constructor privado y la crea
     *
     * @return la instancia de CuentasBancariasDAO
     */
    public static CuentasBancariasDAO getInstancia() {
        if (instancia == null) {
            instancia = new CuentasBancariasDAO();
        }
        return instancia;
    }

    /**
     * Te regresa la cuenta bancaria de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor del que se quiere consultar su
     * cuenta bancaria
     * @return la cuenta bancaria del emprendedor que tiene el id que se mando
     * como parametro
     * @throws PersistenciaException
     */
    @Override
    public CuentaBancaria obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        try {
            Emprendedor emprendedor = coleccion.find(
                    eq("_id", new ObjectId(idEmprendedor))
            ).first();
            return emprendedor.getCuentaBancaria();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al buscar la cuenta", ex);
        }
    }
}
