package com.mycompany.motoamigopersistencia;

import Enums.EstatusEmprendedor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.Entidades.Imagen;
import com.mycompany.emprendedoresdao.ImagenesDAO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jesus Omar
 */
public class ImagenesDAOTest {

    private ImagenesDAO imagenesDAO;
    private MongoCollection<Emprendedor> coleccion;

    /**
     * Este metodo se ejecuta antes de cada prueba, elimina lo que haya en la
     * base de datos y crea un emprendedor con una imagen para las pruebas
     */
    @BeforeEach
    public void setUp() {
        imagenesDAO = ImagenesDAO.getInstancia();

        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = bd.getCollection("emprendedores", Emprendedor.class);

        coleccion.deleteMany(new org.bson.Document());

        Emprendedor emp1 = new Emprendedor();
        emp1.setNombre("Daniel Cosio");
        emp1.setCorreo("daniel@gmail.com");
        emp1.setTelefono("6441234567");
        emp1.setRfc("RAL900101ABC");
        emp1.setEstatus(EstatusEmprendedor.PENDIENTE);
        emp1.setImagen(new Imagen(
                new ObjectId().toHexString(), new byte[]{1, 2, 3}
        ));

        coleccion.insertOne(emp1);
    }

    /**
     * Prueba para verificar que se actualice bien la foto de un emprendedor
     */
    @Test
    public void testActualizarImagen() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            Imagen nuevaImagen = new Imagen(
                    new ObjectId().toHexString(), new byte[]{7, 8, 9}
            );

            Imagen imagenActualizada = imagenesDAO.actualizarImagen(
                    emprendedor.getIdEmprendedor(), nuevaImagen
            );

            assertNotNull(imagenActualizada);
            assertArrayEquals(new byte[]{7, 8, 9}, imagenActualizada.getImagen());
        });
    }

    /**
     * Prueba para verificar que se obtiene correctamente la foto de un
     * emprendedor
     */
    @Test
    public void testObtenerImagenPorIdEmprendedor() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            Imagen imagen = imagenesDAO.obtenerImagenPorIdEmprendedor(
                    emprendedor.getIdEmprendedor()
            );

            assertNotNull(imagen);
            assertArrayEquals(new byte[]{1, 2, 3}, imagen.getImagen());
        });
    }
}
