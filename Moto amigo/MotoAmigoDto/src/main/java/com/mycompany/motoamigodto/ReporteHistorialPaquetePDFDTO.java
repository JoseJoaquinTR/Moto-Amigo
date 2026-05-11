/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author joset
 */
public class ReporteHistorialPaquetePDFDTO {

    private String titulo;
    private LocalDate fecha;
    private int numeroPaquetesRegistrados;
    private int usosTotal;
    private List<PaqueteHistorialDTO> paqueteHistorial;

    public ReporteHistorialPaquetePDFDTO() {
    }

    public ReporteHistorialPaquetePDFDTO(String titulo, LocalDate fecha,
            int numeroPaquetesRegistrados, int usosTotal,
            List<PaqueteHistorialDTO> paqueteHistorial) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.numeroPaquetesRegistrados = numeroPaquetesRegistrados;
        this.usosTotal = usosTotal;
        this.paqueteHistorial = paqueteHistorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPaquetesRegistrados() {
        return numeroPaquetesRegistrados;
    }

    public void setNumeroPaquetesRegistrados(int numeroPaquetesRegistrados) {
        this.numeroPaquetesRegistrados = numeroPaquetesRegistrados;
    }

    public int getUsosTotal() {
        return usosTotal;
    }

    public void setUsosTotal(int usosTotal) {
        this.usosTotal = usosTotal;
    }

    public List<PaqueteHistorialDTO> getPaqueteHistorial() {
        return paqueteHistorial;
    }

    public void setPaqueteHistorial(List<PaqueteHistorialDTO> paqueteHistorial) {
        this.paqueteHistorial = paqueteHistorial;
    }
}