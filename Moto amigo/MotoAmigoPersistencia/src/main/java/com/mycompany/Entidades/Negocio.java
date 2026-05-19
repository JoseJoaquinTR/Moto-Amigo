package com.mycompany.Entidades;

import Enums.TipoNegocio;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Jesus Omar
 */
public class Negocio {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idNegocio;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idEmprendedor;
    private String nombre;
    private TipoNegocio tipoNegocio;
    private Direccion direccion;

    public Negocio() {
    }

    public Negocio(String idNegocio, String idEmprendedor, String nombre, TipoNegocio tipoNegocio, Direccion direccion) {
        this.idNegocio = idNegocio;
        this.idEmprendedor = idEmprendedor;
        this.nombre = nombre;
        this.tipoNegocio = tipoNegocio;
        this.direccion = direccion;
    }

    public String getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(String idNegocio) {
        this.idNegocio = idNegocio;
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

    public TipoNegocio getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(TipoNegocio tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
