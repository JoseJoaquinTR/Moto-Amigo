/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motoamigopresentacion;

import Utilerias.utileriaHeaderSidebar;
import Utilerias.utileriasBotones;
import com.mycompany.motoamigodto.EliminarProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import Producto.EliminarProducto;
import Producto.IEliminarProducto;
import Producto.ProductoException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Sub-pantalla del CU PaqueteProducto: confirmación para eliminar un producto.
 *
 * @author joset
 */
public class FrmEliminarProducto extends JFrame {

    private final IEliminarProducto cuEliminarProducto;
    private String idProductoEliminar;

    public FrmEliminarProducto(ProductoDTO producto) {
        this.cuEliminarProducto = new EliminarProducto();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MotoAmigo - Eliminar Producto");
        utileriaHeaderSidebar.aplicarHeaderYSidebar(jPanel1, "PRODUCTOS");
        estilarComponentes();
        cargarProducto(producto);
    }

    /**
     * Carga los datos del paquete a confirmar.
     * @param producto
     */
    public void cargarProducto(ProductoDTO producto) {
        if (producto != null) {
            this.idProductoEliminar = producto.getId();
            lblNombreProducto.setText(producto.getNombre());
            lblPrecioProducto.setText("Precio: " + (producto.getPrecio() > 0 ? "$" + producto.getPrecio() : "-"));
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
        lblNombreProducto = new javax.swing.JLabel();
        lblPrecioProducto = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Eliminar Producto");
        setMaximumSize(new java.awt.Dimension(990, 660));
        setMinimumSize(new java.awt.Dimension(990, 660));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(990, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Eliminar Producto");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 250, 30));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDescripcion.setText("Confirma la eliminación del producto seleccionado.");
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 56, 500, 20));

        panelAlerta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIcono.setText("!");
        lblIcono.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        panelAlerta.add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 45, 45));

        lblAlertaTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAlertaTitulo.setText("¿Estas seguro?");
        panelAlerta.add(lblAlertaTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 250, 25));

        lblAlertaMensaje.setText("Esta acción no se puede deshacer. El producto sera eliminado permanentemente.");
        panelAlerta.add(lblAlertaMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 650, 30));

        panelTarjeta.add(panelAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 105, 770, 105));

        lblNombreProducto.setText("(Sin producto seleccionado)");
        lblNombreProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        panelTarjeta.add(lblNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 245, 500, 25));

        lblPrecioProducto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPrecioProducto.setText("Precio: ");
        panelTarjeta.add(lblPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 276, 500, 25));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 341, 200, 50));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEliminar.setText("Eliminar producto");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 341, 280, 50));

        jPanel1.add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 990, 580));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idProductoEliminar == null) {
            JOptionPane.showMessageDialog(this, "No hay un producto seleccionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EliminarProductoDTO dto = new EliminarProductoDTO(idProductoEliminar);
        try{
            boolean ok = cuEliminarProducto.eliminar(dto);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar el producto",
                        "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(ProductoException ex){
            JOptionPane.showMessageDialog(this,
                        "Error al eliminar el producto",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
        }  
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAlertaMensaje;
    private javax.swing.JLabel lblAlertaTitulo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecioProducto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAlerta;
    private javax.swing.JPanel panelTarjeta;
    // End of variables declaration//GEN-END:variables
}