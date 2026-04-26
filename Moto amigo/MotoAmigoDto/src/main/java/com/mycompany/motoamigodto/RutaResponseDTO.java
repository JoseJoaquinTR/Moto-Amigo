/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaResponseDTO {

    private String origen;
    private String destino;
    private Double latOrigen;
    private Double lngOrigen;
    private Double latDestino;
    private Double lngDestino;
    private double distancia;       // en km
    private int tiempoEstimado;     // en minutos
    private double costo;
    private boolean exito;          // indica si la llamada al servicio fue exitosa
    private boolean rutaValida;     // indica si la ruta calculada es válida

    public RutaResponseDTO(String origen, String destino,
            Double latOrigen, Double lngOrigen,
            Double latDestino, Double lngDestino,
            double distancia, int tiempoEstimado,
            double costo, boolean exito, boolean rutaValida) {
        this.origen = origen;
        this.destino = destino;
        this.latOrigen = latOrigen;
        this.lngOrigen = lngOrigen;
        this.latDestino = latDestino;
        this.lngDestino = lngDestino;
        this.distancia = distancia;
        this.tiempoEstimado = tiempoEstimado;
        this.costo = costo;
        this.exito = exito;
        this.rutaValida = rutaValida;
    }

    public RutaResponseDTO(String origen, String destino, int tiempoEstimado, boolean exito, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.tiempoEstimado = tiempoEstimado;
        this.exito = exito;
        this.distancia = distancia;
        this.rutaValida = exito;
        this.costo = 0.0;
        this.latOrigen = null;
        this.lngOrigen = null;
        this.latDestino = null;
        this.lngDestino = null;
    }

    public RutaResponseDTO(String origen, String destino, boolean exito, boolean rutaValida) {
        this.origen = origen;
        this.destino = destino;
        this.exito = exito;
        this.rutaValida = rutaValida;
        this.distancia = 0.0;
        this.tiempoEstimado = 0;
        this.costo = 0.0;
        this.latOrigen = null;
        this.lngOrigen = null;
        this.latDestino = null;
        this.lngDestino = null;

    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Double getLatOrigen() {
        return latOrigen;
    }

    public Double getLngOrigen() {
        return lngOrigen;
    }

    public Double getLatDestino() {
        return latDestino;
    }

    public Double getLngDestino() {
        return lngDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public double getCosto() {
        return costo;
    }

    public boolean isExito() {
        return exito;
    }

    public boolean isRutaValida() {
        return rutaValida;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setLatOrigen(Double latOrigen) {
        this.latOrigen = latOrigen;
    }

    public void setLngOrigen(Double lngOrigen) {
        this.lngOrigen = lngOrigen;
    }

    public void setLatDestino(Double latDestino) {
        this.latDestino = latDestino;
    }

    public void setLngDestino(Double lngDestino) {
        this.lngDestino = lngDestino;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public void setRutaValida(boolean rutaValida) {
        this.rutaValida = rutaValida;
    }

}
