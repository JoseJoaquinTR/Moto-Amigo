/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

import java.time.LocalDateTime;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReporteBloqueo extends Reporte {

    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idRepartidor;
    
    private byte[] imagenEvidencia;

    public ReporteBloqueo() {
        super();
    }

    public ReporteBloqueo(String idReporte, LocalDateTime fechaHora, Motivo motivo, String detalles,
                          String idRepartidor,Tipo tipo, byte[] imagenEvidencia) {
        super(idReporte, fechaHora, motivo, detalles, tipo);
        this.idRepartidor = idRepartidor;
        this.imagenEvidencia = imagenEvidencia;
    }

     public ReporteBloqueo( LocalDateTime fechaHora, Motivo motivo, String detalles,
                          String idRepartidor,Tipo tipo, byte[] imagenEvidencia) {
        super(fechaHora, motivo, detalles, tipo);
        this.idRepartidor = idRepartidor;
        this.imagenEvidencia = imagenEvidencia;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    
    public byte[] getImagenEvidencia() {
        return imagenEvidencia;
    }

    public void setImagenEvidencia(byte[] imagenEvidencia) {
        this.imagenEvidencia = imagenEvidencia;
    }
}
