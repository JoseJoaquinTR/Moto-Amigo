/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

/**
 * Representa un sobre o documento que el emprendedor desea enviar sin que
 * forme parte de su inventario de paquetes. Se usa cuando la modalidad de
 * la SolicitudEntregaDTO es TipoEnvio.SOBRE.
 *
 * @author joset
 */
public class SobreDTO {

    private double largo;       
    private double ancho;       
    private double alto;        
    private double peso;        
    private String descripcion;

    public SobreDTO() {
    }

    public SobreDTO(double largo, double ancho, double alto, double peso, String descripcion) {
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.peso = peso;
        this.descripcion = descripcion;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
