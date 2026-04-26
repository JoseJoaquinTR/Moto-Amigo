/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

/**
 *
 * @author xiomi
 */
public class SolicitudEntregaDTO {

    private String origen;
    private String destino;
    private String tipoPaquete;
    private double pesoAprox;
    private String estado;
    private double distancia;
    private double largo;
    private double ancho;
    private double alto;

    public SolicitudEntregaDTO(String origen, String destino, String tipoPaquete, double pesoAprox, String estado, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.tipoPaquete = tipoPaquete;
        this.pesoAprox = pesoAprox;
        this.estado = estado;
        this.distancia = distancia;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public double getPesoAprox() {
        return pesoAprox;
    }

    public String getEstado() {
        return estado;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getLargo() {
        return largo;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    
    
}
