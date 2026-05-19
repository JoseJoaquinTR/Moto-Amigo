
package com.mycompany.paquetesdao;

import com.mycompany.Entidades.Paquete;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import java.util.List;

/**
 * @author joset
 */
public interface IPaquetesDAO {


    Paquete agregar(Paquete paquete) throws PersistenciaException;


    Paquete actualizar(String id, Paquete datosNuevos) throws PersistenciaException;

    boolean eliminar(String id) throws PersistenciaException;

    Paquete consultarPorId(String id) throws PersistenciaException;

    List<Paquete> consultarPorNombre(String nombre,String idEmprendedor) throws PersistenciaException;

    List<Paquete> obtenerPorEmprendedor(String idEmprendedor) throws PersistenciaException;

}
