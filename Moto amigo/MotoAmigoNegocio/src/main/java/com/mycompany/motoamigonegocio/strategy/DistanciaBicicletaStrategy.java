/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio.strategy;

/**
 *
 * @author joset
 */
public class DistanciaBicicletaStrategy implements ICalculoDistanciaStrategy {

    private static final double TARIFA_BASE = 10.0;
    private static final double COSTO_POR_KM = 2.5;

    @Override
    public double calcularCosto(double distanciaKm, int tiempoEstimado) {
        return TARIFA_BASE + (distanciaKm * COSTO_POR_KM);
    }
}