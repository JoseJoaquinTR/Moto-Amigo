/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;

/**
 *
 * @author calo2
 */
public interface IRutaBO {

    public RutaResponseDTO calcularRuta(RutaRequestDTO request);

    public boolean haTerminadoRuta();

    public UbicacionDTO obtenerSiguienteUbicacion();
}
