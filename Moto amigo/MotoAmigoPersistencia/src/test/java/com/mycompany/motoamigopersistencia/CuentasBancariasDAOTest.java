package com.mycompany.motoamigopersistencia;

import Enums.Banco;
import Enums.EstatusEmprendedor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdao.CuentasBancariasDAO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jesus Omar
 */
public class CuentasBancariasDAOTest {

    private CuentasBancariasDAO cuentasBancariasDAO;
    private MongoCollection<Emprendedor> coleccion;

    @BeforeEach
    public void setup() {
        cuentasBancariasDAO = CuentasBancariasDAO.getInstancia();

        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = bd.getCollection("emprendedores", Emprendedor.class);

        coleccion.deleteMany(new org.bson.Document());

        Emprendedor emp1 = new Emprendedor();
        emp1.setNombre("Juan Pablo");
        emp1.setCorreo("pollo@gmail.com");
        emp1.setTelefono("6441234567");
        emp1.setRfc("RAL900101ABC");
        emp1.setEstatus(EstatusEmprendedor.PENDIENTE);
        emp1.setCuentaBancaria(new CuentaBancaria(
                new ObjectId().toHexString(), "1234567890", "Juan Pablo", Banco.BBVA
        ));

        coleccion.insertOne(emp1);
    }

    /**
     * Prueba para verificar que se obtiene correctamente la cuenta bancaria de
     * un emprendedor
     */
    @Test
    public void obtenerCuentaBancariaPorIdEmprendedorOK() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            CuentaBancaria cuenta = cuentasBancariasDAO.obtenerCuentaBancariaPorIdEmprendedor(
                    emprendedor.getIdEmprendedor()
            );

            assertNotNull(cuenta);
            assertEquals("1234567890", cuenta.getNumeroCuenta());
            assertEquals(Banco.BBVA, cuenta.getInstitucionBancaria());
        });
    }
}
