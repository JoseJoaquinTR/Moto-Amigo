/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;

/**
 *
 * @author Jesus Omar
 */
public class AdapterEmprendedorAEmprendedorDTO {
    
    public EmprendedorDTO adaptar(Emprendedor emprendedor) {
        if (emprendedor == null) {
            return null;
        }
        return new EmprendedorDTO(
                emprendedor.getNombre(), 
                emprendedor.getCorreo(), 
                emprendedor.getTelefono(), 
                emprendedor.getNombreNegocio()
        );
    }
    
}
