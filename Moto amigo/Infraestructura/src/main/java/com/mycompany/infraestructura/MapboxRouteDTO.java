/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infraestructura;

import java.util.List;

/**
 *
 * @author joset
 */
public class MapboxRouteDTO {

    private double distanciaMetros;
    private double duracionSegundos;
    private List<double[]> coordenadas;
    private double latOrigen;
    private double lngOrigen;
    private double latDestino;
    private double lngDestino;
    private String origen;
    private String destino;

    public MapboxRouteDTO(double distanciaMetros, double duracionSegundos,
            List<double[]> coordenadas, double latOrigen, double lngOrigen,
            double latDestino, double lngDestino, String origen, String destino) {
        this.distanciaMetros = distanciaMetros;
        this.duracionSegundos = duracionSegundos;
        this.coordenadas = coordenadas;
        this.latOrigen = latOrigen;
        this.lngOrigen = lngOrigen;
        this.latDestino = latDestino;
        this.lngDestino = lngDestino;
        this.origen = origen;
        this.destino = destino;
    }

    public double getDistanciaMetros() {
        return distanciaMetros;
    }

    public double getDuracionSegundos() {
        return duracionSegundos;
    }

    public List<double[]> getCoordenadas() {
        return coordenadas;
    }

    public double getLatOrigen() {
        return latOrigen;
    }

    public double getLngOrigen() {
        return lngOrigen;
    }

    public double getLatDestino() {
        return latDestino;
    }

    public double getLngDestino() {
        return lngDestino;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}
