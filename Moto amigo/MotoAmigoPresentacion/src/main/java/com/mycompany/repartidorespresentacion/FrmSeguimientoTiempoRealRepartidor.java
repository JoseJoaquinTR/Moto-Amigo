/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.repartidorespresentacion;

import Utilerias.OSMTileFactoryCustom;
import Utilerias.utileriasBotones;
import com.mycompany.cusolicitarentrega.ActualizarEntrega;
import com.mycompany.cusolicitarentrega.IActualizarEntrega;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.Observers.EventoEntrega;
import com.mycompany.motoamigonegocio.Observers.GestorNotificacionesEntrega;
import com.mycompany.motoamigopresentacion.controladores.ControlRegistrarIncidente;
import com.mycompany.motoamigopresentacion.controladores.ControlSeguimiento;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
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
public class FrmSeguimientoTiempoRealRepartidor extends javax.swing.JFrame {

    private JXMapViewer mapViewer;
    private WaypointPainter<DefaultWaypoint> waypointPainter;
    private DefaultWaypoint marcador;
    private boolean pedidoRecolectado = false;
    private GeoPosition origen;
    private GeoPosition destino;
    private final ControlSeguimiento control;
    private ControlRegistrarIncidente controlIncidente;
    private String idEntregaActual;
    private RutaResponseDTO ruta;

    public FrmSeguimientoTiempoRealRepartidor(RutaResponseDTO ruta) {
        this.ruta = ruta;
        this.control = ControlSeguimiento.getInstance();
        this.controlIncidente = ControlRegistrarIncidente.getInstance();

        initComponents();
        inicializarUI();
        inicializarMapa();
        setLocationRelativeTo(null);
    }

    public FrmSeguimientoTiempoRealRepartidor(RutaResponseDTO ruta, String idEntrega) {
        this.idEntregaActual = idEntrega;
        this.ruta = ruta;
        this.control = ControlSeguimiento.getInstance();
        this.controlIncidente = ControlRegistrarIncidente.getInstance();

        initComponents();
        inicializarUI();
        inicializarMapa();
        setLocationRelativeTo(null);
    }

    /**
     * Aplica estilos visuales de los componentes.
     */
    private void inicializarUI() {
        panPrincipal.setLayout(new AbsoluteLayout());
        utileriasBotones.btnRedondeado(btnVolverMenu, "naranja", 30);
        utileriasBotones.btnRedondeado(btnReportar, "rojo", 30);
        panPrincipal.add(new PanelHeader(), new AbsoluteConstraints(0, 0, 1366, 130));

    }

    private Painter<JXMapViewer> getRoutePainter(List<GeoPosition> track) {
        return (Graphics2D g, JXMapViewer map, int w, int h) -> {
            g = (Graphics2D) g.create();
            Rectangle rect = map.getViewportBounds();
            g.translate(-rect.x, -rect.y);

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(255, 102, 0));
            g.setStroke(new BasicStroke(4));

            int lastX = -1;
            int lastY = -1;

            for (GeoPosition gp : track) {
                Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
                if (lastX != -1) {
                    g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
                }
                lastX = (int) pt.getX();
                lastY = (int) pt.getY();
            }
            g.dispose();
        };
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

        origen = new GeoPosition(ruta.getLatOrigen(), ruta.getLngOrigen());
        destino = new GeoPosition(ruta.getLatDestino(), ruta.getLngDestino());

        marcador = new DefaultWaypoint(origen);
        waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(new HashSet<>(Arrays.asList(marcador)));

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(compoundPainter);

        mapViewer.setAddressLocation(origen);
        mapViewer.setZoom(3);

        panelMapa.removeAll();
        panelMapa.setLayout(new BorderLayout());
        panelMapa.add(mapViewer, BorderLayout.CENTER);

        panelMapa.revalidate();
        panelMapa.repaint();

        iniciarSeguimiento();
    }

    /**
     * Crea temporizador que cada 3 segundos consulta la siguiente ubicación del
     * repartidor usando el modulo de funcionalidad. Actualiza el estado y el
     * historial de texto y mueve el marcador en el mapa usando mover(lat, lng)
     *
     */
    private void iniciarSeguimiento() {
        Timer timer = new Timer(3000, null);
        timer.addActionListener(e -> {
            UbicacionDTO ubi = control.obtenerSiguienteUbicacion();

            if (ubi == null) {
                timer.stop();
                JOptionPane.showMessageDialog(this,
                        "No hay puntos de ruta para mostrar.",
                        "Ruta no disponible",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            moverMarcador(ubi);
            GestorNotificacionesEntrega.getInstance().notificar(
                    EventoEntrega.UBICACION_ACTUALIZADA,
                    ubi
            );

            if (ubi.getDescripcion().toLowerCase().contains("llegó al origen") && !pedidoRecolectado) {
                timer.stop();

                int respuesta = JOptionPane.showConfirmDialog(this,
                        "¿Has recibido el pedido correctamente?",
                        "Confirmar Recolección",
                        JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    desbloquearDestino();
                    timer.start();
                } else {
                    int confirmarCancelacion = JOptionPane.showConfirmDialog(this,
                            "¿Deseas cancelar el pedido? El pedido quedará disponible para otros repartidores.",
                            "Cancelar Pedido",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmarCancelacion == JOptionPane.YES_OPTION) {
                        controlIncidente.cancelarPedidoPorNoRecoleccion();
                        this.dispose();
                    } else {
                        timer.start();
                    }
                }
                return;
            }

            if (control.haTerminado()) {
                timer.stop();
                if (idEntregaActual != null) {
                    try {
                        IActualizarEntrega cu = ActualizarEntrega.crear();
                        cu.finalizar(idEntregaActual);
                    } catch (NegocioException ex) {
                        JOptionPane.showMessageDialog(this,
                        "Error al finalizar entrega",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }

                GestorNotificacionesEntrega.getInstance().notificar(
                        EventoEntrega.PEDIDO_ENTREGADO,
                        ruta
                );
                JOptionPane.showMessageDialog(this,
                        "Entrega finalizada correctamente.",
                        "Pedido entregado",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        timer.start();
    }

    private void desbloquearDestino() {
        pedidoRecolectado = true;

        List<GeoPosition> puntosRuta = Arrays.asList(origen, destino);

        waypointPainter.setWaypoints(new HashSet<>(Arrays.asList(
                new DefaultWaypoint(origen),
                new DefaultWaypoint(destino)
        )));

        Painter<JXMapViewer> rutaPainter = getRoutePainter(puntosRuta);

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(rutaPainter);
        painters.add(waypointPainter);

        mapViewer.setOverlayPainter(new CompoundPainter<>(painters));

        mapViewer.calculateZoomFrom(new HashSet<>(puntosRuta));

        JOptionPane.showMessageDialog(this, "Ruta de entrega desbloqueada. Dirígete al destino.");
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
        btnVolverMenu = new javax.swing.JButton();
        btnReportar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1008, 738));
        setResizable(false);

        panPrincipal.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGap(0, 604, Short.MAX_VALUE)
        );

        btnVolverMenu.setBackground(new java.awt.Color(255, 102, 0));
        btnVolverMenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolverMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverMenu.setText("Volver al menu");
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });

        btnReportar.setBackground(new java.awt.Color(204, 0, 51));
        btnReportar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReportar.setForeground(new java.awt.Color(255, 255, 255));
        btnReportar.setText("Reportar entrega");
        btnReportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panPrincipalLayout = new javax.swing.GroupLayout(panPrincipal);
        panPrincipal.setLayout(panPrincipalLayout);
        panPrincipalLayout.setHorizontalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panPrincipalLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(btnReportar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(btnVolverMenu)
                .addContainerGap(274, Short.MAX_VALUE))
        );
        panPrincipalLayout.setVerticalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPrincipalLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        dispose();
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    private void btnReportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportarActionPerformed
        controlIncidente.irAFormularioIncidente(this);
    }//GEN-LAST:event_btnReportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReportar;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JPanel panPrincipal;
    private javax.swing.JPanel panelMapa;
    // End of variables declaration//GEN-END:variables
}
