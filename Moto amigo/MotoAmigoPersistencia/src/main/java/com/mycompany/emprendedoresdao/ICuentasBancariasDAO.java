package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface ICuentasBancariasDAO {
    CuentaBancaria obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor)throws PersistenciaException;
}
