package panelesUtilerias;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * JPanel con esquinas redondeadas - Reutilizable en NetBeans
 * @author xiomi
 */
public class PanelRedondeado extends JPanel {
    
    private int radio;
    private Color colorFondo;
    private Color colorBorde = new Color(200, 200, 200);
    
    /**
     * Constructor con radio personalizado
     * @param radio - Radio de las esquinas (ej: 15, 20, 30)
     */
    public PanelRedondeado(int radio) {
        this.radio = radio;
        this.colorFondo = new Color(255, 255, 255); // Blanco
        configurarPanel();
    }
    
    /**
     * Constructor sin parámetros (radio = 15)
     */
    public PanelRedondeado() {
        this(15);
    }
    
    /**
     * Configuración inicial del panel
     */
    private void configurarPanel() {
        // ✅ IMPORTANTE: BorderLayout para que funcione en el diseñador
        setLayout(new java.awt.BorderLayout());
        
        // ✅ IMPORTANTE: setOpaque(true) para que se pinte
        setOpaque(true);
        
        // Usar el color de fondo
        setBackground(colorFondo);
        
        // Padding interno
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    /**
     * Pintar el panel con esquinas redondeadas
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Permitir que se pinten los componentes hijos
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        // Anti-aliasing para suavidad
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        // Pintar el fondo redondeado
        g2.setColor(getBackground());
        g2.fillRoundRect(
            0, 0, 
            getWidth() - 1, 
            getHeight() - 1, 
            radio, 
            radio
        );
        
        // Pintar un borde (opcional pero se ve mejor)
        g2.setColor(colorBorde);
        g2.setStroke(new java.awt.BasicStroke(1f));
        g2.drawRoundRect(
            0, 0, 
            getWidth() - 1, 
            getHeight() - 1, 
            radio, 
            radio
        );
    }
    
    // GETTERS Y SETTERS (para que funcione en el diseñador de NetBeans)
    
    public Color getColorBorde() {
    return colorBorde;
}

public void setColorBorde(Color color) {
    this.colorBorde = color;
    repaint();
}
    
    public int getRadio() {
        return radio;
    }
    
    public void setRadio(int radio) {
        this.radio = radio;
        repaint();
    }
    
    public Color getColorFondo() {
        return colorFondo;
    }
    
    public void setColorFondo(Color color) {
        this.colorFondo = color;
        setBackground(color);
        repaint();
    }
}
