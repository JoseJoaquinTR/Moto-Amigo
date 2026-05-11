/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete;

import Paquetes.IPaqueteBO;
import Paquetes.PaqueteBO;
import com.mycompany.motoamigodto.EditarPaqueteDTO;
import com.mycompany.motoamigodto.PaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author joset
 */
public class EditarPaquete implements IEditarPaquete {

    private final IPaqueteBO paqueteBO;

    public EditarPaquete() {
        this.paqueteBO = PaqueteBO.getInstancia();
    }

    @Override
    public PaqueteDTO editar(String id, EditarPaqueteDTO paquete)throws PaqueteException {
        try {
            return paqueteBO.editarPaquete(id, paquete);
        } catch (NegocioException ex) {
            throw new PaqueteException("Error al editar producto" ,ex); 
        }
    }
}
