package com.mycompany.emprendedoresdao;

import Enums.EstatusEmprendedor;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class EmprendedoresDAO implements IEmprendedoresDAO{

    private static final String NOMBRE_COLECCION = "emprendedores";
    private static EmprendedoresDAO instancia;
    private final MongoCollection<Emprendedor> COLECCION;

    private EmprendedoresDAO() {
        MongoDatabase baseDatos = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        this.COLECCION = baseDatos.getCollection(NOMBRE_COLECCION, Emprendedor.class);
    }
    
    public static EmprendedoresDAO getInstancia(){
        if(instancia == null){
            instancia = new EmprendedoresDAO();
        }
        return instancia;
    }

    /**
     * Resgistra un nuevo emprendedor en la base de datos
     * @param emprendedor
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Emprendedor registrarEmprendedor(Emprendedor emprendedor) throws PersistenciaException {
        try{
            InsertOneResult resultado = COLECCION.insertOne(emprendedor);
            if(!resultado.wasAcknowledged()){
                throw new PersistenciaException("No se pudo resgitrar al emprendedor");
            }
            return emprendedor;
        }catch(MongoException ex){
            throw new PersistenciaException("Error al registrar al emprendedor", ex);
        }
    }

    @Override
    public Emprendedor actualizarEmprendedor(String idEmprendedor, EstatusEmprendedor estatus) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Emprendedor obtenerEmprendedorPorID(String idEmprendedor) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Emprendedor> consultarEmprendedores() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
}
