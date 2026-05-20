package com.mycompany.motoamigopersistencia;

import Enums.EstatusEmprendedor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdao.CuentasDAO;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static com.mongodb.client.model.Filters.*;

/**
 *
 * @author Jesus Omar
 */
public class CuentasDAOTest {

    private CuentasDAO cuentasDAO;
    private MongoCollection<Emprendedor> coleccion;

    /**
     * Antes de cada prueba borra los registros que estan en la base de datos y
     * se crea dos emprendedores con cuenta para activar una y desactivar otra
     */
    @BeforeEach
    public void setup() {
        cuentasDAO = CuentasDAO.getInstancia();

        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = baseDatos.getCollection("emprendedores", Emprendedor.class);

        coleccion.deleteMany(new Document());

        Emprendedor emprendedor1 = new Emprendedor();
        emprendedor1.setNombre("Isaac Iran");
        emprendedor1.setCorreo("isaac@gmail.com");
        emprendedor1.setTelefono("6441234567");
        emprendedor1.setRfc("RAL900101ABC");
        emprendedor1.setEstatus(EstatusEmprendedor.PENDIENTE);
        emprendedor1.setCuentaUsuario(new CuentaUsuario(new ObjectId().toHexString(), "isaac@gmail.com", "12345", false));

        Emprendedor emprendedor2 = new Emprendedor();
        emprendedor2.setNombre("Andres Luna");
        emprendedor2.setCorreo("andres@gmail.com");
        emprendedor2.setTelefono("6449876543");
        emprendedor2.setRfc("LOPM850505XYZ");
        emprendedor2.setEstatus(EstatusEmprendedor.ACTIVO);
        emprendedor2.setCuentaUsuario(new CuentaUsuario(new ObjectId().toHexString(), "andres@gmail.com", "12345", true));

        coleccion.insertOne(emprendedor1);
        coleccion.insertOne(emprendedor2);
    }

    /**
     * Prueba para verificar que se buscan las cuentas correctamente
     */
    @Test
    public void buscarCuentaOK() {
        assertDoesNotThrow(() -> {
            Emprendedor usuario = cuentasDAO.buscarCuentaPorCorreo("isaac@gmail.com");
            CuentaUsuario cuenta = usuario.getCuentaUsuario();

            assertNotNull(cuenta);
            assertEquals("isaac@gmail.com", cuenta.getCorreo());
        });
    }

    /**
     * Prueba para verificar que la cuenta de un emprendedor se activa
     * correctamente
     */
    @Test
    public void activarCuentaOK() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            cuentasDAO.activarCuenta(emprendedor.getIdEmprendedor());

            Emprendedor emprendedorActualizado = coleccion.find(
                    eq("_id", new ObjectId(emprendedor.getIdEmprendedor()))
            ).first();

            assertTrue(emprendedorActualizado.getCuentaUsuario().isActiva());
        });
    }

    /**
     * Prueba para verificar que la cuenta de un emprendedor se desactiva
     * correctamente
     */
    @Test
    public void desactivarCuentaOK() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            cuentasDAO.desactivarCuenta(emprendedor.getIdEmprendedor());

            Emprendedor emprendedorActualizado = coleccion.find(
                    eq("_id", new ObjectId(emprendedor.getIdEmprendedor()))
            ).first();

            assertFalse(emprendedorActualizado.getCuentaUsuario().isActiva());
        });
    }
}
