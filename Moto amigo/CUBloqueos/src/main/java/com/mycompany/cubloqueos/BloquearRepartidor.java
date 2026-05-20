/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cubloqueos;

import Repartidores.IRepartidoresBO;
import Repartidores.RepartidoresBO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.reportes.IMotivosBO;
import com.mycompany.reportes.IReportesBloqueoBO;
import com.mycompany.reportes.MotivosBO;
import com.mycompany.reportes.ReportesBloqueoBO;

import enums.Estado;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class BloquearRepartidor implements IBloquearRepartidor {

    private static BloquearRepartidor instancia;

    private final IRepartidoresBO repartidoresBO;
    private final IReportesBloqueoBO bloqueoBO;
    private final IMotivosBO motivosBO;

    public BloquearRepartidor() {
        this.repartidoresBO = RepartidoresBO.getInstancia();
        this.bloqueoBO = ReportesBloqueoBO.getInstancia();
        this.motivosBO = MotivosBO.getInstancia();
    }

    public static synchronized BloquearRepartidor getInstancia() {
        if (instancia == null) {
            instancia = new BloquearRepartidor();
        }
        return instancia;
    }

    @Override
    public ReporteBloqueoDTO bloquearRepartidor(NuevoReporteBloqueoDTO dto) throws NegocioException {

        if (dto == null) {
            throw new NegocioException("El reporte de bloqueo no puede estar vacío.");
        }

        if (!validarRepartidorSeleccionado(dto.getRepartidor())) {
            throw new NegocioException("Debe seleccionar un repartidor válido.");
        }

        if (!validarMotivoSeleccionado(dto.getMotivo())) {
            throw new NegocioException("Debe seleccionar un motivo válido.");
        }

        RepartidorDTO repartidorBloqueado
                = repartidoresBO.cambiarEstadoRepartidor(
                        dto.getRepartidor().getId(),
                        Estado.BLOQUEADO
                );

        if (repartidorBloqueado == null) {
            throw new NegocioException("No se pudo bloquear el repartidor.");
        }

        RepartidorDTO repartidorActualizado = repartidoresBO.incrementarNumeroBloqueos(repartidorBloqueado.getId());

        dto.setRepartidor(repartidorActualizado);

        return bloqueoBO.guardarReporteBloqueo(dto);
    }

    @Override
    public boolean validarRepartidorSeleccionado(RepartidorDTO repartidor) throws NegocioException {

        if (repartidor == null) {
            return false;
        }

        if (repartidor.getId() == null || repartidor.getId().isBlank()) {
            return false;
        }

        RepartidorDTO repartidorExistente = repartidoresBO.consultarRepartidorPorId(repartidor.getId());

        return repartidorExistente != null;
    }

    @Override
    public boolean validarMotivoSeleccionado(MotivoDTO motivo) throws NegocioException {

        try {
            if (motivo == null) {
                return false;
            }

            if (motivo.getMotivo() == null || motivo.getMotivo().isBlank()) {
                return false;
            }

            return motivosBO.existeMotivo(motivo, motivo.getTipo());

        } catch (NegocioException ex) {
            throw new NegocioException("Error al valdiar motivo");
        }
    }

}
