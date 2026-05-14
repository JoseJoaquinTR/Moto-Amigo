/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReporteDesbloqueo extends Reporte {

    private int numRepartidoresDesbloqueados;

    public ReporteDesbloqueo() {
        super();
    }

    public ReporteDesbloqueo(String idReporte, LocalDateTime fechaHora, Motivo motivo, String detalles,
            Tipo tipo, int numRepartidoresDesbloqueados) {
        super(idReporte, fechaHora, motivo, detalles, tipo);
        this.numRepartidoresDesbloqueados = numRepartidoresDesbloqueados;
    }

    public ReporteDesbloqueo(LocalDateTime fechaHora, Motivo motivo, String detalles,
            Tipo tipo, int numRepartidoresDesbloqueados) {
        super(fechaHora, motivo, detalles, tipo);
        this.numRepartidoresDesbloqueados = numRepartidoresDesbloqueados;
    }

    public int getNumRepartidoresDesbloqueados() {
        return numRepartidoresDesbloqueados;
    }

    public void setNumRepartidoresDesbloqueados(int numRepartidoresDesbloqueados) {
        this.numRepartidoresDesbloqueados = numRepartidoresDesbloqueados;
    }
}
