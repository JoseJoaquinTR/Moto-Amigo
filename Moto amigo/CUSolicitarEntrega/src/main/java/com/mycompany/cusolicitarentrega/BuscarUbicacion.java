/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.IUbicacionBO;
import com.mycompany.motoamigonegocio.UbicacionBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class BuscarUbicacion implements IBuscarUbicacion {
    private final IUbicacionBO dao = UbicacionBO.getInstancia();

    @Override
    public List<UbicacionDTO> buscarUbicacion(String texto) throws Exception {
        if (texto == null || texto.trim().length() < 3)
            return new ArrayList<>();
        return dao.buscarUbicacion(texto.trim());
    }
}
