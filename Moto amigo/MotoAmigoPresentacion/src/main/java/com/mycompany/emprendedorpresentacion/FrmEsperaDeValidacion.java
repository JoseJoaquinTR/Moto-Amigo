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
public class FrmEsperaDeValidacion extends JFrame {

    public FrmEsperaDeValidacion() {
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Cuenta en revisión");
        setSize(1008, 738);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(new Color(240, 242, 245));
        
        JLabel lblTitulo = new JLabel("Cuenta en revision");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(254, 295, 500, 40);

        JLabel lblLinea1 = new JLabel("Tu solicitud fue enviada correctamente");
        lblLinea1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblLinea1.setForeground(Color.GRAY);
        lblLinea1.setHorizontalAlignment(SwingConstants.CENTER);
        lblLinea1.setBounds(204, 350, 600, 25);

        JLabel lblLinea2 = new JLabel("Un administrador revisara tu informacion");
        lblLinea2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblLinea2.setForeground(Color.GRAY);
        lblLinea2.setHorizontalAlignment(SwingConstants.CENTER);
        lblLinea2.setBounds(204, 380, 600, 25);

        JLabel lblLinea3 = new JLabel("Tu cuenta sera activada en un plazo de 24 a 72 horas");
        lblLinea3.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblLinea3.setForeground(new Color(230, 81, 0));
        lblLinea3.setHorizontalAlignment(SwingConstants.CENTER);
        lblLinea3.setBounds(204, 410, 600, 25);

        JLabel lblVolver = new JLabel("Volver al inicio");
        lblVolver.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblVolver.setForeground(new Color(100, 100, 180));
        lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolver.setBounds(354, 470, 300, 25);
        lblVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlNavegacionEmprendedor.getInstancia().irAPrincipal();
            }
        });

        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblLinea1);
        pnlPrincipal.add(lblLinea2);
        pnlPrincipal.add(lblLinea3);
        pnlPrincipal.add(lblVolver);

        setContentPane(pnlPrincipal);
    }
}
