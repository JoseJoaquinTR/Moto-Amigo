package com.mycompany.emprendedorpresentacion;

import Utilerias.utileriaTablas;
import Utilerias.utileriasBotones;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.SobreDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigopresentacion.FrmConsultarRutaEmprendedor;
import com.mycompany.motoamigopresentacion.controladores.ControlSolicitarEntrega;
import com.mycompany.motoamigopresentacion.controladores.SesionActiva;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.paquetespresentacion.DlgBuscarPaquetes;
import enums.TipoEnvioDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import panelesUtilerias.PanelHeader;

/**
 *
 * @author xiomi
 */
public class FrmPublicarPedidosEmprendedor extends javax.swing.JFrame {

    private ControlSolicitarEntrega control = ControlSolicitarEntrega.getInstance();

    private JPopupMenu popupOrigen;
    private JPopupMenu popupDestino;
    private double origenLat, origenLng;
    private double destinoLat, destinoLng;
    private JButton btnCajaPaquete;
    private JButton btnSobreDoc;
    private JPanel panelDimensiones;
    private JTextField txtLargo;
    private JTextField txtAncho;
    private JTextField txtAlto;
    private JLabel lblAlto;
    private String tipoSeleccionado = null;

    private TipoEnvioDTO tipoEnvio;
    private final List<PaqueteDTO> paquetesAgregados = new ArrayList<>();

    private JTable tblPaquetes;
    private JScrollPane scrollPaquetes;
    private JButton btnQuitarPaquete;

    public FrmPublicarPedidosEmprendedor() {
        initComponents();

        this.getContentPane().setBackground(new Color(248, 250, 252));
        setLayout(new AbsoluteLayout());
        add(new PanelHeader(), new AbsoluteConstraints(0, 0, 1366, 130));

        iniciarUI();
        iniciarAutocompletado();
        setLocationRelativeTo(null);
    }

    private void iniciarUI() {
        utileriasBotones.btnRedondeado(btn_solicitar, "negro", 30);

        Rectangle rCaja = new Rectangle(41, 220, 382, 86);
        Rectangle rSobre = new Rectangle(571, 220, 382, 86);

        btnCajaPaquete = new JButton("Caja/Paquete");
        btnCajaPaquete.setBackground(new Color(255, 247, 237));
        btnCajaPaquete.setForeground(new Color(255, 105, 0));
        btnCajaPaquete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCajaPaquete.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 105, 0)));
        btnCajaPaquete.setFocusPainted(false);
        btnCajaPaquete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCajaPaquete.setBounds(rCaja);
        btnCajaPaquete.addActionListener(e -> seleccionarTipo("caja"));

        btnSobreDoc = new JButton("Sobre/Doc");
        btnSobreDoc.setBackground(new Color(255, 247, 237));
        btnSobreDoc.setForeground(new Color(255, 105, 0));
        btnSobreDoc.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSobreDoc.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 105, 0)));
        btnSobreDoc.setFocusPainted(false);
        btnSobreDoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSobreDoc.setBounds(rSobre);
        btnSobreDoc.addActionListener(e -> seleccionarTipo("sobre"));

        panelDimensiones = new JPanel(null);
        panelDimensiones.setBackground(Color.WHITE);
        panelDimensiones.setBounds(rCaja.x, rCaja.y + rCaja.height + 10, rSobre.x + rSobre.width - rCaja.x, 120);
        panelDimensiones.setVisible(false);

        JLabel lblLargo = new JLabel("Largo (cm):");
        lblLargo.setBounds(0, 0, 100, 25);
        txtLargo = new JTextField();
        txtLargo.setBounds(110, 0, 150, 25);

        JLabel lblAncho = new JLabel("Ancho (cm):");
        lblAncho.setBounds(0, 35, 100, 25);
        txtAncho = new JTextField();
        txtAncho.setBounds(110, 35, 150, 25);

        lblAlto = new JLabel("Alto (cm):");
        lblAlto.setBounds(0, 70, 100, 25);
        txtAlto = new JTextField();
        txtAlto.setBounds(110, 70, 150, 25);

        tblPaquetes = new JTable();
        tblPaquetes.setRowHeight(28);
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Nombre", "Tamaño", "Peso (kg)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPaquetes.setModel(modelo);

        scrollPaquetes = new JScrollPane(tblPaquetes);
        scrollPaquetes.setBounds(rCaja.x, rCaja.y + rCaja.height + 10,
                rSobre.x + rSobre.width - rCaja.x, 120);
        scrollPaquetes.setVisible(false);

        btnQuitarPaquete = new JButton("Quitar paquete seleccionado");
        btnQuitarPaquete.setBackground(new Color(255, 247, 237));
        btnQuitarPaquete.setForeground(new Color(255, 105, 0));
        btnQuitarPaquete.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnQuitarPaquete.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 105, 0)));
        btnQuitarPaquete.setFocusPainted(false);
        btnQuitarPaquete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnQuitarPaquete.setBounds(rCaja.x, scrollPaquetes.getY() + scrollPaquetes.getHeight() + 5, 220, 30);
        btnQuitarPaquete.setVisible(false);
        btnQuitarPaquete.addActionListener(e -> quitarPaqueteSeleccionado());

        panelDimensiones.add(lblLargo);
        panelDimensiones.add(txtLargo);
        panelDimensiones.add(lblAncho);
        panelDimensiones.add(txtAncho);
        panelDimensiones.add(lblAlto);
        panelDimensiones.add(txtAlto);

        jPanel1.add(scrollPaquetes);
        jPanel1.add(btnQuitarPaquete);
        jPanel1.add(btnCajaPaquete);
        jPanel1.add(btnSobreDoc);
        jPanel1.add(panelDimensiones);
    }

    private void iniciarAutocompletado() {
        popupOrigen = new JPopupMenu();
        popupDestino = new JPopupMenu();
        configurarCampo(txt_origen, popupOrigen, true);
        configurarCampo(txt_destino, popupDestino, false);
    }

    private void seleccionarTipo(String tipo) {
        tipoSeleccionado = tipo;

        if (tipo.equals("caja")) {
            tipoEnvio = TipoEnvioDTO.PAQUETES;
            btnCajaPaquete.setBackground(new Color(255, 105, 0));
            btnCajaPaquete.setForeground(Color.WHITE);
            btnSobreDoc.setBackground(new Color(255, 247, 237));
            btnSobreDoc.setForeground(new Color(255, 105, 0));

            // Modo PAQUETES: ocultar dimensiones, mostrar tabla
            panelDimensiones.setVisible(false);
            scrollPaquetes.setVisible(true);
            btnQuitarPaquete.setVisible(true);

            // Abrir buscador de paquetes
            abrirBuscadorPaquetes();
        } else {
            tipoEnvio = TipoEnvioDTO.SOBRE;
            btnSobreDoc.setBackground(new Color(255, 105, 0));
            btnSobreDoc.setForeground(Color.WHITE);
            btnCajaPaquete.setBackground(new Color(255, 247, 237));
            btnCajaPaquete.setForeground(new Color(255, 105, 0));

            // Modo SOBRE: mostrar dimensiones, ocultar tabla
            scrollPaquetes.setVisible(false);
            btnQuitarPaquete.setVisible(false);
            panelDimensiones.setVisible(true);

            lblAlto.setVisible(false);
            txtAlto.setVisible(false);
        }

        jPanel1.revalidate();
        jPanel1.repaint();
    }

    /**
     * Abre el diálogo de búsqueda de paquetes filtrado por el emprendedor
     * activo y agrega el paquete seleccionado a
     *
     */
    private void abrirBuscadorPaquetes() {
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        DlgBuscarPaquetes dlg = new DlgBuscarPaquetes(parent, true);
        dlg.setVisible(true);

        PaqueteDTO seleccionado = dlg.getPaqueteSeleccionado();
        if (seleccionado == null) {
            return;
        }

        // Validar duplicado
        for (PaqueteDTO p : paquetesAgregados) {
            if (p.getIdPaquete() != null && p.getIdPaquete().equals(seleccionado.getIdPaquete())) {
                JOptionPane.showMessageDialog(this,
                        "Este paquete ya fue agregado al pedido.",
                        "Paquete duplicado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        paquetesAgregados.add(seleccionado);
        agregarFilaPaquete(seleccionado);
    }

    /**
     * Agrega una fila a la tabla con los datos del paquete recién seleccionado.
     */
    private void agregarFilaPaquete(PaqueteDTO p) {
        DefaultTableModel modelo = (DefaultTableModel) tblPaquetes.getModel();
        modelo.addRow(new Object[]{
            p.getNombre(),
            p.getTamaño() != null ? p.getTamaño().name() : "",
            String.format("%.2f", utileriaTablas.calcularPesoPaquete(p.getProductos()))
        });
    }

    /**
     * Quita el paquete seleccionado en la tabla.
     */
    private void quitarPaqueteSeleccionado() {
        int fila = tblPaquetes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un paquete en la tabla para quitarlo.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int filaModelo = tblPaquetes.convertRowIndexToModel(fila);
        paquetesAgregados.remove(filaModelo);
        DefaultTableModel modelo = (DefaultTableModel) tblPaquetes.getModel();
        modelo.removeRow(filaModelo);
    }

    /**
     * Calcula el peso total sumando todos los paquetes agregados.
     */
    private double calcularPesoTotal() {
        double total = 0;
        for (PaqueteDTO p : paquetesAgregados) {
            total += utileriaTablas.calcularPesoPaquete(p.getProductos());
        }
        return total;
    }

    private void configurarCampo(JTextField campo, JPopupMenu popup, boolean esOrigen) {
        Timer[] timer = {null};

        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_ESCAPE) {
                    return;
                }

                if (timer[0] != null) {
                    timer[0].stop();
                }

                timer[0] = new Timer(400, ev -> {
                    String texto = campo.getText().trim();
                    new Thread(() -> {
                        java.util.List<UbicacionDTO> sugerencias = control.buscarUbicacion(texto);
                        SwingUtilities.invokeLater(() -> mostrarPopup(campo, popup, sugerencias, esOrigen));
                    }).start();
                });
                timer[0].setRepeats(false);
                timer[0].start();
            }
        });
    }

    private void mostrarPopup(JTextField campo, JPopupMenu popup, List<UbicacionDTO> sugerencias, boolean esOrigen) {
        popup.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0), 1));
        popup.setBackground(Color.WHITE);
        popup.removeAll();

        if (sugerencias == null || sugerencias.isEmpty()) {
            popup.setVisible(false);
            return;
        }

        for (UbicacionDTO dto : sugerencias) {
            JMenuItem item = new JMenuItem(dto.getDescripcion());
            item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            item.setBackground(Color.WHITE);
            item.setForeground(Color.DARK_GRAY);
            item.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

            item.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    item.setBackground(new Color(255, 102, 0));
                    item.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    item.setBackground(Color.WHITE);
                    item.setForeground(Color.DARK_GRAY);
                }
            });
            item.addActionListener(e -> {
                campo.setText(dto.getDescripcion());
                popup.setVisible(false);

                if (esOrigen) {
                    origenLat = dto.getLatitud();
                    origenLng = dto.getLongitud();
                    System.out.println("Origen seleccionado: " + dto.getDescripcion() + " [" + origenLng + ", " + origenLat + "]");
                } else {
                    destinoLat = dto.getLatitud();
                    destinoLng = dto.getLongitud();
                    System.out.println("Destino seleccionado: " + dto.getDescripcion() + " [" + destinoLng + ", " + destinoLat + "]");
                }
            });

            popup.add(item);
        }

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
        jLabel8 = new javax.swing.JLabel();
        txt_peso = new javax.swing.JTextField();
        btn_solicitar = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Solicitud de Entrega");
        setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("PESO APROXIMADO (KG) *");

        txt_peso.setColumns(3);
        txt_peso.setForeground(new java.awt.Color(102, 102, 102));
        txt_peso.setText("0.0");

        btn_solicitar.setBackground(new java.awt.Color(0, 0, 0));
        btn_solicitar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_solicitar.setForeground(new java.awt.Color(255, 255, 255));
        btn_solicitar.setText("VALIDARD DATOS");
        btn_solicitar.setToolTipText("");
        btn_solicitar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
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
        if (txt_origen.getText().isBlank() || txt_destino.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Origen y destino son obligatorios.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tipoEnvio == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione el tipo de envío (Caja/Paquete o Sobre/Doc).",
                    "Tipo no seleccionado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idEmprendedor = SesionActiva.getInstancia().getIdEmprendedor();

        SolicitudEntregaDTO solicitud;

        if (tipoEnvio == TipoEnvioDTO.PAQUETES) {
            if (paquetesAgregados.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Agregue al menos un paquete al pedido.",
                        "Sin paquetes",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            solicitud = new SolicitudEntregaDTO(
                    txt_origen.getText(),
                    txt_destino.getText(),
                    new ArrayList<>(paquetesAgregados),
                    0,
                    idEmprendedor
            );
            solicitud.setPesoTotal(calcularPesoTotal());

        } else {
            try {
                double largo = Double.parseDouble(txtLargo.getText().trim());
                double ancho = Double.parseDouble(txtAncho.getText().trim());
                double peso = Double.parseDouble(txt_peso.getText().trim());

                if (largo <= 0 || ancho <= 0 || peso <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Las dimensiones y peso deben ser mayores a 0",
                            "Datos inválidos",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                SobreDTO sobre = new SobreDTO(largo, ancho, 0, peso, "Sobre/Doc");

                solicitud = new SolicitudEntregaDTO(
                        txt_origen.getText(),
                        txt_destino.getText(),
                        sobre,
                        0,
                        idEmprendedor
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingresa valores numéricos válidos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        solicitud.setEstado("Activo");
        RutaRequestDTO request = new RutaRequestDTO(txt_origen.getText(), txt_destino.getText());

        new FrmConsultarRutaEmprendedor(request, solicitud).setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btn_solicitarActionPerformed

    private void txt_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_origenActionPerformed

    private void txt_origenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_origenKeyPressed

    }//GEN-LAST:event_txt_origenKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_solicitar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_destino;
    private javax.swing.JTextField txt_origen;
    private javax.swing.JTextField txt_peso;
    // End of variables declaration//GEN-END:variables
}
