/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motoamigopersistencia;
 
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Producto;
import com.mycompany.Entidades.TipoUnidadProducto;
import com.mycompany.productosdao.ProductosDAO;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
 
/**
 *
 * @author joset
 */
public class ProductosDAOTest {
 
    private ProductosDAO productosDAO;
    private MongoCollection<Producto> coleccion;
 
    private String idEmprendedor1;
    private String idEmprendedor2;
 
    @BeforeEach
    public void setUp() {
        productosDAO = ProductosDAO.getInstancia();
 
        MongoDatabase baseDatos = ManejadorConexiones
                .getInstancia()
                .obtenerBaseDatos();
 
        coleccion = baseDatos.getCollection("productos", Producto.class);
 
        coleccion.deleteMany(new org.bson.Document());
 
        idEmprendedor1 =  "6a0d216d8655134c496f96d9";
        idEmprendedor2 = new ObjectId().toHexString();
 
        Producto producto1 = new Producto();
        producto1.setNombre("Tomate");
        producto1.setPeso(0.2f);
        producto1.setUnidad(TipoUnidadProducto.PIEZA);
        producto1.setPrecio(15f);
        producto1.setIdEmprendedor(idEmprendedor1);
 
        Producto producto2 = new Producto();
        producto2.setNombre("Cebolla");
        producto2.setPeso(0.15f);
        producto2.setUnidad(TipoUnidadProducto.PIEZA);
        producto2.setPrecio(10f);
        producto2.setIdEmprendedor(idEmprendedor1);
 
        Producto producto3 = new Producto();
        producto3.setNombre("Piña");
        producto3.setPeso(2.0f);
        producto3.setUnidad(TipoUnidadProducto.PIEZA);
        producto3.setPrecio(35f);
        producto3.setIdEmprendedor(idEmprendedor2);
 
        coleccion.insertOne(producto1);
        coleccion.insertOne(producto2);
        coleccion.insertOne(producto3);
    }
 
    @Test
    public void testAgregar() {
        assertDoesNotThrow(() -> {
            Producto nuevo = new Producto();
            nuevo.setNombre("Lechuga");
            nuevo.setPeso(0.3f);
            nuevo.setUnidad(TipoUnidadProducto.PIEZA);
            nuevo.setPrecio(20f);
            nuevo.setIdEmprendedor(idEmprendedor1);
 
            Producto agregado = productosDAO.agregar(nuevo);
 
            assertNotNull(agregado);
            assertNotNull(agregado.getId(), "El id debe asignarse al insertar");
            assertEquals("Lechuga", agregado.getNombre());
        });
    }
 
    @Test
    public void testActualizar() {
        assertDoesNotThrow(() -> {
            Producto original = coleccion.find().first();
 
            Producto cambios = new Producto();
            cambios.setNombre("Nombre Modificado");
            cambios.setPrecio(999f);
 
            Producto actualizado = productosDAO.actualizar(original.getId(), cambios);
 
            assertNotNull(actualizado);
            assertEquals("Nombre Modificado", actualizado.getNombre());
            assertEquals(999f, actualizado.getPrecio());
            assertEquals(original.getUnidad(), actualizado.getUnidad());
        });
    }
 
    @Test
    public void testActualizarProductoInexistente() {
        assertDoesNotThrow(() -> {
            Producto cambios = new Producto();
            cambios.setNombre("No existe");
 
            Producto resultado = productosDAO.actualizar(
                    new ObjectId().toHexString(), cambios);
 
            assertNull(resultado);
        });
    }
 
    @Test
    public void testEliminar() {
        assertDoesNotThrow(() -> {
            Producto producto = coleccion.find().first();
 
            boolean eliminado = productosDAO.eliminar(producto.getId());
 
            assertTrue(eliminado);
            assertNull(coleccion.find(
                    com.mongodb.client.model.Filters.eq("_id",
                            new ObjectId(producto.getId()))).first());
        });
    }
 
    @Test
    public void testEliminarProductoInexistente() {
        assertDoesNotThrow(() -> {
            boolean eliminado = productosDAO.eliminar(new ObjectId().toHexString());
            assertFalse(eliminado);
        });
    }
 
    @Test
    public void testConsultarPorId() {
        assertDoesNotThrow(() -> {
            Producto guardado = coleccion.find().first();
 
            Producto consultado = productosDAO.consultarPorId(guardado.getId());
 
            assertNotNull(consultado);
            assertEquals(guardado.getId(), consultado.getId());
            assertEquals(guardado.getNombre(), consultado.getNombre());
        });
    }
 
    @Test
    public void testConsultarPorIdInexistente() {
        assertDoesNotThrow(() -> {
            Producto resultado = productosDAO.consultarPorId(
                    new ObjectId().toHexString());
            assertNull(resultado);
        });
    }
 
    @Test
    public void testConsultarPorNombre() {
        assertDoesNotThrow(() -> {
            List<Producto> resultados = productosDAO.consultarPorNombre(
                    "Tomate", idEmprendedor1);
 
            assertNotNull(resultados);
            assertFalse(resultados.isEmpty());
            resultados.forEach(p
                    -> assertTrue(p.getNombre().toLowerCase().contains("tomate")));
        });
    }
 
    @Test
    public void testConsultarPorNombreVacio() {
        assertDoesNotThrow(() -> {
            List<Producto> resultados = productosDAO.consultarPorNombre(
                    "", idEmprendedor1);
 
            assertNotNull(resultados);
            assertEquals(2, resultados.size(),
                    "Debe devolver solo los productos del emprendedor 1");
            resultados.forEach(p
                    -> assertEquals(idEmprendedor1, p.getIdEmprendedor()));
        });
    }
 
    @Test
    public void testConsultarPorNombreOtroEmprendedor() {
        assertDoesNotThrow(() -> {
            List<Producto> resultados = productosDAO.consultarPorNombre(
                    "Piña", idEmprendedor1);
 
            assertNotNull(resultados);
            assertTrue(resultados.isEmpty(),
                    "No debe encontrar productos de otro emprendedor");
        });
    }
 
    @Test
    public void testObtenerPorEmprendedor() {
        assertDoesNotThrow(() -> {
            List<Producto> resultados = productosDAO.obtenerPorEmprendedor(idEmprendedor1);
 
            assertNotNull(resultados);
            assertEquals(2, resultados.size());
            resultados.forEach(p-> assertEquals(idEmprendedor1, p.getIdEmprendedor()));
        });
    }
 
    @Test
    public void testObtenerPorEmprendedorSinProductos() {
        assertDoesNotThrow(() -> {
            String idSinProductos = new ObjectId().toHexString();
            List<Producto> resultados = productosDAO.obtenerPorEmprendedor(idSinProductos);
 
            assertNotNull(resultados);
            assertTrue(resultados.isEmpty());
        });
    }
}