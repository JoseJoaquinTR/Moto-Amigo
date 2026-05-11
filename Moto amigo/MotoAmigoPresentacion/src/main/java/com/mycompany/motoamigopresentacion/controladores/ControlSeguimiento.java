package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.cusolicitarentrega.FuncionalidadSeguimiento;
import com.mycompany.cusolicitarentrega.IFuncionalidadSeguimiento;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador del seguimiento en tiempo real de una entrega. Encapsula el
 * caso de uso correspondiente para que las pantallas no lo instancien
 * directamente.
 *
 * @author joset
 */
public class ControlSeguimiento {

    private static ControlSeguimiento instancia;

    private final IFuncionalidadSeguimiento funcionalidad;

    private ControlSeguimiento() {
        this.funcionalidad = FuncionalidadSeguimiento.crear();
    }

    /**
     * Obtiene la instancia única del controlador.
     *
     * @return instancia de ControlSeguimiento.
     */
    public static ControlSeguimiento getInstance() {
        if (instancia == null) {
            instancia = new ControlSeguimiento();
        }
        return instancia;
    }

    /**
     * Indica si la ruta de seguimiento ha terminado.
     *
     * @return true si la ruta ha terminado; false si ocurre un error o no ha
     * terminado.
     */
    public boolean haTerminado() {
        try {
            return funcionalidad.haTerminado();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSeguimiento.class.getName()).log(Level.SEVERE, "Error consultando fin de ruta", ex);
            return false;
        }
    }

    /**
     * Obtiene la siguiente ubicación de la ruta de seguimiento.
     *
     * @return siguiente ubicación; null si ocurre un error o no hay más
     * ubicaciones.
     */
    public UbicacionDTO obtenerSiguienteUbicacion() {
        try {
            return funcionalidad.obtenerSiguiente();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSeguimiento.class.getName()).log(Level.SEVERE, "Error obteniendo siguiente ubicación", ex);
            return null;
        }
    }

    /**
     * Obtiene los datos del repartidor asignado a la entrega.
     *
     * @param idRepartidor identificador del repartidor.
     * @return datos del repartidor; null si ocurre un error o no se encuentra.
     */
    public RepartidorDTO obtenerRepartidorAsignado(Long idRepartidor) {
        try {
            return funcionalidad.obtenerRepartidorAsignado(idRepartidor);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlSeguimiento.class.getName()).log(Level.SEVERE, "Error obteniendo repartidor asignado", ex);
            return null;
        }
    }
}
