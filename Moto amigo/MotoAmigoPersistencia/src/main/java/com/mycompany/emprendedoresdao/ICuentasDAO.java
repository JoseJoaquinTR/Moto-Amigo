package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface ICuentasDAO {
    CuentaUsuario buscarCuenta(String correo, String contrasenia)throws PersistenciaException;
    void activarCuenta(String idEmprendedor)throws PersistenciaException;
    void desactivarCuenta(String idEmprendedor)throws PersistenciaException;
}
