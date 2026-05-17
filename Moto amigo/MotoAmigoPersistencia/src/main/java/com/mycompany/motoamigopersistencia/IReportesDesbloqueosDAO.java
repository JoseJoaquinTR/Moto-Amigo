/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.ReporteDesbloqueo;
import com.mycompany.motoamigodto.FiltrosDTO;
import com.mycompany.motoamigodto.NuevoReporteDesbloqueoDTO;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IReportesDesbloqueosDAO {

    ReporteDesbloqueo guardarReporte(NuevoReporteDesbloqueoDTO dto) throws PersistenciaException;

    List<ReporteDesbloqueo> consultarTodos() throws PersistenciaException;

    List<ReporteDesbloqueo> consultarConFiltros(FiltrosDTO filtrosDTO) throws PersistenciaException;
}
