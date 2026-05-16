/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import enums.*;
import com.mycompany.motoamigodto.RepartidorDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

 
/**
 *
 * @author Carmen Andrea Lara Osuna
 */


public class ReportesBloqueosDAOTest {

    private ReportesBloqueosDAO dao;

    @BeforeEach
    public void setUp() {
        dao = new ReportesBloqueosDAO();
    }

    @Test
    public void testGuardarReporteBloqueo() {
        assertDoesNotThrow(() -> {
            MotivoDTO motivoDTO = new MotivoDTO("Incumplimiento de reglas", Tipo.BLOQUEO);

            RepartidorDTO repartidorDTO = new RepartidorDTO();
            repartidorDTO.setId(new ObjectId().toHexString());
            repartidorDTO.setNombre("Juan Pérez");
            repartidorDTO.setTelefono("6441234567");
            repartidorDTO.setVehiculo("Moto");
            repartidorDTO.setEstado(Estado.ACTIVO);
            repartidorDTO.setNumBloqueos(0);

            byte[] evidencia = "imagen de prueba".getBytes();

            NuevoReporteBloqueoDTO nuevoReporte = new NuevoReporteBloqueoDTO();
            nuevoReporte.setMotivo(motivoDTO);
            nuevoReporte.setRepartidor(repartidorDTO);
            nuevoReporte.setDetalles("El repartidor no respetó la zona asignada.");
            nuevoReporte.setFechaHora(LocalDateTime.now());
            nuevoReporte.setImagenEvidencia(evidencia);

            ReporteBloqueo reporte = dao.guardarReporte(nuevoReporte);

            assertNotNull(reporte);
            assertEquals("Incumplimiento de reglas", reporte.getMotivo().getMotivo());
            assertEquals("Juan Pérez", reporte.getRepartidor().getNombre());
            assertEquals("Moto", reporte.getRepartidor().getVehiculo());
            assertArrayEquals(evidencia, reporte.getImagenEvidencia());

            assertNotNull(reporte.getIdReporte());
            assertEquals(24, reporte.getIdReporte().length());
            assertTrue(reporte.getIdReporte().matches("^[a-fA-F0-9]{24}$"));
        });
    }

    @Test
    public void testConsultarTodos() {
        assertDoesNotThrow(() -> {
            List<ReporteBloqueo> reportes = dao.consultarTodos();
            assertNotNull(reportes);
            assertTrue(reportes.size() >= 0);
        });
    }

//    @Test
//    public void testConsultarConFiltros() {
//        assertDoesNotThrow(() -> {
//            com.mycompany.motoamigodto.FiltrosDTO filtros = new com.mycompany.motoamigodto.FiltrosDTO();
//            filtros.setMotivo(new MotivoDTO("Incumplimiento", Tipo.BLOQUEO));
//
//            List<ReporteBloqueo> resultados = dao.consultarConFiltros(filtros);
//            assertNotNull(resultados);
//        });
//    }
}
