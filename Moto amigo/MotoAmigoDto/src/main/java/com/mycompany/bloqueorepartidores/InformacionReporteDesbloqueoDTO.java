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
public class InformacionReporteDesbloqueoDTO {

    private int numCuentasDesbloqueadas;
    private MotivoDTO motivo;
    private LocalDateTime fechaHora;

    public InformacionReporteDesbloqueoDTO() {
    }

    public InformacionReporteDesbloqueoDTO(int numCuentasDesbloqueadas,
            MotivoDTO motivo, LocalDateTime fechaHora) {
        this.numCuentasDesbloqueadas = numCuentasDesbloqueadas;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
    }

    public int getNumCuentasDesbloqueadas() {
        return numCuentasDesbloqueadas;
    }

    public void setNumCuentasDesbloqueadas(int numCuentasDesbloqueadas) {
        this.numCuentasDesbloqueadas = numCuentasDesbloqueadas;
    }

    public MotivoDTO getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoDTO motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}

