/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Estado;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RepartidorDAOTest {

    private RepartidorDAO repartidorDAO;
    private MongoCollection<Repartidor> coleccion;

    @BeforeEach
    public void setUp() {
        repartidorDAO = new RepartidorDAO();

        MongoDatabase baseDatos = ManejadorConexiones
                .getInstancia()
                .obtenerBaseDatos();

        coleccion = baseDatos.getCollection("repartidores", Repartidor.class);

        coleccion.deleteMany(new org.bson.Document());

        Repartidor repartidor1 = new Repartidor();
        repartidor1.setIdRepartidor(new ObjectId().toHexString());
        repartidor1.setNombre("Juan Pérez");
        repartidor1.setEstado(Estado.ACTIVO);

        Repartidor repartidor2 = new Repartidor();
        repartidor2.setIdRepartidor(new ObjectId().toHexString());
        repartidor2.setNombre("Carlos López");
        repartidor2.setEstado(Estado.BLOQUEADO);

        Repartidor repartidor3 = new Repartidor();
        repartidor3.setIdRepartidor(new ObjectId().toHexString());
        repartidor3.setNombre("Juana Martínez");
        repartidor3.setEstado(Estado.ACTIVO);

        coleccion.insertOne(repartidor1);
        coleccion.insertOne(repartidor2);
        coleccion.insertOne(repartidor3);
    }

    @Test
    public void testObtenerActivos() {
        assertDoesNotThrow(() -> {
            List<Repartidor> repartidoresActivos = repartidorDAO.obtenerActivos();
            assertNotNull(repartidoresActivos);
            assertEquals(2, repartidoresActivos.size());
            repartidoresActivos.forEach(r -> assertEquals(Estado.ACTIVO, r.getEstado()));
        });
    }

    @Test
    public void testConsultarTodos() {
        assertDoesNotThrow(() -> {
            List<Repartidor> repartidores = repartidorDAO.consultarTodos();
            assertNotNull(repartidores);
            assertEquals(3, repartidores.size());
        });
    }

    @Test
    public void testBuscarPorNombre() {
        assertDoesNotThrow(() -> {
            List<Repartidor> repartidores = repartidorDAO.buscarPorNombre("Juan");
            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());
            repartidores.forEach(r -> assertTrue(r.getNombre().toLowerCase().contains("juan")));
        });
    }

    @Test
    public void testBuscarPorNombreVacio() {
        assertDoesNotThrow(() -> {
            List<Repartidor> repartidores = repartidorDAO.buscarPorNombre("");
            assertNotNull(repartidores);
            assertEquals(3, repartidores.size());
        });
    }

    @Test
    public void testConsultarPorId() {
        assertDoesNotThrow(() -> {
            Repartidor repartidorGuardado = coleccion.find().first();
            Repartidor repartidorConsultado = repartidorDAO.consultarPorId(
                    repartidorGuardado.getIdRepartidor()
            );
            assertNotNull(repartidorConsultado);
            assertEquals(repartidorGuardado.getIdRepartidor(), repartidorConsultado.getIdRepartidor());
        });
    }

    @Test
    public void testCambiarEstado() {
        assertDoesNotThrow(() -> {
            Repartidor repartidor = coleccion.find().first();
            RepartidorDTO dto = new RepartidorDTO();
            dto.setId(repartidor.getIdRepartidor());

            Repartidor actualizado = repartidorDAO.cambiarEstado(dto, Estado.BLOQUEADO);
            assertNotNull(actualizado);
            assertEquals(Estado.BLOQUEADO, actualizado.getEstado());
        });
    }

    @Test
    public void testCambiarEstadoRepartidorInexistente() {
        assertDoesNotThrow(() -> {
            RepartidorDTO dto = new RepartidorDTO();
            dto.setId(new ObjectId().toHexString()); 

            Repartidor resultado = repartidorDAO.cambiarEstado(dto, Estado.BLOQUEADO);
            assertNull(resultado);
        });
    }
}
