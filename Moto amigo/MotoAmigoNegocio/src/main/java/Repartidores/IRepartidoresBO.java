/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package Repartidores;

import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.Estado;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IRepartidoresBO {

    List<RepartidorDTO> obtenerRepartidoresActivos() throws NegocioException;

    RepartidorDTO cambiarEstadoRepartidor(String id, Estado estado) throws NegocioException;

    RepartidorDTO consultarRepartidorPorId(String id) throws NegocioException;

    List<RepartidorDTO> obtenerRepartidores() throws NegocioException;

    List<RepartidorDTO> buscarRepartidoresPorNombre(String nombre) throws NegocioException;

    RepartidorDTO incrementarNumeroBloqueos(String id) throws NegocioException;
}
