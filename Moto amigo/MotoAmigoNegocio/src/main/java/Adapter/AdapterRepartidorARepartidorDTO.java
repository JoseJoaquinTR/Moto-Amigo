/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.Entidades.Estado;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author joset
 */
public class AdapterRepartidorARepartidorDTO {

    public RepartidorDTO adaptar(Repartidor repartidor) {
        if (repartidor == null) {
            return null;
        }
        return new RepartidorDTO(
                repartidor.getIdRepartidor(),
                repartidor.getNombre(),
                repartidor.getTelefono(),
                repartidor.getCorreo(),
                repartidor.getVehiculo(),
                estadoATexto(repartidor.getEstado())
        );
    }

    public List<RepartidorDTO> adaptarLista(List<Repartidor> repartidores) {
        if (repartidores == null) {
            return new ArrayList<>();
        }
        return repartidores.stream().map(this::adaptar).collect(Collectors.toList());
    }

    private String estadoATexto(Estado estado) {
        return estado != null ? estado.name() : null;
    }
}