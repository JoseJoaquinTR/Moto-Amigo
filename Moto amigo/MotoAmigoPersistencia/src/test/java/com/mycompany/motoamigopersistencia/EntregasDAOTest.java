/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motoamigopersistencia;
 
import Enums.TipoEnvio;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Entrega;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
 
/**
 *
 * @author joset
 */
public class EntregasDAOTest {
 
    private EntregasDAO entregasDAO;
    private MongoCollection<Entrega> coleccion;
 
    private String idEmprendedor1;
    private String idEmprendedor2;
    private String idRepartidor1;
 
    @BeforeEach
    public void setUp() {
        entregasDAO = EntregasDAO.getInstancia();
 
        MongoDatabase baseDatos = ManejadorConexiones
                .getInstancia()
                .obtenerBaseDatos();
 
        coleccion = baseDatos.getCollection("entregas", Entrega.class);
 
        coleccion.deleteMany(new org.bson.Document());
 
        idEmprendedor1 = "6a0d216d8655134c496f96d9";
        idEmprendedor2 = new ObjectId().toHexString();
        idRepartidor1 = "6a0d2317cc336268ad738f61";
 
        // Entrega 1: del emprendedor1, ya asignada al repartidor1, EN_CAMINO
        Entrega e1 = new Entrega();
        e1.setIdEmprendedor(idEmprendedor1);
        e1.setIdRepartidor(idRepartidor1);
        e1.setDireccionOrigen("Cananea 456");
        e1.setDireccionDestino("Norte 322");
        e1.setTipo(TipoEnvio.PAQUETES);
        e1.setEstadoEntrega("EN_CAMINO");
        e1.setPesoTotal(2.5);
        e1.setDistancia(5.2);
        e1.setCosto(45);
        e1.setFechaCreacion(LocalDateTime.now());
 
        // Entrega 2: del emprendedor1, DISPONIBLE (sin repartidor)
        Entrega e2 = new Entrega();
        e2.setIdEmprendedor(idEmprendedor1);
        e2.setDireccionOrigen("Amberes 111");
        e2.setDireccionDestino("Antonio Ocaso 782");
        e2.setTipo(TipoEnvio.SOBRE);
        e2.setEstadoEntrega("DISPONIBLE");
        e2.setPesoTotal(0.5);
        e2.setDistancia(3.0);
        e2.setCosto(10);
        e2.setFechaCreacion(LocalDateTime.now());
 
        // Entrega 3: del emprendedor2, asignada al repartidor1, COMPLETADA
        Entrega e3 = new Entrega();
        e3.setIdEmprendedor(idEmprendedor2);
        e3.setIdRepartidor(idRepartidor1);
        e3.setDireccionOrigen("Morelos 834");
        e3.setDireccionDestino("Michoacan 567");
        e3.setTipo(TipoEnvio.PAQUETES);
        e3.setEstadoEntrega("COMPLETADA");
        e3.setPesoTotal(8.0);
        e3.setDistancia(12.5);
        e3.setCosto(100);
        e3.setFechaCreacion(LocalDateTime.now().minusDays(1));
        e3.setFechaEntrega(LocalDateTime.now());
 
        // Entrega 4: del emprendedor2, DISPONIBLE
        Entrega e4 = new Entrega();
        e4.setIdEmprendedor(idEmprendedor2);
        e4.setDireccionOrigen("Reforma 123");
        e4.setDireccionDestino("Juárez 456");
        e4.setTipo(TipoEnvio.PAQUETES);
        e4.setEstadoEntrega("DISPONIBLE");
        e4.setPesoTotal(1.2);
        e4.setDistancia(2.5);
        e4.setCosto(30);
        e4.setFechaCreacion(LocalDateTime.now());
 
        coleccion.insertOne(e1);
        coleccion.insertOne(e2);
        coleccion.insertOne(e3);
        coleccion.insertOne(e4);
    }
 
    @Test
    public void testAgregar() {
        assertDoesNotThrow(() -> {
            Entrega nueva = new Entrega();
            nueva.setIdEmprendedor(idEmprendedor1);
            nueva.setDireccionOrigen("Origen Nuevo");
            nueva.setDireccionDestino("Destino Nuevo");
            nueva.setTipo(TipoEnvio.PAQUETES);
            nueva.setEstadoEntrega("DISPONIBLE");
            nueva.setPesoTotal(3.0);
            nueva.setDistancia(4.0);
            nueva.setCosto(50);
            nueva.setFechaCreacion(LocalDateTime.now());
 
            Entrega agregada = entregasDAO.agregar(nueva);
 
            assertNotNull(agregada);
            assertNotNull(agregada.getId(), "El id debe asignarse al insertar");
            assertEquals("Origen Nuevo", agregada.getDireccionOrigen());
            assertEquals("DISPONIBLE", agregada.getEstadoEntrega());
        });
    }
 
    @Test
    public void testObtenerTodasLasEntregas() {
        assertDoesNotThrow(() -> {
            List<Entrega> entregas = entregasDAO.obtenerTodasLasEntregas();
 
            assertNotNull(entregas);
            assertEquals(4, entregas.size(),
                    "Debe traer las 4 entregas del setUp");
        });
    }
 
    @Test
    public void testObtenerEntregasRepartidor() {
        assertDoesNotThrow(() -> {
            List<Entrega> entregas = entregasDAO.obtenerEntregasRepartidor(idRepartidor1);
 
            assertNotNull(entregas);
            assertEquals(2, entregas.size(),
                    "Debe traer las 2 entregas asignadas al repartidor1");
            entregas.forEach(e
                    -> assertEquals(idRepartidor1, e.getIdRepartidor()));
        });
    }
 
    @Test
    public void testObtenerEntregasRepartidorIdNulo() {
        assertDoesNotThrow(() -> {
            List<Entrega> entregas = entregasDAO.obtenerEntregasRepartidor(null);
 
            assertNotNull(entregas);
            assertTrue(entregas.isEmpty(),
                    "Con id null debe devolver lista vacía");
        });
    }
 
    @Test
    public void testObtenerEntregasRepartidorSinEntregas() {
        assertDoesNotThrow(() -> {
            String idSinEntregas = new ObjectId().toHexString();
            List<Entrega> entregas = entregasDAO.obtenerEntregasRepartidor(idSinEntregas);
 
            assertNotNull(entregas);
            assertTrue(entregas.isEmpty());
        });
    }
 
    @Test
    public void testObtenerEntregasEmprendedor() {
        assertDoesNotThrow(() -> {
            List<Entrega> entregas = entregasDAO.obtenerEntregasEmprendedor(idEmprendedor1);
 
            assertNotNull(entregas);
            assertEquals(2, entregas.size(),
                    "Debe traer las 2 entregas del emprendedor1");
            entregas.forEach(e
                    -> assertEquals(idEmprendedor1, e.getIdEmprendedor()));
        });
    }
 
    @Test
    public void testObtenerEntregasEmprendedorIdNulo() {
        assertDoesNotThrow(() -> {
            List<Entrega> entregas = entregasDAO.obtenerEntregasEmprendedor(null);
 
            assertNotNull(entregas);
            assertTrue(entregas.isEmpty());
        });
    }
 
    @Test
    public void testObtenerEntregasEmprendedorSinEntregas() {
        assertDoesNotThrow(() -> {
            String idSinEntregas = new ObjectId().toHexString();
            List<Entrega> entregas = entregasDAO.obtenerEntregasEmprendedor(idSinEntregas);
 
            assertNotNull(entregas);
            assertTrue(entregas.isEmpty());
        });
    }
 
    @Test
    public void testObtenerEntregasDisponibles() {
        assertDoesNotThrow(() -> {
            List<Entrega> entregas = entregasDAO.obtenerEntregasDisponibles();
 
            assertNotNull(entregas);
            assertEquals(2, entregas.size(),
                    "Debe traer las 2 entregas en estado DISPONIBLE");
            entregas.forEach(e
                    -> assertEquals("DISPONIBLE", e.getEstadoEntrega()));
        });
    }
 
    @Test
    public void testObtenerEntregasDisponiblesIncluyeAmbosEmprendedores() {
        assertDoesNotThrow(() -> {
            // Las disponibles deben incluir las de varios emprendedores
            // (los repartidores ven todas las disponibles del sistema)
            List<Entrega> entregas = entregasDAO.obtenerEntregasDisponibles();
 
            assertNotNull(entregas);
            boolean tieneEmpr1 = entregas.stream()
                    .anyMatch(e -> idEmprendedor1.equals(e.getIdEmprendedor()));
            boolean tieneEmpr2 = entregas.stream()
                    .anyMatch(e -> idEmprendedor2.equals(e.getIdEmprendedor()));
 
            assertTrue(tieneEmpr1, "Debe incluir entregas DISPONIBLES del emprendedor1");
            assertTrue(tieneEmpr2, "Debe incluir entregas DISPONIBLES del emprendedor2");
        });
    }
 
    @Test
    public void testAgregarConSobre() {
        assertDoesNotThrow(() -> {
            Entrega nueva = new Entrega();
            nueva.setIdEmprendedor(idEmprendedor1);
            nueva.setDireccionOrigen("Origen Sobre");
            nueva.setDireccionDestino("Destino Sobre");
            nueva.setTipo(TipoEnvio.SOBRE);
            nueva.setEstadoEntrega("DISPONIBLE");
            nueva.setPesoTotal(0.1);
            nueva.setDistancia(1.5);
            nueva.setCosto(20);
            nueva.setFechaCreacion(LocalDateTime.now());
 
            Entrega agregada = entregasDAO.agregar(nueva);
 
            assertNotNull(agregada);
            assertEquals(TipoEnvio.SOBRE, agregada.getTipo());
        });
    }
}