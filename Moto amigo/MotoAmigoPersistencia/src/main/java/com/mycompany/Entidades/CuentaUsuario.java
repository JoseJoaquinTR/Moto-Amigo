package com.mycompany.Entidades;

/**
 *
 * @author Jesus Omar
 */
public class CuentaUsuario {

    private String idCuenta;
    private String correo;
    private String contrasenia;
    private boolean activa;

    public CuentaUsuario(String idCuenta, String correo, String contrasenia, boolean activa) {
        this.idCuenta = idCuenta;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.activa = activa;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
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
