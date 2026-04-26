/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import java.util.List;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;

/**
 *
 * @author xiomi
 */
public interface IGestionRepartidores {
    
    List<RepartidorDTO> obtenerRepartidoresDisponibles();
    
    boolean publicarSolicitud(SolicitudEntregaDTO solicitud);
    
}
