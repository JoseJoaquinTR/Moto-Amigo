/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;

/**
 *
 * @author calo2
 */
public interface IConsultarRuta {
    
    public abstract RutaResponseDTO consultarRuta(RutaRequestDTO rutaDTO );
}
