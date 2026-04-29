/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;

/**
 *
 * @author Jesus Omar
 */
public interface IEmprendedoresBO {
    
    EmprendedorDTO obtenerEmprendedorPorId(Long id);
    Emprendedor registrarEmprendedor(EmprendedorDTO emprendedorDTO);
    
}
