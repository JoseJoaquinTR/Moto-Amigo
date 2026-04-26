/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.consultarruta.servicios.mapBox;

import com.mycompany.motoamigodto.RutaResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class MapBoxServiceTest {

  

    @Test
    void testObtenerRutaConDireccionesValidas() {
        MapBoxService service =  MapBoxService.getInstance();

        String origen = "Av. Miguel Alemán 123, Ciudad Obregón, Sonora, México";
        String destino = "Plaza Tutuli, Ciudad Obregón, Sonora, México";

        assertDoesNotThrow(() -> {
            RutaResponseDTO response = service.obtenerRuta(origen, destino);

            assertNotNull(response, "La respuesta no debe ser nula");
            assertTrue(response.isExito(), "La llamada al servicio debe ser exitosa");
            assertTrue(response.isRutaValida(), "La ruta debe ser válida");

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
    void testObtenerRutaConDireccionesInvalidas() {
        MapBoxService service =  MapBoxService.getInstance();

        String origen = "";
        String destino = "";

        assertDoesNotThrow(() -> {
            RutaResponseDTO response = service.obtenerRuta(origen, destino);

            assertNotNull(response, "La respuesta no debe ser nula");
            assertFalse(response.isExito(), "La llamada debe fallar con direcciones inválidas");
            assertFalse(response.isRutaValida(), "La ruta no debe ser válida");
        });
    }
}

