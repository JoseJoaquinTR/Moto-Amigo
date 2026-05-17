/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.Entidades.Motivo;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import enums.Tipo;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class AdapterMotivo {

    public static Motivo toEntity(MotivoDTO dto) {
        if (dto == null) {
            return null;
        }

        com.mycompany.Entidades.Tipo tipo = com.mycompany.Entidades.Tipo.BLOQUEO;
        if (dto.getTipo() == Tipo.BLOQUEO) {
            tipo = com.mycompany.Entidades.Tipo.BLOQUEO;
        } else if (dto.getTipo() == Tipo.DESBLOQUEO) {
            tipo = com.mycompany.Entidades.Tipo.DESBLOQUEO;
        }

        Motivo motivo = new Motivo();
        motivo.setMotivo(dto.getMotivo());
        motivo.setTipo(tipo);
        return motivo;
    }

    public static MotivoDTO toDTO(Motivo entity) {
        if (entity == null) {
            return null;
        }

        Tipo tipo = Tipo.BLOQUEO;
        if (entity.getTipo() == com.mycompany.Entidades.Tipo.BLOQUEO) {
            tipo = Tipo.BLOQUEO;
        } else if (entity.getTipo() == com.mycompany.Entidades.Tipo.DESBLOQUEO) {
            tipo = Tipo.DESBLOQUEO;
        }

        return new MotivoDTO(entity.getMotivo(), tipo);
    }
}
