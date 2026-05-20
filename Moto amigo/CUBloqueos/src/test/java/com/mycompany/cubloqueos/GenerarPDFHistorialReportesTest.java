/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class GenerarPDFHistorialReportesTest {

    private final IGenerarPDFHistorialReportes generarPDF = GenerarPDFHistorialReportes.getInstancia();

    @Test
    public void testDescargarPDFHistorialBloqueos() {
        assertDoesNotThrow(() -> {
            FiltrosDTO filtros = new FiltrosDTO();
            File archivo = File.createTempFile("historial_bloqueos_test", ".pdf");
            archivo.deleteOnExit();

            boolean generado = generarPDF.descargarPDFHistorialBloqueos(filtros, archivo.getAbsolutePath());

            assertTrue(generado);
            assertTrue(archivo.exists());
            assertTrue(archivo.length() > 0);
        });
    }

    @Test
    public void testDescargarPDFHistorialDesbloqueos() {
        assertDoesNotThrow(() -> {
            FiltrosDTO filtros = new FiltrosDTO();
            File archivo = File.createTempFile("historial_desbloqueos_test", ".pdf");
            archivo.deleteOnExit();

            boolean generado = generarPDF.descargarPDFHistorialDesbloqueos(filtros, archivo.getAbsolutePath());

            assertTrue(generado);
            assertTrue(archivo.exists());
            assertTrue(archivo.length() > 0);
        });
    }

    @Test
    public void testDescargarPDFHistorialBloqueosRutaNula() {
        assertThrows(NegocioException.class, () -> {
            generarPDF.descargarPDFHistorialBloqueos(new FiltrosDTO(), null);
        });
    }

    @Test
    public void testDescargarPDFHistorialDesbloqueosRutaVacia() {
        assertThrows(NegocioException.class, () -> {
            generarPDF.descargarPDFHistorialDesbloqueos(new FiltrosDTO(), "");
        });
    }
}
