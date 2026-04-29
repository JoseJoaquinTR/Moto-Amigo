/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

public class EntregaDTO {

    private String direccionOrigen;
    private String direccionDestino;
    private String tipoPaquete;
    private String estadoEntrega;
    private double pesoAprox;
    private double costo;

    public EntregaDTO(String direccionOrigen, String direccionDestino,
            String tipoPaquete, String estadoEntrega,
            double pesoAprox, double costo) {
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.tipoPaquete = tipoPaquete;
        this.estadoEntrega = estadoEntrega;
        this.pesoAprox = pesoAprox;
        this.costo = costo;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
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

    public double getPesoAprox() {
        return pesoAprox;
    }

    public double getCosto() {
        return costo;
    }
}
