package com.mycompany.motoamigodto;

import com.mycompany.paquetesdto.PaqueteDTO;
import enums.TipoEnvioDTO;
import java.time.LocalDateTime;
import java.util.List;

public class EntregaDTO {

    private String id;
    private String idEmprendedor;
    private String idRepartidor;

    private String direccionOrigen;
    private String direccionDestino;
    private TipoEnvioDTO tipo;

    private SobreDTO sobre;
    private List<PaqueteDTO> paquetes;
    

    private String estadoEntrega;
    private double pesoTotal;
    private double distancia;
    private double costo;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntrega;

    public EntregaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public TipoEnvioDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnvioDTO tipo) {
        this.tipo = tipo;
    }

    public SobreDTO getSobre() {
        return sobre;
    }

    public void setSobre(SobreDTO sobre) {
        this.sobre = sobre;
    }

    public List<PaqueteDTO> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PaqueteDTO> paquetes) {
        this.paquetes = paquetes;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    

}
