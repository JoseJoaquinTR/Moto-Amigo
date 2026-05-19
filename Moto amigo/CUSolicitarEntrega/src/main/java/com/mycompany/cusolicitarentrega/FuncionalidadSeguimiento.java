package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 * Implementación del caso de uso de seguimiento de entrega.
 * Delega las consultas a la fachada de negocio.
 *
 * @author joset
 */
public class FuncionalidadSeguimiento implements IFuncionalidadSeguimiento {

    private final IFachadaNegocio fachada;

    private FuncionalidadSeguimiento(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static FuncionalidadSeguimiento crear() {
        return new FuncionalidadSeguimiento(FachadaNegocio.crear());
    }

    @Override
    public boolean haTerminado() throws NegocioException {
        return fachada.haTerminadoRuta();
    }

    @Override
    public UbicacionDTO obtenerSiguiente() throws NegocioException {
        return fachada.obtenerSiguienteUbicacion();
    }

    @Override
    public RepartidorDTO obtenerRepartidorAsignado(String idRepartidor) throws NegocioException {
        return fachada.obtenerRepartidorPorId(idRepartidor);
    }
}
