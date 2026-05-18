package com.mycompany.Entidades;

import Enums.Banco;

/**
 *
 * @author Jesus Omar
 */
public class CuentaBancaria {

    private String idCuentaBancaria;
    private String numeroCuenta;
    private String nombreTitular;
    private Banco institucionBancaria;

    public CuentaBancaria(String idCuentaBancaria, String numeroCuenta, String nombreTitular, Banco institucionBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.institucionBancaria = institucionBancaria;
    }

    public String getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(String idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public Banco getInstitucionBancaria() {
        return institucionBancaria;
    }

    public void setInstitucionBancaria(Banco institucionBancaria) {
        this.institucionBancaria = institucionBancaria;
    }

}
