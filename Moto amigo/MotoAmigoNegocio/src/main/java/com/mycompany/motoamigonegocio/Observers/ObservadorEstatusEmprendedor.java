package com.mycompany.motoamigonegocio.Observers;

import Enums.EstatusEmprendedor;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;

/**
 *
 * @author Jesus Omar
 */
public class ObservadorEstatusEmprendedor implements IObservadorEstatusEmprendedor {

    private final IFachadaPersistencia persistencia;

    public ObservadorEstatusEmprendedor(IFachadaPersistencia persistencia) {
        this.persistencia = FachadaPersistencia.getInstancia();
    }

    /**
     * Este metodo activa o desactiva la cuenta de un emprendedor segun el
     * estatus que le notifiquen
     *
     * @param idEmprendedor el id del emprendedor al que se le actualizara el
     * estado de su cuenta de usuario
     * @param estatus el nuevo estatus de del emprendedor
     * @throws NegocioException
     */
    @Override
    public void estatusEmprendedorActualizado(String idEmprendedor, EstatusEmprendedor estatus) throws NegocioException {
        try {
            if (estatus.equals(EstatusEmprendedor.ACTIVO)) {
                persistencia.activarCuenta(idEmprendedor);
            } else if (estatus.equals(EstatusEmprendedor.BAJA) || estatus.equals(EstatusEmprendedor.RECHAZADO)) {
                persistencia.desactivarCuenta(idEmprendedor);
            }
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la cuenta del emprendedor", ex);
        }
    }
}
