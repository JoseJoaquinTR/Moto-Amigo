/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Reportes;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.reportes.IReportesBloqueoBO;
import com.mycompany.reportes.ReportesBloqueoBO;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.InformacionReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.ManejadorConexiones;
import enums.Estado;
import enums.Tipo;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesBloqueoBOTest {

    private IReportesBloqueoBO reportesBloqueoBO;
    private MongoCollection<Repartidor> coleccionRepartidores;
    private MongoCollection<ReporteBloqueo> coleccionReportes;

    @BeforeEach
    public void setUp() {
        reportesBloqueoBO = ReportesBloqueoBO.getInstancia();

        MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
        coleccionRepartidores = bd.getCollection("repartidores", Repartidor.class);
        coleccionReportes = bd.getCollection("reportesBloqueo", ReporteBloqueo.class);

        coleccionRepartidores.deleteMany(new org.bson.Document());
        coleccionReportes.deleteMany(new org.bson.Document());
    }

    @Test
    public void testGuardarReporteBloqueo() {
        assertDoesNotThrow(() -> {
            RepartidorDTO repartidor = crearRepartidorDePrueba("Juan Pérez", "juan@test.com", Estado.BLOQUEADO, 1);

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
            dto.setRepartidor(repartidor);
            dto.setMotivo(motivo);
            dto.setDetalles("Prueba unitaria de bloqueo.");
            dto.setFechaHora(LocalDateTime.now());
            dto.setImagenEvidencia(null);

            ReporteBloqueoDTO reporteGuardado = reportesBloqueoBO.guardarReporteBloqueo(dto);

            assertNotNull(reporteGuardado);
            assertNotNull(reporteGuardado.getIdReporteBloqueo());
            assertNotNull(reporteGuardado.getRepartidor());
            assertEquals("Juan Pérez", reporteGuardado.getRepartidor().getNombre());
            assertNotNull(reporteGuardado.getMotivo());
            assertEquals("Conducta inapropiada con clientes", reporteGuardado.getMotivo().getMotivo());
            assertEquals("Prueba unitaria de bloqueo.", reporteGuardado.getDetalles());
        });
    }

    @Test
    public void testConsultarReportesBloqueos() {
        assertDoesNotThrow(() -> {
            RepartidorDTO repartidor = crearRepartidorDePrueba("Luis Reporte", "luis@test.com", Estado.BLOQUEADO, 1);
            guardarReporteBloqueoDePrueba(repartidor, "Conducta inapropiada con clientes");

            List<ReporteBloqueoDTO> reportes = reportesBloqueoBO.consultarReportesBloqueos();

            assertNotNull(reportes);
            assertFalse(reportes.isEmpty());

            for (ReporteBloqueoDTO reporte : reportes) {
                assertNotNull(reporte);
                assertNotNull(reporte.getIdReporteBloqueo());
                assertNotNull(reporte.getRepartidor());
                assertNotNull(reporte.getRepartidor().getNombre());
                assertNotNull(reporte.getMotivo());
                assertNotNull(reporte.getFechaHora());
            }
        });
    }

    @Test
    public void testConsultarReportesBloqueosConFiltros() {
        assertDoesNotThrow(() -> {
            RepartidorDTO repartidor = crearRepartidorDePrueba("Carlos Filtro", "carlos@test.com", Estado.BLOQUEADO, 1);
            guardarReporteBloqueoDePrueba(repartidor, "Conducta inapropiada con clientes");

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);

            List<ReporteBloqueoDTO> reportes = reportesBloqueoBO.consultarReportesBloqueos(filtros);

            assertNotNull(reportes);
            assertFalse(reportes.isEmpty());

            for (ReporteBloqueoDTO reporte : reportes) {
                assertNotNull(reporte);
                assertNotNull(reporte.getIdReporteBloqueo());
                assertNotNull(reporte.getMotivo());
                assertEquals("Conducta inapropiada con clientes", reporte.getMotivo().getMotivo());
            }
        });
    }

    @Test
    public void testObtenerRepartidoresParaDesbloqueoMasivo() {
        assertDoesNotThrow(() -> {
            RepartidorDTO repartidor = crearRepartidorDePrueba("Ana Bloqueada", "ana@test.com", Estado.BLOQUEADO, 2);
            guardarReporteBloqueoDePrueba(repartidor, "Conducta inapropiada con clientes");

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);
            filtros.setNumBloqueos(1);

            List<RepartidorDTO> repartidores = reportesBloqueoBO.obtenerRepartidoresParaDesbloqueoMasivo(filtros);

            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());

            for (RepartidorDTO rep : repartidores) {
                assertNotNull(rep);
                assertNotNull(rep.getId());
                assertNotNull(rep.getNombre());
                assertEquals(Estado.BLOQUEADO, rep.getEstado());
                assertTrue(rep.getNumBloqueos() >= 1);
            }
        });
    }

    @Test
    public void testConsultarReportesBloqueoParaPDF() {
        assertDoesNotThrow(() -> {
            RepartidorDTO repartidor = crearRepartidorDePrueba("Mario PDF", "mario@test.com", Estado.BLOQUEADO, 1);
            guardarReporteBloqueoDePrueba(repartidor, "Conducta inapropiada con clientes");

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            FiltrosDTO filtros = new FiltrosDTO();
            filtros.setFechaDesde(LocalDateTime.now().minusDays(30));
            filtros.setFechaHasta(LocalDateTime.now().plusDays(1));
            filtros.setMotivo(motivo);

            List<InformacionReporteBloqueoDTO> reportesPDF = reportesBloqueoBO.consultarReportesBloqueoParaPDF(filtros);

            assertNotNull(reportesPDF);
            assertFalse(reportesPDF.isEmpty());

            InformacionReporteBloqueoDTO reportePDF = reportesPDF.stream()
                    .filter(r -> "Mario PDF".equals(r.getNombreRepartidor()))
                    .findFirst()
                    .orElse(null);

            assertNotNull(reportePDF);
            assertEquals("Mario PDF", reportePDF.getNombreRepartidor());
            assertNotNull(reportePDF.getMotivo());
            assertNotNull(reportePDF.getFechaHora());
        });
    }

    private RepartidorDTO crearRepartidorDePrueba(String nombre, String correo, Estado estadoDTO, int numBloqueos) {
        com.mycompany.Entidades.Estado estadoEntidad = com.mycompany.Entidades.Estado.INACTIVO;

        if (estadoDTO == Estado.ACTIVO) {
            estadoEntidad = com.mycompany.Entidades.Estado.ACTIVO;
        } else if (estadoDTO == Estado.BLOQUEADO) {
            estadoEntidad = com.mycompany.Entidades.Estado.BLOQUEADO;
        }

        Repartidor repartidor = new Repartidor();
        repartidor.setNombre(nombre);
        repartidor.setTelefono("6441234567");
        repartidor.setCorreo(correo);
        repartidor.setVehiculo("Moto");
        repartidor.setEstado(estadoEntidad);
        repartidor.setNumBloqueos(numBloqueos);

        coleccionRepartidores.insertOne(repartidor);

        RepartidorDTO dto = new RepartidorDTO();
        dto.setId(repartidor.getIdRepartidor());
        dto.setNombre(repartidor.getNombre());
        dto.setTelefono(repartidor.getTelefono());
        dto.setCorreo(repartidor.getCorreo());
        dto.setVehiculo(repartidor.getVehiculo());
        dto.setEstado(estadoDTO);
        dto.setNumBloqueos(numBloqueos);

        return dto;
    }

    private void guardarReporteBloqueoDePrueba(RepartidorDTO repartidor, String motivoTexto) throws NegocioException {
        MotivoDTO motivo = new MotivoDTO();
        motivo.setMotivo(motivoTexto);
        motivo.setTipo(Tipo.BLOQUEO);

        NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
        dto.setRepartidor(repartidor);
        dto.setMotivo(motivo);
        dto.setDetalles("Reporte de bloqueo de prueba.");
        dto.setFechaHora(LocalDateTime.now());
        dto.setImagenEvidencia(null);

        reportesBloqueoBO.guardarReporteBloqueo(dto);
    }
}
