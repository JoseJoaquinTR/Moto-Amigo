/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IGenerarPDFHistorialReportes {

    boolean descargarPDFHistorialBloqueos(FiltrosDTO filtros, String rutaDestino) throws NegocioException;

    boolean descargarPDFHistorialDesbloqueos(FiltrosDTO filtros, String rutaDestino) throws NegocioException;
}
