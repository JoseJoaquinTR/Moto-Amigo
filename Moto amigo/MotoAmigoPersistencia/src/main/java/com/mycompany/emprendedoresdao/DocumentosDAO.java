package com.mycompany.emprendedoresdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Documento;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

/**
 *
 * @author Jesus Omar
 */
public class DocumentosDAO implements IDocumentosDAO {

    private static final String NOMBRE_COLECCION = "emprendedores";
    private static DocumentosDAO instancia;
    private final MongoCollection<Emprendedor> coleccion;

    /**
     * Constructor privado para crear una instancia de DocumentosDAO solamente
     * desde su clase
     */
    private DocumentosDAO() {
        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = bd.getCollection(NOMBRE_COLECCION, Emprendedor.class);
    }

    /**
     * Metodo para obtener la instancia de DocumentosDAO si no existe accede al
     * constructor privado para crearla y te la regresa
     *
     * @return la instancia de DocumentosDAO
     */
    public static DocumentosDAO getInstancia() {
        if (instancia == null) {
            instancia = new DocumentosDAO();
        }
        return instancia;
    }

    /**
     * Metodo para actualizar el documento de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor que actualizara su docuemnto
     * @param documento el nuevo documento
     * @return el documento actualizado
     * @throws PersistenciaException
     */
    @Override
    public Documento actualizarDocumentoEmprendedor(String idEmprendedor, Documento documento) throws PersistenciaException {
        try {
            ObjectId id = new ObjectId(idEmprendedor);
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", id),
                    set("documento.documento", documento.getDocumento())
            );
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontró el emprendedor");
            }
            return documento;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar el documento", ex);
        }
    }

    /**
     * Metodo para obetener el documento de la INE de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener su
     * documento
     * @return el documento del emprendedor que se le envio como parametro
     * @throws PersistenciaException
     */
    @Override
    public Documento obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        try {
            Emprendedor emprendedor = coleccion.find(
                    eq("_id", new ObjectId(idEmprendedor))
            ).first();
            if (emprendedor == null) {
                throw new PersistenciaException("No se encontro el emprendedor");
            }
            return emprendedor.getDocumento();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al obtener el documento", ex);
        }
    }

}
