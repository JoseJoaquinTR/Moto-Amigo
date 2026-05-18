package com.mycompany.emprendedoresdto;

import enums.BancoDTO;

/**
 *
 * @author Jesus Omar
 */
public class CuentaBancariaDTO {

    private String numeroCuenta;
    private String nombreTitular;
    private BancoDTO institucionBancaria;

    public CuentaBancariaDTO() {
    }

    public CuentaBancariaDTO(String numeroCuenta, String nombreTitular, BancoDTO institucionBancaria) {
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.institucionBancaria = institucionBancaria;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public BancoDTO getInstitucionBancaria() {
        return institucionBancaria;
    }

}
