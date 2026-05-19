/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import static Adapter.AdapterMotivoToDTO.adaptarLista;
import com.mycompany.Entidades.Motivo;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import enums.Tipo;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class MotivosBO implements IMotivosBO {

    private static MotivosBO instancia;

    private final IFachadaPersistencia persistencia;

    public MotivosBO() {
        this.persistencia = FachadaPersistencia.getInstancia();
    }

    public static synchronized MotivosBO getInstancia() {

        if (instancia == null) {
            instancia = new MotivosBO();
        }

        return instancia;
    }

    @Override
    public List<MotivoDTO> obetnerMotivos(Tipo tipo) throws NegocioException {
        List<Motivo> motivos = persistencia.obtenerMotivos(tipo);

        return adaptarLista(motivos);
    }

    @Override
    public boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws NegocioException {

        try {
            if (motivo == null || tipo == null) {
                return false;
            }
            
            return persistencia.existeMotivo(motivo, tipo);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al validar motivo");
        }
    }
}
