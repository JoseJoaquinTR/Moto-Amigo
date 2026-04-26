/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.infraestructura;

import com.mycompany.Entidades.Ubicacion;
import java.util.List;

/**
 *
 * @author joset
 */
public interface IUbicacionDAO {
    List<Ubicacion> buscarUbicacion(String ubi) throws Exception;
}