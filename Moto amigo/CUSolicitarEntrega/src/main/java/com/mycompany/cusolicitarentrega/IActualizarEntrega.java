/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cusolicitarentrega;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
/**
 *
 * @author joset
 */


public interface IActualizarEntrega {
    EntregaDTO aceptar(String idEntrega, String idRepartidor) throws NegocioException;
    EntregaDTO finalizar(String idEntrega) throws NegocioException;
}