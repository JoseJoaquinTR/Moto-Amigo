/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

public class IncidenteDTO {

    private String tipoIncidente;
    private String descripcion;

    public IncidenteDTO(String tipoIncidente, String descripcion) {
        this.tipoIncidente = tipoIncidente;
        this.descripcion = descripcion;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
