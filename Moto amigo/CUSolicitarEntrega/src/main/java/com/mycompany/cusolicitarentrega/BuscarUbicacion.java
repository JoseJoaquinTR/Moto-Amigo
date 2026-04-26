/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigopersistencia.UbicacionDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class BuscarUbicacion implements IBuscarUbicacion {
    private final UbicacionDAO dao = UbicacionDAO.getInstancia();

    @Override
    public List<UbicacionDTO> ejecutar(String texto) throws Exception {
        if (texto == null || texto.trim().length() < 3)
            return new ArrayList<>();
        return dao.buscarSugerencias(texto.trim());
    }
}
