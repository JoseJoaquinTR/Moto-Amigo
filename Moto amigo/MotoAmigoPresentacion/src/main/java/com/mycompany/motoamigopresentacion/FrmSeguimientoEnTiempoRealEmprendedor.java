package com.mycompany.motoamigopresentacion;

import Utilerias.OSMTileFactoryCustom;
import Utilerias.utileriasBotones;
import com.mycompany.cusolicitarentrega.FuncionalidadSeguimiento;
import com.mycompany.cusolicitarentrega.IFuncionalidadSeguimiento;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.Observer.EventoEntrega;
import com.mycompany.motoamigonegocio.Observer.GestorNotificacionesEntrega;
import com.mycompany.motoamigonegocio.Observer.INotificacionPedido;
import java.awt.BorderLayout;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import panelesUtilerias.PanelHeader;

/**
 *
 * @author joset
 */
public class FrmSeguimientoEnTiempoRealEmprendedor extends javax.swing.JFrame implements INotificacionPedido {

    private JXMapViewer mapViewer;
    private WaypointPainter<DefaultWaypoint> waypointPainter;
    private DefaultWaypoint marcador;

    private RutaResponseDTO ruta;
    private final IFuncionalidadSeguimiento funcionalidad;

    /**
     * Creates new form FrmSeguimientoEnTiempoReal_Emprendedor
     */
    public FrmSeguimientoEnTiempoRealEmprendedor(RutaResponseDTO ruta) {
        this.ruta = ruta;
        this.funcionalidad = FuncionalidadSeguimiento.crear();
        initComponents();
        GestorNotificacionesEntrega.getInstance().agregarObserver(this);
        inicializarUI();
        inicializarMapa();
        setLocationRelativeTo(null);
    }

    private void inicializarUI() {
        panPrincipal.setLayout(new AbsoluteLayout());
        utileriasBotones.btnRedondeado(btnContactarRepa, "naranja", 30);
        utileriasBotones.btnRedondeado(btnVolverMenu, "naranja", 30);
        panPrincipal.add(new PanelHeader(), new AbsoluteConstraints(0, 0, 1366, 130));

    }

    private void inicializarMapa() {
        mapViewer = new JXMapViewer();

        OSMTileFactoryCustom tileFactory = new OSMTileFactoryCustom();
        tileFactory.setThreadPoolSize(4);
        mapViewer.setTileFactory(tileFactory);

        PanMouseInputListener mm = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mm);
        mapViewer.addMouseMotionListener(mm);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        GeoPosition posInicial = new GeoPosition(ruta.getLatOrigen(), ruta.getLngOrigen());

        mapViewer.setAddressLocation(posInicial);
        mapViewer.setZoom(4);

        marcador = new DefaultWaypoint(posInicial);
        waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(new HashSet<>(Arrays.asList(marcador)));

        mapViewer.setOverlayPainter(waypointPainter);

        panelMapa.setLayout(new BorderLayout());
        panelMapa.add(mapViewer, BorderLayout.CENTER);

        iniciarSeguimiento();
    }

    /**
     * Crea temporizador que cada 3 segundos consulta la siguiente ubicación del
     * repartidor usando el modulo de funcionalidad. Actualiza el estado y el
     * historial de texto y mueve el marcador en el mapa usando mover(lat, lng)
     *
     */
    private void iniciarSeguimiento() {
        lblEstado.setText("Estado: Esperando actualización del repartidor");
        txtSeguimiento.append("[" + LocalTime.now().withNano(0)
                + "] Esperando ubicación del repartidor...\n");
        txtSeguimiento.setCaretPosition(txtSeguimiento.getDocument().getLength());
    }

    @Override
    public void actualizar(String evento, Object datos) {
        EventoEntrega eventoEntrega = convertirEvento(evento);
        if (eventoEntrega == null) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            switch (eventoEntrega) {
                case UBICACION_ACTUALIZADA -> {
                    if (datos instanceof UbicacionDTO ubi) {
                        lblEstado.setText("Estado: " + ubi.getDescripcion());
                        txtSeguimiento.append("[" + LocalTime.now().withNano(0)
                                + "] " + ubi.getDescripcion() + "\n");
                        txtSeguimiento.setCaretPosition(txtSeguimiento.getDocument().getLength());
                        moverMarcador(ubi);
                    }
                }
                case PEDIDO_ENTREGADO -> {
                    lblEstado.setText("Estado: Entrega Finalizada");
                    txtSeguimiento.append("[" + LocalTime.now().withNano(0)
                            + "] El pedido ha sido entregado con éxito.\n");
                    txtSeguimiento.setCaretPosition(txtSeguimiento.getDocument().getLength());
                    JOptionPane.showMessageDialog(this,
                            "¡El repartidor ha llegado al destino! El pedido se marca como entregado.",
                            "Pedido Completado",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                case PEDIDO_CANCELADO -> {
                    lblEstado.setText("Estado: Pedido cancelado");
                    txtSeguimiento.append("[" + LocalTime.now().withNano(0)
                            + "] El pedido fue cancelado y volvió a quedar disponible.\n");
                    txtSeguimiento.setCaretPosition(txtSeguimiento.getDocument().getLength());
                }
                case PEDIDO_NO_COMPLETADO -> {
                    lblEstado.setText("Estado: Pedido no completado");
                    txtSeguimiento.append("[" + LocalTime.now().withNano(0)
                            + "] El repartidor reportó un incidente.\n");
                    txtSeguimiento.setCaretPosition(txtSeguimiento.getDocument().getLength());
                }
                default -> {
                }
            }
        });
    }

    private EventoEntrega convertirEvento(String evento) {
        try {
            return EventoEntrega.valueOf(evento);
        } catch (IllegalArgumentException | NullPointerException ex) {
            return null;
        }
    }

    @Override
    public void dispose() {
        GestorNotificacionesEntrega.getInstance().eliminarObserver(this);
        super.dispose();
    }

    /**
     * Mueve el marcador en el mapa al hilo de. Solo actúa si el mapa ya terminó
     * de cargar.
     */
    private void moverMarcador(UbicacionDTO ubi) {
        if (mapViewer == null || ubi == null) {
            return;
        }

        GeoPosition nuevaPos = new GeoPosition(ubi.getLatitud(), ubi.getLongitud());
        marcador = new DefaultWaypoint(nuevaPos);
        waypointPainter.setWaypoints(new HashSet<>(Arrays.asList(marcador)));

        mapViewer.setAddressLocation(nuevaPos);

        mapViewer.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panPrincipal = new javax.swing.JPanel();
        panelMapa = new javax.swing.JPanel();
        panelInformacionRuta = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSeguimiento = new javax.swing.JTextArea();
        btnContactarRepa = new javax.swing.JButton();
        btnVolverMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1008, 738));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panPrincipal.setMaximumSize(new java.awt.Dimension(1008, 738));
        panPrincipal.setMinimumSize(new java.awt.Dimension(1008, 738));
        panPrincipal.setPreferredSize(new java.awt.Dimension(1008, 738));

        panelMapa.setBackground(new java.awt.Color(255, 255, 255));
        panelMapa.setInheritsPopupMenu(true);
        panelMapa.setMaximumSize(new java.awt.Dimension(1008, 438));
        panelMapa.setMinimumSize(new java.awt.Dimension(1008, 438));
        panelMapa.setName(""); // NOI18N
        panelMapa.setPreferredSize(new java.awt.Dimension(1008, 438));

        javax.swing.GroupLayout panelMapaLayout = new javax.swing.GroupLayout(panelMapa);
        panelMapa.setLayout(panelMapaLayout);
        panelMapaLayout.setHorizontalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelMapaLayout.setVerticalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(102, 102, 102));
        lblEstado.setText("Estado");

        txtSeguimiento.setBackground(new java.awt.Color(245, 245, 245));
        txtSeguimiento.setColumns(20);
        txtSeguimiento.setRows(5);
        jScrollPane1.setViewportView(txtSeguimiento);

        javax.swing.GroupLayout panelInformacionRutaLayout = new javax.swing.GroupLayout(panelInformacionRuta);
        panelInformacionRuta.setLayout(panelInformacionRutaLayout);
        panelInformacionRutaLayout.setHorizontalGroup(
            panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionRutaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformacionRutaLayout.createSequentialGroup()
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelInformacionRutaLayout.setVerticalGroup(
            panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionRutaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnContactarRepa.setBackground(new java.awt.Color(255, 102, 0));
        btnContactarRepa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnContactarRepa.setForeground(new java.awt.Color(255, 255, 255));
        btnContactarRepa.setText("Contactar Repartidor");
        btnContactarRepa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactarRepaActionPerformed(evt);
            }
        });

        btnVolverMenu.setBackground(new java.awt.Color(255, 102, 0));
        btnVolverMenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolverMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverMenu.setText("Volver al menu");
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panPrincipalLayout = new javax.swing.GroupLayout(panPrincipal);
        panPrincipal.setLayout(panPrincipalLayout);
        panPrincipalLayout.setHorizontalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInformacionRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panPrincipalLayout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(btnContactarRepa)
                .addGap(99, 99, 99)
                .addComponent(btnVolverMenu)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        panPrincipalLayout.setVerticalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPrincipalLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(panelInformacionRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContactarRepa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContactarRepaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactarRepaActionPerformed
        RepartidorDTO repartidor = funcionalidad.obtenerRepartidorAsignado(ruta.getIdRepartidorAsignado());
        
        if (repartidor == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró información del repartidor asignado.",
                    "Sin repartidor",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Nombre: " + repartidor.getNombre() + "\nTeléfono: " + repartidor.getTelefono(),
                "Contactar Repartidor",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnContactarRepaActionPerformed

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        new FrmMenuPrincipalEmprendedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContactarRepa;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel panPrincipal;
    private javax.swing.JPanel panelInformacionRuta;
    private javax.swing.JPanel panelMapa;
    private javax.swing.JTextArea txtSeguimiento;
    // End of variables declaration//GEN-END:variables
}
