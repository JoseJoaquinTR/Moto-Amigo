package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class CuentaUsuarioDTO {

    private String correo;
    private String contrasenia;

    public CuentaUsuarioDTO() {
    }

    public CuentaUsuarioDTO(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

}
