
package com.mycompany.Entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author joset
 */
public class Producto {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private String nombre;
    private float peso;
    private TipoUnidadProducto unidad;
    private float precio;
    private byte[] imagen;

    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idEmprendedor;

    public Producto() {
    }

    public Producto(String nombre, float peso, TipoUnidadProducto unidad,
            float precio, byte[] imagen, String idEmprendedor) {
        this.nombre = nombre;
        this.peso = peso;
        this.unidad = unidad;
        this.precio = precio;
        this.imagen = imagen;
        this.idEmprendedor = idEmprendedor;
    }

    public Producto(String id, String nombre, float peso, TipoUnidadProducto unidad,
            float precio, byte[] imagen, String idEmprendedor) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.unidad = unidad;
        this.precio = precio;
        this.imagen = imagen;
        this.idEmprendedor = idEmprendedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public TipoUnidadProducto getUnidad() {
        return unidad;
    }

    public void setUnidad(TipoUnidadProducto unidad) {
        this.unidad = unidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

}
