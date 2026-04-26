/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigopersistencia.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.RepartidorDAO;

/**
 *
 * @author joset
 */
public class RepartidorBO implements IRepartidorBO{

    private IRepartidorDAO dao = RepartidorDAO.getInstance();

    @Override
    public RepartidorDTO obtenerRepartidorPorId(Long idRepartidor) {
        return dao.obtenerRepartidorPorId(idRepartidor);
    }
}
