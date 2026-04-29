/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.Entidades.Entrega;
import com.mycompany.motoamigopersistencia.EntregasDAO;
import com.mycompany.motoamigopersistencia.IEntregasDAO;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class EntregasBO implements IEntregasBO{

    private IEntregasDAO dao = EntregasDAO.getInstancia();
    
    @Override
    public List<Entrega>obtenerEntregasRepartidor(Long id){
        return dao.obtenerEntregasRepartidor(id);
    }

    @Override
    public List<Entrega> obtenerEntregasEmprendedor(Long id) {
        return dao.obtenerEntregasEmprendedor(id);
    }
    
}
