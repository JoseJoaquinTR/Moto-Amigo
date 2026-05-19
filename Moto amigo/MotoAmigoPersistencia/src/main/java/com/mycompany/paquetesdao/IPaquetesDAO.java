
package com.mycompany.paquetesdao;

import com.mycompany.Entidades.Paquete;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import java.util.List;

/**
 * @author joset
 */
public interface IPaquetesDAO {


    Paquete agregar(NuevoPaqueteDTO paquete) throws PersistenciaException;


    Paquete actualizar(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException;

    boolean eliminar(String id) throws PersistenciaException;

    Paquete consultarPorId(String id) throws PersistenciaException;

    List<Paquete> consultarPorNombre(String nombre,String idEmprendedor) throws PersistenciaException;

    List<Paquete> obtenerPorEmprendedor(String idEmprendedor) throws PersistenciaException;

}
