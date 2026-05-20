/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigodto;

import com.mycompany.paquetesdto.PaqueteDTO;
import enums.TipoEnvioDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO de la solicitud de entrega que el emprendedor publica. Puede tener dos
 * modalidades según el campo {@code tipo}:
 *
 * - TipoEnvio.PAQUETES: el campo {@code paquetes} lleva uno o varios paquetes
 *   del inventario del emprendedor (sin duplicados). El campo {@code sobre} es
 *   null.
 * - TipoEnvio.SOBRE: el campo {@code sobre} lleva las dimensiones y datos del
 *   sobre o documento. El campo {@code paquetes} es null.
 *
 * Las validaciones de consistencia entre el tipo y los campos correspondientes
 * se hacen en el CU PublicarSolicitud, no en el DTO.
 *
 * @author joset
 */
public class SolicitudEntregaDTO {

    private String idSolicitud;
    private String origen;
    private String destino;

    private TipoEnvioDTO tipo;
    private List<PaqueteDTO> paquetes;
    private SobreDTO sobre;

    private double distancia;
    private double pesoTotal;
    private Double costo;
    private String estado;
    private String idEmprendedor;

    public SolicitudEntregaDTO() {
    }

    /**
     * Constructor para la modalidad PAQUETES.
     */
    public SolicitudEntregaDTO(String origen, String destino,
            List<PaqueteDTO> paquetes, double distancia, String idEmprendedor) {
        this.origen = origen;
        this.destino = destino;
        this.tipo = TipoEnvioDTO.PAQUETES;
        this.paquetes = paquetes != null ? paquetes : new ArrayList<>();
        this.sobre = null;
        this.distancia = distancia;
        this.idEmprendedor = idEmprendedor;
    }

    /**
     * Constructor para la modalidad SOBRE.
     */
    public SolicitudEntregaDTO(String origen, String destino,
            SobreDTO sobre, double distancia, String idEmprendedor) {
        this.origen = origen;
        this.destino = destino;
        this.tipo = TipoEnvioDTO.SOBRE;
        this.paquetes = null;
        this.sobre = sobre;
        this.distancia = distancia;
        this.idEmprendedor = idEmprendedor;
        this.pesoTotal = sobre != null ? sobre.getPeso() : 0;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public TipoEnvioDTO getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnvioDTO tipo) {
        this.tipo = tipo;
    }

    public List<PaqueteDTO> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PaqueteDTO> paquetes) {
        this.paquetes = paquetes;
    }

    public SobreDTO getSobre() {
        return sobre;
    }

    public void setSobre(SobreDTO sobre) {
        this.sobre = sobre;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }
}
