package com.mycompany.motoamigopresentacion;

import com.mycompany.emprendedorpresentacion.FrmMenuPrincipalEmprendedor;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.EventQueue;

/**
 *
 * @author joset
 */
public class Main {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalEmprendedor().setVisible(true);
        });
//        EventQueue.invokeLater(() -> {
//            new FrmMenuPrincipalRepartidor().setVisible(true);
//        });

    }
}
