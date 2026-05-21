package com.mycompany.emprendedorpresentacion;

import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Omar
 */
public class FrmPrincipal extends JFrame {

    private JLabel lblTitulo;
    private JButton btnIniciarSesion;
    private JButton btnCrearCuenta;

    public FrmPrincipal() {
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("motoAmigo");
        setSize(1008, 738);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(Color.BLACK);

        lblTitulo = new JLabel("motoAmigo");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(54, 290, 900, 50);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBackground(new Color(230, 81, 0));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setBounds(54, 360, 900, 50);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.addActionListener(e -> {
            ControlNavegacionEmprendedor.getInstancia().irAIniciarSesion();
        });

        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.setBackground(Color.BLACK);
        btnCrearCuenta.setForeground(Color.WHITE);
        btnCrearCuenta.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCrearCuenta.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        btnCrearCuenta.setFocusPainted(false);
        btnCrearCuenta.setBounds(54, 425, 900, 50);
        btnCrearCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearCuenta.addActionListener(e -> {
            ControlNavegacionEmprendedor.getInstancia().irATipoUsuario();

        });

        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(btnIniciarSesion);
        pnlPrincipal.add(btnCrearCuenta);

        setContentPane(pnlPrincipal);
    }
}
