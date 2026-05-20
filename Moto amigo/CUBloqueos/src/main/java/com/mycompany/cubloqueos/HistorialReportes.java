/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.reportes.IMotivosBO;
import com.mycompany.reportes.IReportesBloqueoBO;
import com.mycompany.reportes.IReportesDesbloqueosBO;
import com.mycompany.reportes.MotivosBO;
import com.mycompany.reportes.ReportesBloqueoBO;
import com.mycompany.reportes.ReportesDesbloqueosBO;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class HistorialReportes implements IHistorialReportes {

    private static HistorialReportes instancia;

    private final IReportesBloqueoBO reportesBloqueoBO;
    private final IReportesDesbloqueosBO reportesDesbloqueosBO;
    private final IMotivosBO motivosBO;

    public HistorialReportes() {
        this.reportesBloqueoBO = ReportesBloqueoBO.getInstancia();
        this.reportesDesbloqueosBO = ReportesDesbloqueosBO.getInstancia();
        this.motivosBO = MotivosBO.getInstancia();
    }

    public static synchronized HistorialReportes getInstancia() {
        if (instancia == null) {
            instancia = new HistorialReportes();
        }
        return instancia;
    }

    @Override
    public List<ReporteBloqueoDTO> consultarHistorialBloqueos(FiltrosDTO filtros) throws NegocioException {
        if (!validarFiltros(filtros)) {
            throw new NegocioException("Los filtros proporcionados no son válidos.");
        }

        if (filtros == null) {
            return reportesBloqueoBO.consultarReportesBloqueos();
        }

        return reportesBloqueoBO.consultarReportesBloqueos(filtros);
    }

    @Override
    public List<ReporteDesbloqueoDTO> consultarHistorialDesbloqueos(FiltrosDTO filtros) throws NegocioException {
        if (!validarFiltros(filtros)) {
            throw new NegocioException("Los filtros proporcionados no son válidos.");
        }

        if (filtros == null) {
            return reportesDesbloqueosBO.consultarReportesDesbloqueos();
        }

        return reportesDesbloqueosBO.consultarReportesDesbloqueos(filtros);
    }

    @Override
    public boolean validarFiltros(FiltrosDTO filtros) {
        if (filtros == null) {
            return true;
        }

        if (!validarFechas(filtros.getFechaDesde(), filtros.getFechaHasta())) {
            return false;
        }

        if (filtros.getMotivo() != null && !validarMotivoSeleccionado(filtros.getMotivo())) {
            return false;
        }

        if(filtros.getNumBloqueos() !=null && filtros.getNumBloqueos() < 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean validarFechas(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
        if (fechaDesde == null || fechaHasta == null) {
            return true;
        }

        if (fechaDesde.isAfter(fechaHasta)) {
            return false;
        }

        if(fechaHasta.isAfter(LocalDateTime.now())){
            return false;
        }
        return true;
    }

    @Override
    public boolean validarMotivoSeleccionado(MotivoDTO motivo) {
        if (motivo == null) {
            return true;
        }

        if (motivo.getMotivo() == null || motivo.getMotivo().isBlank()) {
            return false;
        }

        if (motivo.getTipo() == null) {
            return false;
        }

        try {
            return motivosBO.existeMotivo(motivo, motivo.getTipo());
        } catch (NegocioException ex) {
            return false;
        }
    }
}
