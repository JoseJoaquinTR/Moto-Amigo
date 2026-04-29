/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopresentacion.controladores;

import Utilerias.utileriasBotones;
import com.mycompany.Entidades.Entrega;
import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.EmprendedorBO;
import com.mycompany.motoamigonegocio.EntregasBO;
import com.mycompany.motoamigonegocio.IEmprendedoresBO;
import com.mycompany.motoamigonegocio.IEntregasBO;
import com.mycompany.motoamigonegocio.IRepartidorBO;
import com.mycompany.motoamigonegocio.RepartidorBO;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import panelesUtilerias.PanelTarjetaPedido;

/**
 *
 * @author Jesus Omar
 */
public class ControlMenuPrincipal {

    private final IRepartidorBO repartidorBO = new RepartidorBO();
    private final IEntregasBO entregasBO = new EntregasBO();
    private final IEmprendedoresBO emprendedoresBO = new EmprendedorBO();
    private static ControlMenuPrincipal instancia;

    public static ControlMenuPrincipal getInstance() {
        if (instancia == null) {
            instancia = new ControlMenuPrincipal();
        }
        return instancia;
    }

    public RepartidorDTO buscarRepartidorPorId(Long id) {
        return repartidorBO.obtenerRepartidorPorId(id);
    }
    
    public EmprendedorDTO buscarEmprendedorPorId(Long id){
        return emprendedoresBO.obtenerEmprendedorPorId(id);
    }
    
    public void cargarEntregas(JPanel panel, Long id) {
        List<Entrega> entregas = entregasBO.obtenerEntregasRepartidor(id);
        panel.removeAll();
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 0, 10));
        for (Entrega e : entregas) {
            PanelTarjetaPedido tarjetaPedido = new PanelTarjetaPedido();
            tarjetaPedido.cargarDatosEnTarjeta(e);

            tarjetaPedido.setBackground(new Color(248, 249, 250));
            tarjetaPedido.setOpaque(true);

            Border margenExterior = BorderFactory.createEmptyBorder(0, 10, 15, 10);
            Border lineaSuave = BorderFactory.createLineBorder(new Color(222, 226, 230), 1);

            tarjetaPedido.setBorder(BorderFactory.createCompoundBorder(margenExterior, lineaSuave));

            tarjetaPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, tarjetaPedido.getPreferredSize().height));
            panel.add(tarjetaPedido);
        }
        panel.revalidate();
        panel.repaint();
    }
}
