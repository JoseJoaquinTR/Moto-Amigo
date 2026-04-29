/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author joset
 */
public class utileriasBotones {

    /**
     * Este metodo le da formato a los botones
     *
     * @param btn el boton a modificar
     * @param fondo el color deseado del fondo
     * @param radio que tanta curva tendran los bordes
     */
    public static void btnRedondeado(JButton btn, String fondo, int radio) {
        Color naranja = new Color(232, 100, 10);
        Color negro = new Color(20, 20, 20);
        Color rojo = new Color(200, 16, 46);
        Color verde = new Color(30, 122, 60);
        Color blanco = Color.WHITE;
        Color azulOscuro = new Color(26, 43, 109);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        switch (fondo) {
            case "naranja":
                btn.setBackground(naranja);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                btn.setForeground(Color.WHITE);
                break;
            case "negro":
                btn.setBackground(negro);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
                btn.setForeground(Color.WHITE);
                break;
            case "rojo":
                btn.setBackground(rojo);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                btn.setForeground(Color.WHITE);
                break;
            case "verde":
                btn.setBackground(verde);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                btn.setForeground(Color.WHITE);
                break;
            case "blanco":
                btn.setBackground(blanco);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                btn.setForeground(naranja);
                break;
            case "azulOscuro":
                btn.setBackground(azulOscuro);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                btn.setForeground(Color.WHITE);
                break;
        }

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dibujar fondo redondeado
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radio, radio);

                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    /**
     * Botón REDONDEADO Seleccionado
     *
     * Para cuando el usuario seleccione un boton de una lista de botones.
     */
    public static void btnRedondeadoSeleccionado(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(
                new LineBorder(new Color(26, 43, 109), 1, true)
        );
    }

    /**
     * Hace las JLabel con borde redondo
     *
     * @param label la label que se va a modificar
     * @param colorFondo el color del fondo de la JLabel
     * @param colorTexto el color de del texto
     */
    public static void lblRedondeado(JLabel label, Color colorFondo, Color colorTexto) {
        label.setOpaque(false);
        label.setBackground(colorFondo);
        label.setForeground(colorTexto);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        label.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        label.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    /**
     * Convierte un JToggleButton en un boton circular y cambia de 
     * color, cuando esta apagado esta en gris, y cuando se prende
     * se pone de color verde
     * @param boton el boton que se quiere convertir
     * @param diametro el diametro del circulo
     */
    public static void hacerBotonToggleCircular(JToggleButton boton, int diametro) {
        boton.setText("");
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension size = new Dimension(diametro, diametro);
        boton.setPreferredSize(size);
        boton.setMinimumSize(size);
        boton.setMaximumSize(size);

        Color colorDesconectado = new Color(156, 163, 175); // Gris
        Color colorConectado = new Color(34, 197, 94);      // Verde

        boton.setIcon(generarIconoCirculo(colorDesconectado, diametro));
        boton.setSelectedIcon(generarIconoCirculo(colorConectado, diametro));
    }

    /**
     * Este es un metodo que dibuja un circulo de un color en especifico
     */
    private static ImageIcon generarIconoCirculo(Color color, int diametro) {
        BufferedImage img = new BufferedImage(diametro, diametro, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(0, 0, diametro - 1, diametro - 1);
        g2.dispose();

        return new ImageIcon(img);
    }

    /**
     * Crea un panel con bordes redondos
     */
    public static void panelRedondeado(JPanel panel, Color colorFondo, int radio) {
        panel.setOpaque(false);
        panel.setBackground(colorFondo);
        panel.setBorder(new javax.swing.border.AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(x, y, width - 1, height - 1, radio, radio);
                g2.dispose();
            }
        });
    }
}
