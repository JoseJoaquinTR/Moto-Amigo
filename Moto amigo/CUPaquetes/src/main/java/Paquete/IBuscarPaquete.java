/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Paquete;

import com.mycompany.paquetesdto.PaqueteDTO;
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
     * @throws Paquete.PaqueteException
     */
    List<PaqueteDTO> buscar(String criterio,String idEmprendedor)throws PaqueteException ;
}