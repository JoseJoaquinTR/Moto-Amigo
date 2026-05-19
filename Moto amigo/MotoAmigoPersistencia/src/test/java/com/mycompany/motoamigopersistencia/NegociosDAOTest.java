package com.mycompany.motoamigopersistencia;

import Enums.TipoNegocio;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Direccion;
import com.mycompany.Entidades.Negocio;
import com.mycompany.emprendedoresdao.NegociosDAO;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jesus Omar
 */
public class NegociosDAOTest {

    private NegociosDAO negociosDAO;
    private MongoCollection<Negocio> coleccion;
    private String idEmprendedorDePrueba;

    /**
     * antes de cada prueba vacia la base de datos y crea el id de un
     * emprendedor, una direccion para guadrar un negocio y poder hacer la
     * prueba de consultar por idEmprendedor
     */
    @BeforeEach
    public void setup() {
        negociosDAO = NegociosDAO.getInstancia();
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = baseDatos.getCollection("negocios", Negocio.class);

        coleccion.deleteMany(new Document());

        // Este id es nomas para la prueba de obtener por idEmprendedor
        // y se crea una direccion para poner el id y direccion en un negocio
        idEmprendedorDePrueba = new ObjectId().toHexString();

        Direccion direccionPrueba = new Direccion(
                new ObjectId().toHexString(),
                "Nainari",
                "123",
                "Centro",
                "85000"
        );

        Negocio negocioPrueba = new Negocio();
        negocioPrueba.setIdEmprendedor(idEmprendedorDePrueba);
        negocioPrueba.setNombre("Dogos del Macario");
        negocioPrueba.setTipoNegocio(TipoNegocio.RESTAURANTE);
        negocioPrueba.setDireccion(direccionPrueba);

        coleccion.insertOne(negocioPrueba);
    }

    /**
     * Prueba para verificar que un negocio se registra correctamente
     */
    @Test
    public void registrarNuevoNegocioOK() {
        assertDoesNotThrow(() -> {
            Direccion direccion = new Direccion(
                    new ObjectId().toHexString(),
                    "Calle Cananea",
                    "451",
                    "Real Del Sol",
                    "83400"
            );

            Negocio negocio = new Negocio();
            negocio.setIdEmprendedor(new ObjectId().toHexString());
            negocio.setNombre("Taco Fish");
            negocio.setTipoNegocio(TipoNegocio.RESTAURANTE);
            negocio.setDireccion(direccion);

            Negocio negocioRegistrado = negociosDAO.registrarNegocio(negocio);

            assertNotNull(negocioRegistrado);
            assertNotNull(negocioRegistrado.getIdNegocio());
            assertEquals("Taco Fish", negocioRegistrado.getNombre());
            assertEquals(TipoNegocio.RESTAURANTE, negocioRegistrado.getTipoNegocio());
        });
    }
    
    @Test
    public void obtenerNegocioPorIdEmprendedorOK(){
        assertDoesNotThrow(() -> {
            Negocio negocioEncontrado = negociosDAO.obtenerNegocioPorIdEmprendedor(idEmprendedorDePrueba);
            assertNotNull(negocioEncontrado);
            assertEquals(idEmprendedorDePrueba, negocioEncontrado.getIdEmprendedor());
            assertEquals("Dogos del Macario", negocioEncontrado.getNombre());
        });
    }
}
