/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bloqueorepartidores;

import java.time.LocalDateTime;
import com.mycompany.bloqueorepartidores.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class NuevoReporteDesbloqueoDTO {

    private MotivoDTO motivo;
    private String detalles;
    private LocalDateTime fechaHora;
    private FiltrosDTO filtros;
    private int NumRepartidoresDesbloqueados;

    public NuevoReporteDesbloqueoDTO() {
    }

    public NuevoReporteDesbloqueoDTO(MotivoDTO motivo, String detalles, LocalDateTime fechaHora, FiltrosDTO filtros) {
        this.motivo = motivo;
        this.detalles = detalles;
        this.fechaHora = fechaHora;
        this.filtros = filtros;
    }

    public NuevoReporteDesbloqueoDTO(MotivoDTO motivo, String detalles, LocalDateTime fechaHora, FiltrosDTO filtros, int NumRepartidoresDesbloqueados) {
        this.motivo = motivo;
        this.detalles = detalles;
        this.fechaHora = fechaHora;
        this.filtros = filtros;
        this.NumRepartidoresDesbloqueados = NumRepartidoresDesbloqueados;
    }
    
    

    public MotivoDTO getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoDTO motivo) {
        this.motivo = motivo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public FiltrosDTO getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosDTO filtros) {
        this.filtros = filtros;
    }

    public int getNumRepartidoresDesbloqueados() {
        return NumRepartidoresDesbloqueados;
    }

    public void setNumRepartidoresDesbloqueados(int NumRepartidoresDesbloqueados) {
        this.NumRepartidoresDesbloqueados = NumRepartidoresDesbloqueados;
    }
    
    
            
}
