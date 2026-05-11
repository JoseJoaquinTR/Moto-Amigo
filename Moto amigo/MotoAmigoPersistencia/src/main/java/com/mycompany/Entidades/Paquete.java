
package com.mycompany.Entidades;

import java.util.ArrayList;
import java.util.List;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author joset
 */
public class Paquete {

    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private String nombre;
    private TamañoPaquete tamaño;
    private List<ProductosPaquete> productos;
    private float precio;
    private byte[] imagen;

    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idEmprendedor;

    public Paquete() {
        this.productos = new ArrayList<>();
    }

    public Paquete(String nombre, TamañoPaquete tamaño, List<ProductosPaquete> productos,
            float precio, byte[] imagen, String idEmprendedor) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.productos = productos != null ? productos : new ArrayList<>();
        this.precio = precio;
        this.imagen = imagen;
        this.idEmprendedor = idEmprendedor;
    }

    public Paquete(String id, String nombre, TamañoPaquete tamaño,
            List<ProductosPaquete> productos, float precio, byte[] imagen,
            String idEmprendedor) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.productos = productos != null ? productos : new ArrayList<>();
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

    public TamañoPaquete getTamaño() {
        return tamaño;
    }

    public void setTamaño(TamañoPaquete tamaño) {
        this.tamaño = tamaño;
    }

    public List<ProductosPaquete> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosPaquete> productos) {
        this.productos = productos;
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
