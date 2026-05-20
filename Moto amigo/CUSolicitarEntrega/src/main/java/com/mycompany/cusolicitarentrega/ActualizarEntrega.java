/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cusolicitarentrega;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;

/**
 *
 * @author joset
 */

public class ActualizarEntrega implements IActualizarEntrega {
    
    private final IFachadaNegocio fachada;
    
    private ActualizarEntrega(IFachadaNegocio fachada) {
        this.fachada = fachada;
    }
    
    public static ActualizarEntrega crear() {
        return new ActualizarEntrega(FachadaNegocio.crear());
    }
    
    @Override
    public EntregaDTO aceptar(String idEntrega, String idRepartidor) throws NegocioException {
        if (idEntrega == null || idRepartidor == null) {
            throw new NegocioException("Datos incompletos para aceptar la entrega.");
        }
        try {
            return fachada.aceptarEntrega(idEntrega, idRepartidor);
        } catch (NegocioException ex) {
            throw new  NegocioException(ex.getMessage(), ex);
        }
    }
    @Override
    public EntregaDTO finalizar(String idEntrega) throws NegocioException {
        if (idEntrega == null) {
            throw new NegocioException("Id de entrega requerido.");
        }
        try {
            return fachada.finalizarEntrega(idEntrega);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}