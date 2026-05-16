
package com.mycompany.paquetesdto;

import java.time.LocalDate;

/**
 *
 * @author joset
 */
public class PaqueteHistorialDTO {

    private int numeroUsos;
    private String nombre;
    private LocalDate ultimoUso;
    private int pesoPaquete;

    public PaqueteHistorialDTO() {
    }

    public PaqueteHistorialDTO(int numeroUsos, String nombre,
            LocalDate ultimoUso, int pesoPaquete) {
        this.numeroUsos = numeroUsos;
        this.nombre = nombre;
        this.ultimoUso = ultimoUso;
        this.pesoPaquete = pesoPaquete;
    }

    public int getNumeroUsos() {
        return numeroUsos;
    }

    public void setNumeroUsos(int numeroUsos) {
        this.numeroUsos = numeroUsos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getUltimoUso() {
        return ultimoUso;
    }

    public void setUltimoUso(LocalDate ultimoUso) {
        this.ultimoUso = ultimoUso;
    }

    public int getPesoPaquete() {
        return pesoPaquete;
    }

    public void setPesoPaquete(int pesoPaquete) {
        this.pesoPaquete = pesoPaquete;
    }
}