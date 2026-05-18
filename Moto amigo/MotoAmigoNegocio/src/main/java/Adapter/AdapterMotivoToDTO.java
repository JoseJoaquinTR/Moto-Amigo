/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Adapter;

import com.mycompany.Entidades.Motivo;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import enums.Tipo;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class AdapterMotivoToDTO {

     public static MotivoDTO toDTO(Motivo motivo) {

        if (motivo == null) {
            return null;
        }

        
        MotivoDTO dto = new MotivoDTO();
        
        Tipo tipo = Tipo.BLOQUEO;
        if(motivo.getTipo() == com.mycompany.Entidades.Tipo.DESBLOQUEO){
            tipo = Tipo.DESBLOQUEO;
        }
        
        dto.setMotivo(motivo.getMotivo());
        dto.setTipo(tipo);

        return dto;
    }

    public static List<MotivoDTO> adaptarLista(List<Motivo> motivos) {

        if (motivos == null) {
            return new LinkedList<>();
        }

        return motivos.stream()
                .map(motivo -> toDTO(motivo))
                .collect(Collectors.toList());
    }
}
