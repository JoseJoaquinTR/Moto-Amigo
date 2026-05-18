package com.mycompany.emprendedoresdao;

import Enums.EstatusEmprendedor;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public interface IEmprendedoresDAO {
    Emprendedor registrarEmprendedor(Emprendedor emprendedor)throws PersistenciaException;
    Emprendedor actualizarEmprendedor(String idEmprendedor, EstatusEmprendedor estatus)throws PersistenciaException;
    Emprendedor obtenerEmprendedorPorID(String idEmprendedor)throws PersistenciaException;
    List<Emprendedor> consultarEmprendedores()throws PersistenciaException;
}
