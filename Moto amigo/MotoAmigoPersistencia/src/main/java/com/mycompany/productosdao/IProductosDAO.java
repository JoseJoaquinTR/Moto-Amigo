
package com.mycompany.productosdao;

import com.mycompany.Entidades.Producto;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import com.mycompany.productosdto.EditarProductoDTO;
import com.mycompany.productosdto.NuevoProductoDTO;
import java.util.List;

/**
 * @author joset
 */
public interface IProductosDAO {

    /**
     * Crea un producto nuevo en la base de datos. 
     *
     * @param producto producto a guardar .
     * @return producto con el id asignado por la base de datos.
     * @throws PersistenciaException si ocurre un error.
     */
    Producto agregar(NuevoProductoDTO producto) throws PersistenciaException;

    /**
     * Actualiza los campos de un producto existente. 
     *
     * @param id identificador del producto a modificar.
     * @param datosNuevos producto con los campos a actualizar.
     * @return el producto actualizado, o null.
     * @throws PersistenciaException si ocurre un error.
     */
    Producto actualizar(String id, EditarProductoDTO datosNuevos) throws PersistenciaException;

    /**
     * Elimina un producto por su id.
     *
     * @param id id del producto a eliminar.
     * @return true si se eliminó, false si no existía.
     * @throws PersistenciaException si ocurre un error.
     */
    boolean eliminar(String id) throws PersistenciaException;

    /**
     * Busca un producto por su id.
     *
     * @param id id del producto.
     * @return el producto encontrado, o null si no.
     * @throws PersistenciaException si ocurre un error .
     */
    Producto consultarPorId(String id) throws PersistenciaException;

    /**
     * Busca productos con el nombre que contenga el string .
     *
     * @param criterio fragmento de texto a buscar dentro del nombre.
     * @return lista de productos que coinciden.
     * @throws PersistenciaException si ocurre un error .
     */
    List<Producto> consultarPorNombre(String criterio,String idEmprendedor) throws PersistenciaException;

    /**
     * Obtiene todos los productos pertenecientes a un emprendedor específico.
     *
     * @param idEmprendedor id del emprendedor dueño de los productos.
     * @return lista de productos del emprendedor.
     * @throws PersistenciaException si ocurre un error .
     */
    List<Producto> obtenerPorEmprendedor(String idEmprendedor) throws PersistenciaException;

}
