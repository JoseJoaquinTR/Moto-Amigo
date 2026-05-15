package com.mycompany.Entidades;

/**
 *
 * @author Jesus Omar
 */
public class CuentaUsuario {
    
    private Long idCuenta;
    private Long idEmprendedor;
    private String correo;
    private String contrasenia;
    private boolean activa;

    public CuentaUsuario(Long idCuenta, Long idEmprendedor, String correo, String contrasenia, boolean activa) {
        this.idCuenta = idCuenta;
        this.idEmprendedor = idEmprendedor;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.activa = activa;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Long getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(Long idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
