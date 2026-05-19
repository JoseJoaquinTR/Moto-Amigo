/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.cubloqueos;

import Repartidores.IRepartidoresBO;
import Repartidores.RepartidoresBO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.IMotivosBO;
import com.mycompany.motoamigonegocio.MotivosBO;
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
public class BloquearRepartidorTest {

    private final IBloquearRepartidor bloquearRepartidor = BloquearRepartidor.getInstancia();
    private final IRepartidoresBO repartidoresBO = RepartidoresBO.getInstancia();
    private final IMotivosBO motivosBO = MotivosBO.getInstancia();

    @Test
    public void testValidarRepartidorSeleccionado() {
        assertDoesNotThrow(() -> {
            List<RepartidorDTO> repartidores = repartidoresBO.obtenerRepartidores();
            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());

            RepartidorDTO repartidor = repartidores.get(0);
            assertTrue(bloquearRepartidor.validarRepartidorSeleccionado(repartidor));
            assertFalse(bloquearRepartidor.validarRepartidorSeleccionado(null));

            RepartidorDTO repartidorSinId = new RepartidorDTO();
            repartidorSinId.setId("");
            assertFalse(bloquearRepartidor.validarRepartidorSeleccionado(repartidorSinId));
        });
    }

    @Test
    public void testValidarMotivo() {

        assertDoesNotThrow(() -> {

            List<MotivoDTO> motivos = motivosBO.obetnerMotivos(Tipo.BLOQUEO);
            MotivoDTO motivo = motivos.get(0);

            assertTrue(bloquearRepartidor.validarMotivoSeleccionado(motivo));

            MotivoDTO motivoInvalido = new MotivoDTO();
            motivoInvalido.setMotivo("");
            motivoInvalido.setTipo(Tipo.BLOQUEO);

            assertFalse(bloquearRepartidor.validarMotivoSeleccionado(motivoInvalido));
        });
    }

    @Test
    public void testBloquearRepartidor() {
        assertDoesNotThrow(() -> {
            List<RepartidorDTO> repartidores = repartidoresBO.obtenerRepartidores();
            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());

            RepartidorDTO repartidor = repartidores.get(0);
            int bloqueosAntes = repartidor.getNumBloqueos();

            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
            dto.setRepartidor(repartidor);
            dto.setMotivo(motivo);
            dto.setDetalles("Prueba unitaria desde CU de bloqueo.");
            dto.setFechaHora(LocalDateTime.now());
            dto.setImagenEvidencia(null);

            ReporteBloqueoDTO reporte = bloquearRepartidor.bloquearRepartidor(dto);

            assertNotNull(reporte);
            assertNotNull(reporte.getIdReporteBloqueo());
            assertNotNull(reporte.getRepartidor());
            assertNotNull(reporte.getMotivo());
            assertEquals(Estado.BLOQUEADO, reporte.getRepartidor().getEstado());
            assertEquals(bloqueosAntes + 1, reporte.getRepartidor().getNumBloqueos());
            assertEquals("Conducta inapropiada con clientes", reporte.getMotivo().getMotivo());
        });
    }

    @Test
    public void testBloquearRepartidorSinDTO() {
        assertThrows(NegocioException.class, () -> {
            bloquearRepartidor.bloquearRepartidor(null);
        });
    }

    @Test
    public void testBloquearRepartidorSinRepartidor() {
        assertThrows(NegocioException.class, () -> {
            MotivoDTO motivo = new MotivoDTO();
            motivo.setMotivo("Conducta inapropiada con clientes");
            motivo.setTipo(Tipo.BLOQUEO);

            NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
            dto.setRepartidor(null);
            dto.setMotivo(motivo);
            dto.setDetalles("Prueba sin repartidor.");
            dto.setFechaHora(LocalDateTime.now());

            bloquearRepartidor.bloquearRepartidor(dto);
        });
    }

    @Test
    public void testBloquearRepartidorSinMotivo() {
        assertThrows(NegocioException.class, () -> {
            List<RepartidorDTO> repartidores = repartidoresBO.obtenerRepartidores();
            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());

            NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
            dto.setRepartidor(repartidores.get(0));
            dto.setMotivo(null);
            dto.setDetalles("Prueba sin motivo.");
            dto.setFechaHora(LocalDateTime.now());

            bloquearRepartidor.bloquearRepartidor(dto);
        });
    }

}
