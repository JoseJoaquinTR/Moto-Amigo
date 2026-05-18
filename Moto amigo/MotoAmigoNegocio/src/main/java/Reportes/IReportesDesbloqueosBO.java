/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reportes;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IReportesDesbloqueosBO {

    ReporteDesbloqueoDTO guardarReporteDesbloqueo(NuevoReporteDesbloqueoDTO dto) throws NegocioException;

    List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos() throws NegocioException;

    List<ReporteDesbloqueoDTO> consultarReportesDesbloqueos(FiltrosDTO filtros) throws NegocioException;

    List<InformacionReporteDesbloqueoDTO> consultarReportesDesbloqueoParaPDF(FiltrosDTO filtros) throws NegocioException;

}
