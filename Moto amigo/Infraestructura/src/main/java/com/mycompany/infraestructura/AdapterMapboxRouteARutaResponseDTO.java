/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infraestructura;

import com.mycompany.Entidades.Ubicacion;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigopersistencia.SeguimientoEntregaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class AdapterMapboxRouteARutaResponseDTO {

    private static final double TARIFA_BASE = 20.0;
    private static final double COSTO_POR_KM = 5.0;

    public RutaResponseDTO adaptar(MapboxRouteDTO rutaMapbox) {
        double distanciaKm = rutaMapbox.getDistanciaMetros() / 1000.0;
        int etaMin = (int) Math.round(rutaMapbox.getDuracionSegundos() / 60.0);
        double costo = TARIFA_BASE + (distanciaKm * COSTO_POR_KM);

        List<double[]> coords = rutaMapbox.getCoordenadas();
        List<Ubicacion> puntos = new ArrayList<>();
        for (int i = 0; i < coords.size(); i++) {
            double lng = coords.get(i)[0];
            double lat = coords.get(i)[1];
            String desc = "En camino...";
            if (i == 0) {
                desc = "Llegó al origen";
            }
            if (i == coords.size() - 1) {
                desc = "Destino";
            }
            puntos.add(new Ubicacion(lat, lng, desc));
        }
        SeguimientoEntregaDAO.getInstance().setRutaReal(puntos);

        return new RutaResponseDTO(
                rutaMapbox.getOrigen(),
                rutaMapbox.getDestino(),
                rutaMapbox.getLatOrigen(),
                rutaMapbox.getLngOrigen(),
                rutaMapbox.getLatDestino(),
                rutaMapbox.getLngDestino(),
                distanciaKm, etaMin, costo, true, true
        );
    }
}
