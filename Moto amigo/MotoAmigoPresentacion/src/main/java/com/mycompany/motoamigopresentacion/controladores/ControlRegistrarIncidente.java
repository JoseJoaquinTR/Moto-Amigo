/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigonegocio.Observer.GestorNotificacionesEntrega;
import com.mycompany.motoamigopresentacion.FrmEstadoReporteRepartidor;
import com.mycompany.motoamigopresentacion.FrmFormularioIncidenteRepartidor;
import com.mycompany.motoamigopresentacion.FrmSeguimientoTiempoRealRepartidor;

import javax.swing.JOptionPane;

public class ControlRegistrarIncidente {

    private EntregaDTO entregaActual;
    private IncidenteDTO incidenteNuevo;
    private Long idEntregaActual = 101L;
    private FrmFormularioIncidenteRepartidor frmFormulario;
    private FrmEstadoReporteRepartidor frmEstado;

    private static ControlRegistrarIncidente instancia;

    private ControlRegistrarIncidente() {
        entregaActual = new EntregaDTO("", "Polanco 45", "Caja", "DISPONIBLE", 0, 0);

    }

    public static ControlRegistrarIncidente getInstance() {
        if (instancia == null) {
            instancia = new ControlRegistrarIncidente();
        }
        return instancia;
    }

    public void irAFormularioIncidente(FrmSeguimientoTiempoRealRepartidor frmMapa) {
        FrmFormularioIncidenteRepartidor frm = new FrmFormularioIncidenteRepartidor(frmMapa);
        frm.setVisible(true);
        frmMapa.setVisible(false);
    }

    public void registrarIncidente(String tipoIncidenteSeleccionado) {
        if (tipoIncidenteSeleccionado == null || tipoIncidenteSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(frmFormulario, "Error: Debes seleccionar un incidente", "Datos Inválidos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        incidenteNuevo = new IncidenteDTO(tipoIncidenteSeleccionado,"");

        entregaActual = new EntregaDTO(
            entregaActual.getDireccionOrigen(),
            entregaActual.getDireccionDestino(),
            entregaActual.getTipoPaquete(),
            "NO COMPLETADA",
            45,
            150
                
        );
        frmFormulario.dispose();

        frmEstado = new FrmEstadoReporteRepartidor(this, entregaActual, incidenteNuevo);
        frmEstado.setVisible(true);
    }

    /**
     * Cancela el pedido cuando el repartidor no pudo recogerlo. El pedido debe
     * volver a estado DISPONIBLE para todos los usuarios. TODO: Notificar via
     * observer a los repartidores disponibles cuando se implemente.
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
        GestorNotificacionesEntrega.getInstance().notificar("PEDIDO_CANCELADO", entregaActual);
        System.out.println("Pedido " + idEntregaActual + " cancelado y puesto como DISPONIBLE");
    }
}
