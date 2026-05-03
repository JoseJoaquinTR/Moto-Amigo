/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio.strategy;

/**
 *
 * @author joset
 */
public class DistanciaAutomovilStrategy implements ICalculoDistanciaStrategy {

    private static final double TARIFA_BASE = 20.0;
    private static final double COSTO_POR_KM = 5.0;

    @Override
    public double calcularCosto(double distanciaKm, int tiempoEstimado) {
        return TARIFA_BASE + (distanciaKm * COSTO_POR_KM);
    }
}