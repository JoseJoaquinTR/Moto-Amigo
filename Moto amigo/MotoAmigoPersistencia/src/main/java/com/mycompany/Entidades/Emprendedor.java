/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

import Enums.EstatusEmprendedor;

/**
 *
 * @author Jesus Omar
 */
public class Emprendedor {

    private Long idEmprendedor;
    private String nombre;
    private String correo;
    private String telefono;
    private String rfc;
    private EstatusEmprendedor estatus;

    public Emprendedor(Long idEmprendedor, String nombre, String correo, String telefono,String rfc, EstatusEmprendedor estatus) {
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.estatus = estatus;
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

    public EstatusEmprendedor getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusEmprendedor estatus) {
        this.estatus = estatus;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

}
