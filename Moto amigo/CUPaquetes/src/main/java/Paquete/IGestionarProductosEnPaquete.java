/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Paquete;

import com.mycompany.productosdto.ProductoDTO;

/**
 *
 * @author joset
 */
public interface IGestionarProductosEnPaquete {

    /**
     * Agrega un producto al paquete indicado.
     *
     * @param idPaquete identificador del paquete.
     * @param producto producto a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     */
    boolean agregarProducto(String idPaquete, ProductoDTO producto)throws PaqueteException;

    /**
     * elimina un producto del paquete indicado.
     *
     * @param idPaquete identificador del paquete.
     * @param producto producto a quitar.
     * @return true si se quitó correctamente, false en caso contrario.
     */
    boolean quitarProducto(String idPaquete, ProductoDTO producto)throws PaqueteException;

    /**
     * Calcula el peso total del paquete sumando el peso de todos sus
     * productos.
     *
     * @param idPaquete identificador del paquete.
     * @return peso total del paquete en kilogramos.
     */
    float calcularPeso(String idPaquete)throws PaqueteException;
}
