package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.FachadaNegocio;
import com.mycompany.motoamigonegocio.IFachadaNegocio;

public class FuncionalidadSeguimiento implements IFuncionalidadSeguimiento {

    private final IFachadaNegocio fachada;

    private FuncionalidadSeguimiento(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }

    public static FuncionalidadSeguimiento crear() {
        return new FuncionalidadSeguimiento(FachadaNegocio.crear());
    }

    @Override
    public boolean haTerminado() {
        return fachada.haTerminadoRuta();
    }

    @Override
    public UbicacionDTO obtenerSiguiente() {
        return fachada.obtenerSiguienteUbicacion();
    }

    @Override
    public RepartidorDTO obtenerRepartidorAsignado(Long idRepartidor) {
        return fachada.obtenerRepartidorPorId(idRepartidor);
    }
}
