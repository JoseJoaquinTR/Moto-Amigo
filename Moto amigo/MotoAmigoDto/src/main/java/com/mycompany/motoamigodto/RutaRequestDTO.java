/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaRequestDTO {

    private String direccionRecoleccion;
    private String direccionEntrega;

    public RutaRequestDTO(String direccionRecoleccion, String direccionEntrega) {
        this.direccionRecoleccion = direccionRecoleccion;
        this.direccionEntrega = direccionEntrega;
    }

    public String getDireccionRecoleccion() {
        return direccionRecoleccion;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }
}
