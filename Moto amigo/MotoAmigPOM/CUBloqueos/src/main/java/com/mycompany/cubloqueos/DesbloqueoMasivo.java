/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cubloqueos;

import Repartidores.IRepartidoresBO;
import Repartidores.RepartidoresBO;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.reportes.IMotivosBO;
import com.mycompany.reportes.IReportesBloqueoBO;
import com.mycompany.reportes.IReportesDesbloqueosBO;
import com.mycompany.reportes.MotivosBO;
import com.mycompany.reportes.ReportesBloqueoBO;
import com.mycompany.reportes.ReportesDesbloqueosBO;

import enums.Estado;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class DesbloqueoMasivo implements IDesbloqueoMasivo {

    private static DesbloqueoMasivo instancia;

    private final IRepartidoresBO repartidoresBO;
    private final IReportesBloqueoBO reportesBloqueoBO;
    private final IReportesDesbloqueosBO reportesDesbloqueosBO;
    private final IMotivosBO motivosBO;

    public DesbloqueoMasivo() {
        this.repartidoresBO = RepartidoresBO.getInstancia();
        this.reportesBloqueoBO = ReportesBloqueoBO.getInstancia();
        this.reportesDesbloqueosBO = ReportesDesbloqueosBO.getInstancia();
        this.motivosBO = MotivosBO.getInstancia();
    }

    public static synchronized DesbloqueoMasivo getInstancia() {
        if (instancia == null) {
            instancia = new DesbloqueoMasivo();
        }
        return instancia;
    }

    @Override
    public ReporteDesbloqueoDTO desbloqueoMasivo(FiltrosDTO filtros, NuevoReporteDesbloqueoDTO dto) throws NegocioException {

        if (!validarFiltros(filtros)) {
            throw new NegocioException("Debe proporcionar filtros válidos.");
        }

        if (!validarMotivo(dto.getMotivo())) {
            throw new NegocioException("Debe seleccionar un motivo válido.");
        }

        List<RepartidorDTO> repartidores = obtenerRepartidores(filtros);

        if (repartidores.isEmpty()) {
            throw new NegocioException("No se encontraron repartidores para desbloquear.");
        }

        int desbloqueados = 0;

        for (RepartidorDTO repartidor : repartidores) {

            RepartidorDTO repartidorActualizado
                    = repartidoresBO.cambiarEstadoRepartidor(
                            repartidor.getId(),
                            Estado.ACTIVO
                    );

            if (repartidorActualizado != null) {
                desbloqueados++;
            }
        }

        dto.setNumRepartidoresDesbloqueados(desbloqueados);

        return reportesDesbloqueosBO.guardarReporteDesbloqueo(dto);
    }

    @Override
    public boolean validarFiltros(FiltrosDTO filtros) {

        if (filtros == null) {
            return false;
        }

        if (!validarFechas(filtros.getFechaDesde(), filtros.getFechaHasta())) {
            return false;
        }

        return filtros.getFechaDesde() != null
                || filtros.getFechaHasta() != null
                || filtros.getMotivo() != null
                || filtros.getNumBloqueos() > 0;
    }

    @Override
    public boolean validarFechas(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {

        if (fechaDesde == null || fechaHasta == null) {
            return true;
        }

        if (fechaDesde.isAfter(fechaHasta)) {
            return false;
        }

        return !fechaHasta.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean validarMotivo(MotivoDTO motivo) throws NegocioException {

        if (motivo == null) {
            return false;
        }

        if (motivo.getMotivo() == null || motivo.getMotivo().isBlank()) {
            return false;
        }

        return motivosBO.existeMotivo(motivo, motivo.getTipo());
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidores(FiltrosDTO filtros) throws NegocioException {
        return reportesBloqueoBO.obtenerRepartidoresParaDesbloqueoMasivo(filtros);
    }
}
