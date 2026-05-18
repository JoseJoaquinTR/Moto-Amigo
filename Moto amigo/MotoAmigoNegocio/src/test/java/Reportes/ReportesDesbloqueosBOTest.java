/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Reportes;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import enums.Tipo;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesDesbloqueosBOTest {

    private final IReportesDesbloqueosBO reportesDesbloqueosBO
            = ReportesDesbloqueosBO.getInstancia();

    @Test
    public void testGuardarReporteDesbloqueo() {

        assertDoesNotThrow(() -> {

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Pago de adeudo realizado");
            motivo.setTipo(Tipo.DESBLOQUEO);

            NuevoReporteDesbloqueoDTO dto
                    = new NuevoReporteDesbloqueoDTO();

            dto.setMotivo(motivo);
            dto.setDetalles("Prueba unitaria de desbloqueo.");
            dto.setFechaHora(LocalDateTime.now());
            

            ReporteDesbloqueoDTO reporteGuardado
                    = reportesDesbloqueosBO.guardarReporteDesbloqueo(dto);

            assertNotNull(reporteGuardado);
            assertNotNull(reporteGuardado.getIdReporteDesbloqueos());
            assertNotNull(reporteGuardado.getMotivo());

            assertEquals(
                    "Pago de adeudo realizado",
                    reporteGuardado.getMotivo().getMotivo()
            );

            assertEquals(
                    "Prueba unitaria de desbloqueo.",
                    reporteGuardado.getDetalles()
            );

            
        });
    }

    @Test
    public void testConsultarReportesDesbloqueos() {

        assertDoesNotThrow(() -> {

            List<ReporteDesbloqueoDTO> reportes
                    = reportesDesbloqueosBO.consultarReportesDesbloqueos();

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
    public void testConsultarReportesDesbloqueosConFiltros() {

        assertDoesNotThrow(() -> {

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Pago de adeudo realizado");
            motivo.setTipo(Tipo.DESBLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();

            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);

            List<ReporteDesbloqueoDTO> reportes
                    = reportesDesbloqueosBO.consultarReportesDesbloqueos(filtros);

            assertNotNull(reportes);

            for (ReporteDesbloqueoDTO reporte : reportes) {

                assertNotNull(reporte);
                assertNotNull(reporte.getIdReporteDesbloqueos());
                assertNotNull(reporte.getMotivo());

                assertEquals(
                        "Pago de adeudo realizado",
                        reporte.getMotivo().getMotivo()
                );
            }
        });
    }

    @Test
    public void testConsultarReportesDesbloqueoParaPDF() {

        assertDoesNotThrow(() -> {

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Pago de adeudo realizado");
            motivo.setTipo(Tipo.DESBLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();

            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);

            List<InformacionReporteDesbloqueoDTO> reportesPDF
                    = reportesDesbloqueosBO.consultarReportesDesbloqueoParaPDF(filtros);

            assertNotNull(reportesPDF);

            for (InformacionReporteDesbloqueoDTO reporte : reportesPDF) {

                assertNotNull(reporte);
                assertNotNull(reporte.getMotivo());
                assertNotNull(reporte.getFechaHora());

                assertTrue(
                        reporte.getNumCuentasDesbloqueadas() >= 0
                );
            }
        });
    }
}
