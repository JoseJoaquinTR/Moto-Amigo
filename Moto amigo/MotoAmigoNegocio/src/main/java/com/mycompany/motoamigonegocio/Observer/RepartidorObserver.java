/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio.Observer;

import com.mycompany.motoamigodto.SolicitudEntregaDTO;

/**
 *
 * @author joset
 */
public class RepartidorObserver implements INotificacionPedido {

    private String nombreRepartidor;
    private boolean pedidoDisponible = false;
    private SolicitudEntregaDTO ultimoPedido;

    public RepartidorObserver(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    @Override
    public void actualizar(String evento, Object datos) {
        switch (evento) {
            case "PEDIDO_DISPONIBLE" -> {
                pedidoDisponible = true;
                ultimoPedido = (SolicitudEntregaDTO) datos;
                System.out.println("Repartidor " + nombreRepartidor+ " notificado: nuevo pedido disponible de "+ ultimoPedido.getOrigen() + " a " + ultimoPedido.getDestino());
            }
            case "PEDIDO_CANCELADO" -> {
                pedidoDisponible = false;
                ultimoPedido = null;
                System.out.println("Repartidor " + nombreRepartidor+ " notificado: pedido cancelado");
            }
        }
    }

    public boolean isPedidoDisponible() {
        return pedidoDisponible;
    }

    public SolicitudEntregaDTO getUltimoPedido() {
        return ultimoPedido;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }
}
