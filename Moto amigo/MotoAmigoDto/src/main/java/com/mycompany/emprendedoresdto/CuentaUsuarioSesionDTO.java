package com.mycompany.emprendedoresdto;

import enums.EstatusEmprendedorDTO;

/**
 *
 * @author Jesus Omar
 */
public class CuentaUsuarioSesionDTO {

    private String idCuenta;
    private String correo;
    private boolean activa;
    private EstatusEmprendedorDTO estatusEmprendedor;

    public CuentaUsuarioSesionDTO() {
    }

    public CuentaUsuarioSesionDTO(String idCuenta, String correo, boolean activa, EstatusEmprendedorDTO estatusEmprendedor) {
        this.idCuenta = idCuenta;
        this.correo = correo;
        this.activa = activa;
        this.estatusEmprendedor = estatusEmprendedor;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean isActiva() {
        return activa;
    }

    public EstatusEmprendedorDTO getEstatusEmprendedor() {
        return estatusEmprendedor;
    }

}
