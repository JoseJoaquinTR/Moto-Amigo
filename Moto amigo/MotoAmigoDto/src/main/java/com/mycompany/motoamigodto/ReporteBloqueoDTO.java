/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import com.mycompany.bloqueorepartidores.MotivoDTO;
import java.time.LocalDateTime;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReporteBloqueoDTO {

    private String idReporteBloqueo;
    private RepartidorDTO repartidor;
    private MotivoDTO motivo;
    private String detalles;
    private byte[] imagenEvidencia;
    private LocalDateTime fechaHora;

    public ReporteBloqueoDTO() {
    }

    public ReporteBloqueoDTO(String idReporteBloqueo, RepartidorDTO repartidor, MotivoDTO motivo,
            String detalles, byte[] imagenEvidencia, LocalDateTime fechaHora) {
        this.idReporteBloqueo = idReporteBloqueo;
        this.repartidor = repartidor;
        this.motivo = motivo;
        this.detalles = detalles;
        this.imagenEvidencia = imagenEvidencia;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters
    public String getIdReporteBloqueo() {
        return idReporteBloqueo;
    }

    public void setIdReporteBloqueo(String idReporteBloqueo) {
        this.idReporteBloqueo = idReporteBloqueo;
    }

    public RepartidorDTO getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(RepartidorDTO repartidor) {
        this.repartidor = repartidor;
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

    public byte[] getImagenEvidencia() {
        return imagenEvidencia;
    }

    public void setImagenEvidencia(byte[] imagenEvidencia) {
        this.imagenEvidencia = imagenEvidencia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
