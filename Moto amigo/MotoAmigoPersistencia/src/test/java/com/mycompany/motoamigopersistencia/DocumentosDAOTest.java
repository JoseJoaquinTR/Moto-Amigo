package com.mycompany.motoamigopersistencia;

import Enums.EstatusEmprendedor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Documento;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdao.DocumentosDAO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jesus Omar
 */
public class DocumentosDAOTest {

    private DocumentosDAO documentosDAO;
    private MongoCollection<Emprendedor> coleccion;

    /**
     * Este metodo se ejecuta antes de cada prueba, elimina lo que hay en la
     * base de datos y crea un emprendedor con un documento para las pruebas
     */
    @BeforeEach
    public void setUp() {
        documentosDAO = DocumentosDAO.getInstancia();

        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccion = bd.getCollection("emprendedores", Emprendedor.class);

        coleccion.deleteMany(new org.bson.Document());

        Emprendedor emp1 = new Emprendedor();
        emp1.setNombre("Miguel Medina");
        emp1.setCorreo("miguel@gmail.com");
        emp1.setTelefono("6441234567");
        emp1.setRfc("RAL900101ABC");
        emp1.setEstatus(EstatusEmprendedor.PENDIENTE);
        emp1.setDocumento(new Documento(
                new ObjectId().toHexString(), new byte[]{1, 2, 3}
        ));

        coleccion.insertOne(emp1);
    }

    /**
     * Metodo para verificar que se actualiza correctamente el documento de un
     * emprendedor
     */
    @Test
    public void actualizarDocumentoEmprendedorOK() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            Documento nuevoDocumento = new Documento(
                    new ObjectId().toHexString(), new byte[]{7, 8, 9}
            );

            Documento actualizado = documentosDAO.actualizarDocumentoEmprendedor(
                    emprendedor.getIdEmprendedor(), nuevoDocumento
            );

            assertNotNull(actualizado);
            assertArrayEquals(new byte[]{7, 8, 9}, actualizado.getDocumento());
        });
    }

    /**
     * Metodo para verificar que se obtiene el documento de un emprendedor
     * correctamente
     */
    @Test
    public void obtenerDocumentoPorIdEmprendedorOK() {
        assertDoesNotThrow(() -> {
            Emprendedor emprendedor = coleccion.find().first();

            Documento documento = documentosDAO.obtenerDocumentoPorIdEmprendedor(
                    emprendedor.getIdEmprendedor()
            );

            assertNotNull(documento);
            assertArrayEquals(new byte[]{1, 2, 3}, documento.getDocumento());
        });
    }
}
