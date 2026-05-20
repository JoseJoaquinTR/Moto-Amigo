package com.mycompany.motoamigonegocio.Observers;

import Enums.EstatusEmprendedor;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author Jesus Omar
 */
public interface IObservadorEstatusEmprendedor {

    /**
     * Este metodo activa o desactiva la cuenta de un emprendedor segun el
     * estatus que le notifiquen
     *
     * @param idEmprendedor el id del emprendedor al que se le actualizara el
     * estado de su cuenta de usuario
     * @param estatus el nuevo estatus de del emprendedor
     * @throws NegocioException
     */
    void estatusEmprendedorActualizado(String idEmprendedor, EstatusEmprendedor estatus) throws NegocioException;
}
