/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author joset
 */
public class utileriasBotones {

    /**
     * Boton NARANJA para todo lo principal de la app
     * tipo Contactar Repartidor o Volver al menú
     */
    public static void btnNaranja(JButton btn) {
        btn.setBackground(new Color(232, 100, 10));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }

    /**
     * Botn ROJO es para alerta o algo crítica
     * tipo Reportar o Cancelar
     */
    public static void btnRojo(JButton btn) {
        btn.setBackground(new Color(200, 16, 46));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }

    /**
     * AZUL OSCURO  es para la navegación o algo x
     *  Guardar o Ver detalle
     */
    public static void btnAzulOscuro(JButton btn) {
        btn.setBackground(new Color(26, 43, 109));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }

    /**
     * Boton VERDE es para la confirmación o algo bien hecho
     * Confirmar o Aceptar
     */
    public static void btnVerde(JButton btn) {
        btn.setBackground(new Color(30, 122, 60));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }
    
    
}
