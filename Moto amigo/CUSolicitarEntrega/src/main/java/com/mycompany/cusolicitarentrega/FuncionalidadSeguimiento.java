package com.mycompany.cusolicitarentrega;

import com.consultarruta.servicios.mapBox.IMapBoxService;
import com.consultarruta.servicios.mapBox.MapBoxService;
import com.mycompany.motoamigodto.UbicacionDTO;

public class FuncionalidadSeguimiento implements IFuncionalidadSeguimiento {

    private final IMapBoxService mapBoxService;
    private int pasos = 0;
    private final int TOTAL_PASOS = 12;

    private FuncionalidadSeguimiento(IMapBoxService mapBoxService) {
        this.mapBoxService = mapBoxService;
    }

    public static FuncionalidadSeguimiento crear() {
        return new FuncionalidadSeguimiento( MapBoxService.getInstance());
    }

    @Override
    public boolean haTerminado() {
        return mapBoxService.comprobarSiFinalizoRuta(); 
        
    }

    @Override
    public UbicacionDTO obtenerSiguiente() {
        return mapBoxService.obtenerSiguienteUbicacion();
    }
}