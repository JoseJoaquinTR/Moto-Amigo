package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.Direccion;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface IDireccionesDAO {
    Direccion actualizarDireccion(String idNegocio, Direccion direccion)throws PersistenciaException;
    Direccion obtenerDireccionPorIdNegocio(String idNegocio)throws PersistenciaException;
}
