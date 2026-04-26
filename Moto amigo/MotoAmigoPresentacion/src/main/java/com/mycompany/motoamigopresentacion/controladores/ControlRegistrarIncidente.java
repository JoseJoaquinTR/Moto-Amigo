/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigopresentacion.FrmEstadoReporte;
import com.mycompany.motoamigopresentacion.FrmFormularioIncidente;
import com.mycompany.motoamigopresentacion.FrmSeguimientoEnTiempoReal;

import javax.swing.JOptionPane;

public class ControlRegistrarIncidente {
    
    private EntregaDTO entregaActual;
    private IncidenteDTO incidenteNuevo;

    private FrmFormularioIncidente frmFormulario;
    private FrmEstadoReporte frmEstado;

    private static ControlRegistrarIncidente instancia;

    private ControlRegistrarIncidente() {
        entregaActual = new EntregaDTO(101, "Polanco 45", "Caja", "DISPONIBLE");
        incidenteNuevo = new IncidenteDTO();
    }

    public static ControlRegistrarIncidente getInstance() {
        if (instancia == null) {
            instancia = new ControlRegistrarIncidente();
        }
        return instancia;
    }

    public void irAFormularioIncidente(FrmSeguimientoEnTiempoReal frmMapa) {
        FrmFormularioIncidente frm = new FrmFormularioIncidente(frmMapa);
        frm.setVisible(true);
        frmMapa.setVisible(false);
    }

    public void registrarIncidente(String tipoIncidenteSeleccionado) {
        if (tipoIncidenteSeleccionado == null || tipoIncidenteSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(frmFormulario, "Error: Debes seleccionar un incidente", "Datos Inválidos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        incidenteNuevo.setTipoIncidente(tipoIncidenteSeleccionado);
        incidenteNuevo.setIdEntregaAsociada(entregaActual.getIdEntrega());

        entregaActual.setEstadoEntrega("NO COMPLETADA");
        frmFormulario.dispose();

        frmEstado = new FrmEstadoReporte(this, entregaActual, incidenteNuevo);
        frmEstado.setVisible(true); 
    }

    public void volverAlInicio() {
        frmEstado.dispose();
        System.out.println("Regresando al menú principal...");
    }
}
