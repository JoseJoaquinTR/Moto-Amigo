
package panelesUtilerias;

import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @author joset
 */
public class PanelSidebarEmprendedor extends javax.swing.JPanel {

    private static final Color COLOR_ACTIVO = new Color(232, 100, 10);
    private static final Color COLOR_TEXTO = new Color(60, 60, 60);
    private static final Color COLOR_BORDE = new Color(222, 226, 230);

    private String seccionActiva = "INICIO";

    public PanelSidebarEmprendedor() {
        initComponents();
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, COLOR_BORDE));
        aplicarEstilos();
    }

    /**
     * Indica cuál pantalla está activa. El botón correspondiente se pinta en
     * naranja con texto blanco.
     *
     * @param pantalla nombre de la pantalla activa: INICIO, PAQUETES, PRODUCTOS,
     * PEDIDOS o PERFIL.
     */
    public void setPantallaActiva(String pantalla) {
        this.seccionActiva = pantalla;
        aplicarEstilos();
    }

    /**
     * Pinta cada botón según si es el activo o no.
     */
    private void aplicarEstilos() {
        pintarBoton(btnInicio, "INICIO");
        pintarBoton(btnPaquetes, "PAQUETES");
        pintarBoton(btnProductos, "PRODUCTOS");
        pintarBoton(btnPedidos, "PEDIDOS");
        pintarBoton(btnPerfil, "PERFIL");
    }

    /**
     * Aplica el estilo activo o inactivo a un botón del sidebar.
     */
    private void pintarBoton(JButton btn, String nombreSeccion) {
        boolean activo = nombreSeccion.equals(seccionActiva);

        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);

        if (activo) {
            btn.setForeground(COLOR_ACTIVO);
            btn.setCursor(Cursor.getDefaultCursor());
        } else {
            btn.setForeground(COLOR_TEXTO);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        btn.putClientProperty("activo", activo);
        btn.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Boolean btnActivo = (Boolean) ((JButton) c).getClientProperty("activo");
                if (btnActivo != null && btnActivo) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.WHITE);
                    g2.fillRoundRect(8, 0, c.getWidth() - 16, c.getHeight(), 12, 12);
                    g2.dispose();
                }
                super.paint(g, c);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInicio = new javax.swing.JButton();
        btnPaquetes = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 100, 10));
        setMaximumSize(new java.awt.Dimension(180, 738));
        setMinimumSize(new java.awt.Dimension(180, 738));
        setPreferredSize(new java.awt.Dimension(180, 738));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio.setText("INICIO");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 46, 150, 42));

        btnPaquetes.setText("PAQUETES");
        btnPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaquetesActionPerformed(evt);
            }
        });
        add(btnPaquetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 122, 150, 42));

        btnProductos.setText("PRODUCTOS");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 198, 150, 42));

        btnPedidos.setText("PEDIDOS");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });
        add(btnPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 274, 150, 42));

        btnPerfil.setText("PERFIL");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        add(btnPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 350, 150, 42));
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        ControlNavegacionEmprendedor.getInstancia().irAInicio();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaquetesActionPerformed
        ControlNavegacionEmprendedor.getInstancia().irAPaquetes();
    }//GEN-LAST:event_btnPaquetesActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        ControlNavegacionEmprendedor.getInstancia().irAProductos();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed

    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed

    }//GEN-LAST:event_btnPerfilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPaquetes;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JButton btnProductos;
    // End of variables declaration//GEN-END:variables
}
