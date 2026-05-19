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
    private String idEmprendedor;

    public CuentaUsuarioSesionDTO() {
    }

    public CuentaUsuarioSesionDTO(String idCuenta, String correo, boolean activa, EstatusEmprendedorDTO estatusEmprendedor, String idEmprendedor) {
        this.idCuenta = idCuenta;
        this.correo = correo;
        this.activa = activa;
        this.estatusEmprendedor = estatusEmprendedor;
        this.idEmprendedor = idEmprendedor;
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

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

}
