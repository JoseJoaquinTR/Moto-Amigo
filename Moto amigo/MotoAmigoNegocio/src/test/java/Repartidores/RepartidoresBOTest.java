/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package Repartidores;

import com.mycompany.motoamigodto.RepartidorDTO;
import enums.Estado;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class RepartidoresBOTest {

     private final IRepartidoresBO repartidorBO = RepartidoresBO.getInstancia();

    @Test
    public void testObtenerRepartidoresActivos() {

        assertDoesNotThrow(() -> {

            List<RepartidorDTO> repartidores =
                    repartidorBO.obtenerRepartidoresActivos();

            assertNotNull(repartidores);

            for (RepartidorDTO repartidor : repartidores) {
                assertNotNull(repartidor);
                assertNotNull(repartidor.getId());
                assertNotNull(repartidor.getNombre());
                assertEquals(Estado.ACTIVO, repartidor.getEstado());
            }
        });
    }

    @Test
    public void testObtenerRepartidores() {

        assertDoesNotThrow(() -> {

            List<RepartidorDTO> repartidores =
                    repartidorBO.obtenerRepartidores();

            assertNotNull(repartidores);

            for (RepartidorDTO repartidor : repartidores) {
                assertNotNull(repartidor);
                assertNotNull(repartidor.getId());
                assertNotNull(repartidor.getNombre());
            }
        });
    }

    @Test
    public void testBuscarRepartidoresPorNombre() {

        assertDoesNotThrow(() -> {

            List<RepartidorDTO> repartidores =
                    repartidorBO.buscarRepartidoresPorNombre("a");

            assertNotNull(repartidores);

            for (RepartidorDTO repartidor : repartidores) {
                assertNotNull(repartidor);
                assertNotNull(repartidor.getId());
                assertNotNull(repartidor.getNombre());
                assertTrue(
                        repartidor.getNombre().toLowerCase().contains("a")
                );
            }
        });
    }

    @Test
    public void testConsultarRepartidorPorId() {

        assertDoesNotThrow(() -> {

            List<RepartidorDTO> repartidores =
                    repartidorBO.obtenerRepartidores();

            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());

            RepartidorDTO repartidorBase = repartidores.get(0);

            RepartidorDTO repartidorConsultado =
                    repartidorBO.consultarRepartidorPorId(repartidorBase.getId());

            assertNotNull(repartidorConsultado);
            assertEquals(repartidorBase.getId(), repartidorConsultado.getId());
            assertEquals(repartidorBase.getNombre(), repartidorConsultado.getNombre());
        });
    }

    @Test
    public void testCambiarEstadoRepartidor() {

        assertDoesNotThrow(() -> {

            List<RepartidorDTO> repartidores =
                    repartidorBO.obtenerRepartidores();

            assertNotNull(repartidores);
            assertFalse(repartidores.isEmpty());

            RepartidorDTO repartidorBase = repartidores.get(0);

            RepartidorDTO repartidorActualizado =
                    repartidorBO.cambiarEstadoRepartidor(
                            repartidorBase.getId(),
                            Estado.BLOQUEADO
                    );

            assertNotNull(repartidorActualizado);
            assertEquals(repartidorBase.getId(), repartidorActualizado.getId());
            assertEquals(Estado.BLOQUEADO, repartidorActualizado.getEstado());

            repartidorBO.cambiarEstadoRepartidor(
                    repartidorBase.getId(),
                    repartidorBase.getEstado()
            );
        });
    }
}