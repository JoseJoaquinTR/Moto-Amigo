/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factoryReporte;

import Adapter.AdapterMotivo;
import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class FabricaReporteBloqueo implements IFabricaReporte<NuevoReporteBloqueoDTO, ReporteBloqueo> {

    @Override
    public ReporteBloqueo crearReporte(NuevoReporteBloqueoDTO dto) {
        ReporteBloqueo reporte = new ReporteBloqueo();

        reporte.setIdRepartidor(dto.getRepartidor().getId());
        reporte.setMotivo(AdapterMotivo.toEntity(dto.getMotivo()));
        reporte.setDetalles(dto.getDetalles());
        reporte.setFechaHora(dto.getFechaHora());
        reporte.setImagenEvidencia(dto.getImagenEvidencia());

        return reporte;
    }
}
