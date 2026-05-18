package com.mycompany.curepartidor;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 * Implementación del caso de uso para buscar un repartidor por su identificador.
 * Delega la consulta a la fachada de negocio.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class BuscarRepartidorPorId implements IBuscarRepartidorPorId {

    private final IFachadaNegocio fachada;

    private BuscarRepartidorPorId(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static BuscarRepartidorPorId crear() {
        return new BuscarRepartidorPorId(FachadaNegocio.crear());
    }

    @Override
    public RepartidorDTO buscarRepartidorPorId(String id) throws NegocioException {
        return fachada.obtenerRepartidorPorId(id);
    }
}
