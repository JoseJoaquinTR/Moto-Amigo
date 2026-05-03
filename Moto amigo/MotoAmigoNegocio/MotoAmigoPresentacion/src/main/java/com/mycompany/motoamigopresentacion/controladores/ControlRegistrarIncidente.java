package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.cuincidente.IRegistrarIncidente;
import com.mycompany.cuincidente.RegistrarIncidente;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.Observer.EventoEntrega;
import com.mycompany.motoamigonegocio.Observer.GestorNotificacionesEntrega;
import com.mycompany.motoamigopresentacion.FrmEstadoReporteRepartidor;
import com.mycompany.motoamigopresentacion.FrmFormularioIncidenteRepartidor;
import com.mycompany.motoamigopresentacion.FrmSeguimientoTiempoRealRepartidor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Controlador del flujo de registro de incidentes durante una entrega. Coordina
 * la captura del incidente, su persistencia mediante caso de uso y la
 * notificación a las pantallas suscritas vía Observer.
 *
 * @author joset
 */
public class ControlRegistrarIncidente {

    private static ControlRegistrarIncidente instancia;

    private final IRegistrarIncidente cuRegistrar;
    private EntregaDTO entregaActual;
    private IncidenteDTO incidenteNuevo;
    private FrmFormularioIncidenteRepartidor frmFormulario;
    private FrmEstadoReporteRepartidor frmEstado;

    private ControlRegistrarIncidente() {
        this.cuRegistrar = RegistrarIncidente.crear();
        this.entregaActual = new EntregaDTO("", "Polanco 45", "Caja", "DISPONIBLE", 0, 0);
    }

    /**
     * Obtiene la instancia única del controlador.
     *
     * @return instancia de ControlRegistrarIncidente.
     */
    public static synchronized ControlRegistrarIncidente getInstance() {
        if (instancia == null) {
            instancia = new ControlRegistrarIncidente();
        }
        return instancia;
    }

    /**
     * Abre el formulario de registro de incidente y oculta la pantalla de
     * seguimiento.
     *
     * @param frmMapa pantalla de seguimiento desde la que se invoca.
     */
    public void irAFormularioIncidente(FrmSeguimientoTiempoRealRepartidor frmMapa) {
        frmFormulario = new FrmFormularioIncidenteRepartidor(frmMapa);
        frmFormulario.setVisible(true);
        frmMapa.setVisible(false);
    }

    /**
     * Registra un incidente del tipo indicado, persistiéndolo vía caso de uso
     * y notificando a las pantallas suscritas. Si ocurre un error de negocio,
     * se notifica al usuario y no se cambia el estado de la entrega.
     *
     * @param tipoIncidenteSeleccionado tipo de incidente seleccionado por el
     * repartidor.
     */
    public void registrarIncidente(String tipoIncidenteSeleccionado) {
        if (tipoIncidenteSeleccionado == null || tipoIncidenteSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(frmFormulario, "Error: Debes seleccionar un incidente", "Datos Inválidos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        incidenteNuevo = new IncidenteDTO(tipoIncidenteSeleccionado, "");

        try {
            cuRegistrar.registrarIncidente(incidenteNuevo);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlRegistrarIncidente.class.getName()).log(Level.SEVERE, "Error registrando incidente", ex);
            JOptionPane.showMessageDialog(frmFormulario,
                    "No se pudo registrar el incidente: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        entregaActual = new EntregaDTO(
                entregaActual.getDireccionOrigen(),
                entregaActual.getDireccionDestino(),
                entregaActual.getTipoPaquete(),
                "NO COMPLETADA",
                45,
                150
        );

        GestorNotificacionesEntrega.getInstance().notificar(
                EventoEntrega.PEDIDO_NO_COMPLETADO,
                incidenteNuevo
        );

        if (frmFormulario != null) {
            frmFormulario.dispose();
        }

        frmEstado = new FrmEstadoReporteRepartidor(this, entregaActual, incidenteNuevo);
        frmEstado.setVisible(true);
    }

    /**
     * Cancela el pedido cuando el repartidor no pudo recogerlo. El pedido debe
     * volver a estado DISPONIBLE para todos los usuarios. Se notifica por
     * Observer para que las pantallas se actualicen.
     */
    public void cancelarPedidoPorNoRecoleccion() {
        entregaActual = new EntregaDTO(
                entregaActual.getDireccionOrigen(),
                entregaActual.getDireccionDestino(),
                entregaActual.getTipoPaquete(),
                "DISPONIBLE",
                0,
                0
        );
        GestorNotificacionesEntrega.getInstance().notificar(EventoEntrega.PEDIDO_CANCELADO, entregaActual);
    }
}
