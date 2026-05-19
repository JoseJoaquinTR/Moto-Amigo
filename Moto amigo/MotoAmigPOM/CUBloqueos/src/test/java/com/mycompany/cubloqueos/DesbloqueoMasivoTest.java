/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.cubloqueos;

import Repartidores.IRepartidoresBO;
import Repartidores.RepartidoresBO;
import Reportes.IMotivosBO;
import Reportes.IReportesBloqueoBO;
import Reportes.MotivosBO;
import Reportes.ReportesBloqueoBO;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.Estado;
import enums.Tipo;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class DesbloqueoMasivoTest {

    private final IDesbloqueoMasivo desbloqueoMasivo = DesbloqueoMasivo.getInstancia();
    private final IRepartidoresBO repartidoresBO = RepartidoresBO.getInstancia();
    private final IMotivosBO motivosBO = MotivosBO.getInstancia();

    @Test
    public void testValidarFiltros() {

        FiltrosDTO filtros = new FiltrosDTO();
        filtros.setNumBloqueos(1);

        assertTrue(desbloqueoMasivo.validarFiltros(filtros));
        assertFalse(desbloqueoMasivo.validarFiltros(null));
    }

    @Test
    public void testValidarFechas() {

        LocalDateTime ahora = LocalDateTime.now();

        assertTrue(desbloqueoMasivo.validarFechas(ahora.minusDays(5), ahora));
        assertFalse(desbloqueoMasivo.validarFechas(ahora, ahora.minusDays(5)));
        assertFalse(desbloqueoMasivo.validarFechas(ahora.minusDays(5), ahora.plusDays(1)));
        assertTrue(desbloqueoMasivo.validarFechas(null, ahora));
        assertTrue(desbloqueoMasivo.validarFechas(ahora.minusDays(5), null));
    }

    @Test
    public void testValidarMotivo() {

        assertDoesNotThrow(() -> {

             List<MotivoDTO> motivos = motivosBO.obetnerMotivos(Tipo.DESBLOQUEO);
             MotivoDTO motivo = motivos.get(0);
             
            assertTrue(desbloqueoMasivo.validarMotivo(motivo));

            MotivoDTO motivoInvalido = new MotivoDTO();
            motivoInvalido.setMotivo("");
            motivoInvalido.setTipo(Tipo.DESBLOQUEO);

            assertFalse(desbloqueoMasivo.validarMotivo(motivoInvalido));
        });
    }

    @Test
    public void testObtenerRepartidores() {

        assertDoesNotThrow(() -> {

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setNumBloqueos(1);

            List<RepartidorDTO> repartidores = desbloqueoMasivo.obtenerRepartidores(filtros);

            assertNotNull(repartidores);
            assertTrue(repartidores.size() > 0);
        });
    }

@Test
public void testDesbloqueoMasivo() {

    assertDoesNotThrow(() -> {

        IRepartidoresBO repartidoresBO = RepartidoresBO.getInstancia();
        IReportesBloqueoBO reportesBloqueoBO = ReportesBloqueoBO.getInstancia();

        MotivoDTO motivoBloqueo = new MotivoDTO();
        motivoBloqueo.setMotivo("Conducta inapropiada con clientes");
        motivoBloqueo.setTipo(Tipo.BLOQUEO);

        MotivoDTO motivoDesbloqueo = new MotivoDTO();
        motivoDesbloqueo.setMotivo("Aclaración aprobada");
        motivoDesbloqueo.setTipo(Tipo.DESBLOQUEO);

        List<RepartidorDTO> repartidores = repartidoresBO.obtenerRepartidores();
        assertNotNull(repartidores);
        assertTrue(repartidores.size() >= 2);

        RepartidorDTO repartidor1 = repartidores.get(0);
        RepartidorDTO repartidor2 = repartidores.get(1);

        repartidoresBO.cambiarEstadoRepartidor(repartidor1.getId(), Estado.BLOQUEADO);
        repartidoresBO.cambiarEstadoRepartidor(repartidor2.getId(), Estado.BLOQUEADO);

        repartidoresBO.incrementarNumeroBloqueos(repartidor1.getId());
        repartidoresBO.incrementarNumeroBloqueos(repartidor2.getId());

        repartidor1 = repartidoresBO.consultarRepartidorPorId(repartidor1.getId());
        repartidor2 = repartidoresBO.consultarRepartidorPorId(repartidor2.getId());

        NuevoReporteBloqueoDTO reporteBloqueo1 = new NuevoReporteBloqueoDTO();
        reporteBloqueo1.setRepartidor(repartidor1);
        reporteBloqueo1.setMotivo(motivoBloqueo);
        reporteBloqueo1.setDetalles("Reporte de bloqueo de prueba 1.");
        reporteBloqueo1.setFechaHora(LocalDateTime.now().minusDays(1));
        reporteBloqueo1.setImagenEvidencia(null);

        NuevoReporteBloqueoDTO reporteBloqueo2 = new NuevoReporteBloqueoDTO();
        reporteBloqueo2.setRepartidor(repartidor2);
        reporteBloqueo2.setMotivo(motivoBloqueo);
        reporteBloqueo2.setDetalles("Reporte de bloqueo de prueba 2.");
        reporteBloqueo2.setFechaHora(LocalDateTime.now().minusDays(1));
        reporteBloqueo2.setImagenEvidencia(null);

        reportesBloqueoBO.guardarReporteBloqueo(reporteBloqueo1);
        reportesBloqueoBO.guardarReporteBloqueo(reporteBloqueo2);

        FiltrosDTO filtros = new FiltrosDTO();
        filtros.setFechaDesde(LocalDateTime.now().minusDays(5));
        filtros.setFechaHasta(LocalDateTime.now());
        filtros.setMotivo(motivoBloqueo);
        filtros.setNumBloqueos(1);

        NuevoReporteDesbloqueoDTO dto = new NuevoReporteDesbloqueoDTO();
        dto.setMotivo(motivoDesbloqueo);
        dto.setDetalles("Prueba unitaria de desbloqueo masivo.");
        dto.setFechaHora(LocalDateTime.now());

        ReporteDesbloqueoDTO reporte = desbloqueoMasivo.desbloqueoMasivo(filtros, dto);

        assertNotNull(reporte);
        assertNotNull(reporte.getIdReporteDesbloqueos());
        assertNotNull(reporte.getMotivo());
        assertEquals(motivoDesbloqueo.getMotivo(), reporte.getMotivo().getMotivo());
        assertTrue(reporte.getNumRepartidoresDesbloqueados() >= 2);

        RepartidorDTO repartidor1Actualizado = repartidoresBO.consultarRepartidorPorId(repartidor1.getId());
        RepartidorDTO repartidor2Actualizado = repartidoresBO.consultarRepartidorPorId(repartidor2.getId());

        assertEquals(Estado.ACTIVO, repartidor1Actualizado.getEstado());
        assertEquals(Estado.ACTIVO, repartidor2Actualizado.getEstado());
    });
}

    @Test
    public void testDesbloqueoMasivoSinFiltros() {

        assertThrows(NegocioException.class, () -> {

            NuevoReporteDesbloqueoDTO dto
                    = new NuevoReporteDesbloqueoDTO();

            desbloqueoMasivo.desbloqueoMasivo(null, dto);
        });
    }
}
