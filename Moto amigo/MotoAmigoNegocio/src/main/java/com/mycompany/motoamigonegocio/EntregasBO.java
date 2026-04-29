/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import Adapter.AdapterEntregaAEntregaDTO;
import com.mycompany.Entidades.Entrega;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigopersistencia.EntregasDAO;
import com.mycompany.motoamigopersistencia.IEntregasDAO;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class EntregasBO implements IEntregasBO{

    private IEntregasDAO dao = EntregasDAO.getInstancia();
    
    private static EntregasBO instancia;

    public static EntregasBO getInstance() {
        if (instancia == null) {
            instancia = new EntregasBO();
        }
        return instancia;
    }
    
    @Override
    public List<EntregaDTO>obtenerEntregasRepartidor(Long id){
        return new AdapterEntregaAEntregaDTO().adaptarLista(dao.obtenerEntregasRepartidor(id));
    }

    @Override
    public List<EntregaDTO> obtenerEntregasEmprendedor(Long id) {
        return new AdapterEntregaAEntregaDTO().adaptarLista(dao.obtenerEntregasEmprendedor(id));
    }
    
}
