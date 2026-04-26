/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopresentacion;

import com.mycompany.motoamigodto.SolicitudEntregaDTO;

/**
 *
 * @author joset
 */
public class Main {
    public static void main(String[] args) {
        
        
        System.setProperty("https.agent", "MotoAmigo/1.0 (student project)");
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo aplicar Nimbus");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FrmPublicarARepartidores_vistaEmprendedor().setVisible(true);
        });
        java.awt.EventQueue.invokeLater(() -> {
            new FrmPublicarARepartidores(new SolicitudEntregaDTO("calle amberes 314", "calle tabasco 322", "Caja", 10.5, 1, 1, "Activo", 10)).setVisible(true);
            
        });
    }
}