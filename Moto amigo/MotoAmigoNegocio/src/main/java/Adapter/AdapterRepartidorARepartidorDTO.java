/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

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
                repartidor.getNombre(),
                repartidor.getTelefono(),
                repartidor.getCorreo(),
                repartidor.getVehiculo(),
                repartidor.getEstado()
        );
    }

    public List<RepartidorDTO> adaptarLista(List<Repartidor> repartidores) {
        if (repartidores == null) {
            return new ArrayList<>();
        }
        return repartidores.stream().map(this::adaptar).collect(Collectors.toList());
    }
}
