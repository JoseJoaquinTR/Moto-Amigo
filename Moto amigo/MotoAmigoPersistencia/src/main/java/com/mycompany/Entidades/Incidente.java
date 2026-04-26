/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

/**
 *
 * @author joset
 */
public class Incidente {
    private Long idIncidente;
    private Long idEntregaAsociada;
    private String tipoIncidente;
    private String descripcion;

    public Incidente(Long idIncidente, Long idEntregaAsociada, String tipoIncidente, String descripcion) {
        this.idIncidente = idIncidente;
        this.idEntregaAsociada = idEntregaAsociada;
        this.tipoIncidente = tipoIncidente;
        this.descripcion = descripcion;
    }

    public Long getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Long idIncidente) {
        this.idIncidente = idIncidente;
    }

    public Long getIdEntregaAsociada() {
        return idEntregaAsociada;
    }

    public void setIdEntregaAsociada(Long idEntregaAsociada) {
        this.idEntregaAsociada = idEntregaAsociada;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
