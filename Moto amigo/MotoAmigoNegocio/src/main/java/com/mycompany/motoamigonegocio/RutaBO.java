/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.consultarruta.servicios.mapBox.IMapBoxService;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaBO implements IRutaBO {

    private IMapBoxService mapbox;

    public RutaBO(IMapBoxService mapbox) {
        this.mapbox = mapbox;
    }

    @Override
    public RutaResponseDTO calcularRuta(RutaRequestDTO dto) {
        if (dto.getDireccionRecoleccion() == null || dto.getDireccionRecoleccion().isEmpty()
                || dto.getDireccionEntrega() == null || dto.getDireccionEntrega().isEmpty()) {
            return new RutaResponseDTO(
                    dto.getDireccionRecoleccion(),
                    dto.getDireccionEntrega(),
                    0,
                    false,
                    0
            );
        }

        RutaResponseDTO ruta = mapbox.obtenerRuta(
                dto.getDireccionRecoleccion(),
                dto.getDireccionEntrega()
        );

        if (!ruta.isRutaValida()) {
            return ruta;
        }

        double costoCalculado = calcularCosto(ruta.getTiempoEstimado());
        ruta.setCosto(costoCalculado);

        return ruta;
    }

    private double calcularCosto(int tiempoEstimado) {
        double costoBase = 10.0; 
        double tarifaMinuto = 1.0; 
        return costoBase + (tarifaMinuto * tiempoEstimado);
    }
}
