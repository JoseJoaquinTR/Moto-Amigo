/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.infraestructura.IMapBoxService;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaBO implements IRutaBO {

    private IMapBoxService mapbox;
    private CalculadoraDistanciasManager calculadora;

    public RutaBO(IMapBoxService mapbox) {
        this.mapbox = mapbox;
        this.calculadora = new CalculadoraDistanciasManager(new DistanciaAutomovilStrategy());
    }

    public void setTipoTransporte(String tipoTransporte) {
        if (tipoTransporte != null && tipoTransporte.equalsIgnoreCase("bicicleta")) {
            calculadora.setStrategy(new DistanciaBicicletaStrategy());
        } else {
            calculadora.setStrategy(new DistanciaAutomovilStrategy());
        }
    }

    @Override
    public RutaResponseDTO calcularRuta(RutaRequestDTO dto) {
        if (dto.getDireccionRecoleccion() == null || dto.getDireccionRecoleccion().isEmpty()
                || dto.getDireccionEntrega() == null || dto.getDireccionEntrega().isEmpty()) {
            return new RutaResponseDTO(
                    dto.getDireccionRecoleccion(),
                    dto.getDireccionEntrega(),
                    false,
                    false
            );
        }
        return mapbox.obtenerRuta(
                dto.getDireccionRecoleccion(),
                dto.getDireccionEntrega()
        );
    }

    @Override
    public boolean haTerminadoRuta() {
        return mapbox.comprobarSiFinalizoRuta();
    }

    @Override
    public UbicacionDTO obtenerSiguienteUbicacion() {
        return mapbox.obtenerSiguienteUbicacion();
    }
}

