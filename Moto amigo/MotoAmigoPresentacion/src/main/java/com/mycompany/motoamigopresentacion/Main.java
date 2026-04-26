package com.mycompany.motoamigopresentacion;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import java.awt.EventQueue;

/**
 *
 * @author joset
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> {
            new FrmPublicarARepartidores_vistaEmprendedor().setVisible(true);
        });
        EventQueue.invokeLater(() -> {
            new FrmPublicarARepartidores(new SolicitudEntregaDTO("calle amberes 314", "calle tabasco 322", "Caja", 10.5, 1, 1, "Activo", 10)).setVisible(true);
        });
       
    }
}