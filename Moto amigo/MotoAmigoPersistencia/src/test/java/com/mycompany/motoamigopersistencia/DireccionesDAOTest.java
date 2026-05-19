package com.mycompany.motoamigopersistencia;

import Enums.TipoNegocio;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Direccion;
import com.mycompany.Entidades.Negocio;
import com.mycompany.emprendedoresdao.DireccionesDAO;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jesus Omar
 */
public class DireccionesDAOTest {

    private DireccionesDAO direccionesDAO;
    private MongoCollection<Negocio> coleccion;

    /**
     * Este metodo se ejecuta antes de cada prueba, borra lo que haya en la base
     * de datos y crea un negocio con una direccion para las pruebas
     */
    @BeforeEach
    public void setUp() {
        direccionesDAO = DireccionesDAO.getInstancia();

        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = bd.getCollection("negocios", Negocio.class);

        coleccion.deleteMany(new Document());

        Direccion direccion = new Direccion(
                new ObjectId().toHexString(),
                "Av. Antonio Ocaso",
                "123",
                "Casa Blanca",
                "45000"
        );

        Negocio negocio = new Negocio();
        negocio.setIdEmprendedor(new ObjectId().toHexString());
        negocio.setNombre("Otro Rollo Sushio");
        negocio.setTipoNegocio(TipoNegocio.RESTAURANTE);
        negocio.setDireccion(direccion);

        coleccion.insertOne(negocio);
    }

    /**
     * Prueba para verificar que una direccion se actualiza correctamente
     */
    @Test
    public void actualizarDireccionOK() {
        assertDoesNotThrow(() -> {
            Negocio negocio = coleccion.find().first();

            Direccion nuevaDireccion = new Direccion(
                    new ObjectId().toHexString(),
                    "Calle Tlaxcala",
                    "456",
                    "Villa California",
                    "85000"
            );

            Direccion actualizada = direccionesDAO.actualizarDireccion(
                    negocio.getIdNegocio(), nuevaDireccion
            );

            assertNotNull(actualizada);
            assertEquals("Calle Tlaxcala", actualizada.getCalle());
            assertEquals("456", actualizada.getNumero());
            assertEquals("Villa California", actualizada.getColonia());
            assertEquals("85000", actualizada.getCodigoPostal());
        });
    }

    /**
     * Prueba para verificar que se obtenga una direccion por el id de un
     * negocio correctamente
     */
    @Test
    public void obtenerDireccionPorIdNegocioOK() {
        assertDoesNotThrow(() -> {
            Negocio negocio = coleccion.find().first();

            Direccion direccion = direccionesDAO.obtenerDireccionPorIdNegocio(
                    negocio.getIdNegocio()
            );

            assertNotNull(direccion);
            assertEquals("Av. Antonio Ocaso", direccion.getCalle());
            assertEquals("123", direccion.getNumero());
            assertEquals("Casa Blanca", direccion.getColonia());
            assertEquals("45000", direccion.getCodigoPostal());
        });
    }
}
