/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

public class EntregaDTO {

    private String direccionDestino;
    private String tipoPaquete;
    private String estadoEntrega;

    public EntregaDTO(String direccionDestino, String tipoPaquete, String estadoEntrega) {
        this.direccionDestino = direccionDestino;
        this.tipoPaquete = tipoPaquete;
        this.estadoEntrega = estadoEntrega;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }
}
