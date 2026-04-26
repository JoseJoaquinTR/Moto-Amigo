/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.UbicacionDTO;
import java.util.List;

/**
 *
 * @author joset
 */
public interface IUbicacionBO {
    public List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException;
}
