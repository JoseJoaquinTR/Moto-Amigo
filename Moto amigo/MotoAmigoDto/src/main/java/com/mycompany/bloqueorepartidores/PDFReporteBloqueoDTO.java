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
public class PDFReporteBloqueoDTO {

     private String tituloReporte;
    private LocalDateTime fechaHora;
    private List<InformacionReporteBloqueoDTO> reportes;
    private FiltrosDTO filtros;

    public PDFReporteBloqueoDTO() {
    }

    public PDFReporteBloqueoDTO(String tituloReporte, LocalDateTime fechaHora,
            List<InformacionReporteBloqueoDTO> reportes, FiltrosDTO filtros) {
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

    public List<InformacionReporteBloqueoDTO> getReportes() {
        return reportes;
    }

    public void setReportes(List<InformacionReporteBloqueoDTO> reportes) {
        this.reportes = reportes;
    }

    public FiltrosDTO getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosDTO filtros) {
        this.filtros = filtros;
    }
    
    
    
}
