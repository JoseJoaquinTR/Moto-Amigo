/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Reportes;

import com.mycompany.bloqueorepartidores.MotivoDTO;
import enums.Tipo;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class MotivosBOTest {

    private final IMotivosBO motivosBO= MotivosBO.getInstancia();

    @Test
    public void testObtenerMotivosBloqueo() {

        assertDoesNotThrow(() -> {

            List<MotivoDTO> motivos
                    = motivosBO.obetnerMotivos(Tipo.BLOQUEO);

            assertNotNull(motivos);
            assertFalse(motivos.isEmpty());

            for (MotivoDTO motivo : motivos) {

                assertNotNull(motivo);
                assertNotNull(motivo.getMotivo());

                assertEquals(
                        Tipo.BLOQUEO,
                        motivo.getTipo()
                );
            }
        });
    }

    @Test
    public void testObtenerMotivosDesbloqueo() {

        assertDoesNotThrow(() -> {

            List<MotivoDTO> motivos
                    = motivosBO.obetnerMotivos(Tipo.DESBLOQUEO);

            assertNotNull(motivos);
            assertFalse(motivos.isEmpty());

            for (MotivoDTO motivo : motivos) {

                assertNotNull(motivo);
                assertNotNull(motivo.getMotivo());

                assertEquals(
                        Tipo.DESBLOQUEO,
                        motivo.getTipo()
                );
            }
        });
    }
}
