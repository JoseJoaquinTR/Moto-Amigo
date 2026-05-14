/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public abstract class Reporte {

    private String idReporte;
    private LocalDateTime fechaHora;
    private Motivo motivo;
    private String detalles;
    private Tipo tipo;

    public Reporte() {
    }

    public Reporte(LocalDateTime fechaHora, Motivo motivo, String detalles, Tipo tipo) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.detalles = detalles;
        this.tipo = tipo;
    }

    public Reporte(String idReporte, LocalDateTime fechaHora, Motivo motivo, String detalles, Tipo tipo) {
        this.idReporte = idReporte;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.detalles = detalles;
        this.tipo = tipo;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    
}
