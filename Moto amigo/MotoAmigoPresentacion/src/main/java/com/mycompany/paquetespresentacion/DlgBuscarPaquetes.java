/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.paquetespresentacion;

import Utilerias.utileriasBotones;
import Paquete.BuscarPaquete;
import Paquete.IBuscarPaquete;
import Paquete.PaqueteException;
import Utilerias.utileriaTablas;
import com.mycompany.paquetesdto.PaqueteDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author joset
 */
public class DlgBuscarPaquetes extends JDialog {

    private final IBuscarPaquete cuBuscarPaquete;
    private List<PaqueteDTO> paquetes;
    private PaqueteDTO seleccionado;

    public DlgBuscarPaquetes(Frame parent, boolean modal) {
        super(parent, modal);
        this.cuBuscarPaquete = new BuscarPaquete();
        initComponents();
        utileriaTablas.configurarTablaPaquetes(tblPaquetes);
        setLocationRelativeTo(parent);
        setTitle("MotoAmigo - Buscar Paquete");
        estilarComponentes();
        try {
            buscar();
        } catch (PaqueteException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Sin resultados",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * @return el paquete seleccionado por el usuario, o null si canceló.
     */
    public PaqueteDTO getPaqueteSeleccionado() {
        return seleccionado;
    }

    private void estilarComponentes() {
        utileriasBotones.panelRedondeado(panelTarjeta, Color.WHITE, 18);
        utileriasBotones.btnRedondeado(btnBuscar, "naranja", 14);
        utileriasBotones.btnRedondeado(btnSeleccionar, "negro", 16);
        utileriasBotones.btnRedondeado(btnCancelar, "blanco", 16);

        btnBuscar.setForeground(Color.WHITE);
        btnSeleccionar.setForeground(Color.WHITE);
        btnCancelar.setForeground(new Color(60, 60, 60));

        Font fontBoton = new Font("Segoe UI", Font.BOLD, 14);
        btnSeleccionar.setFont(fontBoton);
        btnCancelar.setFont(fontBoton);
    }

    private void buscar() throws PaqueteException {
        String criterio = txtBuscar.getText().trim();
        paquetes = cuBuscarPaquete.buscar(criterio);
        utileriaTablas.cargarPaquetes(tblPaquetes, paquetes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTarjeta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        scrollResultados = new javax.swing.JScrollPane();
        tblPaquetes = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Buscar Paquete");
        setMinimumSize(new java.awt.Dimension(661, 550));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        panelTarjeta.setMaximumSize(new java.awt.Dimension(661, 510));
        panelTarjeta.setMinimumSize(new java.awt.Dimension(661, 510));
        panelTarjeta.setName(""); // NOI18N
        panelTarjeta.setPreferredSize(new java.awt.Dimension(661, 510));
        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Buscar Paquete");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 20, 250, 30));

        lblDescripcion.setText("Selecciona un paquete de la lista.");
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 56, 400, 20));

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 96, 455, 35));

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 96, 130, 35));

        tblPaquetes.setRowHeight(28);
        tblPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPaquetesMouseClicked(evt);
            }
        });
        scrollResultados.setViewportView(tblPaquetes);

        panelTarjeta.add(scrollResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 151, 610, 280));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 451, 150, 45));

        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setBorderPainted(false);
        btnSeleccionar.setFocusPainted(false);
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 451, 200, 45));

        getContentPane().add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPaquetesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPaquetesMouseClicked
        if (evt.getClickCount() == 2) {
            seleccionarPaquete();
        }
    }//GEN-LAST:event_tblPaquetesMouseClicked

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        seleccionarPaquete();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            buscar();
        } catch (PaqueteException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Sin resultados",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        seleccionado = null;
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void seleccionarPaquete() {
        int filaSeleccionada = tblPaquetes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un paquete de la tabla.",
                    "Paquete no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (paquetes == null || paquetes.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay paquetes cargados para seleccionar.",
                    "Sin resultados",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int filaModelo = tblPaquetes.convertRowIndexToModel(filaSeleccionada);

        if (filaModelo < 0 || filaModelo >= paquetes.size()) {
            JOptionPane.showMessageDialog(
                    this,
                    "La selección no es válida.",
                    "Error de selección",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        seleccionado = paquetes.get(filaModelo);

        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JScrollPane scrollResultados;
    private javax.swing.JTable tblPaquetes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
