/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Paquete;

import com.mycompany.motoamigodto.PaqueteDTO;
import java.util.List;

/**
 *
 * @author joset
 */
public interface IBuscarPaquete {

    /**
     * Busca paquetes que coincidan con el criterio .
     *
     * @param criterio texto de búsqueda.
     * @return lista de paquetes encontrados, o lista vacía si no hay
     * coincidencias.
     */
    List<PaqueteDTO> buscar(String criterio)throws PaqueteException ;
}