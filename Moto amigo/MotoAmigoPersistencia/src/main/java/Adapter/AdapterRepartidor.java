/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import enums.Estado;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class AdapterRepartidor {

    public static Repartidor toEntity(RepartidorDTO dto) {
        if (dto == null) {
            return null;
        }
        com.mycompany.Entidades.Estado estado = com.mycompany.Entidades.Estado.INACTIVO;
        if (dto.getEstado() == Estado.ACTIVO) {
            estado = com.mycompany.Entidades.Estado.ACTIVO;
        } else if (dto.getEstado() == Estado.BLOQUEADO) {
            estado = com.mycompany.Entidades.Estado.BLOQUEADO;
        }

        Repartidor repartidor = new Repartidor();
        repartidor.setIdRepartidor(dto.getId());
        repartidor.setNombre(dto.getNombre());
        repartidor.setTelefono(dto.getTelefono());
        repartidor.setVehiculo(dto.getVehiculo());
        repartidor.setEstado(estado);
        repartidor.setNumBloqueos(dto.getNumBloqueos());
        return repartidor;
    }

    public static RepartidorDTO toDTO(Repartidor entity) {
        if (entity == null) {
            return null;
        }

        Estado estado = Estado.INACTIVO;
        if (entity.getEstado() == com.mycompany.Entidades.Estado.ACTIVO) {
            estado = Estado.ACTIVO;
        } else if (entity.getEstado() == com.mycompany.Entidades.Estado.BLOQUEADO) {
            estado = Estado.BLOQUEADO;
        }

        RepartidorDTO dto = new RepartidorDTO();
        dto.setId(entity.getIdRepartidor());
        dto.setNombre(entity.getNombre());
        dto.setTelefono(entity.getTelefono());
        dto.setVehiculo(entity.getVehiculo());
        dto.setEstado(estado);
        dto.setNumBloqueos(entity.getNumBloqueos());
        return dto;
    }

}
