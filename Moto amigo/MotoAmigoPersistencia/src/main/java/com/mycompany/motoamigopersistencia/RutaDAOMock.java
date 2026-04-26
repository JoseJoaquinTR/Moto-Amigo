/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.motoamigopersistencia;

import com.mycompany.motoamigodto.RutaResponseDTO;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaDAOMock implements IRutaDAO{

    @Override
    public Ruta guardarRuta(RutaResponseDTO ruta) {
        return new Ruta(ruta.getOrigen(), ruta.getDestino(), ruta.getTiempoEstimado());
    }
    
    
}
