
package Paquetes;

import com.mycompany.motoamigodto.EditarPaqueteDTO;
import com.mycompany.motoamigodto.NuevoPaqueteDTO;
import com.mycompany.motoamigodto.PaqueteDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 *
 * @author joset
 */
public interface IPaqueteBO  {

    /**
     * Crea un nuevo paquete en el sistema.
     *
     * @param nuevoPaquete datos del paquete a registrar.
     * @return el paquete creado con su identificador asignado.
     * @throws NegocioException si los datos son inválidos o falla la persistencia.
     */
    PaqueteDTO crearPaquete(NuevoPaqueteDTO nuevoPaquete) throws NegocioException;

    /**
     * Modifica los datos de un paquete existente.
     *
     * @param id identificador del paquete a editar.
     * @param datosNuevos campos del paquete a actualizar.
     * @return el paquete ya actualizado, o null si no se encontró.
     * @throws NegocioException si los datos son inválidos o falla la persistencia.
     */
    PaqueteDTO editarPaquete(String id, EditarPaqueteDTO datosNuevos) throws NegocioException;

    /**
     * Elimina un paquete del sistema.
     *
     * @param id identificador del paquete a eliminar.
     * @return true si se eliminó, false si no existía.
     * @throws NegocioException si falla la persistencia.
     */
    boolean eliminarPaquete(String id) throws NegocioException;

    /**
     * Consulta un paquete por su id, con todos sus productos
     * resueltos.
     *
     * @param id identificador del paquete.
     * @return el paquete encontrado, o null si no existe.
     * @throws NegocioException si falla la persistencia.
     */
    PaqueteDTO consultarPaquetePorId(String id) throws NegocioException;

    /**
     * Busca paquetes cuyo nombre contenga el texto indicado.
     *
     * @param nombreSimilar fragmento del nombre a buscar.
     * @return lista de paquetes que coinciden, con sus productos resueltos.
     * @throws NegocioException si falla la persistencia.
     */
    List<PaqueteDTO> buscarPaquetesPorNombre(String nombreSimilar) throws NegocioException;

    /**
     * Obtiene todos los paquetes pertenecientes a un emprendedor.
     *
     * @param idEmprendedor identificador del emprendedor.
     * @return lista de paquetes del emprendedor con sus productos resueltos.
     * @throws NegocioException si falla la persistencia.
     */
    List<PaqueteDTO> obtenerPaquetesPorEmprendedor(String idEmprendedor) throws NegocioException;

    /**
     * Agrega un producto a un paquete existente.
     *
     * @param idPaquete identificador del paquete.
     * @param producto producto a agregar.
     * @param cantidad cantidad de unidades a incluir.
     * @return el paquete actualizado.
     * @throws NegocioException si los datos son inválidos o falla la persistencia.
     */
    PaqueteDTO agregarProductoAPaquete(String idPaquete, ProductoDTO producto, int cantidad)
            throws NegocioException;

    /**
     * Quita un producto de un paquete existente.
     *
     * @param idPaquete identificador del paquete.
     * @param idProducto identificador del producto a quitar.
     * @return el paquete actualizado.
     * @throws NegocioException si los datos son inválidos o falla la persistencia.
     */
    PaqueteDTO quitarProductoDePaquete(String idPaquete, String idProducto)
            throws NegocioException;

}
