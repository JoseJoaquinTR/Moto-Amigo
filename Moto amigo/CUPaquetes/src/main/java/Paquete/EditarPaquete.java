/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete;

import Paquetes.PaquetesBO;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import Paquetes.IPaquetesBO;

/**
 *
 * @author joset
 */
public class EditarPaquete implements IEditarPaquete {

    private final IPaquetesBO paqueteBO;

    public EditarPaquete() {
        this.paqueteBO = PaquetesBO.getInstancia();
    }

    @Override
    public PaqueteDTO editar(String id, EditarPaqueteDTO paquete)throws PaqueteException {
        if (id == null || id.isBlank()) {
            throw new PaqueteException("El id del paquete a editar es obligatorio.");
        }
        if (paquete == null) {
            throw new PaqueteException("Los datos del paquete a editar no pueden ser nulos.");
        }
        if (paquete.getProductos() != null && paquete.getProductos().isEmpty()) {
            throw new PaqueteException("El paquete debe tener al menos un producto.");
        }
        try {
            return paqueteBO.editarPaquete(id, paquete);
        } catch (NegocioException ex) {
            throw new PaqueteException("Error al editar producto" ,ex); 
        }
    }
}
