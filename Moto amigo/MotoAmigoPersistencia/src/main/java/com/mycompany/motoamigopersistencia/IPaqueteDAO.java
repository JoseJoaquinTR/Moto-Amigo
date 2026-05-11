
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Paquete;
import com.mycompany.motoamigodto.EditarPaqueteDTO;
import com.mycompany.motoamigodto.NuevoPaqueteDTO;
import java.util.List;

/**
 * @author joset
 */
public interface IPaqueteDAO {


    Paquete agregar(NuevoPaqueteDTO paquete) throws PersistenciaException;


    Paquete actualizar(String id, EditarPaqueteDTO datosNuevos) throws PersistenciaException;

    boolean eliminar(String id) throws PersistenciaException;

    Paquete consultarPorId(String id) throws PersistenciaException;

    List<Paquete> consultarPorNombre(String nombre) throws PersistenciaException;

    List<Paquete> obtenerPorEmprendedor(String idEmprendedor) throws PersistenciaException;

}
