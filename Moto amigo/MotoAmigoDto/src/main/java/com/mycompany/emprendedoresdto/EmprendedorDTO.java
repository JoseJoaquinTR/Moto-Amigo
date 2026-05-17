/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emprendedoresdto;

/**
 * Esta clase es la DTO para mostrar los datos
 * del emprendedor en las pantallas
 * @author Jesus Omar
 */
public class EmprendedorDTO {

    private Long idEmprendedor;
    private String nombre;
    private String correo;
    private String telefono;
    private String rfc;

    public EmprendedorDTO(Long idEmprendedor, String nombre, String correo, String telefono, String nombreNegocio) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = nombreNegocio;
    }

    public Long getIdEmprendedor() {
        return idEmprendedor;
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

    public String getRfc() {
        return rfc;
    }
}
