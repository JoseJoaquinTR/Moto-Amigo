package com.mycompany.emprendedorpresentacion;

import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Omar
 */
public class FrmTipoUsuario extends JFrame{

    public FrmTipoUsuario() {
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Crear Cuenta");
        setSize(1008, 738);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(new Color(240, 242, 245));

        JLabel lblTitulo = new JLabel("Crear Cuenta");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitulo.setBounds(354, 50, 300, 40);

        JLabel lblSubtitulo = new JLabel("Selecciona el tipo de usuario para continuar.");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setBounds(354, 90, 350, 25);

        JPanel pnlEmprendedor = new JPanel(null);
        pnlEmprendedor.setBackground(Color.BLACK);
        pnlEmprendedor.setBounds(354, 130, 300, 80);
        pnlEmprendedor.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblEmprendedor = new JLabel("Emprendedor");
        lblEmprendedor.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblEmprendedor.setForeground(Color.WHITE);
        lblEmprendedor.setBounds(50, 15, 200, 25);

        JLabel lblDescEmprendedor = new JLabel("Registra tu negocio y solicita entregas.");
        lblDescEmprendedor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDescEmprendedor.setForeground(Color.LIGHT_GRAY);
        lblDescEmprendedor.setBounds(50, 42, 230, 20);

        pnlEmprendedor.add(lblEmprendedor);
        pnlEmprendedor.add(lblDescEmprendedor);
        pnlEmprendedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlNavegacionEmprendedor.getInstancia().irARegistroEmprendedor();
            }
        });

        JPanel pnlRepartidor = new JPanel(null);
        pnlRepartidor.setBackground(new Color(230, 81, 0));
        pnlRepartidor.setBounds(354, 225, 300, 80);
        pnlRepartidor.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblRepartidor = new JLabel("Repartidor");
        lblRepartidor.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblRepartidor.setForeground(Color.WHITE);
        lblRepartidor.setBounds(50, 15, 200, 25);

        JLabel lblDescRepartidor = new JLabel("Regístrate para recibir pedidos disponibles.");
        lblDescRepartidor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDescRepartidor.setForeground(Color.WHITE);
        lblDescRepartidor.setBounds(50, 42, 230, 20);

        pnlRepartidor.add(lblRepartidor);
        pnlRepartidor.add(lblDescRepartidor);

        JLabel lblVolver = new JLabel("Volver");
        lblVolver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblVolver.setForeground(new Color(100, 100, 180));
        lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolver.setBounds(354, 320, 300, 25);
        lblVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlNavegacionEmprendedor.getInstancia().irAPrincipal();
            }
        });

        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblSubtitulo);
        pnlPrincipal.add(pnlEmprendedor);
        pnlPrincipal.add(pnlRepartidor);
        pnlPrincipal.add(lblVolver);

        setContentPane(pnlPrincipal);
    }
}
