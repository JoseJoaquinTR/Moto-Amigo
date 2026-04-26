/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author joset
 */
public class utileriasBotones {

    /**
     * Boton NARANJA para todo lo principal de la app tipo Contactar Repartidor
     * o Volver al menú
     */
    public static void btnNaranja(JButton btn) {
        btn.setBackground(new Color(232, 100, 10));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        
        //Redondear con el lookandFeel del FlatLaf
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 222, 228), 1, true), 
            new EmptyBorder(10, 16, 10, 16)
        ));
    }

    /**
     * Botn ROJO es para alerta o algo crítica tipo Reportar o Cancelar
     */
    public static void btnRojo(JButton btn) {
        btn.setBackground(new Color(200, 16, 46));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        
        //Redondear con el lookandFeel del FlatLaf
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 222, 228), 1, true), 
            new EmptyBorder(10, 16, 10, 16)
        ));
    }

    /**
     * AZUL OSCURO es para la navegación o algo x Guardar o Ver detalle
     */
    public static void btnAzulOscuro(JButton btn) {
        btn.setBackground(new Color(26, 43, 109));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        
        //Redondear con el lookandFeel del FlatLaf
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 222, 228), 1, true), 
            new EmptyBorder(10, 16, 10, 16)
        ));
    }

    /**
     * Boton VERDE es para la confirmación o algo bien hecho Confirmar o Aceptar
     */
    public static void btnVerde(JButton btn) {
        btn.setBackground(new Color(30, 122, 60));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        //Redondear con el lookandFeel del FlatLaf
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 222, 228), 1, true), 
            new EmptyBorder(10, 16, 10, 16)
        ));
    }

    /**
     * Botón REDONDEADO Blanco
     * 
     * 
     */
    public static void btnRedondeadoBlanco(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(26, 26, 46));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 222, 228), 1, true), 
            new EmptyBorder(10, 16, 10, 16)
        ));
    }

    /**
     * Botón REDONDEADO Negro
     * 
     * 
     */
    public static void btnRedondeadoNegro(JButton btn) {
        btn.setBackground(new Color(20, 20, 20));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);

        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 222, 228), 1, true), 
            new EmptyBorder(10, 16, 10, 16)
        ));
    }
    
    /**
     * Botón REDONDEADO Seleccionado
     * 
     * Para cuando el usuario seleccione un boton de una lista de botones. 
     */
    public static void btnRedondeadoSeleccionado(JButton btn) {
        btn.setBackground(new Color(232, 100, 10)); 
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(26, 43, 109), 1, true),
            new EmptyBorder(10, 16, 10, 16)
        ));
    }
}
