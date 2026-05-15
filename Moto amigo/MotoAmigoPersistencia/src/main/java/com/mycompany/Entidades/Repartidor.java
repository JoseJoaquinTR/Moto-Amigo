/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author joset
 */
public class Repartidor {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idRepartidor;
    private String nombre;
    private String telefono;
    private String correo;
    private String vehiculo;
    private Estado estado;
    private int numBloqueos;

    public Repartidor() {
    }

    public Repartidor(String idRepartidor, String nombre, String telefono, String correo, String vehiculo, Estado estado, int numBloqueos) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
        this.numBloqueos = numBloqueos;
    }

    public Repartidor(String nombre, String telefono, String correo, String vehiculo, Estado estado, int numBloqueos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
        this.numBloqueos = numBloqueos;
    }

    public Repartidor(String idRepartidor, String nombre, String telefono, String correo, String vehiculo, Estado estado) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getNumBloqueos() {
        return numBloqueos;
    }

    public void setNumBloqueos(int numBloqueos) {
        this.numBloqueos = numBloqueos;
    }

}
