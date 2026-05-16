/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.paquetespresentacion;

import Utilerias.utileriaHeaderSidebar;
import Utilerias.utileriasBotones;
import com.mycompany.paquetesdto.EditarPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import Paquete.EditarPaquete;
import Paquete.IEditarPaquete;
import Paquete.PaqueteException;
import Utilerias.UtileriaImagen;
import com.mycompany.productospresentacion.DlgBuscarProductos;
import enums.TamañoPaqueteDTO;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author joset
 */
public class FrmEditarPaquete extends JFrame {

    private final IEditarPaquete cuEditarPaquete;
    private final List<ProductosPaqueteDTO> productosAgregados;
    private String idPaqueteEditando;
    private byte[] imagenSeleccionada;

    /**
     * Crea una nueva instancia del formulario.
     *
     * @param paqueteSeleccionado
     */
    public FrmEditarPaquete(PaqueteDTO paqueteSeleccionado) {
        this.cuEditarPaquete = new EditarPaquete();
        this.productosAgregados = new ArrayList<>();
        initComponents();
        configurarTabla();
        setLocationRelativeTo(null);
        setTitle("MotoAmigo - Editar Paquete");
        utileriaHeaderSidebar.aplicarHeader(jPanel1);
        configurarTamanios();
        estilarComponentes();
        actualizarTotales();
        cargarPaquete(paqueteSeleccionado);
    }

    /**
     * Carga los datos de un paquete existente al formulario.
     *
     * @param paquete paquete a editar.
     */
    private void cargarPaquete(PaqueteDTO paquete) {
        if (paquete == null) {
            return;
        }
        idPaqueteEditando = paquete.getIdPaquete();
        txtNombre.setText(paquete.getNombre());
        if (paquete.getTamaño() != null) {
            cmbTamanio.setSelectedItem(paquete.getTamaño().name());
        }
        if (paquete.getProductos() != null) {
            productosAgregados.clear();
            productosAgregados.addAll(paquete.getProductos());
            refrescarTablaProductos();
        }
        this.imagenSeleccionada = paquete.getImagen();
        if (this.imagenSeleccionada != null) {
            lblPreview.setIcon(UtileriaImagen.bytesAImageIcon(
                    this.imagenSeleccionada,
                    lblPreview.getWidth(),
                    lblPreview.getHeight()
            ));
            lblPreview.setText("");
        }

    }

    private void configurarTamanios() {
        cmbTamanio.removeAllItems();
        cmbTamanio.addItem("Selecciona un tamaño");
        for (TamañoPaqueteDTO t : TamañoPaqueteDTO.values()) {
            cmbTamanio.addItem(t.name());
        }
    }

    private void estilarComponentes() {
        utileriasBotones.panelRedondeado(panelTarjeta, Color.WHITE, 18);
        utileriasBotones.panelRedondeado(panelPesoCalc, new Color(255, 245, 235), 16);
        utileriasBotones.panelRedondeado(panelPrecioCalc, new Color(232, 246, 232), 16);
        utileriasBotones.btnRedondeado(btnGuardar, "negro", 16);
        utileriasBotones.btnRedondeado(btnCancelar, "blanco", 16);
        utileriasBotones.btnRedondeado(btnAgregarProducto, "naranja", 14);
        utileriasBotones.btnRedondeado(btnQuitarProducto, "blanco", 14);

        Font fontBoton = new Font("Segoe UI", Font.BOLD, 16);
        btnGuardar.setFont(fontBoton);
        btnCancelar.setFont(fontBoton);
        btnCancelar.setForeground(new Color(60, 60, 60));
        btnQuitarProducto.setForeground(new Color(220, 53, 69));
        btnQuitarProducto.setBackground(new Color(253, 237, 240));
    }

    private void actualizarTotales() {
        float pesoTotal = 0f;
        float precioTotal = 0f;
        for (ProductosPaqueteDTO pp : productosAgregados) {
            pesoTotal = pesoTotal + pp.getPesoTotal();
            if (pp.getProducto() != null) {
                precioTotal += pp.getProducto().getPrecio() * pp.getCantidad();
            }
        }
        lblPesoCalculado.setText(String.format("%.2f kg", pesoTotal));
        lblPrecioCalculado.setText(String.format("$%.2f", precioTotal));
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Nombre", "Cantidad", "Precio total", "Peso total"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblProductos.setModel(modelo);
    }

    private void refrescarTablaProductos() {
        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0);

        for (ProductosPaqueteDTO productoPaquete : productosAgregados) {
            ProductoDTO producto = productoPaquete.getProducto();

            String nombre = producto != null ? producto.getNombre() : "Sin producto";

            float precioTotal = 0f;
            if (productoPaquete.getProducto() != null) {
                precioTotal = productoPaquete.getProducto().getPrecio() * productoPaquete.getCantidad();
            }

            modelo.addRow(new Object[]{
                nombre,
                productoPaquete.getCantidad(),
                String.format("$ %.2f", precioTotal),
                String.format("%.2f kg", productoPaquete.getPesoTotal())
            });
        }

        actualizarTotales();
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
        lblTamanio = new javax.swing.JLabel();
        cmbTamanio = new javax.swing.JComboBox();
        lblImagen = new javax.swing.JLabel();
        panelPesoCalc = new javax.swing.JPanel();
        lblPesoTitulo = new javax.swing.JLabel();
        lblPesoCalculado = new javax.swing.JLabel();
        panelPrecioCalc = new javax.swing.JPanel();
        lblPrecioTitulo = new javax.swing.JLabel();
        lblPrecioCalculado = new javax.swing.JLabel();
        lblProductosTitulo = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        scrollProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSeleccionarImagen = new javax.swing.JButton();
        lblPreview = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MotoAmigo - Editar Paquete");
        setMinimumSize(new java.awt.Dimension(990, 660));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(990, 660));
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Editar Paquete");
        lblTitulo.setForeground(new java.awt.Color(51, 55, 69));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        panelTarjeta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, 30));

        lblDescripcion.setText("Modifica los datos del paquete.");
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 56, 400, 20));

        lblNombre.setText("NOMBRE");
        lblNombre.setForeground(new java.awt.Color(115, 128, 135));
        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, 18));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 114, 370, 32));

        lblTamanio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTamanio.setForeground(new java.awt.Color(115, 128, 135));
        lblTamanio.setText("TAMAÑO");
        panelTarjeta.add(lblTamanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 120, 18));

        cmbTamanio.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelTarjeta.add(cmbTamanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 114, 550, 32));

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(115, 128, 135));
        lblImagen.setText("IMAGEN ");
        panelTarjeta.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 161, 140, 18));

        panelPesoCalc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPesoTitulo.setText("PESO CALCULADO");
        lblPesoTitulo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        panelPesoCalc.add(lblPesoTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 150, 18));

        lblPesoCalculado.setText("0.00 kg");
        lblPesoCalculado.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        panelPesoCalc.add(lblPesoCalculado, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 36, 150, 35));

        panelTarjeta.add(panelPesoCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 220, 85));

        panelPrecioCalc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioTitulo.setText("PRECIO CALCULADO");
        lblPrecioTitulo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        panelPrecioCalc.add(lblPrecioTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 160, 18));

        lblPrecioCalculado.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblPrecioCalculado.setText("$0.00");
        panelPrecioCalc.add(lblPrecioCalculado, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 36, 150, 35));

        panelTarjeta.add(panelPrecioCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 220, 85));

        lblProductosTitulo.setText("PRODUCTOS DEL PAQUETE");
        lblProductosTitulo.setForeground(new java.awt.Color(115, 128, 135));
        lblProductosTitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        panelTarjeta.add(lblProductosTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 342, 220, 18));

        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarProducto.setText("+ Agregar producto");
        btnAgregarProducto.setBorderPainted(false);
        btnAgregarProducto.setFocusPainted(false);
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 160, 35));

        btnQuitarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnQuitarProducto.setText("Quitar seleccionado");
        btnQuitarProducto.setBorderPainted(false);
        btnQuitarProducto.setFocusPainted(false);
        btnQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnQuitarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, 180, 35));

        tblProductos.setRowHeight(28);
        scrollProductos.setViewportView(tblProductos);

        panelTarjeta.add(scrollProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 379, 950, 120));

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
        panelTarjeta.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 515, 200, 50));

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
        panelTarjeta.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 510, 280, 50));

        btnSeleccionarImagen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSeleccionarImagen.setText("Seleccionar imagen ");
        btnSeleccionarImagen.setBorderPainted(false);
        btnSeleccionarImagen.setFocusPainted(false);
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });
        panelTarjeta.add(btnSeleccionarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 190, 35));

        lblPreview.setText("Preview ");
        panelTarjeta.add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 450, 170));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(115, 128, 135));
        jLabel1.setText("La imagen puede aparecer recortada para llenar el espacio");
        panelTarjeta.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 340, -1));

        jPanel1.add(panelTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1000, 580));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbTamanio.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un tamaño.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (productosAgregados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El paquete debe tener al menos un producto. Agrega productos antes de guardar.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TamañoPaqueteDTO tamanio = TamañoPaqueteDTO.valueOf((String) cmbTamanio.getSelectedItem());
        float precio = 0f;
        for (ProductosPaqueteDTO pp : productosAgregados) {
            if (pp.getProducto() != null) {
                precio += pp.getProducto().getPrecio() * pp.getCantidad();
            }
        }

        EditarPaqueteDTO editado = new EditarPaqueteDTO(
                txtNombre.getText().trim(),
                tamanio,
                productosAgregados,
                precio,
                imagenSeleccionada,
                null
        );

        PaqueteDTO actualizado;
        try {
            actualizado = cuEditarPaquete.editar(idPaqueteEditando, editado);
            if (actualizado != null) {
                JOptionPane.showMessageDialog(this, "Paquete actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo editar el paquete",
                        "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PaqueteException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al editar el paquete",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        DlgBuscarProductos dlg = new DlgBuscarProductos(this, true);
        dlg.setVisible(true);

        ProductoDTO productoSeleccionado = dlg.getProductoSeleccionado();

        if (productoSeleccionado == null) {
            return;
        }

        int cantidad = solicitarCantidadProducto();

        if (cantidad <= 0) {
            return;
        }

        ProductosPaqueteDTO existente = buscarProductoExistente(productoSeleccionado.getId());
        if (existente != null) {
            int nuevaCantidad = existente.getCantidad() + cantidad;
            existente.setCantidad(nuevaCantidad);
            existente.setPesoTotal(productoSeleccionado.getPeso() * nuevaCantidad);
        } else {
            ProductosPaqueteDTO productoPaquete = new ProductosPaqueteDTO();
            productoPaquete.setProducto(productoSeleccionado);
            productoPaquete.setCantidad(cantidad);
            productoPaquete.setPesoTotal(productoSeleccionado.getPeso() * cantidad);
            productosAgregados.add(productoPaquete);
        }

        refrescarTablaProductos();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed
    private ProductosPaqueteDTO buscarProductoExistente(String idProducto) {
        if (idProducto == null) {
            return null;
        }
        for (ProductosPaqueteDTO pp : productosAgregados) {
            if (pp.getProducto() != null && idProducto.equals(pp.getProducto().getId())) {
                return pp;
            }
        }
        return null;
    }

    private int solicitarCantidadProducto() {
        String cantidadTexto = JOptionPane.showInputDialog(
                this,
                "Ingresa la cantidad del producto:",
                "Cantidad",
                JOptionPane.QUESTION_MESSAGE
        );

        if (cantidadTexto == null) {
            return 0;
        }

        cantidadTexto = cantidadTexto.trim();

        if (cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "La cantidad es obligatoria.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return 0;
        }

        try {
            int cantidad = Integer.parseInt(cantidadTexto);

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "La cantidad debe ser mayor a 0.",
                        "Validación",
                        JOptionPane.WARNING_MESSAGE
                );
                return 0;
            }

            return cantidad;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "La cantidad debe ser un número entero válido.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return 0;
        }
    }
    private void btnQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoActionPerformed
        int fila = tblProductos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla para quitarlo.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (productosAgregados.size() == 1) {
            JOptionPane.showMessageDialog(this,
                    "No puede existir un paquete sin productos. Si desea eliminar este producto, debe eliminar el paquete completo.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tblProductos.convertRowIndexToModel(fila);
        productosAgregados.remove(filaModelo);
        refrescarTablaProductos();
    }//GEN-LAST:event_btnQuitarProductoActionPerformed

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        byte[] datos = UtileriaImagen.seleccionarYPrevisualizar(this, lblPreview);
        if (datos != null) {
            this.imagenSeleccionada = datos;
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitarProducto;
    private javax.swing.JButton btnSeleccionarImagen;
    private javax.swing.JComboBox cmbTamanio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPesoCalculado;
    private javax.swing.JLabel lblPesoTitulo;
    private javax.swing.JLabel lblPrecioCalculado;
    private javax.swing.JLabel lblPrecioTitulo;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JLabel lblProductosTitulo;
    private javax.swing.JLabel lblTamanio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelPesoCalc;
    private javax.swing.JPanel panelPrecioCalc;
    private javax.swing.JPanel panelTarjeta;
    private javax.swing.JScrollPane scrollProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
