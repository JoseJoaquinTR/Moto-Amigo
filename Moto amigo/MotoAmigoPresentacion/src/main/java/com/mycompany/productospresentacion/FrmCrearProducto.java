/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.productospresentacion;

import Utilerias.utileriaHeaderSidebar;
import Utilerias.utileriasBotones;
import com.mycompany.productosdto.NuevoProductoDTO;
import com.mycompany.productosdto.ProductoDTO;
import Producto.CrearProducto;
import Producto.ICrearProducto;
import Producto.ProductoException;
import Utilerias.UtileriaImagen;
import com.mycompany.motoamigopresentacion.controladores.SesionActiva;
import enums.TipoUnidadProductoDTO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author joset
 */
public class FrmCrearProducto extends JFrame {

    private final ICrearProducto cuCrearProducto;
    private byte[] imagenSeleccionada;

    public FrmCrearProducto() {
        this.cuCrearProducto = new CrearProducto();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MotoAmigo - Crear Producto");
        utileriaHeaderSidebar.aplicarHeader(jPanel1);
        configurarUnidades();
        estilarComponentes();
    }

    private void configurarUnidades() {
        cmbUnidad.removeAllItems();
        cmbUnidad.addItem("Selecciona una unidad");
        for (TipoUnidadProductoDTO u : TipoUnidadProductoDTO.values()) {
            cmbUnidad.addItem(u.name());
        }
    }

    private void estilarComponentes() {
        utileriasBotones.panelRedondeado(panelTarjeta, Color.WHITE, 18);
        utileriasBotones.btnRedondeado(btnGuardar, "negro", 16);
        utileriasBotones.btnRedondeado(btnCancelar, "blanco", 16);

        Font fontBoton = new Font("Segoe UI", Font.BOLD, 16);
        btnGuardar.setFont(fontBoton);
        btnCancelar.setFont(fontBoton);
        btnCancelar.setForeground(new Color(60, 60, 60));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTarjeta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblPeso = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        lblUnidad = new javax.swing.JLabel();
        cmbUnidad = new javax.swing.JComboBox();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblImagen = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSeleccionarImagen = new javax.swing.JButton();
        lblPreview = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Crear Producto");
        setMinimumSize(new java.awt.Dimension(990, 660));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Crear Producto");
        lblTitulo.setForeground(new java.awt.Color(51, 55, 69));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 30));

        lblDescripcion.setText("Define los datos del nuevo producto.");
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 56, 350, 20));

        lblNombre.setText("NOMBRE");
        lblNombre.setForeground(new java.awt.Color(115, 128, 135));
        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 96, 120, 18));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 950, 32));

        lblPeso.setText("PESO (KG)");
        lblPeso.setForeground(new java.awt.Color(115, 128, 135));
        lblPeso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 167, 120, 18));

        txtPeso.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 191, 370, 32));

        lblUnidad.setText("UNIDAD");
        lblUnidad.setForeground(new java.awt.Color(115, 128, 135));
        lblUnidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 167, 120, 18));

        cmbUnidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(cmbUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 191, 550, 32));

        lblPrecio.setText("PRECIO (MXN)");
        lblPrecio.setForeground(new java.awt.Color(115, 128, 135));
        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 238, 140, 18));

        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 262, 370, 32));

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(115, 128, 135));
        lblImagen.setText("IMAGEN ");
        panelTarjeta.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 309, 140, 18));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(96, 96, 96));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 200, 50));

        btnGuardar.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardar.setText("Guardar producto");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 280, 50));

        btnSeleccionarImagen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSeleccionarImagen.setText("Seleccionar imagen ");
        btnSeleccionarImagen.setBorderPainted(false);
        btnSeleccionarImagen.setFocusPainted(false);
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnSeleccionarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 190, 35));

        lblPreview.setText("Preview ");
        panelTarjeta.add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 550, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(115, 128, 135));
        jLabel1.setText("La imagen puede aparecer recortada para llenar el espacio");
        panelTarjeta.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 340, -1));

        jPanel1.add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 990, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbUnidad.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una unidad.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        float peso;
        float precio;
        try {
            peso = Float.parseFloat(txtPeso.getText().trim());
            precio = Float.parseFloat(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Peso y precio deben ser números válidos.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TipoUnidadProductoDTO unidad = TipoUnidadProductoDTO.valueOf((String) cmbUnidad.getSelectedItem());
        String idEmprendedor = SesionActiva.getInstancia().getIdEmprendedor();
        NuevoProductoDTO nuevo = new NuevoProductoDTO(
                txtNombre.getText().trim(),
                peso,
                unidad,
                precio,
                imagenSeleccionada,
                idEmprendedor
        );
        try {
            ProductoDTO creado = cuCrearProducto.crear(nuevo);
            if (creado != null) {
                JOptionPane.showMessageDialog(this, "Producto creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al crear el producto",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ProductoException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al crear el producto",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        byte[] datos = UtileriaImagen.seleccionarYPrevisualizar(this, lblPreview);
        if (datos != null) {
            this.imagenSeleccionada = datos;
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionarImagen;
    private javax.swing.JComboBox cmbUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
