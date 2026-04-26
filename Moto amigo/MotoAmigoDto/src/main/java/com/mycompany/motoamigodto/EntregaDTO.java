/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

public class EntregaDTO {

    private int idEntrega;
    private String direccionDestino;
    private String tipoPaquete;
    private String estadoEntrega;

    public EntregaDTO(int idEntrega, String direccionDestino, String tipoPaquete, String estadoEntrega) {
        this.idEntrega = idEntrega;
        this.direccionDestino = direccionDestino;
        this.tipoPaquete = tipoPaquete;
        this.estadoEntrega = estadoEntrega;
    }

    // Getters y Setters
    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }
}
