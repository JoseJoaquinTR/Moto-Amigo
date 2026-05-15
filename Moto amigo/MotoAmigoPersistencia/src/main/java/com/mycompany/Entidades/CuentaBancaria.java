package com.mycompany.Entidades;

import Enums.Banco;

/**
 *
 * @author Jesus Omar
 */
public class CuentaBancaria {

    private Long idCuentaBancaria;
    private Long idEmprendedor;
    private String numeroCuenta;
    private String nombreTitular;
    private Banco institucionBancaria;

    public CuentaBancaria(Long idCuentaBancaria, Long idEmprendedor, String numeroCuenta, String nombreTitular, Banco institucionBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.idEmprendedor = idEmprendedor;
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.institucionBancaria = institucionBancaria;
    }

    public Long getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Long idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public Long getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(Long idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
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
