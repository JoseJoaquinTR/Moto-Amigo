/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

import Enums.EstatusEmprendedor;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Jesus Omar
 */
public class Emprendedor {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idEmprendedor;
    private String nombre;
    private String correo;
    private String telefono;
    private String rfc;
    private EstatusEmprendedor estatus;
    // Documentos anidados
    private CuentaUsuario cuentaUsuario;
    private CuentaBancaria cuentaBancaria;
    private Imagen imagen;
    private Documento documento;

    /**
     * Constructor de Emprendedor
     *
     * @param idEmprendedor
     * @param nombre
     * @param correo
     * @param telefono
     * @param rfc
     * @param estatus
     * @param cuentaUsuario
     * @param cuentaBancaria
     * @param imagen
     * @param documento
     */
    public Emprendedor(
            String idEmprendedor,
            String nombre,
            String correo,
            String telefono,
            String rfc,
            EstatusEmprendedor estatus,
            CuentaUsuario cuentaUsuario,
            CuentaBancaria cuentaBancaria,
            Imagen imagen,
            Documento documento) {
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.estatus = estatus;
        this.cuentaUsuario = cuentaUsuario;
        this.cuentaBancaria = cuentaBancaria;
        this.imagen = imagen;
        this.documento = documento;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public EstatusEmprendedor getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusEmprendedor estatus) {
        this.estatus = estatus;
    }

    public CuentaUsuario getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(CuentaUsuario cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

}
