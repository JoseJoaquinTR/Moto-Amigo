/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.consultarruta.servicios.mapBox.IMapBoxService;
import com.consultarruta.servicios.mapBox.MapBoxService;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RutaBOTest {

    @Test
    void testFlujoCompletoRutaValida() {
        IMapBoxService mapboxService =  MapBoxService.getInstance();
        RutaBO rutaBO = new RutaBO(mapboxService);

        RutaRequestDTO request = new RutaRequestDTO(
                "Av. Miguel Alemán 123, Ciudad Obregón, Sonora, México",
                "Plaza Tutuli, Ciudad Obregón, Sonora, México"
        );

        assertDoesNotThrow(() -> {
            RutaResponseDTO response = rutaBO.calcularRuta(request);

            // Validaciones explícitas
            assertNotNull(response, "La respuesta no debe ser nula");
            assertEquals(request.getDireccionRecoleccion(), response.getOrigen(), "Origen debe coincidir");
            assertEquals(request.getDireccionEntrega(), response.getDestino(), "Destino debe coincidir");

            assertTrue(response.isExito(), "Debe marcar exito = true");
            assertTrue(response.isRutaValida(), "Debe marcar rutaValida = true");

            assertNotNull(response.getLatOrigen(), "Latitud origen no debe ser nula");
            assertNotNull(response.getLngOrigen(), "Longitud origen no debe ser nula");
            assertNotNull(response.getLatDestino(), "Latitud destino no debe ser nula");
            assertNotNull(response.getLngDestino(), "Longitud destino no debe ser nula");

            assertTrue(response.getDistancia() > 0, "La distancia debe ser mayor a 0");
            assertTrue(response.getTiempoEstimado() > 0, "El tiempo estimado debe ser mayor a 0");
            assertTrue(response.getCosto() > 0, "El costo debe ser mayor a 0");
        });
    }

    @Test
    void testFlujoCamposVacios() {
        IMapBoxService mapboxService = MapBoxService.getInstance();
        RutaBO rutaBO = new RutaBO(mapboxService);

        RutaRequestDTO request = new RutaRequestDTO("", "");

        assertDoesNotThrow(() -> {
            RutaResponseDTO response = rutaBO.calcularRuta(request);

            assertNotNull(response, "La respuesta no debe ser nula");
            assertFalse(response.isExito(), "Debe marcar exito = false");
            assertFalse(response.isRutaValida(), "Debe marcar rutaValida = false");
            assertEquals(0, response.getDistancia(), "Distancia debe ser 0");
            assertEquals(0, response.getTiempoEstimado(), "Tiempo estimado debe ser 0");
            assertEquals(0.0, response.getCosto(), "Costo debe ser 0");
        });
    }

    @Test
    void testObtenerRutaDireccionesValidas() {
        MapBoxService service =  MapBoxService.getInstance();

        String origen = "Av. Miguel Alemán 123, Ciudad Obregón, Sonora, México";
        String destino = "Plaza Tutuli, Ciudad Obregón, Sonora, México";

        assertDoesNotThrow(() -> {
            RutaResponseDTO response = service.obtenerRuta(origen, destino);

            // Mensajes explícitos en consola
            System.out.println("Origen: " + response.getOrigen());
            System.out.println("Destino: " + response.getDestino());
            System.out.println("Lat Origen: " + response.getLatOrigen());
            System.out.println("Lng Origen: " + response.getLngOrigen());
            System.out.println("Lat Destino: " + response.getLatDestino());
            System.out.println("Lng Destino: " + response.getLngDestino());
            System.out.println("Distancia: " + response.getDistancia() + " km");
            System.out.println("Tiempo estimado: " + response.getTiempoEstimado() + " min");
            System.out.println("Costo: $" + response.getCosto());

            // Validaciones
            assertNotNull(response, "La respuesta no debe ser nula");
            assertTrue(response.isExito(), "Debe marcar exito = true");
            assertTrue(response.isRutaValida(), "Debe marcar rutaValida = true");
            assertTrue(response.getDistancia() > 0, "La distancia debe ser mayor a 0");
            assertTrue(response.getTiempoEstimado() > 0, "El tiempo estimado debe ser mayor a 0");
            assertTrue(response.getCosto() > 0, "El costo debe ser mayor a 0");
        });
    }

}
