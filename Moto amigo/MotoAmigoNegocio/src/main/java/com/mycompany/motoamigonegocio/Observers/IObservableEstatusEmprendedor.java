package com.mycompany.motoamigonegocio.Observers;

import Enums.EstatusEmprendedor;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author Jesus Omar
 */
public interface IObservableEstatusEmprendedor {
    void agregarObservador(IObservadorEstatusEmprendedor observador);
    void notificarObservadores(String idEmprendedor, EstatusEmprendedor estatus);
}
