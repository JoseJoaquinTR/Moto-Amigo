/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class AdapterInformacionReporte {

    public static InformacionReporteBloqueoDTO adaptar(ReporteBloqueoDTO reporte) {

        if (reporte == null) {
            return null;
        }

        InformacionReporteBloqueoDTO dto = new InformacionReporteBloqueoDTO();

        if (reporte.getRepartidor() != null) {

            dto.setNombreRepartidor(reporte.getRepartidor().getNombre());
        }

        dto.setMotivo(reporte.getMotivo());

        dto.setFechaHora(reporte.getFechaHora());

        return dto;
    }

    public static List<InformacionReporteBloqueoDTO> adaptarLista(List<ReporteBloqueoDTO> reportes) {

        if (reportes == null) {
            return new LinkedList<>();
        }

        return reportes.stream()
                .map(reporte -> adaptar(reporte))
                .collect(Collectors.toList());
    }

    public static InformacionReporteDesbloqueoDTO adaptar(ReporteDesbloqueoDTO reporte) {

        if (reporte == null) {
            return null;
        }

        InformacionReporteDesbloqueoDTO dto = new InformacionReporteDesbloqueoDTO();

        dto.setNumCuentasDesbloqueadas(reporte.getNumRepartidoresDesbloqueados());

        dto.setMotivo(reporte.getMotivo());
        dto.setFechaHora(reporte.getFechaHora());

        return dto;
    }

    public static List<InformacionReporteDesbloqueoDTO> adaptarListaDesbloqueos(List<ReporteDesbloqueoDTO> reportes) {

        if (reportes == null) {
            return new LinkedList<>();
        }

         return reportes.stream()
                .map(reporte -> adaptar(reporte))
                .collect(Collectors.toList());
    }
}
