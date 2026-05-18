/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class AdapterReporteBloqueoToDTO {

    public static ReporteBloqueoDTO toDTO(ReporteBloqueo reporte) {

        if (reporte == null) {
            return null;
        }

        ReporteBloqueoDTO dto = new ReporteBloqueoDTO();

        dto.setIdReporteBloqueo(reporte.getIdReporte());

        dto.setRepartidor(AdapterRepartidor.toDTO(reporte.getRepartidor()));

        dto.setMotivo(AdapterMotivo.toDTO(reporte.getMotivo()));

        dto.setDetalles(reporte.getDetalles());

        dto.setImagenEvidencia(reporte.getImagenEvidencia());

        dto.setFechaHora(reporte.getFechaHora());

        return dto;
    }

    public  static List<ReporteBloqueoDTO> adaptarLista(List<ReporteBloqueo> reportes) {

        if (reportes == null) {
            return new LinkedList<>();
        }

        return reportes.stream()
                .map(reporte -> toDTO(reporte))
                .collect(Collectors.toList());
    }
}
