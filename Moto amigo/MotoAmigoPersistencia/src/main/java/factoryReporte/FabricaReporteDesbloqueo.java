/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factoryReporte;

import Adapter.AdapterMotivo;
import com.mycompany.Entidades.ReporteDesbloqueo;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class FabricaReporteDesbloqueo implements IFabricaReporte<NuevoReporteDesbloqueoDTO, ReporteDesbloqueo> {

    @Override
    public ReporteDesbloqueo crearReporte(NuevoReporteDesbloqueoDTO dto) {
        ReporteDesbloqueo reporte = new ReporteDesbloqueo();

        reporte.setMotivo(AdapterMotivo.toEntity(dto.getMotivo()));
        reporte.setDetalles(dto.getDetalles());
        reporte.setFechaHora(dto.getFechaHora());
        reporte.setNumRepartidoresDesbloqueados(dto.getNumRepartidoresDesbloqueados());

        return reporte;
    }
}
