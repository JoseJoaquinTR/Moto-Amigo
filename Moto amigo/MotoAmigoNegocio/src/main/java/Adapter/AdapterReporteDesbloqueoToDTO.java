/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.Entidades.ReporteDesbloqueo;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class AdapterReporteDesbloqueoToDTO {

    public static ReporteDesbloqueoDTO toDTO(ReporteDesbloqueo reporte) {

        if (reporte == null) {
            return null;
        }

        ReporteDesbloqueoDTO dto = new ReporteDesbloqueoDTO();

        dto.setIdReporteDesbloqueos(reporte.getIdReporte());
        dto.setMotivo(AdapterMotivo.toDTO(reporte.getMotivo()));
        dto.setDetalles(reporte.getDetalles());
        dto.setFechaHora(reporte.getFechaHora());
        dto.setNumRepartidoresDesbloqueados(reporte.getNumRepartidoresDesbloqueados());

        return dto;
    }

    public static List<ReporteDesbloqueoDTO> adaptarLista(List<ReporteDesbloqueo> reportes) {

        if (reportes == null) {
            return new LinkedList<>();
        }

        return reportes.stream().map(reporte -> toDTO(reporte)).collect(Collectors.toList());
    }
}
