/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;

import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import static Adapter.AdapterReporteBloqueoToDTO.*;
import static Adapter.AdapterInformacionReporte.*;
import Repartidores.IRepartidoresBO;
import Repartidores.RepartidoresBO;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesBloqueoBO implements IReportesBloqueoBO {

    private static ReportesBloqueoBO instancia;
    private final IRepartidoresBO repartidoresBO;
    private final IFachadaPersistencia persistencia;

    public ReportesBloqueoBO() {
        this.persistencia = FachadaPersistencia.getInstancia();
        this.repartidoresBO = RepartidoresBO.getInstancia();
    }

    public static synchronized ReportesBloqueoBO getInstancia() {
        if (instancia == null) {
            instancia = new ReportesBloqueoBO();
        }
        return instancia;
    }

    @Override
    public ReporteBloqueoDTO guardarReporteBloqueo(NuevoReporteBloqueoDTO dto) throws NegocioException {
        try {
            ReporteBloqueoDTO reporte = toDTO(persistencia.guardarReporteBloqueo(dto));

            if (reporte.getRepartidor() != null && reporte.getRepartidor().getId() != null) {
                RepartidorDTO repartidor = repartidoresBO.consultarRepartidorPorId(reporte.getRepartidor().getId());

                if (repartidor != null) {
                    reporte.setRepartidor(repartidor);
                }
            }

            return reporte;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar reporte de bloqueo.", ex);
        }
    }

    @Override

    public List<ReporteBloqueoDTO> consultarReportesBloqueos() throws NegocioException {
        try {
            List<ReporteBloqueoDTO> reportes = adaptarLista(persistencia.consultarReportesBloqueos());

            for (ReporteBloqueoDTO reporte : reportes) {
                if (reporte.getRepartidor() != null && reporte.getRepartidor().getId() != null) {
                    RepartidorDTO repartidor = repartidoresBO.consultarRepartidorPorId(reporte.getRepartidor().getId());
                    reporte.setRepartidor(repartidor);
                }
            }

            return reportes;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar reportes de bloqueo.", ex);
        }
    }

    @Override
    public List<ReporteBloqueoDTO> consultarReportesBloqueos(FiltrosDTO filtros) throws NegocioException {
        try {
            List<ReporteBloqueoDTO> reportes = adaptarLista(persistencia.consultarReportesBloqueos(filtros));

            for (ReporteBloqueoDTO reporte : reportes) {
                if (reporte.getRepartidor() != null && reporte.getRepartidor().getId() != null) {
                    RepartidorDTO repartidor = repartidoresBO.consultarRepartidorPorId(reporte.getRepartidor().getId());
                    reporte.setRepartidor(repartidor);
                }
            }

            return reportes;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar reportes de bloqueo con filtros.", ex);
        }
    }

    @Override
    public List<RepartidorDTO> obtenerRepartidoresParaDesbloqueoMasivo(FiltrosDTO filtros)
            throws NegocioException {
        try {
            return Adapter.AdapterRepartidorARepartidorDTO.adaptarLista(persistencia.obtenerRepartidoresParaDesbloqueo(filtros));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener repartidores para desbloqueo masivo.", ex);
        }
    }

    @Override
    public List<InformacionReporteBloqueoDTO> consultarReportesBloqueoParaPDF(FiltrosDTO filtros)
            throws NegocioException {
        try {
            List<ReporteBloqueoDTO> reportes
                    = consultarReportesBloqueos(filtros);

            return adaptarLista(reportes);

        } catch (NegocioException ex) {
            throw new NegocioException("Error al consultar reportes de bloqueo para PDF.", ex);
        }
    }
}
