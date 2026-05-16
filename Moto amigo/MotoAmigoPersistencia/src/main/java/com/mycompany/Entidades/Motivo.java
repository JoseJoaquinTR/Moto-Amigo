/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Motivo {

    private String motivo;
    private Tipo tipo;

    public Motivo() {
    }

    public Motivo(String motivo, Tipo tipo) {
        this.motivo = motivo;
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
