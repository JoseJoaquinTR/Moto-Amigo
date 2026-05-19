/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IHistorialReportes {

    List<ReporteBloqueoDTO> consultarHistorialBloqueos(FiltrosDTO filtros) throws NegocioException;

    List<ReporteDesbloqueoDTO> consultarHistorialDesbloqueos(FiltrosDTO filtros) throws NegocioException;

    boolean validarFechas(LocalDateTime fechaDesde, LocalDateTime fechaHasta);

    boolean validarMotivoSeleccionado(MotivoDTO motivo);
    
    boolean validarFiltros(FiltrosDTO filtros);
}
