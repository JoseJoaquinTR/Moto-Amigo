
package Utilerias;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class utileriasMensajes {

    /**
     * Dialog Alert Incidente
     */
    public static void mostrarAlertaIncidente(Frame parent, String titulo, String descripcion, Runnable alVolver) {

        JDialog dialog = new JDialog(parent, true);
        dialog.setUndecorated(true);
        dialog.setSize(500, 420);
        dialog.setLocationRelativeTo(parent);
        dialog.setBackground(new Color(0, 0, 0, 0));

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 246, 250));
        panel.setBorder(new EmptyBorder(50, 40, 50, 40));

        //Círculo con ícono
        JPanel circulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 235, 210)); 
                g2.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        circulo.setOpaque(false);
        circulo.setPreferredSize(new Dimension(100, 100));
        circulo.setMaximumSize(new Dimension(100, 100));
        circulo.setLayout(new GridBagLayout());

        JLabel icono = new JLabel("⚠");
        icono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 38));
        icono.setForeground(new Color(232, 100, 10));
        circulo.add(icono);

        JPanel circuloWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        circuloWrapper.setOpaque(false);
        circuloWrapper.add(circulo);

        // Título
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(26, 43, 109));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Descripción 
        JLabel lblDesc = new JLabel("<html><div style='text-align:center;width:300px'>" + descripcion + "</div></html>");
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(120, 130, 150));
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón Volver 
        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBackground(new Color(20, 20, 20));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setOpaque(true);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setMaximumSize(new Dimension(400, 52));
        btnVolver.setPreferredSize(new Dimension(400, 52));
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Borde redondeado en el botón
        btnVolver.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new Color(20, 20, 20), 1, true),
            new EmptyBorder(10, 20, 10, 20)
        ));

        btnVolver.addActionListener(e -> {
            dialog.dispose();
            if (alVolver != null) alVolver.run();
        });

        //Armar panel
        panel.add(circuloWrapper);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblDesc);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnVolver);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}