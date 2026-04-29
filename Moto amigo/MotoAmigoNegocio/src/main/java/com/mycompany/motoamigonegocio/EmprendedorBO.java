/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigopersistencia.EmprendedoresDAO;
import com.mycompany.motoamigopersistencia.IEmprendedoresDAO;
import com.mycompany.motoamigopersistencia.IRepartidorDAO;
import com.mycompany.motoamigopersistencia.RepartidorDAO;

/**
 *
 * @author joset
 */
public class EmprendedorBO implements IEmprendedoresBO{
    
    private IEmprendedoresDAO dao = EmprendedoresDAO.getInstance();
    
    @Override
    public EmprendedorDTO obtenerEmprendedorPorId(Long id) {
        Emprendedor emprendedor = dao.obtenerEmprendedorPorId(id);
        return new Adapter.AdapterEmprendedorAEmprendedorDTO().adaptar(emprendedor);
    }

    @Override
    public Emprendedor registrarEmprendedor(EmprendedorDTO emprendedorDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
