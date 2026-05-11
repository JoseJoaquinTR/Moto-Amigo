
package com.mycompany.Entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author joset
 */
public class ProductosPaquete {

    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idProducto;

    private int cantidad;
    private float pesoTotal;

    public ProductosPaquete() {
    }

    public ProductosPaquete(String idProducto, int cantidad, float pesoTotal) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.pesoTotal = pesoTotal;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

}
