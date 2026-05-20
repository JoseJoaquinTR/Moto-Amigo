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

    public RegistroEmprendedorDTO(NuevoEmprendedorDTO emprendedor, NegocioDTO negocio, CuentaBancariaDTO cuentaBancaria) {
        this.emprendedor = emprendedor;
        this.negocio = negocio;
        this.cuentaBancaria = cuentaBancaria;
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
