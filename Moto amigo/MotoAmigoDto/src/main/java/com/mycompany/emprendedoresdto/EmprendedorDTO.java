package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class EmprendedorDTO {

    private String idEmprendedor;
    private String nombre;
    private String correo;
    private String telefono;
    private String rfc;

    public EmprendedorDTO() {
    }

    public EmprendedorDTO(String idEmprendedor, String nombre, String correo, String telefono, String rfc) {
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRfc() {
        return rfc;
    }

}
