/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Reportes;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import enums.Estado;
import enums.Tipo;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesBloqueoBOTest {

    private final IReportesBloqueoBO reportesBloqueoBO
            = ReportesBloqueoBO.getInstancia();

    @Test
    public void testGuardarReporteBloqueo() {

        assertDoesNotThrow(() -> {

            RepartidorDTO repartidor = new RepartidorDTO();
            repartidor.setId(new ObjectId().toHexString());
            repartidor.setNombre("Juan Pérez");
            repartidor.setTelefono("6441234567");
            repartidor.setCorreo("juan@test.com");
            repartidor.setVehiculo("Moto");
            repartidor.setEstado(Estado.BLOQUEADO);
            repartidor.setNumBloqueos(1);

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
            dto.setRepartidor(repartidor);
            dto.setMotivo(motivo);
            dto.setDetalles("Prueba unitaria de bloqueo.");
            dto.setFechaHora(LocalDateTime.now());
            dto.setImagenEvidencia(null);

            ReporteBloqueoDTO reporteGuardado
                    = reportesBloqueoBO.guardarReporteBloqueo(dto);

            assertNotNull(reporteGuardado);
            assertNotNull(reporteGuardado.getIdReporteBloqueo());
            assertNotNull(reporteGuardado.getRepartidor());
            assertNotNull(reporteGuardado.getMotivo());
            assertEquals(
                    "Conducta inapropiada con clientes",
                    reporteGuardado.getMotivo().getMotivo()
            );
            assertEquals(
                    "Prueba unitaria de bloqueo.",
                    reporteGuardado.getDetalles()
            );
        });
    }

    @Test
    public void testConsultarReportesBloqueos() {

        assertDoesNotThrow(() -> {

            List<ReporteBloqueoDTO> reportes = reportesBloqueoBO.consultarReportesBloqueos();

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
    public void testConsultarReportesBloqueosConFiltros() {

        assertDoesNotThrow(() -> {

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);

            List<ReporteBloqueoDTO> reportes
                    = reportesBloqueoBO.consultarReportesBloqueos(filtros);

            assertNotNull(reportes);

            for (ReporteBloqueoDTO reporte : reportes) {
                assertNotNull(reporte);
                assertNotNull(reporte.getIdReporteBloqueo());
                assertNotNull(reporte.getMotivo());
                assertEquals(
                        "Conducta inapropiada con clientes",
                        reporte.getMotivo().getMotivo()
                );
            }
        });
    }

    @Test
    public void testObtenerRepartidoresParaDesbloqueoMasivo() {

        assertDoesNotThrow(() -> {

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);
            filtros.setNumBloqueos(1);

            List<RepartidorDTO> repartidores
                    = reportesBloqueoBO.obtenerRepartidoresParaDesbloqueoMasivo(filtros);

            assertNotNull(repartidores);

            for (RepartidorDTO repartidor : repartidores) {
                assertNotNull(repartidor);
                assertNotNull(repartidor.getId());
                assertNotNull(repartidor.getNombre());
                assertEquals(Estado.BLOQUEADO, repartidor.getEstado());
                assertTrue(repartidor.getNumBloqueos() >= 1);
            }
        });
    }

    @Test
    public void testConsultarReportesBloqueoParaPDF() {

        assertDoesNotThrow(() -> {

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);

            List<InformacionReporteBloqueoDTO> reportesPDF
                    = reportesBloqueoBO.consultarReportesBloqueoParaPDF(filtros);

            assertNotNull(reportesPDF);

            for (InformacionReporteBloqueoDTO reporte : reportesPDF) {
                assertNotNull(reporte);
                assertNotNull(reporte.getNombreRepartidor());
                assertNotNull(reporte.getMotivo());
                assertNotNull(reporte.getFechaHora());
            }
        });
    }
}
