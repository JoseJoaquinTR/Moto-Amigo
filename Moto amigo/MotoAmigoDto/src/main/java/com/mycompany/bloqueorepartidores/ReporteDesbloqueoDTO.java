/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bloqueorepartidores;

import java.time.LocalDateTime;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReporteDesbloqueoDTO {

    private String idReporteDesbloqueo;
    private MotivoDTO motivo;
    private String detalles;
    private LocalDateTime fechaHora;
    private int numRepartidoresDesbloqueados;

    public ReporteDesbloqueoDTO() {
    }

    public ReporteDesbloqueoDTO(String idReporteDesbloqueo, MotivoDTO motivo,String detalles,LocalDateTime fechaHora,int numRepartidoresDesbloqueados) {
        this.idReporteDesbloqueo = idReporteDesbloqueo;
        this.motivo = motivo;
        this.detalles = detalles;
        this.fechaHora = fechaHora;
        this.numRepartidoresDesbloqueados
                = numRepartidoresDesbloqueados;
    }

    public ReporteDesbloqueoDTO(
            MotivoDTO motivo,
            String detalles,
            LocalDateTime fechaHora,
            int numRepartidoresDesbloqueados
    ) {
        this.motivo = motivo;
        this.detalles = detalles;
        this.fechaHora = fechaHora;
        this.numRepartidoresDesbloqueados
                = numRepartidoresDesbloqueados;
    }

    public String getIdReporteDesbloqueos() {
        return idReporteDesbloqueo;
    }

    public void setIdReporteDesbloqueos(
            String idReporteDesbloqueos
    ) {
        this.idReporteDesbloqueo = idReporteDesbloqueos;
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

    public int getNumRepartidoresDesbloqueados() {
        return numRepartidoresDesbloqueados;
    }

    public void setNumRepartidoresDesbloqueados(
            int numRepartidoresDesbloqueados
    ) {
        this.numRepartidoresDesbloqueados
                = numRepartidoresDesbloqueados;
    }

}
