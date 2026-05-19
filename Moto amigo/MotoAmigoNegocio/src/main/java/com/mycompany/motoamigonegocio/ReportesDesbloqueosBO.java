/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.motoamigonegocio;

import static Adapter.AdapterInformacionReporte.adaptarListaDesbloqueos;
import static Adapter.AdapterReporteDesbloqueoToDTO.adaptarLista;
import static Adapter.AdapterReporteDesbloqueoToDTO.toDTO;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesDesbloqueosBO implements IReportesDesbloqueosBO {

    private static ReportesDesbloqueosBO instancia;
    private final IFachadaPersistencia persistencia;

    public ReportesDesbloqueosBO() {
        this.persistencia = FachadaPersistencia.getInstancia();
    }

    public static synchronized ReportesDesbloqueosBO getInstancia() {
        if (instancia == null) {
            instancia = new ReportesDesbloqueosBO();
        }
        return instancia;
    }

    @Override
    public ReporteDesbloqueoDTO guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto) throws NegocioException {
        try {
            return toDTO(persistencia.guardarReporteDesbloqueo(dto));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar reporte de desbloqueo.", ex);
        }
    }

    @Override
    public List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos() throws NegocioException {
        try {
            return adaptarLista(persistencia.consultarReportesDesbloqueos());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar reportes de desbloqueo.", ex);
        }
    }

    @Override
    public List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos(FiltrosDTO filtros) throws NegocioException {
        try {
            return adaptarLista(persistencia.consultarReportesDesbloqueos(filtros));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar reportes de desbloqueo con filtros.", ex);
        }
    }

    @Override
    public List<InformacionReporteDesbloqueoDTO> consultarReportesDesbloqueoParaPDF(FiltrosDTO filtros) throws NegocioException {
        try {
            List<ReporteDesbloqueoDTO> reportes = consultarReportesDesbloqueos(filtros);
            return adaptarListaDesbloqueos(reportes);
        } catch (NegocioException ex) {
            throw new NegocioException("Error al consultar reportes de desbloqueo para PDF.", ex);
        }
    }
}
