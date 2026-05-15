package com.mycompany.Entidades;

import Enums.TipoNegocio;

/**
 *
 * @author Jesus Omar
 */
public class Negocio {
    
    private Long idNegocio;
    private Long idEmprendedor;
    private String nombre;
    private TipoNegocio tipoNegocio;

    public Negocio(Long idNegocio, Long idEmprendedor, String nombre, TipoNegocio tipoNegocio) {
        this.idNegocio = idNegocio;
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.tipoNegocio = tipoNegocio;
    }

    public Long getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Long idNegocio) {
        this.idNegocio = idNegocio;
    }

    public Long getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(Long idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoNegocio getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(TipoNegocio tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }
    
}
