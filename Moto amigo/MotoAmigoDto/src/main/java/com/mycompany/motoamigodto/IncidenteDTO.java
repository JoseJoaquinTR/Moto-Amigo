/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

public class IncidenteDTO {

    private int idEntregaAsociada;
    private String tipoIncidente; 

    public IncidenteDTO() {
    }

    // Getters y Setters
    public int getIdEntregaAsociada() {
        return idEntregaAsociada;
    }

    public void setIdEntregaAsociada(int idEntregaAsociada) {
        this.idEntregaAsociada = idEntregaAsociada;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }
}
