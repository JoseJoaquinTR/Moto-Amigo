/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

/**
 *
 * @author joset
 */
public class Emprendedor {

    private Long idEmprendedor;
    private String nombre;
    private String correo;
    private String telefono;
    private String nombreNegocio;

    public Emprendedor(Long idEmprendedor, String nombre, String correo, String telefono, String nombreNegocio) {
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreNegocio = nombreNegocio;
    }

    public Long getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(Long idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

}
