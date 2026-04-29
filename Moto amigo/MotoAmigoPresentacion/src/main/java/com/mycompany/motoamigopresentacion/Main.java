package com.mycompany.motoamigopresentacion;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.EventQueue;

/**
 *
 * @author joset
 */
public class Main {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalEmprendedor().setVisible(true);
        });
        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalRepartidor().setVisible(true);
        });

    }
}
