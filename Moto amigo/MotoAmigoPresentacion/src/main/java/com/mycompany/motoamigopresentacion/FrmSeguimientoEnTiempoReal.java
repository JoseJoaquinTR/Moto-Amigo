/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motoamigopresentacion;

import Utilerias.OSMTileFactoryCustom;
import Utilerias.utileriasBotones;

import com.mycompany.cusolicitarentrega.FuncionalidadSeguimiento;
import com.mycompany.cusolicitarentrega.IFuncionalidadSeguimiento;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigopresentacion.controladores.ControlRegistrarIncidente;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.swing.Timer;
import org.jxmapviewer.JXMapViewer;
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
public class FrmSeguimientoEnTiempoReal extends javax.swing.JFrame {

    private JXMapViewer mapViewer;
    private WaypointPainter<DefaultWaypoint> waypointPainter;
    private DefaultWaypoint marcador;
    private boolean pedidoRecolectado = false;
    private GeoPosition origen;
    private GeoPosition destino;
    private final IFuncionalidadSeguimiento funcionalidad;
    private ControlRegistrarIncidente control;

    private RutaResponseDTO ruta;

    public FrmSeguimientoEnTiempoReal(RutaResponseDTO ruta) {
        this.ruta = ruta;
        this.funcionalidad = FuncionalidadSeguimiento.crear();
        this.control = ControlRegistrarIncidente.getInstance();
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
        utileriasBotones.btnNaranja(btnVolverMenu);
        utileriasBotones.btnRojo(btnReportar);
        panPrincipal.add(new PanelHeader(), new AbsoluteConstraints(0, 0, 1366, 130));

    }

    private org.jxmapviewer.painter.Painter<JXMapViewer> getRoutePainter(java.util.List<GeoPosition> track) {
        return (Graphics2D g, JXMapViewer map, int w, int h) -> {
            g = (Graphics2D) g.create();
            Rectangle rect = map.getViewportBounds();
            g.translate(-rect.x, -rect.y);

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new java.awt.Color(255, 102, 0));
            g.setStroke(new java.awt.BasicStroke(4));

            int lastX = -1;
            int lastY = -1;

            for (GeoPosition gp : track) {
                java.awt.geom.Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
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

        org.jxmapviewer.input.PanMouseInputListener mm = new org.jxmapviewer.input.PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mm);
        mapViewer.addMouseMotionListener(mm);
        mapViewer.addMouseWheelListener(new org.jxmapviewer.input.ZoomMouseWheelListenerCenter(mapViewer));

        origen = new GeoPosition(ruta.getLatOrigen(), ruta.getLngOrigen());
        destino = new GeoPosition(ruta.getLatDestino(), ruta.getLngDestino());

        marcador = new DefaultWaypoint(origen);
        waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(new HashSet<>(Arrays.asList(marcador)));

        List<org.jxmapviewer.painter.Painter<JXMapViewer>> painters = new ArrayList<>();
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
     * Agrega zoom con rueda del mouse y desplazamiento con arrastre al mapa.
     */
    private void agregarInteraccionMapa() {
        org.jxmapviewer.input.PanMouseInputListener mm = new org.jxmapviewer.input.PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mm);
        mapViewer.addMouseMotionListener(mm);

        mapViewer.addMouseWheelListener(new org.jxmapviewer.input.ZoomMouseWheelListenerCenter(mapViewer));
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
            UbicacionDTO ubi = funcionalidad.obtenerSiguiente();

            moverMarcador(ubi);

            if (ubi.getDescripcion().toLowerCase().contains("llegó al origen") && !pedidoRecolectado) {
                timer.stop(); 

                int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
                        "¿Has recibido el pedido correctamente?",
                        "Confirmar Recolección",
                        javax.swing.JOptionPane.YES_NO_OPTION);

                if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
                    desbloquearDestino();
                    timer.start();
                } else {
                    control.irAFormularioIncidente();
                    this.dispose();
                }
            }
        });
        timer.start();
    }

    private void desbloquearDestino() {
        pedidoRecolectado = true;

        java.util.List<GeoPosition> puntosRuta = Arrays.asList(origen, destino);

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

        javax.swing.JOptionPane.showMessageDialog(this, "Ruta de entrega desbloqueada. Dirígete al destino.");
    }

    /**
     * Mueve el marcador en el mapa al hilo de. Solo actúa si el mapa ya terminó
     * de cargar.
     */
    private void moverMarcador(UbicacionDTO ubi) {
        if (mapViewer == null) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
            .addGap(0, 610, Short.MAX_VALUE)
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
                .addGap(165, 165, 165)
                .addComponent(btnReportar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnVolverMenu)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        panPrincipalLayout.setVerticalGroup(
            panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPrincipalLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    private void btnReportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportarActionPerformed
        control.irAFormularioIncidente();
    }//GEN-LAST:event_btnReportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReportar;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JPanel panPrincipal;
    private javax.swing.JPanel panelMapa;
    // End of variables declaration//GEN-END:variables
}
