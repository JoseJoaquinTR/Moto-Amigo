
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Repartidor;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author xiomi
 */
public class RepartidorDAO implements IRepartidorDAO {
    private static RepartidorDAO instancia;

    private RepartidorDAO() {
    }
    
    public static RepartidorDAO getInstance() {
        if (instancia == null) {
            instancia = new RepartidorDAO();
        }
        return instancia;
    }

    
    @Override
    public List<Repartidor> obtenerRepartidoresDisponibles() {
        List<Repartidor> repartidores = new ArrayList<>();
        repartidores.add(new Repartidor(1L, "Carlos Pérez", "6441234567", "carlos@mail.com", "Moto", "disponible"));
        repartidores.add(new Repartidor(2L, "Ana López", "6449876543", "ana@mail.com", "Bicicleta", "disponible"));
        repartidores.add(new Repartidor(3L, "Luis Ramírez", "6445556677", "luis@mail.com", "Moto", "disponible"));
        return repartidores;
    }
    @Override
    public Repartidor obtenerRepartidorPorId(Long idRepartidor) {
        return obtenerRepartidoresDisponibles()
            .stream()
            .filter(repa -> repa.getIdRepartidor().equals(idRepartidor))
            .findFirst()
            .orElse(null);
    }
}
