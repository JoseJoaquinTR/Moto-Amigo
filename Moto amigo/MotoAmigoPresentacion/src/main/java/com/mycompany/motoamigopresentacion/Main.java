package com.mycompany.motoamigopresentacion;

import Paquetes.PaquetesBO;
import com.mycompany.emprendedorpresentacion.FrmMenuPrincipalEmprendedor;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.motoamigopresentacion.controladores.ControlPaquetes;
import com.mycompany.motoamigopresentacion.controladores.SesionActiva;
import java.awt.EventQueue;
import observerCambiosPaquete.ObserverCambiosPaquete;

/**
 *
 * @author joset
 */
public class Main {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatLightLaf.setup();

        CuentaUsuarioSesionDTO cuentaMock = new CuentaUsuarioSesionDTO();
        cuentaMock.setIdEmprendedor("");
        SesionActiva.getInstancia().setCuenta(cuentaMock);

        ObserverCambiosPaquete observerCU = ObserverCambiosPaquete.getInstancia();
        PaquetesBO.getInstancia().agregarObservador(observerCU);
        observerCU.agregarObservador(ControlPaquetes.getInstancia());

        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalEmprendedor().setVisible(true);
        });
//        EventQueue.invokeLater(() -> {
//            new FrmMenuPrincipalRepartidor().setVisible(true);
//        });

    }
}
