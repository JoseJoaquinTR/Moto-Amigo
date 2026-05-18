package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class DireccionDTO {

    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;

    public DireccionDTO() {
    }

    public DireccionDTO(String calle, String numero, String colonia, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

}
