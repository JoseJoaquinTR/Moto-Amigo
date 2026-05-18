package com.mycompany.emprendedoresdto;

import enums.TipoNegocioDTO;

/**
 *
 * @author Jesus Omar
 */
public class NegocioDTO {

    private String nombre;
    private DireccionDTO direccion;
    private TipoNegocioDTO tipoNegocio;

    public NegocioDTO() {
    }

    public NegocioDTO(String nombre, DireccionDTO direccion, TipoNegocioDTO tipoNegocio) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoNegocio = tipoNegocio;
    }

    public String getNombre() {
        return nombre;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public TipoNegocioDTO getTipoNegocio() {
        return tipoNegocio;
    }

}
