/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.cubloqueos;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.Tipo;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class HistorialReportesTest {

    private final IHistorialReportes historialReportes = HistorialReportes.getInstancia();

    @Test
    public void testValidarFechas() {
        LocalDateTime ahora = LocalDateTime.now();

        assertTrue(historialReportes.validarFechas(ahora.minusDays(5), ahora));
        assertFalse(historialReportes.validarFechas(ahora, ahora.minusDays(5)));
        assertFalse(historialReportes.validarFechas(ahora.minusDays(5), ahora.plusDays(1)));
        assertTrue(historialReportes.validarFechas(null, ahora));
        assertTrue(historialReportes.validarFechas(ahora.minusDays(5), null));
    }

    @Test
    public void testValidarMotivoSeleccionado() {
        MotivoDTO motivo = new MotivoDTO();
        motivo.setMotivo("Conducta inapropiada con clientes");
        motivo.setTipo(Tipo.BLOQUEO);

        assertTrue(historialReportes.validarMotivoSeleccionado(motivo));
        assertTrue(historialReportes.validarMotivoSeleccionado(null));

        MotivoDTO motivoVacio = new MotivoDTO();
        motivoVacio.setMotivo("");
        motivoVacio.setTipo(Tipo.BLOQUEO);
        assertFalse(historialReportes.validarMotivoSeleccionado(motivoVacio));

        MotivoDTO motivoInexistente = new MotivoDTO();
        motivoInexistente.setMotivo("Motivo inexistente");
        motivoInexistente.setTipo(Tipo.BLOQUEO);
        assertFalse(historialReportes.validarMotivoSeleccionado(motivoInexistente));
    }

    @Test
    public void testValidarFiltros() {
        MotivoDTO motivo = new MotivoDTO();
        motivo.setMotivo("Conducta inapropiada con clientes");
        motivo.setTipo(Tipo.BLOQUEO);

        FiltrosDTO filtros = new FiltrosDTO();
        filtros.setFechaDesde(LocalDateTime.now().minusDays(5));
        filtros.setFechaHasta(LocalDateTime.now());
        filtros.setMotivo(motivo);
        filtros.setNumBloqueos(1);

        assertTrue(historialReportes.validarFiltros(filtros));
        assertTrue(historialReportes.validarFiltros(null));
    }

    @Test
    public void testValidarFiltrosInvalidos() {
        FiltrosDTO filtrosFechasInvalidas = new FiltrosDTO();
        filtrosFechasInvalidas.setFechaDesde(LocalDateTime.now());
        filtrosFechasInvalidas.setFechaHasta(LocalDateTime.now().minusDays(1));
        assertFalse(historialReportes.validarFiltros(filtrosFechasInvalidas));

        FiltrosDTO filtrosFechaFutura = new FiltrosDTO();
        filtrosFechaFutura.setFechaDesde(LocalDateTime.now().minusDays(1));
        filtrosFechaFutura.setFechaHasta(LocalDateTime.now().plusDays(1));
        assertFalse(historialReportes.validarFiltros(filtrosFechaFutura));

        FiltrosDTO filtrosNumNegativo = new FiltrosDTO();
        filtrosNumNegativo.setNumBloqueos(-1);
        assertFalse(historialReportes.validarFiltros(filtrosNumNegativo));
    }

    @Test
    public void testConsultarHistorialBloqueos() {
        assertDoesNotThrow(() -> {
            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now());
            filtros.setMotivo(motivo);

            List<ReporteBloqueoDTO> reportes = historialReportes.consultarHistorialBloqueos(filtros);

            assertNotNull(reportes);

            for (ReporteBloqueoDTO reporte : reportes) {
                assertNotNull(reporte);
                assertNotNull(reporte.getIdReporteBloqueo());
                assertNotNull(reporte.getMotivo());
                assertNotNull(reporte.getFechaHora());
            }
        });
    }

    @Test
    public void testConsultarHistorialDesbloqueos() {
        assertDoesNotThrow(() -> {
            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Aclaración aprobada");
            motivo.setTipo(Tipo.DESBLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now());
            filtros.setMotivo(motivo);

            List<ReporteDesbloqueoDTO> reportes = historialReportes.consultarHistorialDesbloqueos(filtros);

            assertNotNull(reportes);

            for (ReporteDesbloqueoDTO reporte : reportes) {
                assertNotNull(reporte);
                assertNotNull(reporte.getIdReporteDesbloqueos());
                assertNotNull(reporte.getMotivo());
                assertNotNull(reporte.getFechaHora());
            }
        });
    }

    @Test
    public void testConsultarHistorialBloqueosConFiltrosInvalidos() {
        assertThrows(NegocioException.class, () -> {
            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now());
            filtros.setFechaHasta(LocalDateTime.now().minusDays(1));
            historialReportes.consultarHistorialBloqueos(filtros);
        });
    }

    @Test
    public void testConsultarHistorialDesbloqueosConFiltrosInvalidos() {
        assertThrows(NegocioException.class, () -> {
            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(1));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            historialReportes.consultarHistorialDesbloqueos(filtros);
        });
    }
}
