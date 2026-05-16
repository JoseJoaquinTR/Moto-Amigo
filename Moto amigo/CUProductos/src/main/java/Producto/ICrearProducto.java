/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Producto;

import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.productosdto.ProductoDTO;

/**
 * @author joset
 */
public interface ICrearProducto {

    /**
     * Crea un nuevo producto a partir de los datos proporcionados.
     *
     * @param producto datos del producto a crear.
     * @return el producto creado con su id asignado, o null si ocurre un error.
     */
    ProductoDTO crear(NuevoProductoDTO producto)throws ProductoException;
}