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
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.EntregasBO;
import com.mycompany.motoamigonegocio.IEmprendedoresBO;
import com.mycompany.motoamigonegocio.IEntregasBO;
import com.mycompany.motoamigonegocio.IRepartidorBO;
import com.mycompany.motoamigonegocio.RepartidorBO;
import com.mycompany.motoamigopresentacion.FrmPublicarPedidoRepartidor;
import com.mycompany.motoamigopresentacion.FrmPublicarPedidosEmprendedor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
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
    private final IEmprendedoresBO emprendedoresBO = new EmprendedorBO();
    private final IEntregasBO entregasBO = EntregasBO.getInstance();
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
    
    public void cargarEntregas(JPanel panel, Long id, String filtro) {
        List<EntregaDTO> entregas = new ArrayList<>();
        if(filtro.equals("repartidor")){
            entregas = entregasBO.obtenerEntregasRepartidor(id);
        }else{
            entregas = entregasBO.obtenerEntregasEmprendedor(id);
        }
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        for (EntregaDTO entrega : entregas) {
            PanelTarjetaPedido tarjetaPedido = new PanelTarjetaPedido();

            tarjetaPedido.cargarDatosEnTarjeta(entrega);

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
    public void abrirPantallaEnvioEmpr(){
        new FrmPublicarPedidosEmprendedor().setVisible(true);
    }
    public void mostrarDetallePedido(EntregaDTO entrega) {
        SolicitudEntregaDTO solicitud = new SolicitudEntregaDTO(
                entrega.getDireccionOrigen(),
                entrega.getDireccionDestino(),
                entrega.getTipoPaquete(),
                entrega.getPesoAprox(),
                entrega.getEstadoEntrega(),
                0
        );
        new FrmPublicarPedidoRepartidor(solicitud).setVisible(true);
    }
}
