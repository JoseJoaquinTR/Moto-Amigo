package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.Negocio;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface INegociosDAO {
    Negocio registrarNegocio(Negocio negocio)throws PersistenciaException;
    Negocio obtenerNegocioPorIdEmprendedor(String idEmprendedor)throws PersistenciaException;
}
