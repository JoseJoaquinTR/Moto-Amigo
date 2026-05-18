/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reportes;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IReportesBloqueoBO {

    ReporteBloqueoDTO guardarReporteBloqueo(NuevoReporteBloqueoDTO dto) throws NegocioException;

    List<ReporteBloqueoDTO> consultarReportesBloqueos() throws NegocioException;

    List<ReporteBloqueoDTO> consultarReportesBloqueos(FiltrosDTO filtros) throws NegocioException;

    List<RepartidorDTO> obtenerRepartidoresParaDesbloqueoMasivo(FiltrosDTO filtros) throws NegocioException;
    
    List<InformacionReporteBloqueoDTO> consultarReportesBloqueoParaPDF(FiltrosDTO filtros)throws NegocioException;
}
