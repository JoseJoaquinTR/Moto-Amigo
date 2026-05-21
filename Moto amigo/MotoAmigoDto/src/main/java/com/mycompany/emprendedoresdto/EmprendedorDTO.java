package com.mycompany.emprendedoresdto;

import enums.EstatusEmprendedorDTO;

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
    private EstatusEmprendedorDTO estatus;

    public EmprendedorDTO() {
    }

    public EmprendedorDTO(String idEmprendedor, String nombre, String correo, String telefono, String rfc, EstatusEmprendedorDTO estatus) {
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.estatus = estatus;
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

    public EstatusEmprendedorDTO getEstatus() {
        return estatus;
    }

}
