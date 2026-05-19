/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productosdto;

import enums.TipoUnidadProductoDTO;

/**
 *
 * @author joset
 */
public class EditarProductoDTO {

    private String nombre;
    private float peso;
    private TipoUnidadProductoDTO unidad;
    private float precio;
    private byte[] imagen;

    public EditarProductoDTO() {
    }

    public EditarProductoDTO(String nombre, float peso, TipoUnidadProductoDTO unidad,
            float precio, byte[] imagen) {
        this.nombre = nombre;
        this.peso = peso;
        this.unidad = unidad;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public TipoUnidadProductoDTO getUnidad() {
        return unidad;
    }

    public void setUnidad(TipoUnidadProductoDTO unidad) {
        this.unidad = unidad;
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

}
