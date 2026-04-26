/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motoamigopresentacion;

import Utilerias.OSMTileFactoryCustom;
import com.consultarruta.servicios.mapBox.MapBoxService;
import com.mycompany.cusolicitarentrega.ConsultarRuta;
import com.mycompany.cusolicitarentrega.IConsultarRuta;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigonegocio.IRutaBO;
import com.mycompany.motoamigonegocio.RutaBO;
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
import java.util.logging.Logger;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import panelesUtilerias.PanelHeader;

/**
 *
 * @author calo2
 */
public class frmConsultarRuta extends javax.swing.JFrame {

    private IConsultarRuta casoUso;
    private JXMapViewer mapViewer;
    private WaypointPainter<DefaultWaypoint> waypointPainter;
    private static final Logger LOGGER = Logger.getLogger(frmConsultarRuta.class.getName());
    private RutaResponseDTO response;
    public frmConsultarRuta(RutaRequestDTO request) {
        initComponents();

        IRutaBO rutaBO = new RutaBO(MapBoxService.getInstance());
        casoUso = new ConsultarRuta(rutaBO);

        // Tamaño predeterminado y centrado
        this.setSize(1008, 738);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        try {
            // Consultar ruta con direcciones en texto
            this.response = casoUso.consultarRuta(request);

            if (response != null && response.isRutaValida()) {
                inicializarPanelMapa(response);

                lblDistancia.setText(String.format("%.2f km", response.getDistancia()));
                lblETA.setText(response.getTiempoEstimado() + " min");
                lblCosto.setText("$" + String.format("%.2f MXN", response.getCosto()));
            } else {
                mostrarErrorEnLabels("No se pudo calcular la ruta");
            }
        } catch (Exception e) {
            LOGGER.severe("Error al consultar ruta: " + e.getMessage());
            mostrarErrorEnLabels("Error al consultar ruta");
        }

        panelPrincipal.setLayout(new AbsoluteLayout());
        panelPrincipal.add(new PanelHeader(), new AbsoluteConstraints(0, 0, 1366, 130));
    }

    /**
     * Inicializa el mapa usando las coordenadas ya calculadas en el response.
     */
    private void inicializarPanelMapa(RutaResponseDTO response) {
        mapViewer = new JXMapViewer();
        org.jxmapviewer.input.PanMouseInputListener mm = new org.jxmapviewer.input.PanMouseInputListener(mapViewer);
            mapViewer.addMouseListener(mm);
            mapViewer.addMouseMotionListener(mm);
            mapViewer.addMouseWheelListener(new org.jxmapviewer.input.ZoomMouseWheelListenerCenter(mapViewer));
        OSMTileFactoryCustom tileFactory = new OSMTileFactoryCustom();
        tileFactory.setThreadPoolSize(4);
        mapViewer.setTileFactory(tileFactory);

        if (response.getLatOrigen() == null || response.getLngOrigen() == null
                || response.getLatDestino() == null || response.getLngDestino() == null) {
            LOGGER.severe("El response no contiene coordenadas válidas");
            mostrarErrorEnLabels("Coordenadas inválidas");
            return;
        }

        GeoPosition origen = new GeoPosition(response.getLatOrigen(), response.getLngOrigen());
        GeoPosition destino = new GeoPosition(response.getLatDestino(), response.getLngDestino());

        List<GeoPosition> rutaPuntos = Arrays.asList(origen, destino);

        waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(new HashSet<>(Arrays.asList(new DefaultWaypoint(origen), new DefaultWaypoint(destino))));

        Painter<JXMapViewer> rutaPainter = getRoutePainter(rutaPuntos);

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(rutaPainter);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(compoundPainter);

        mapViewer.calculateZoomFrom(new HashSet<>(rutaPuntos));
        mapViewer.setZoom(mapViewer.getZoom() - 1); 
        panelMapa.removeAll();
        panelMapa.setLayout(new BorderLayout());
        panelMapa.add(mapViewer, BorderLayout.CENTER);

        panelMapa.revalidate();
        panelMapa.repaint();
    }

    private void mostrarErrorEnLabels(String mensaje) {
        lblDistancia.setText(mensaje);
        lblETA.setText("-");
        lblCosto.setText("-");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelMapa = new javax.swing.JPanel();
        panelInformacionRuta = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        lblDistancia = new javax.swing.JLabel();
        lblETA = new javax.swing.JLabel();
        btnEnviarSolicitud = new javax.swing.JButton();
        btnCancelarSolicitud = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1008, 738));
        setResizable(false);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMapa.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMapaLayout = new javax.swing.GroupLayout(panelMapa);
        panelMapa.setLayout(panelMapaLayout);
        panelMapaLayout.setHorizontalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
        );
        panelMapaLayout.setVerticalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        panelPrincipal.add(panelMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 52, 1090, 440));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Distancia");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Tiempo est.");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Costo");

        lblCosto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCosto.setForeground(new java.awt.Color(255, 102, 0));

        lblDistancia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblETA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelInformacionRutaLayout = new javax.swing.GroupLayout(panelInformacionRuta);
        panelInformacionRuta.setLayout(panelInformacionRutaLayout);
        panelInformacionRutaLayout.setHorizontalGroup(
            panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionRutaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(panelInformacionRutaLayout.createSequentialGroup()
                        .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblETA, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(lblDistancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInformacionRutaLayout.setVerticalGroup(
            panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionRutaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDistancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(lblETA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformacionRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelInformacionRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 510, -1, -1));

        btnEnviarSolicitud.setBackground(new java.awt.Color(255, 102, 0));
        btnEnviarSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEnviarSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarSolicitud.setText("Publicar Solicitud");
        btnEnviarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarSolicitudActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnEnviarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 660, -1, -1));

        btnCancelarSolicitud.setBackground(new java.awt.Color(255, 102, 0));
        btnCancelarSolicitud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelarSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarSolicitud.setText("Cancelar");
        btnCancelarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSolicitudActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnCancelarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 660, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1086, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private Painter<JXMapViewer> getRoutePainter(List<GeoPosition> track) {
        return new Painter<JXMapViewer>() {
            @Override
            public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
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
            }
        };
    }
    private void btnEnviarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarSolicitudActionPerformed
        new FrmSeguimientoEnTiempoReal_Emprendedor(this.response).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEnviarSolicitudActionPerformed

    private void btnCancelarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSolicitudActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarSolicitudActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarSolicitud;
    private javax.swing.JButton btnEnviarSolicitud;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblETA;
    private javax.swing.JPanel panelInformacionRuta;
    private javax.swing.JPanel panelMapa;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
