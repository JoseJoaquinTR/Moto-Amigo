/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motoamigopresentacion;

import Utilerias.utileriaHeaderSidebar;
import Utilerias.utileriasBotones;
import com.mycompany.motoamigodto.EliminarPaqueteDTO;
import com.mycompany.motoamigodto.PaqueteDTO;
import Paquete.EliminarPaquete;
import Paquete.IEliminarPaquete;
import Paquete.PaqueteException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Sub-pantalla del CU PaqueteProducto: confirmación para eliminar un paquete.
 *
 * @author joset
 */
public class FrmEliminarPaquete extends JFrame {

    private final IEliminarPaquete cuEliminarPaquete;
    private String idPaqueteEliminar;

    public FrmEliminarPaquete(PaqueteDTO paquete) {
        this.cuEliminarPaquete = new EliminarPaquete();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MotoAmigo - Eliminar Paquete");
        utileriaHeaderSidebar.aplicarHeaderYSidebar(jPanel1, "PAQUETES");
        estilarComponentes();
        cargarPaquete(paquete);
    }

    /**
     * Carga los datos del paquete a confirmar.
     * @param paquete paquete a eliminar
     */
    public void cargarPaquete(PaqueteDTO paquete) {

        if (paquete != null) {
            idPaqueteEliminar = paquete.getIdPaquete();
            lblNombrePaquete.setText(paquete.getNombre());
            lblTamanioPaquete.setText("Tamaño: " + (paquete.getTamaño() != null ? paquete.getTamaño().name() : "-"));
        }
    }

    private void estilarComponentes() {
        utileriasBotones.panelRedondeado(panelTarjeta, Color.WHITE, 18);
        utileriasBotones.panelRedondeado(panelAlerta, new Color(253, 237, 240), 16);
        utileriasBotones.btnRedondeado(btnEliminar, "rojo", 16);
        utileriasBotones.btnRedondeado(btnCancelar, "blanco", 16);

        Font fontBoton = new Font("Segoe UI", Font.BOLD, 16);
        btnEliminar.setFont(fontBoton);
        btnCancelar.setFont(fontBoton);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(new Color(60, 60, 60));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTarjeta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        panelAlerta = new javax.swing.JPanel();
        lblIcono = new javax.swing.JLabel();
        lblAlertaTitulo = new javax.swing.JLabel();
        lblAlertaMensaje = new javax.swing.JLabel();
        lblNombrePaquete = new javax.swing.JLabel();
        lblTamanioPaquete = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Eliminar Paquete");
        setMaximumSize(new java.awt.Dimension(990, 660));
        setMinimumSize(new java.awt.Dimension(990, 660));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(990, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Eliminar Paquete");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 250, 30));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDescripcion.setText("Confirma la eliminación del paquete seleccionado.");
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 56, 500, 20));

        panelAlerta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIcono.setText("!");
        lblIcono.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        panelAlerta.add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 45, 45));

        lblAlertaTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAlertaTitulo.setText("¿Estas seguro?");
        panelAlerta.add(lblAlertaTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 250, 25));

        lblAlertaMensaje.setText("Esta acción no se puede deshacer. El paquete será eliminado permanentemente.");
        panelAlerta.add(lblAlertaMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 650, 30));

        panelTarjeta.add(panelAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 105, 940, 105));

        lblNombrePaquete.setText("(Sin paquete seleccionado)");
        lblNombrePaquete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        panelTarjeta.add(lblNombrePaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 245, 500, 25));

        lblTamanioPaquete.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTamanioPaquete.setText("Tamaño: ");
        panelTarjeta.add(lblTamanioPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 276, 500, 25));

        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 341, 200, 50));

        btnEliminar.setText("Eliminar paquete");
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 341, 280, 50));

        jPanel1.add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 990, 570));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (idPaqueteEliminar == null) {
            JOptionPane.showMessageDialog(this, "No hay un paquete seleccionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EliminarPaqueteDTO dto = new EliminarPaqueteDTO(idPaqueteEliminar);
        try{
            boolean ok = cuEliminarPaquete.eliminar(dto);
        
        
            if (ok) {
                JOptionPane.showMessageDialog(this, "Paquete eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar el paquete.",
                        "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(PaqueteException ex ){
            JOptionPane.showMessageDialog(this,
                        "Error al eliminar el paquete.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAlertaMensaje;
    private javax.swing.JLabel lblAlertaTitulo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblNombrePaquete;
    private javax.swing.JLabel lblTamanioPaquete;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAlerta;
    private javax.swing.JPanel panelTarjeta;
    // End of variables declaration//GEN-END:variables
}