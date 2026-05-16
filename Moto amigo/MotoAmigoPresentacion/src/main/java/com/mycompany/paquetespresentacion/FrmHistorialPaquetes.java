/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.paquetespresentacion;

import Utilerias.utileriaHeaderSidebar;
import Utilerias.utileriasBotones;
import com.mycompany.paquetesdto.PaqueteHistorialDTO;
import com.mycompany.paquetesdto.ReporteHistorialPaquetePDFDTO;
import Paquete.GenerarHistorialPDF;
import Paquete.IGenerarHistorialPDF;
import Paquete.PaqueteException;
import Utilerias.utileriaTablas;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Sub-pantalla del CU PaqueteProducto: muestra el historial de uso de los
 * paquetes y permite generar/descargar el reporte en PDF.
 *
 * @author joset
 */
public class FrmHistorialPaquetes extends JFrame {

    private final IGenerarHistorialPDF cuHistorial;
    private ReporteHistorialPaquetePDFDTO reporteActual;

    public FrmHistorialPaquetes() {
        this.cuHistorial = new GenerarHistorialPDF();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MotoAmigo - Historial de Paquetes");
        utileriaHeaderSidebar.aplicarHeader(jPanel1);
        utileriaTablas.configurarTablaPaquetesHistorial(tblHistorial);
        
        estilarComponentes();
        try {
            cargarHistorial();
        } catch (PaqueteException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el Historial",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void estilarComponentes() {
        utileriasBotones.panelRedondeado(panelTarjeta, Color.WHITE, 18);
        utileriasBotones.btnRedondeado(btnGenerarPDF, "naranja", 16);
        utileriasBotones.btnRedondeado(btnDescargarPDF, "negro", 16);

        Font fontBoton = new Font("Segoe UI", Font.BOLD, 14);
        btnGenerarPDF.setFont(fontBoton);
        btnDescargarPDF.setFont(fontBoton);
        btnGenerarPDF.setForeground(Color.WHITE);
        btnDescargarPDF.setForeground(Color.WHITE);
    }
    private void cargarHistorial() throws PaqueteException {
        reporteActual = cuHistorial.generar();

        utileriaTablas.cargarPaquetesHistorial(tblHistorial, reporteActual);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTarjeta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        btnGenerarPDF = new javax.swing.JButton();
        btnDescargarPDF = new javax.swing.JButton();
        scrollHistorial = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Historial de Paquetes");
        setMinimumSize(new java.awt.Dimension(1020, 740));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setText("Historial");
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 250, 30));

        lblDescripcion.setText("Paquetes utilizados con mayor frecuencia.");
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 61, 350, 20));

        btnGenerarPDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenerarPDF.setText("Refrescar historial");
        btnGenerarPDF.setBorderPainted(false);
        btnGenerarPDF.setFocusPainted(false);
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnGenerarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 180, 40));

        btnDescargarPDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDescargarPDF.setText("Descargar PDF");
        btnDescargarPDF.setBorderPainted(false);
        btnDescargarPDF.setFocusPainted(false);
        btnDescargarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarPDFActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnDescargarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 180, 40));

        tblHistorial.setRowHeight(30);
        scrollHistorial.setViewportView(tblHistorial);

        panelTarjeta.add(scrollHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 930, 360));

        jPanel1.add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 990, 580));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        try {
            cargarHistorial();

            if (reporteActual == null) {
                JOptionPane.showMessageDialog(this,
                        "Error al cargar el Historial",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PaqueteException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el Historial",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private void btnDescargarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarPDFActionPerformed

        if (reporteActual == null) {
            JOptionPane.showMessageDialog(this, "Primero genera el historial.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean ok;
        try {
            ok = cuHistorial.descargar(reporteActual);
            if (ok) {
                JOptionPane.showMessageDialog(this, "PDF descargado correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo generar el pdf",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PaqueteException ex) {
            JOptionPane.showMessageDialog(this,
                        "Error al descargar el pdf",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnDescargarPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescargarPDF;
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JScrollPane scrollHistorial;
    private javax.swing.JTable tblHistorial;
    // End of variables declaration//GEN-END:variables
}
