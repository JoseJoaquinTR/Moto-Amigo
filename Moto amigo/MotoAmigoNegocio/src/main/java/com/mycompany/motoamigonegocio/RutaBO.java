package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigonegocio.strategy.CalculadoraDistanciasManager;
import com.mycompany.motoamigonegocio.strategy.DistanciaAutomovilStrategy;
import com.mycompany.motoamigonegocio.strategy.DistanciaBicicletaStrategy;
import com.mycompany.infraestructura.IMapBoxService;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;

/**
 * Implementación de la lógica de negocio para rutas.
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaBO implements IRutaBO {

    private final IMapBoxService mapbox;
    private final CalculadoraDistanciasManager calculadora;

    /**
     * Construye el BO con el servicio de mapas indicado.
     *
     * @param mapbox servicio de mapas a utilizar.
     */
    public RutaBO(IMapBoxService mapbox) {
        this.mapbox = mapbox;
        this.calculadora = new CalculadoraDistanciasManager(new DistanciaAutomovilStrategy());
    }

    /**
     * Configura la estrategia de cálculo de distancia según el tipo de transporte.
     *
     * @param tipoTransporte tipo de transporte ("bicicleta" u otro valor).
     */
    public void setTipoTransporte(String tipoTransporte) {
        if (tipoTransporte != null && tipoTransporte.equalsIgnoreCase("bicicleta")) {
            calculadora.setStrategy(new DistanciaBicicletaStrategy());
        } else {
            calculadora.setStrategy(new DistanciaAutomovilStrategy());
        }
    }

    @Override
    public RutaResponseDTO calcularRuta(RutaRequestDTO dto) throws NegocioException {
        if (dto == null
                || dto.getDireccionRecoleccion() == null || dto.getDireccionRecoleccion().isEmpty()
                || dto.getDireccionEntrega() == null || dto.getDireccionEntrega().isEmpty()) {
            return new RutaResponseDTO(
                    dto == null ? null : dto.getDireccionRecoleccion(),
                    dto == null ? null : dto.getDireccionEntrega(),
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
    public boolean haTerminadoRuta() throws NegocioException {
        return mapbox.comprobarSiFinalizoRuta();
    }

    @Override
    public UbicacionDTO obtenerSiguienteUbicacion() throws NegocioException {
        return mapbox.obtenerSiguienteUbicacion();
    }
}
