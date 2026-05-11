package com.mycompany.motoamigopresentacion;

import Utilerias.utileriaHeaderSidebar;
import Utilerias.utileriasBotones;
import com.mycompany.motoamigodto.EditarProductoDTO;
import com.mycompany.motoamigodto.ProductoDTO;
import Producto.EditarProducto;
import Producto.IEditarProducto;
import Producto.ProductoException;
import enums.TipoUnidadProductoDTO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author joset
 */
public class FrmEditarProducto extends JFrame {

    private final IEditarProducto cuEditarProducto;
    private String idEditando;
    private byte[] imagenSeleccionada;

    public FrmEditarProducto(ProductoDTO producto) {
        this.cuEditarProducto = new EditarProducto();
        this.idEditando = null;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MotoAmigo - Editar Producto");
        utileriaHeaderSidebar.aplicarHeader(jPanel1);
        configurarUnidades();
        estilarComponentes();
        cargarProducto(producto);
    }

    /**
     * Carga los datos de un producto existente al formulario.
     *
     * @param producto producto a editar
     */
    private void cargarProducto(ProductoDTO producto) {
        if (producto == null) {
            return;
        }

        this.idEditando = producto.getId();
        txtNombre.setText(producto.getNombre());
        txtPeso.setText(String.valueOf(producto.getPeso()));
        txtPrecio.setText(String.valueOf(producto.getPrecio()));

        if (producto.getUnidad() != null) {
            cmbUnidad.setSelectedItem(producto.getUnidad().name());
        }

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
        txtImagen = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Editar Producto");
        setMaximumSize(new java.awt.Dimension(990, 660));
        setMinimumSize(new java.awt.Dimension(990, 660));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(990, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Editar Producto");
        lblTitulo.setForeground(new java.awt.Color(51, 55, 69));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, 30));

        lblDescripcion.setText("Modifica los datos del producto.");
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 56, 400, 20));

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

        lblImagen.setText("IMAGEN (URL)");
        lblImagen.setForeground(new java.awt.Color(115, 128, 135));
        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 309, 140, 18));

        txtImagen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(txtImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 333, 950, 32));

        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new java.awt.Color(96, 96, 96));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 395, 200, 50));

        btnGuardar.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardar.setText("Guardar cambios");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 280, 50));

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
        EditarProductoDTO editado = new EditarProductoDTO(
                txtNombre.getText().trim(),
                peso,
                unidad,
                precio,
                imagenSeleccionada,
                null
        );
        try {
            ProductoDTO actualizado = cuEditarProducto.editar(idEditando, editado);
            if (actualizado != null) {
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al editar producto",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ProductoException ex) {
            JOptionPane.showMessageDialog(this,
                        "Error al editar producto",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbUnidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
