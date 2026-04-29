/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.Entidades.Entrega;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public interface IEntregasBO {
    
    List<Entrega> obtenerEntregasRepartidor(Long id);
    List<Entrega> obtenerEntregasEmprendedor(Long id);
    
}
