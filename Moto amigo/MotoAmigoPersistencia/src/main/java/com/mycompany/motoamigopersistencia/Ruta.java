/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Ruta {

    private String direccionRecoleccion;
    private String direccionEntrega;
    private int tiempoEstimado; // minutos

    public Ruta(String direccionRecoleccion, String direccionEntrega, int tiempoEstimado) {
        this.direccionRecoleccion = direccionRecoleccion;
        this.direccionEntrega = direccionEntrega;
        this.tiempoEstimado = tiempoEstimado;

    }

    public String getDireccionRecoleccion() {
        return direccionRecoleccion;
    }

    public void setDireccionRecoleccion(String direccionRecoleccion) {
        this.direccionRecoleccion = direccionRecoleccion;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

}
