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
            new FrmPublicarPedidosEmprendedor().setVisible(true);
        });
        EventQueue.invokeLater(() -> {
            new FrmPublicarPedidoRepartidor(new SolicitudEntregaDTO(
                    "calle amberes 314",
                    "calle tabasco 322",
                    "Caja",
                    10.5,
                    "Activo",
                    10
            )).setVisible(true);
        });
        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalEmprendedor().setVisible(true);
        });

        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalRepartidor().setVisible(true);
        });

    }
}
