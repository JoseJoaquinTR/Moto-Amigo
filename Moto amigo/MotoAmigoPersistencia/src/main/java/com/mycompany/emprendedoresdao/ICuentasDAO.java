package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface ICuentasDAO {

    /**
     * Busca una cuenta que coincida con el correo que se mando
     *
     * @param correo el correo de la cuenta que se buscara
     * @return la cuenta que coincide ocn el correo
     * @throws PersistenciaException
     */
    CuentaUsuario buscarCuenta(String correo) throws PersistenciaException;

    /**
     * Activa la cuenta del emprendedor que tiene el id que se recibe como
     * parametro
     *
     * @param idEmprendedor el id del emprendedor al que se le activara la
     * cuenta
     * @throws PersistenciaException
     */
    void activarCuenta(String idEmprendedor) throws PersistenciaException;

    /**
     * Desactiva la cuenta del emprendedor que tiene el id que se recibe como
     * parametro
     *
     * @param idEmprendedor el id del emprendedor al que se le desactivara la
     * cuenta
     * @throws PersistenciaException
     */
    void desactivarCuenta(String idEmprendedor) throws PersistenciaException;
}
