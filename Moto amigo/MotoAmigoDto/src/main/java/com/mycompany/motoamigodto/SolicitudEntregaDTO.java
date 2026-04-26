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
    private int idEmprendedor;
    private int idEnvio;
    private String estado;
    private double distancia;

    public SolicitudEntregaDTO() {
    }

    public SolicitudEntregaDTO(String origen, String destino, String tipoPaquete, double pesoAprox, int idEmprendedor, int idEnvio, String estado, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.tipoPaquete = tipoPaquete;
        this.pesoAprox = pesoAprox;
        this.idEmprendedor = idEmprendedor;
        this.idEnvio = idEnvio;
        this.estado = estado;
        this.distancia = distancia;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public double getPesoAprox() {
        return pesoAprox;
    }

    public void setPesoAprox(double pesoAprox) {
        this.pesoAprox = pesoAprox;
    }

    public int getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(int idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    
    
}
