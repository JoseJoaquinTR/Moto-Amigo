/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

/**
 *
 * @author joset
 */
public class CalculadoraDistanciasManager {

    private ICalculoDistanciaStrategy strategy;

    public CalculadoraDistanciasManager(ICalculoDistanciaStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ICalculoDistanciaStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcularCosto(double distanciaKm, int tiempoEstimado) {
        return strategy.calcularCosto(distanciaKm, tiempoEstimado);
    }
}
