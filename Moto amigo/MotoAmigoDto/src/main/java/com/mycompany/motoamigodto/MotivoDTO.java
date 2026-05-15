/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import enums.Tipo;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class MotivoDTO {

    private String motivo;
    private Tipo tipo;

    public MotivoDTO() {
    }

    public MotivoDTO(String motivo, Tipo tipo) {
        this.motivo = motivo;
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    
   
}
