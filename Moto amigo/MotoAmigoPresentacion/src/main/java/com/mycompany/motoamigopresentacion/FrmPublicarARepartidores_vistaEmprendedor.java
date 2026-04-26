package com.mycompany.motoamigopresentacion;

import com.mycompany.cusolicitarentrega.BuscarUbicacion;
import com.mycompany.cusolicitarentrega.IBuscarUbicacion;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigopresentacion.controladores.ControlSolicitarEntrega;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import panelesUtilerias.PanelHeader;

/**
 *
 * @author xiomi
 */
public class FrmPublicarARepartidores_vistaEmprendedor extends javax.swing.JFrame {

    private ControlSolicitarEntrega control = ControlSolicitarEntrega.getInstance();
    
    private final IBuscarUbicacion buscarUbicacion = new BuscarUbicacion();
    private JPopupMenu popupOrigen;
    private JPopupMenu popupDestino;
    private double origenLat, origenLng;
    private double destinoLat, destinoLng;
    
    public FrmPublicarARepartidores_vistaEmprendedor() {
        initComponents();
        Color bg = new Color(248, 250, 252);
        this.getContentPane().setBackground(bg);
        iniciarAutocompletado();
        setLayout(new AbsoluteLayout());
        add(new PanelHeader(), new AbsoluteConstraints(0, 0, 1366, 130));
    }

    private void iniciarAutocompletado() {
        popupOrigen = new JPopupMenu();
        popupDestino = new JPopupMenu();
        configurarCampo(txt_origen, popupOrigen, true);
        configurarCampo(txt_destino, popupDestino, false);
    }

    private void configurarCampo(JTextField campo, JPopupMenu popup, boolean esOrigen) {
        Timer[] timer = {null};

        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP
                        || key == KeyEvent.VK_DOWN
                        || key == KeyEvent.VK_ENTER
                        || key == KeyEvent.VK_ESCAPE) {
                    return;
                }

                if (timer[0] != null) {
                    timer[0].stop();
                }

                timer[0] = new Timer(400, ev -> {
                    String texto = campo.getText().trim();
                    new Thread(() -> {
                        try {
                            java.util.List<UbicacionDTO> sugerencias = buscarUbicacion.ejecutar(texto);
                            SwingUtilities.invokeLater(() -> mostrarPopup(campo, popup, sugerencias, esOrigen));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                });
                timer[0].setRepeats(false);
                timer[0].start();
            }
        });
    }

    private void mostrarPopup(JTextField campo, JPopupMenu popup, List<UbicacionDTO> sugerencias, boolean esOrigen) {
        popup.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0), 1)); // borde naranja como tu tema
        popup.setBackground(Color.WHITE);
        popup.removeAll();

        if (sugerencias == null || sugerencias.isEmpty()) {
            popup.setVisible(false);
            return;
        }

        for (UbicacionDTO dto : sugerencias) {
            JMenuItem item = new JMenuItem(dto.getDescripcion());
            item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            item.setBackground(java.awt.Color.WHITE);
            item.setForeground(java.awt.Color.DARK_GRAY);
            item.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

            item.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    item.setBackground(new java.awt.Color(255, 102, 0)); // naranja
                    item.setForeground(java.awt.Color.WHITE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    item.setBackground(java.awt.Color.WHITE);
                    item.setForeground(java.awt.Color.DARK_GRAY);
                }
            });
            item.addActionListener(e -> {
                campo.setText(dto.getDescripcion());
                popup.setVisible(false);

                if (esOrigen) {
                    origenLat = dto.getLatitud();
                    origenLng = dto.getLongitud();
                    System.out.println("Origen seleccionado: "
                            + dto.getDescripcion()
                            + " [" + origenLng + ", " + origenLat + "]");
                } else {
                    destinoLat = dto.getLatitud();
                    destinoLng = dto.getLongitud();
                    System.out.println("Destino seleccionado: "
                            + dto.getDescripcion()
                            + " [" + destinoLng + ", " + destinoLat + "]");
                }
            });

            popup.add(item);
        }

        // Mostrar el popup justo debajo del JTextArea
        popup.show(campo, 0, campo.getHeight());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_origen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_destino = new javax.swing.JTextField();
        txt_caja_paquete = new javax.swing.JTextField();
        txt_sobre_doc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_peso = new javax.swing.JTextField();
        btn_solicitar = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solicitud de Entrega");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1008, 738));
        setMinimumSize(new java.awt.Dimension(1008, 738));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setText("DETALLE PEDIDO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 738, -1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("PUNTO DE RECOLECCIÓN *");

        txt_origen.setForeground(new java.awt.Color(102, 102, 102));
        txt_origen.setText("Ej. Av. Guerrero 550");
        txt_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_origenActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("PUNTO DE ENTREGA * ");

        txt_destino.setForeground(new java.awt.Color(102, 102, 102));
        txt_destino.setText("Ej. Calle Nainari 316");

        txt_caja_paquete.setEditable(false);
        txt_caja_paquete.setBackground(new java.awt.Color(255, 247, 237));
        txt_caja_paquete.setColumns(5);
        txt_caja_paquete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_caja_paquete.setForeground(new java.awt.Color(255, 105, 0));
        txt_caja_paquete.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_caja_paquete.setText("Caja/Paquete");
        txt_caja_paquete.setToolTipText("");
        txt_caja_paquete.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 105, 0)));

        txt_sobre_doc.setEditable(false);
        txt_sobre_doc.setBackground(new java.awt.Color(255, 247, 237));
        txt_sobre_doc.setColumns(5);
        txt_sobre_doc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_sobre_doc.setForeground(new java.awt.Color(255, 105, 0));
        txt_sobre_doc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_sobre_doc.setText("Sobre/Doc");
        txt_sobre_doc.setToolTipText("");
        txt_sobre_doc.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 105, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("PESO APROXIMADO (KG) *");

        txt_peso.setColumns(3);
        txt_peso.setForeground(new java.awt.Color(102, 102, 102));
        txt_peso.setText("0.0");

        btn_solicitar.setBackground(new java.awt.Color(0, 0, 0));
        btn_solicitar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_solicitar.setForeground(new java.awt.Color(255, 255, 255));
        btn_solicitar.setText("VALIDARD DATOS");
        btn_solicitar.setToolTipText("");
        btn_solicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_solicitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_caja_paquete, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(txt_sobre_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_peso, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(btn_solicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_caja_paquete, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sobre_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_peso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_solicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 59, 1010, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_solicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_solicitarActionPerformed
         
    RutaRequestDTO request = new RutaRequestDTO(txt_origen.getText(),txt_destino.getText());

    frmConsultarRuta pantalla = new frmConsultarRuta(request);
    pantalla.setVisible(true);

    this.dispose();
    }//GEN-LAST:event_btn_solicitarActionPerformed

    private void txt_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_origenActionPerformed

    private void txt_origenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_origenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_origenKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_solicitar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_caja_paquete;
    private javax.swing.JTextField txt_destino;
    private javax.swing.JTextField txt_origen;
    private javax.swing.JTextField txt_peso;
    private javax.swing.JTextField txt_sobre_doc;
    // End of variables declaration//GEN-END:variables
}
