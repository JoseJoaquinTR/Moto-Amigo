/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

/**
 *
 * @author joset
 */
public class EmprendedorDTO {

    private String nombre;
    private String correo;
    private String telefono;
    private String nombreNegocio;

    public EmprendedorDTO(String nombre, String correo, String telefono, String nombreNegocio) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreNegocio = nombreNegocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }
}
