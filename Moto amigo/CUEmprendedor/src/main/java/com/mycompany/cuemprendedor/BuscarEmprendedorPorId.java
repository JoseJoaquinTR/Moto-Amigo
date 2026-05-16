package com.mycompany.cuemprendedor;

import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 * Implementación del caso de uso para buscar un emprendedor por su identificador.
 * Delega la consulta a la fachada de negocio.
 *
 * @author Jesus Omar
 */
public class BuscarEmprendedorPorId implements IBuscarEmprendedorPorId {

    private final IFachadaNegocio fachada;

    private BuscarEmprendedorPorId(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    /**
     * Crea una nueva instancia del caso de uso usando la fachada por defecto.
     *
     * @return instancia lista para usarse.
     */
    public static BuscarEmprendedorPorId crear() {
        return new BuscarEmprendedorPorId(FachadaNegocio.crear());
    }

    @Override
    public EmprendedorDTO buscarEmprendedorPorId(Long id) throws NegocioException {
        return fachada.obtenerEmprendedorPorId(id);
    }
}
