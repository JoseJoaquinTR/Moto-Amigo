package com.mycompany.motoamigopresentacion.controladores;
 
import com.mycompany.cuincidente.IRegistrarIncidente;
import com.mycompany.cuincidente.RegistrarIncidente;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.Observers.EventoEntrega;
import com.mycompany.motoamigonegocio.Observers.GestorNotificacionesEntrega;
import com.mycompany.motoamigopresentacion.FrmEstadoReporteRepartidor;
import com.mycompany.motoamigopresentacion.FrmFormularioIncidenteRepartidor;
import com.mycompany.repartidorespresentacion.FrmSeguimientoTiempoRealRepartidor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
/**
 * Controlador del flujo de registro de incidentes durante una entrega. 
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
    }
 
    /**
     * Obtiene la instancia única del controlador.
     *
     * @return instancia de ControlRegistrarIncidente.
     */
    public static ControlRegistrarIncidente getInstance() {
        if (instancia == null) {
            instancia = new ControlRegistrarIncidente();
        }
        return instancia;
    }
 
    
    /**
     * Establece la entrega sobre la que el repartidor podrá registrar
     * incidentes. Debe llamarse desde el flujo principal antes de abrir el
     * formulario de incidente.
     *
     * @param entrega entrega activa del repartidor.
     */
    public void setEntregaActual(EntregaDTO entrega) {
        this.entregaActual = entrega;
    }
 
    public EntregaDTO getEntregaActual() {
        return entregaActual;
    }
 
    /**
     * Abre el formulario de registro de incidente y oculta la pantalla de
     * seguimiento.
     *
     * @param frmMapa pantalla de seguimiento desde la que se invoca.
     */
    public void irAFormularioIncidente(FrmSeguimientoTiempoRealRepartidor frmMapa) {
        if (entregaActual == null) {
            JOptionPane.showMessageDialog(frmMapa,
                    "No hay una entrega activa para registrar un incidente.",
                    "Sin entrega",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
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
            JOptionPane.showMessageDialog(frmFormulario,
                    "Error: Debes seleccionar un incidente",
                    "Datos Inválidos",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        if (entregaActual == null) {
            JOptionPane.showMessageDialog(frmFormulario,
                    "No hay una entrega activa.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        incidenteNuevo = new IncidenteDTO(tipoIncidenteSeleccionado, "");
 
        try {
            cuRegistrar.registrarIncidente(incidenteNuevo);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlRegistrarIncidente.class.getName())
                    .log(Level.SEVERE, "Error registrando incidente", ex);
            JOptionPane.showMessageDialog(frmFormulario,
                    "No se pudo registrar el incidente: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        // Solo cambia el estado de la entrega activa, sin reconstruir el DTO
        entregaActual.setEstadoEntrega("NO COMPLETADA");
 
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
        if (entregaActual == null) {
            return;
        }
        entregaActual.setEstadoEntrega("DISPONIBLE");
        GestorNotificacionesEntrega.getInstance()
                .notificar(EventoEntrega.PEDIDO_CANCELADO, entregaActual);
    }
}