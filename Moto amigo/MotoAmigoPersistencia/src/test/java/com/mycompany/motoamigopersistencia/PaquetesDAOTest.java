/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.ProductosPaquete;
import Enums.TamanoPaquete;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.TipoUnidadProducto;
import com.mycompany.paquetesdao.PaquetesDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author joset
 */
public class PaquetesDAOTest {

    private PaquetesDAO paquetesDAO;
    private MongoCollection<Paquete> coleccion;

    private String idEmprendedor1;
    private String idEmprendedor2;
    private MongoCollection<Producto> coleccionProductos;
    private List<String> idsProductosDisponibles;

    @BeforeEach
    public void setUp() {
        paquetesDAO = PaquetesDAO.getInstancia();

        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();

        coleccion = baseDatos.getCollection("paquetes", Paquete.class);
        coleccionProductos = baseDatos.getCollection("productos", Producto.class);

        coleccion.deleteMany(new org.bson.Document());
        coleccionProductos.deleteMany(new org.bson.Document());

        idEmprendedor1 = "6a0d216d8655134c496f96d9";
        idEmprendedor2 = new ObjectId().toHexString();

        // Crear productos REALES en BD que los paquetes puedan referenciar
        idsProductosDisponibles = crearProductosEnBD();

        Paquete paquete1 = new Paquete();
        paquete1.setNombre("Paquete Básico");
        paquete1.setTamaño(TamanoPaquete.CHICO);
        paquete1.setPrecio(150f);
        paquete1.setIdEmprendedor(idEmprendedor1);
        paquete1.setProductos(crearProductosDelPaquete(idsProductosDisponibles, 2));

        Paquete paquete2 = new Paquete();
        paquete2.setNombre("Paquete Premium");
        paquete2.setTamaño(TamanoPaquete.MEDIANO);
        paquete2.setPrecio(350f);
        paquete2.setIdEmprendedor(idEmprendedor1);
        paquete2.setProductos(crearProductosDelPaquete(idsProductosDisponibles, 3));

        Paquete paquete3 = new Paquete();
        paquete3.setNombre("Paquete Familiar");
        paquete3.setTamaño(TamanoPaquete.GRANDE);
        paquete3.setPrecio(800f);
        paquete3.setIdEmprendedor(idEmprendedor2);
        paquete3.setProductos(crearProductosDelPaquete(idsProductosDisponibles, 5));

        coleccion.insertOne(paquete1);
        coleccion.insertOne(paquete2);
        coleccion.insertOne(paquete3);
    }

    /**
     * Crea 5 productos reales en la colección "productos" y devuelve sus ids.
     */
    private List<String> crearProductosEnBD() {
        List<String> ids = new ArrayList<>();
        String[] nombres = {"Tomate", "Cebolla", "Pollo", "Arroz", "Frijol"};

        for (String nombre : nombres) {
            Producto p = new Producto();
            p.setNombre(nombre);
            p.setPeso(0.5f);
            p.setUnidad(TipoUnidadProducto.PIEZA);
            p.setPrecio(15f);
            p.setIdEmprendedor(idEmprendedor1);
            coleccionProductos.insertOne(p);
            ids.add(p.getId());
        }
        return ids;
    }

    /**
     * Crea referencias a productos del paquete usando los ids reales ya
     * insertados.
     */
    private List<ProductosPaquete> crearProductosDelPaquete(List<String> idsDisponibles, int cantidad) {
        List<ProductosPaquete> productos = new ArrayList<>();
        int max = Math.min(cantidad, idsDisponibles.size());
        for (int i = 0; i < max; i++) {
            productos.add(new ProductosPaquete(
                    idsDisponibles.get(i),
                    1,
                    1.5f
            ));
        }
        return productos;
    }

    @Test
    public void testAgregar() {
        assertDoesNotThrow(() -> {
            Paquete nuevo = new Paquete();
            nuevo.setNombre("Paquete Nuevo");
            nuevo.setTamaño(TamanoPaquete.CHICO);
            nuevo.setPrecio(100f);
            nuevo.setIdEmprendedor(idEmprendedor1);
            nuevo.setProductos(crearProductosDelPaquete(idsProductosDisponibles, 1));

            Paquete agregado = paquetesDAO.agregar(nuevo);

            assertNotNull(agregado);
            assertNotNull(agregado.getId(), "El id debe asignarse al insertar");
            assertEquals("Paquete Nuevo", agregado.getNombre());
        });
    }

    @Test
    public void testActualizar() {
        assertDoesNotThrow(() -> {
            Paquete original = coleccion.find().first();

            Paquete cambios = new Paquete();
            cambios.setNombre("Nombre Modificado");
            cambios.setPrecio(999f);

            Paquete actualizado = paquetesDAO.actualizar(original.getId(), cambios);

            assertNotNull(actualizado);
            assertEquals("Nombre Modificado", actualizado.getNombre());
            assertEquals(999f, actualizado.getPrecio());
            assertEquals(original.getTamaño(), actualizado.getTamaño());
        });
    }

    @Test
    public void testActualizarPaqueteInexistente() {
        assertDoesNotThrow(() -> {
            Paquete cambios = new Paquete();
            cambios.setNombre("No existe");

            Paquete resultado = paquetesDAO.actualizar(
                    new ObjectId().toHexString(), cambios);

            assertNull(resultado);
        });
    }

    @Test
    public void testEliminar() {
        assertDoesNotThrow(() -> {
            Paquete paquete = coleccion.find().first();

            boolean eliminado = paquetesDAO.eliminar(paquete.getId());

            assertTrue(eliminado);
            assertNull(coleccion.find(
                    com.mongodb.client.model.Filters.eq("_id",
                            new ObjectId(paquete.getId()))).first());
        });
    }

    @Test
    public void testEliminarPaqueteInexistente() {
        assertDoesNotThrow(() -> {
            boolean eliminado = paquetesDAO.eliminar(new ObjectId().toHexString());
            assertFalse(eliminado);
        });
    }

    @Test
    public void testConsultarPorId() {
        assertDoesNotThrow(() -> {
            Paquete guardado = coleccion.find().first();

            Paquete consultado = paquetesDAO.consultarPorId(guardado.getId());

            assertNotNull(consultado);
            assertEquals(guardado.getId(), consultado.getId());
            assertEquals(guardado.getNombre(), consultado.getNombre());
        });
    }

    @Test
    public void testConsultarPorIdInexistente() {
        assertDoesNotThrow(() -> {
            Paquete resultado = paquetesDAO.consultarPorId(
                    new ObjectId().toHexString());
            assertNull(resultado);
        });
    }

    @Test
    public void testConsultarPorNombre() {
        assertDoesNotThrow(() -> {
            List<Paquete> resultados = paquetesDAO.consultarPorNombre(
                    "Premium", idEmprendedor1);

            assertNotNull(resultados);
            assertFalse(resultados.isEmpty());
            resultados.forEach(p
                    -> assertTrue(p.getNombre().toLowerCase().contains("premium")));
        });
    }

    @Test
    public void testConsultarPorNombreVacio() {
        assertDoesNotThrow(() -> {
            List<Paquete> resultados = paquetesDAO.consultarPorNombre(
                    "", idEmprendedor1);

            assertNotNull(resultados);
            assertEquals(2, resultados.size(),
                    "Debe devolver solo los paquetes del emprendedor 1");
            resultados.forEach(p
                    -> assertEquals(idEmprendedor1, p.getIdEmprendedor()));
        });
    }

    @Test
    public void testConsultarPorNombreOtroEmprendedor() {
        assertDoesNotThrow(() -> {
            List<Paquete> resultados = paquetesDAO.consultarPorNombre(
                    "Familiar", idEmprendedor1);

            assertNotNull(resultados);
            assertTrue(resultados.isEmpty(),
                    "No debe encontrar paquetes de otro emprendedor");
        });
    }

    @Test
    public void testObtenerPorEmprendedor() {
        assertDoesNotThrow(() -> {
            List<Paquete> resultados = paquetesDAO.obtenerPorEmprendedor(idEmprendedor1);

            assertNotNull(resultados);
            assertEquals(2, resultados.size());
            resultados.forEach(p
                    -> assertEquals(idEmprendedor1, p.getIdEmprendedor()));
        });
    }

    @Test
    public void testObtenerPorEmprendedorSinPaquetes() {
        assertDoesNotThrow(() -> {
            String idSinPaquetes = new ObjectId().toHexString();
            List<Paquete> resultados = paquetesDAO.obtenerPorEmprendedor(idSinPaquetes);
            assertNotNull(resultados);
            assertTrue(resultados.isEmpty());
        });
    }
}
