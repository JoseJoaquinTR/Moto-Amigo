package com.mycompany.emprendedorpresentacion;

import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Omar
 */
public class FrmRegistroEmprendedor extends JFrame{

    private int pasoActual = 1;
    private final int TOTAL_PASOS = 3;

    private JPanel pnlProgreso;
    private JLabel lblPaso;
    private JPanel pnlContenedor;
    private CardLayout cardLayout;

    private PanelPaso1 paso1;
    private PanelPaso2 paso2;
    private PanelPaso3 paso3;

    public FrmRegistroEmprendedor() {
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Registro Emprendedor");
        setSize(1008, 738);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(new Color(240, 242, 245));

        // Título
        JLabel lblTitulo = new JLabel("Registro Emprendedor");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(254, 30, 500, 35);

        JLabel lblSubtitulo = new JLabel("Completa tus datos en tres partes.");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(254, 65, 500, 20);

        // Barra de progreso 
        pnlProgreso = new JPanel(null);
        pnlProgreso.setBackground(new Color(240, 242, 245));
        pnlProgreso.setBounds(254, 90, 500, 12);

        JPanel seg1 = new JPanel();
        seg1.setBackground(new Color(230, 81, 0));
        seg1.setBounds(0, 0, 158, 8);

        JPanel seg2 = new JPanel();
        seg2.setBackground(new Color(200, 200, 200));
        seg2.setBounds(163, 0, 158, 8);

        JPanel seg3 = new JPanel();
        seg3.setBackground(new Color(200, 200, 200));
        seg3.setBounds(326, 0, 158, 8);

        pnlProgreso.add(seg1);
        pnlProgreso.add(seg2);
        pnlProgreso.add(seg3);

        lblPaso = new JLabel("PASO 1 DE 3");
        lblPaso.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblPaso.setForeground(new Color(230, 81, 0));
        lblPaso.setBounds(254, 105, 200, 20);

        cardLayout = new CardLayout();
        pnlContenedor = new JPanel(cardLayout);
        pnlContenedor.setBackground(new Color(240, 242, 245));
        pnlContenedor.setBounds(204, 130, 600, 520);

        paso1 = new PanelPaso1(this);
        paso2 = new PanelPaso2(this);
        paso3 = new PanelPaso3(this);

        pnlContenedor.add(paso1, "PASO1");
        pnlContenedor.add(paso2, "PASO2");
        pnlContenedor.add(paso3, "PASO3");

        cardLayout.show(pnlContenedor, "PASO1");

        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblSubtitulo);
        pnlPrincipal.add(pnlProgreso);
        pnlPrincipal.add(lblPaso);
        pnlPrincipal.add(pnlContenedor);

        setContentPane(pnlPrincipal);
    }

    public void irSiguiente() {
        if (pasoActual < TOTAL_PASOS) {
            pasoActual++;
            actualizarProgreso();
            cardLayout.show(pnlContenedor, "PASO" + pasoActual);
        }
    }

    public void irAtras() {
        if (pasoActual > 1) {
            pasoActual--;
            actualizarProgreso();
            cardLayout.show(pnlContenedor, "PASO" + pasoActual);
        } else {
            ControlNavegacionEmprendedor.getInstancia().irATipoUsuario();
        }
    }

    private void actualizarProgreso() {
        lblPaso.setText("PASO " + pasoActual + " DE " + TOTAL_PASOS);
        for (int i = 0; i < pnlProgreso.getComponentCount(); i++) {
            JPanel seg = (JPanel) pnlProgreso.getComponent(i);
            seg.setBackground(i < pasoActual
                    ? new Color(230, 81, 0)
                    : new Color(200, 200, 200));
        }
        pnlProgreso.repaint();
    }

    public PanelPaso1 getPaso1() {
        return paso1;
    }

    public PanelPaso2 getPaso2() {
        return paso2;
    }

    public PanelPaso3 getPaso3() {
        return paso3;
    }
}
