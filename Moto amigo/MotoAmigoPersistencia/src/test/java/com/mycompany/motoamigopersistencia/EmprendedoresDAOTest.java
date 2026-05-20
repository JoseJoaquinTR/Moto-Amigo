package com.mycompany.motoamigopersistencia;

import Enums.Banco;
import Enums.EstatusEmprendedor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.Entidades.Documento;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.Entidades.Imagen;
import com.mycompany.emprendedoresdao.EmprendedoresDAO;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jesus Omar
 */
public class EmprendedoresDAOTest {

    private EmprendedoresDAO emprendedoresDAO;
    private MongoCollection<Emprendedor> coleccion;

    /**
     * antes de cada prueba, obtenemos la base de datos, la coleccion de
     * emprendedores y borramos lo que haya para que no haya problema con las
     * pruebas
     */
    @BeforeEach
    public void setup() {
        emprendedoresDAO = EmprendedoresDAO.getInstancia();
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = baseDatos.getCollection("emprendedores", Emprendedor.class);
        coleccion.deleteMany(new Document());

        // Estos son los emprendores para la prueba de actualizar y la de consultarTodos
        Emprendedor emprendedor1 = new Emprendedor();
        emprendedor1.setNombre("Omar Cosio");
        emprendedor1.setCorreo("omar@gmail.com");
        emprendedor1.setTelefono("6441234567");
        emprendedor1.setRfc("RAL900101ABC");
        emprendedor1.setEstatus(EstatusEmprendedor.PENDIENTE);
        emprendedor1.setCuentaUsuario(new CuentaUsuario(new ObjectId().toHexString(), "omar@gmail.com", "1234565", false));
        emprendedor1.setCuentaBancaria(new CuentaBancaria(new ObjectId().toHexString(), "1234567890", "Omar Cosio", Banco.BBVA));
        emprendedor1.setImagen(new Imagen(new ObjectId().toHexString(), new byte[]{1,2,3}));
        emprendedor1.setDocumento(new Documento(new ObjectId().toHexString(), new byte[]{4, 5, 6}));

        Emprendedor emprendedor2 = new Emprendedor();
        emprendedor2.setNombre("Ernesto Cisneros");
        emprendedor2.setCorreo("ernesto@gmail.com");
        emprendedor2.setTelefono("6449876543");
        emprendedor2.setRfc("LOPM850505XYZ");
        emprendedor2.setEstatus(EstatusEmprendedor.ACTIVO);
        emprendedor2.setCuentaUsuario(new CuentaUsuario(new ObjectId().toHexString(), "ernesto@gmail.com", "123456", true));
        emprendedor2.setCuentaBancaria(new CuentaBancaria(new ObjectId().toHexString(), "0987654321", "Ernesto Cisneros", Banco.HSBC));
        emprendedor2.setImagen(new Imagen(new ObjectId().toHexString(), new byte[]{1}));
        emprendedor2.setDocumento(new Documento(new ObjectId().toHexString(), new byte[]{4}));

        coleccion.insertOne(emprendedor1);
        coleccion.insertOne(emprendedor2);
    }

    /**
     * Prueba para verificar que un emprendedor se registra correctamente
     */
    @Test
    public void registrarEmprendedorOK() {
        assertDoesNotThrow(() -> {
            Emprendedor nuevoEmprendedor = new Emprendedor();
            nuevoEmprendedor.setNombre("Jose Trista");
            nuevoEmprendedor.setCorreo("jose@gmail.com");
            nuevoEmprendedor.setTelefono("6445551234");
            nuevoEmprendedor.setRfc("SOTP920202DEF");
            nuevoEmprendedor.setEstatus(EstatusEmprendedor.PENDIENTE);
            nuevoEmprendedor.setCuentaUsuario(new CuentaUsuario(new ObjectId().toHexString(), "jose@gmail.com", "12345", false));
            nuevoEmprendedor.setCuentaBancaria(new CuentaBancaria(new ObjectId().toHexString(), "1111222233", "Jose Trista", Banco.SANTANDER));
            nuevoEmprendedor.setImagen(new Imagen(new ObjectId().toHexString(), new byte[]{1}));
            nuevoEmprendedor.setDocumento(new Documento(new ObjectId().toHexString(), new byte[]{2}));

            Emprendedor registroEmprendedor = emprendedoresDAO.registrarEmprendedor(nuevoEmprendedor);

            assertNotNull(registroEmprendedor);
            assertNotNull(registroEmprendedor.getIdEmprendedor());
            assertEquals("Jose Trista", registroEmprendedor.getNombre());
            assertEquals(EstatusEmprendedor.PENDIENTE, registroEmprendedor.getEstatus());
        });
    }

    /**
     * Prueba para verificar que el estatus de un emprendedor se actualiza correctamente
     */
    @Test
    public void actualizarEstatusEmprendedorOK() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();
            
            Emprendedor emprendedorActualizado = emprendedoresDAO.actualizarEmprendedor(
                    emprendedor.getIdEmprendedor(), 
                    EstatusEmprendedor.ACTIVO
            );
            
            assertNotNull(emprendedorActualizado);
            assertEquals(EstatusEmprendedor.ACTIVO, emprendedorActualizado.getEstatus());
        });
    }
    
    /**
     * Prueba para verificar que encuentra correctamente a un emprendedor por su id
     */
    @Test
    public void obtenerEmprendedorPorIdOK(){
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();
            
            Emprendedor emprendedorEncontrado = emprendedoresDAO.obtenerEmprendedorPorID(emprendedor.getIdEmprendedor());
            
            assertNotNull(emprendedorEncontrado);
            assertEquals(emprendedor.getIdEmprendedor(), emprendedorEncontrado.getIdEmprendedor());
            assertEquals(emprendedor.getNombre(), emprendedorEncontrado.getNombre());
        });
    }
    
    /**
     * Prueba para verificar que consulta correctamente a los emprendedores de la
     * base de datos
     */
    @Test
    public void consultarEmprendedoresOK(){
        assertDoesNotThrow(() -> {
            List<Emprendedor> emprendedores = emprendedoresDAO.consultarEmprendedores();
            assertNotNull(emprendedores);
            assertEquals(2, emprendedores.size());
        });
    }
}
