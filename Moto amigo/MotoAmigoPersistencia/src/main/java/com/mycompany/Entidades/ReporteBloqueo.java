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
public class ReporteBloqueo extends Reporte {

    private Repartidor repartidor;
    private byte[] imagenEvidencia;

    public ReporteBloqueo() {
        super();
    }

    public ReporteBloqueo(String idReporte, LocalDateTime fechaHora, Motivo motivo, String detalles,
                          Repartidor repartidor,Tipo tipo, byte[] imagenEvidencia) {
        super(idReporte, fechaHora, motivo, detalles, tipo);
        this.repartidor = repartidor;
        this.imagenEvidencia = imagenEvidencia;
    }

     public ReporteBloqueo( LocalDateTime fechaHora, Motivo motivo, String detalles,
                          Repartidor repartidor,Tipo tipo, byte[] imagenEvidencia) {
        super(fechaHora, motivo, detalles, tipo);
        this.repartidor = repartidor;
        this.imagenEvidencia = imagenEvidencia;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public byte[] getImagenEvidencia() {
        return imagenEvidencia;
    }

    public void setImagenEvidencia(byte[] imagenEvidencia) {
        this.imagenEvidencia = imagenEvidencia;
    }
}
