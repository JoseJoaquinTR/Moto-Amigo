/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import enums.Estado;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author joset
 */
public class AdapterRepartidorARepartidorDTO {

    public static RepartidorDTO toDTO(Repartidor repartidor) {
        if (repartidor == null) {
            return null;
        }

        RepartidorDTO dto = new RepartidorDTO();
        Estado estado = Estado.INACTIVO;

        if (repartidor.getEstado() == com.mycompany.Entidades.Estado.ACTIVO) {
            estado = Estado.ACTIVO;
        } else if (repartidor.getEstado() == com.mycompany.Entidades.Estado.BLOQUEADO) {
            estado = Estado.BLOQUEADO;
        }

        dto.setId(repartidor.getIdRepartidor());
        dto.setNombre(repartidor.getNombre());
        dto.setTelefono(repartidor.getTelefono());
        dto.setCorreo(repartidor.getCorreo());
        dto.setVehiculo(repartidor.getVehiculo());
        dto.setEstado(estado);
        dto.setNumBloqueos(repartidor.getNumBloqueos());

        return dto;
    }

    public static List<RepartidorDTO> adaptarLista(List<Repartidor> repartidores) {
        if (repartidores == null) {
            return new LinkedList<>();
        }

        return repartidores.stream().map(AdapterRepartidorARepartidorDTO::toDTO).collect(Collectors.toList());
    }
}