/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bloqueorepartidores;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class PDFReporteDesbloqueoDTO {

    private String tituloReporte;
    private LocalDateTime fechaHora;
    private List<InformacionReporteDesbloqueoDTO> reportes;
    private FiltrosDTO filtros;

    public PDFReporteDesbloqueoDTO() {
    }

    public PDFReporteDesbloqueoDTO(String tituloReporte, LocalDateTime fechaHora,
            List<InformacionReporteDesbloqueoDTO> reportes, FiltrosDTO filtros) {
        this.tituloReporte = tituloReporte;
        this.fechaHora = fechaHora;
        this.reportes = reportes;
        this.filtros = filtros;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<InformacionReporteDesbloqueoDTO> getReportes() {
        return reportes;
    }

    public void setReportes(List<InformacionReporteDesbloqueoDTO> reportes) {
        this.reportes = reportes;
    }

    public FiltrosDTO getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosDTO filtros) {
        this.filtros = filtros;
    }
}
