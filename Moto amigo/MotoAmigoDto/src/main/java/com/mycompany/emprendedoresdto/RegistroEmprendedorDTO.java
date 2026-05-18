package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class RegistroEmprendedorDTO {
    
    private NuevoEmprendedorDTO emprendedor;
    private NegocioDTO negocio;
    private CuentaBancariaDTO cuentaBancaria;

    public RegistroEmprendedorDTO() {
    }

    public NuevoEmprendedorDTO getEmprendedor() {
        return emprendedor;
    }

    public NegocioDTO getNegocio() {
        return negocio;
    }

    public CuentaBancariaDTO getCuentaBancaria() {
        return cuentaBancaria;
    }
    
}
