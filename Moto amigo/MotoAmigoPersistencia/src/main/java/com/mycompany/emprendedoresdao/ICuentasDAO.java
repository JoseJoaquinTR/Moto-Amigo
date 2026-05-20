package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface ICuentasDAO {

    /**
     * Busca una cuenta que coincida con el correo que se mando
     * y te regresa el emprendedor que tiene la cuenta, este 
     * metodo regresa un emprendedor por que la DTO de CuentaUsuarioSesion
     * necesita el id y estatus del emprendedor, ademas de las credenciales
     * de la cuenta
     *
     * @param correo el correo de la cuenta que se buscara
     * @return la cuenta que coincide ocn el correo
     * @throws PersistenciaException
     */     
    Emprendedor buscarCuentaPorCorreo(String correo) throws PersistenciaException;

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
