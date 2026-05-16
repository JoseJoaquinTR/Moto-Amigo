package com.mycompany.motoamigodto;

import enums.Estado;

/**
 *
 * @author xiomi
 */
public class RepartidorDTO {

    private String id;
    private String nombre;
    private String telefono;
    private String correo;
    private String vehiculo;
    private Estado estado;
    private int numBloqueos;

    public RepartidorDTO() {
    }

    public RepartidorDTO(String id, String nombre, String telefono, String correo, String vehiculo, Estado estado, int numBloqueos) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
        this.numBloqueos = numBloqueos;
    }

    public RepartidorDTO(String nombre, String telefono, String correo, String vehiculo, Estado estado, int numBloqueos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
        this.numBloqueos = numBloqueos;
    }

    
    public RepartidorDTO(String nombre, String telefono, String correo, String vehiculo, Estado estado) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public RepartidorDTO(String id, String nombre, String telefono, String correo, String vehiculo, Estado estado) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getNumBloqueos() {
        return numBloqueos;
    }

    public void setNumBloqueos(int numBloqueos) {
        this.numBloqueos = numBloqueos;
    }
    
}
