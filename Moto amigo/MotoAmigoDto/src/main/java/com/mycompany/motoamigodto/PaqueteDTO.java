/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import enums.TamañoPaqueteDTO;
import java.util.List;

/**
 *
 * @author joset
 */
public class PaqueteDTO {
    private String idPaquete;
    private String nombre;
    private TamañoPaqueteDTO tamaño;
    private List<ProductosPaqueteDTO> productos;
    private float precio;
    private byte[] imagen;
    private String idEmprendedor;

    public PaqueteDTO() {
    }

    public PaqueteDTO(String nombre, TamañoPaqueteDTO tamaño,
            List<ProductosPaqueteDTO> productos, float precio,
            byte[] imagen, String idEmprendedor) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.productos = productos;
        this.precio = precio;
        this.imagen = imagen;
        this.idEmprendedor = idEmprendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TamañoPaqueteDTO getTamaño() {
        return tamaño;
    }

    public void setTamaño(TamañoPaqueteDTO tamaño) {
        this.tamaño = tamaño;
    }

    public List<ProductosPaqueteDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosPaqueteDTO> productos) {
        this.productos = productos;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }
    
}