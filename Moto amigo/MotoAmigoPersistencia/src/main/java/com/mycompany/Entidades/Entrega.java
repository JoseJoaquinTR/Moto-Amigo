/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

/**
 *
 * @author joset
 */

public class Entrega {
    private Long idEntrega;
    private Long idEmprendedor;
    private Long idRepartidor;
    private String direccionOrigen;
    private String direccionDestino;
    private String tipoPaquete;
    private String estadoEntrega;
    private double pesoAprox;
    private double costo;

    public Entrega(Long idEntrega, Long idEmprendedor, Long idRepartidor, String direccionOrigen, String direccionDestino, String tipoPaquete, String estadoEntrega, double pesoAprox, double costo) {
        this.idEntrega = idEntrega;
        this.idEmprendedor = idEmprendedor;
        this.idRepartidor = idRepartidor;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.tipoPaquete = tipoPaquete;
        this.estadoEntrega = estadoEntrega;
        this.pesoAprox = pesoAprox;
        this.costo = costo;
    }

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Long getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(Long idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public Long getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Long idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
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

    public double getPesoAprox() {
        return pesoAprox;
    }

    public void setPesoAprox(double pesoAprox) {
        this.pesoAprox = pesoAprox;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    
}