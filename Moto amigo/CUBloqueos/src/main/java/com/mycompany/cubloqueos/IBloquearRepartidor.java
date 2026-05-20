/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IBloquearRepartidor {

    
    ReporteBloqueoDTO bloquearRepartidor(NuevoReporteBloqueoDTO dto) throws NegocioException ;

    boolean validarRepartidorSeleccionado(RepartidorDTO repartidor)throws NegocioException ;

    boolean validarMotivoSeleccionado(MotivoDTO motivo)throws NegocioException ;
}
