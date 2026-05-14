/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import java.time.LocalDateTime;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class FiltrosDTO {

    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private MotivoDTO motivo;
    private int numBloqueos;

    public FiltrosDTO() {
    }

    public FiltrosDTO(LocalDateTime fechaDesde, LocalDateTime fechaHasta, MotivoDTO motivo, int numBloqueos) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.motivo = motivo;
        this.numBloqueos = numBloqueos;
    }

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public MotivoDTO getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoDTO motivo) {
        this.motivo = motivo;
    }

    public int getNumBloqueos() {
        return numBloqueos;
    }

    public void setNumBloqueos(int numBloqueos) {
        this.numBloqueos = numBloqueos;
    }
}
