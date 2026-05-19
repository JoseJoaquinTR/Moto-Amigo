package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface ICuentasBancariasDAO {
    /**
     * Te regresa la cuenta bancaria de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor del que se quiere consultar su
     * cuenta bancaria
     * @return la cuenta bancaria del emprendedor que tiene el id que se mando
     * como parametro
     * @throws PersistenciaException
     */
    CuentaBancaria obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor)throws PersistenciaException;
}
