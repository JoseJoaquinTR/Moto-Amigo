
package com.mycompany.motoamigodto;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaResponseDTO {
    private String origen;
    private String destino;
    private Double latOrigen;
    private Double lngOrigen;
    private Double latDestino;
    private Double lngDestino;
    private Double distancia;
    private int tiempoEstimado;
    private Double costo;
    private boolean exito;
    private boolean rutaValida;
    private Long idRepartidorAsignado;
    public RutaResponseDTO(String origen, String destino, Double latOrigen, Double lngOrigen, Double latDestino, Double lngDestino, Double distancia, int tiempoEstimado, Double costo, boolean exito, boolean rutaValida) {
        this.origen = origen;
        this.destino = destino;
        this.latOrigen = latOrigen;
        this.lngOrigen = lngOrigen;
        this.latDestino = latDestino;
        this.lngDestino = lngDestino;
        this.distancia = distancia;
        this.tiempoEstimado = tiempoEstimado;
        this.costo = costo;
        this.exito = exito;
        this.rutaValida = rutaValida;
    }
    public RutaResponseDTO(String origen, String destino, boolean exito, boolean rutaValida) {
        this.origen = origen;
        this.destino = destino;
        this.exito = exito;
        this.rutaValida = rutaValida;
        this.distancia = 0.0;
        this.tiempoEstimado = 0;
        this.costo = 0.0;
        this.latOrigen = null;
        this.lngOrigen = null;
        this.latDestino = null;
        this.lngDestino = null;
    }
    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Double getLatOrigen() {
        return latOrigen;
    }

    public Double getLngOrigen() {
        return lngOrigen;
    }

    public Double getLatDestino() {
        return latDestino;
    }

    public Double getLngDestino() {
        return lngDestino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public Double getCosto() {
        return costo;
    }

    public boolean isExito() {
        return exito;
    }

    public boolean isRutaValida() {
        return rutaValida;
    }

    public Long getIdRepartidorAsignado() {
        return idRepartidorAsignado;
    }

    public void setIdRepartidorAsignado(Long idRepartidorAsignado) {
        this.idRepartidorAsignado = idRepartidorAsignado;
    }

    
}
