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
public class InformacionReporteBloqueoDTO {

    private String nombreRepartidor;
    private MotivoDTO motivo;
    private LocalDateTime fechaHora;

    public InformacionReporteBloqueoDTO() {
    }

    public InformacionReporteBloqueoDTO(String nombreRepartidor, MotivoDTO motivo,
            LocalDateTime fechaHora) {
        this.nombreRepartidor = nombreRepartidor;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
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