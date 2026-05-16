/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IReportesBloqueosDAO {

    ReporteBloqueo guardarReporte(NuevoReporteBloqueoDTO dto) throws PersistenciaException;

    List<ReporteBloqueo> consultarTodos() throws PersistenciaException;

    List<ReporteBloqueo> consultarConFiltros(FiltrosDTO filtrosDTO) throws PersistenciaException;
}
