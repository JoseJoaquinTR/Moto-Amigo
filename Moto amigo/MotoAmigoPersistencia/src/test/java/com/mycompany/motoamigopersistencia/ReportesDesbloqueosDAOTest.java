/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.ReporteDesbloqueo;
import com.mycompany.motoamigodto.*;
import enums.Tipo;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import com.mycompany.bloqueorepartidores.*;

 
/**
 *
 * @author Carmen Andrea Lara Osuna
 */

public class ReportesDesbloqueosDAOTest {

    private ReportesDesbloqueosDAO dao;

    @BeforeEach
    public void setUp() {
        dao = ReportesDesbloqueosDAO.getInstancia();
    }

    @Test
    public void testGuardarReporte() {
        assertDoesNotThrow(() -> {
            MotivoDTO motivoDTO = new MotivoDTO("Solicitud de desbloqueo", Tipo.DESBLOQUEO);

            NuevoReporteDesbloqueoDTO nuevoReporte = new NuevoReporteDesbloqueoDTO();
            nuevoReporte.setMotivo(motivoDTO);
            nuevoReporte.setDetalles("El repartidor cumplió con los requisitos para desbloqueo.");
            nuevoReporte.setFechaHora(LocalDateTime.now());

            ReporteDesbloqueo reporte = dao.guardarReporte(nuevoReporte);

            assertNotNull(reporte);
            assertEquals("Solicitud de desbloqueo", reporte.getMotivo().getMotivo());
            assertEquals("El repartidor cumplió con los requisitos para desbloqueo.", reporte.getDetalles());
            assertNotNull(reporte.getFechaHora());
        });
    }

    @Test
    public void testConsultarTodos() {
        assertDoesNotThrow(() -> {
            List<ReporteDesbloqueo> reportes = dao.consultarTodos();
            assertNotNull(reportes);
            assertTrue(reportes.size() >= 0);
        });
    }

    @Test
    public void testConsultarConFiltros() {
        assertDoesNotThrow(() -> {
            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setMotivo(new MotivoDTO("Se reviso el caso", Tipo.DESBLOQUEO));

            List<ReporteDesbloqueo> resultados = dao.consultarConFiltros(filtros);
            assertNotNull(resultados);
        });
    }
}
