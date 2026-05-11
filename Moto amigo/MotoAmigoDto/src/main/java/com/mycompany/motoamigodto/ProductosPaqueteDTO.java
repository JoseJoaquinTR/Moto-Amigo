
package com.mycompany.motoamigodto;

/**
 * DTO de la asociación entre Paquete y Producto. Indica cuántas unidades de
 * un producto se incluyen en un paquete y el peso total resultante.
 * @author joset
 */

public class ProductosPaqueteDTO {

    private ProductoDTO producto;
    private int cantidad;
    private float pesoTotal;

    public ProductosPaqueteDTO() {
    }

    public ProductosPaqueteDTO(ProductoDTO producto, int cantidad, float pesoTotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.pesoTotal = pesoTotal;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
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