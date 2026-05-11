/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Paquete;

import com.mycompany.motoamigodto.EditarPaqueteDTO;
import com.mycompany.motoamigodto.PaqueteDTO;

/**
 *
 * @author joset
 */
public interface IEditarPaquete {

    /**
     * edita un paquete con los datos proporcionados.
     *
     * @param id identificador del paquete a editar.
     * @param paquete datos a actualizar.
     * @return el paquete actualizado, o null si ocurre un error.
     */
    PaqueteDTO editar(String id, EditarPaqueteDTO paquete)throws PaqueteException;
}
