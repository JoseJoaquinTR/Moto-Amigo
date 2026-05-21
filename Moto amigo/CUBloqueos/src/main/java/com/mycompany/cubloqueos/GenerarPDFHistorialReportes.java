/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.reportes.IReportesBloqueoBO;
import com.mycompany.reportes.IReportesDesbloqueosBO;
import com.mycompany.reportes.ReportesBloqueoBO;
import com.mycompany.reportes.ReportesDesbloqueosBO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class GenerarPDFHistorialReportes implements IGenerarPDFHistorialReportes {

    private static GenerarPDFHistorialReportes instancia;
    private final IReportesBloqueoBO reportesBloqueoBO;
    private final IReportesDesbloqueosBO reportesDesbloqueosBO;

    public GenerarPDFHistorialReportes() {
        this.reportesBloqueoBO = ReportesBloqueoBO.getInstancia();
        this.reportesDesbloqueosBO = ReportesDesbloqueosBO.getInstancia();
    }

    public static synchronized GenerarPDFHistorialReportes getInstancia() {
        if (instancia == null) {
            instancia = new GenerarPDFHistorialReportes();
        }
        return instancia;
    }

    @Override
    public boolean descargarPDFHistorialBloqueos(FiltrosDTO filtros, String rutaDestino) throws NegocioException {
        if (rutaDestino == null || rutaDestino.isBlank()) {
            throw new NegocioException("La ruta de destino es obligatoria.");
        }

        try (InputStream reporteStream = getClass().getResourceAsStream("/Reportes/historial_bloqueos.jrxml")) {
            if (reporteStream == null) {
                throw new NegocioException("No se encontró la plantilla del reporte de bloqueos.");
            }

            List<InformacionReporteBloqueoDTO> reportes = reportesBloqueoBO.consultarReportesBloqueoParaPDF(filtros);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO", "Historial de Bloqueos");
            parametros.put("FECHA", LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/M/yyyy")));
            parametros.put("FILTROS", construirTextoFiltros(filtros));
            parametros.put("TOTAL_REPORTES", reportes.size());

            List<Map<String, Object>> filas = new ArrayList<>();
            for (InformacionReporteBloqueoDTO reporte : reportes) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("fechaHora", reporte.getFechaHora() != null ? reporte.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "");
                fila.put("repartidor", reporte.getNombreRepartidor() != null ? reporte.getNombreRepartidor() : "");
                fila.put("motivo", reporte.getMotivo() != null ? reporte.getMotivo().getMotivo() : "");
                filas.add(fila);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(filas));

            try (OutputStream out = new FileOutputStream(new File(rutaDestino))) {
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();
            }

            return true;
        } catch (JRException | IOException ex) {
            throw new NegocioException("Error al generar PDF de bloqueos: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean descargarPDFHistorialDesbloqueos(FiltrosDTO filtros, String rutaDestino) throws NegocioException {
        if (rutaDestino == null || rutaDestino.isBlank()) {
            throw new NegocioException("La ruta de destino es obligatoria.");
        }

        try (InputStream reporteStream = getClass().getResourceAsStream("/Reportes/historial_desbloqueos.jrxml")) {
            if (reporteStream == null) {
                throw new NegocioException("No se encontró la plantilla del reporte de desbloqueos.");
            }

            List<InformacionReporteDesbloqueoDTO> reportes = reportesDesbloqueosBO.consultarReportesDesbloqueoParaPDF(filtros);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO", "Historial de Desbloqueos");
            parametros.put("FECHA", LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/M/yyyy")));
            parametros.put("FILTROS", construirTextoFiltros(filtros));
            parametros.put("TOTAL_REPORTES", reportes.size());

            List<Map<String, Object>> filas = new ArrayList<>();
            for (InformacionReporteDesbloqueoDTO reporte : reportes) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("fechaHora", reporte.getFechaHora() != null ? reporte.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "");
                fila.put("numRepartidoresDesbloqueados", reporte.getNumCuentasDesbloqueadas());
                fila.put("motivo", reporte.getMotivo() != null ? reporte.getMotivo().getMotivo() : "");
                filas.add(fila);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(filas));

            try (OutputStream out = new FileOutputStream(new File(rutaDestino))) {
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();
            }

            return true;
        } catch (JRException | IOException ex) {
            throw new NegocioException("Error al generar PDF de desbloqueos: " + ex.getMessage(), ex);
        }
    }

    private String construirTextoFiltros(FiltrosDTO filtros) {
        if (filtros == null) {
            return "Filtros: Todos";
        }

        String desde = filtros.getFechaDesde() != null ? filtros.getFechaDesde().toLocalDate().toString() : "Todos";
        String hasta = filtros.getFechaHasta() != null ? filtros.getFechaHasta().toLocalDate().toString() : "Todos";
        String motivo = filtros.getMotivo() != null ? filtros.getMotivo().getMotivo() : "Todos";

        return "Desde: " + desde + " | Hasta: " + hasta + " | Motivo: " + motivo;
    }
    
    
}
