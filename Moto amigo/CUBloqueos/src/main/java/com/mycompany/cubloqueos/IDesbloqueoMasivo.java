/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IDesbloqueoMasivo {

    ReporteDesbloqueoDTO desbloqueoMasivo(FiltrosDTO filtros, NuevoReporteDesbloqueoDTO dto) throws NegocioException;

    boolean validarFiltros(FiltrosDTO filtros);

    boolean validarFechas(LocalDateTime fechaDesde, LocalDateTime fechaHasta);

    boolean validarMotivo(MotivoDTO motivo) throws NegocioException;

    List<RepartidorDTO> obtenerRepartidores(FiltrosDTO filtros) throws NegocioException;
}
