///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package com.mycompany.motoamigopersistencia;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mycompany.Entidades.Estado;
//import com.mycompany.Entidades.Repartidor;
//import java.util.List;
//import org.bson.types.ObjectId;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//
///**
// *
// * @author Carmen Andrea Lara Osuna
// */
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mycompany.motoamigodto.RepartidorDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RepartidorDAOTest {
//
//    private RepartidorDAO repartidorDAO;
//    private MongoCollection<Repartidor> coleccion;
//
//    @BeforeEach
//    public void setUp() {
//        repartidorDAO = new RepartidorDAO();
//
//        MongoDatabase baseDatos = ManejadorConexiones
//                .getInstancia()
//                .obtenerBaseDatos();
//
//        coleccion = baseDatos.getCollection("repartidores", Repartidor.class);
//
//        // Limpiar colección antes de cada prueba
//        coleccion.deleteMany(new org.bson.Document());
//
//        // Insertar datos de prueba con id como String
//        Repartidor repartidor1 = new Repartidor();
//        repartidor1.setIdRepartidor("R1");
//        repartidor1.setNombre("Juan Pérez");
//        repartidor1.setEstado(Estado.ACTIVO);
//
//        Repartidor repartidor2 = new Repartidor();
//        repartidor2.setIdRepartidor("R2");
//        repartidor2.setNombre("Carlos López");
//        repartidor2.setEstado(Estado.BLOQUEADO);
//
//        Repartidor repartidor3 = new Repartidor();
//        repartidor3.setIdRepartidor("R3");
//        repartidor3.setNombre("Juana Martínez");
//        repartidor3.setEstado(Estado.ACTIVO);
//
//        coleccion.insertOne(repartidor1);
//        coleccion.insertOne(repartidor2);
//        coleccion.insertOne(repartidor3);
//    }
//
//    @Test
//    public void testObtenerActivos() {
//        assertDoesNotThrow(() -> {
//            List<Repartidor> repartidoresActivos = repartidorDAO.obtenerActivos();
//            assertNotNull(repartidoresActivos);
//            assertEquals(2, repartidoresActivos.size());
//            for (Repartidor repartidor : repartidoresActivos) {
//                assertEquals(Estado.ACTIVO, repartidor.getEstado());
//            }
//        });
//    }
//
//    @Test
//    public void testConsultarTodos() {
//        assertDoesNotThrow(() -> {
//            List<Repartidor> repartidores = repartidorDAO.consultarTodos();
//            assertNotNull(repartidores);
//            assertEquals(3, repartidores.size());
//        });
//    }
//
//    @Test
//    public void testBuscarPorNombre() {
//        assertDoesNotThrow(() -> {
//            List<Repartidor> repartidores = repartidorDAO.buscarPorNombre("Juan");
//            assertNotNull(repartidores);
//            assertFalse(repartidores.isEmpty());
//            for (Repartidor repartidor : repartidores) {
//                assertTrue(repartidor.getNombre().toLowerCase().contains("juan"));
//            }
//        });
//    }
//
//    @Test
//    public void testBuscarPorNombreVacio() {
//        assertDoesNotThrow(() -> {
//            List<Repartidor> repartidores = repartidorDAO.buscarPorNombre("");
//            assertNotNull(repartidores);
//            assertEquals(3, repartidores.size());
//        });
//    }
//
//    @Test
//    public void testConsultarPorId() {
//        assertDoesNotThrow(() -> {
//            Repartidor repartidorGuardado = coleccion.find().first();
//            Repartidor repartidorConsultado = repartidorDAO.consultarPorId(
//                    repartidorGuardado.getIdRepartidor()
//            );
//            assertNotNull(repartidorConsultado);
//            assertEquals(
//                    repartidorGuardado.getIdRepartidor(),
//                    repartidorConsultado.getIdRepartidor()
//            );
//        });
//    }
//
//    @Test
//    public void testCambiarEstado() {
//        assertDoesNotThrow(() -> {
//            Repartidor repartidor = coleccion.find().first();
//            RepartidorDTO dto = new RepartidorDTO();
//            dto.setIdRepartidor(repartidor.getIdRepartidor());
//
//            Repartidor repartidorActualizado = repartidorDAO.cambiarEstado(dto, Estado.BLOQUEADO);
//            assertNotNull(repartidorActualizado);
//            assertEquals(Estado.BLOQUEADO, repartidorActualizado.getEstado());
//        });
//    }
//
//    @Test
//    public void testCambiarEstadoRepartidorInexistente() {
//        assertDoesNotThrow(() -> {
//            RepartidorDTO dto = new RepartidorDTO();
//            dto.setId("R999"); // id inexistente
//
//            Repartidor resultado = repartidorDAO.cambiarEstado(dto, Estado.BLOQUEADO);
//            assertNull(resultado);
//        });
//    }
//}
