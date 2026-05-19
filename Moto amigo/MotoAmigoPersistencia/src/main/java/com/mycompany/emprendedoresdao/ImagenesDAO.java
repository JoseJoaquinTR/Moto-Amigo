package com.mycompany.emprendedoresdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.Entidades.Imagen;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/**
 *
 * @author Jesus Omar
 */
public class ImagenesDAO implements IImagenesDAO {

    private static final String NOMBRE_COLECCION = "emprendedores";
    private static ImagenesDAO instancia;
    private final MongoCollection<Emprendedor> coleccion;

    /**
     * Constructor privado para crear una instancia de ImagenesDAO solo de su
     * propia clase
     */
    private ImagenesDAO() {
        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.coleccion = bd.getCollection(NOMBRE_COLECCION, Emprendedor.class);
    }

    /**
     * Metodo para obtener la instancia de ImagenesDAO, si no existe accede al
     * constructor privado para crearla y te la regresa
     *
     * @return la instancia de ImagenesDAO
     */
    public static ImagenesDAO getInstancia() {
        if (instancia == null) {
            instancia = new ImagenesDAO();
        }
        return instancia;
    }

    /**
     * Metodo para actualizar la foto de perfil de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor que actualizara su foto
     * @param imagen la imagen nueva
     * @return la imagen nueva
     * @throws PersistenciaException
     */
    @Override
    public Imagen actualizarImagen(String idEmprendedor, Imagen imagen) throws PersistenciaException {
        try {
            ObjectId id = new ObjectId(idEmprendedor);
            UpdateResult resultado = coleccion.updateOne(
                    eq("_id", id),
                    set("imagen.imagen", imagen.getImagen())
            );
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontro el emprendedor");
            }
            return imagen;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al actualizar la imagen", ex);
        }
    }

    /**
     * Metodo para obetener la foto de perfil de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener su
     * foto de perfil
     * @return la foto de perfil del emprendedor
     * @throws PersistenciaException
     */
    @Override
    public Imagen obtenerImagenPorIdEmprendedor(String idEmprendedor) throws PersistenciaException {
        try {
            Emprendedor emprendedor = coleccion.find(
                    eq("_id", new ObjectId(idEmprendedor))
            ).first();

            return emprendedor.getImagen();
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al obtener la imagen");
        }
    }

}
