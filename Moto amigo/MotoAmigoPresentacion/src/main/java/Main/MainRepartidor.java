/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.mycompany.repartidorespresentacion.FrmMenuPrincipalRepartidor;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigopresentacion.controladores.SesionActiva;
import java.awt.EventQueue;

/**
 * Punto de entrada para probar la aplicación como repartidor.
 * 
 * @author joset
 */
public class MainRepartidor {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatLightLaf.setup();

        RepartidorDTO repartidorMock = new RepartidorDTO();
        repartidorMock.setId("6a0d2317cc336268ad738f61");// id real
        repartidorMock.setNombre("Juan Repartidor");
        SesionActiva.getInstancia().setRepartidor(repartidorMock);

        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalRepartidor().setVisible(true);
        });
    }
}