package com.mycompany.motoamigonegocio.Observers;

import com.mycompany.motoamigodto.SolicitudEntregaDTO;

/**
 * Observer de apoyo para repartidores.
 */
public class RepartidorObserver implements INotificacionPedido {

    private final String nombreRepartidor;
    private boolean pedidoDisponible;
    private SolicitudEntregaDTO ultimoPedido;

    public RepartidorObserver(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    @Override
    public void actualizar(String evento, Object datos) {
        EventoEntrega eventoEntrega = convertirEvento(evento);
        if (eventoEntrega == null) {
            return;
        }

        switch (eventoEntrega) {
            case PEDIDO_DISPONIBLE -> {
                if (datos instanceof SolicitudEntregaDTO solicitud) {
                    pedidoDisponible = true;
                    ultimoPedido = solicitud;
                    System.out.println("Repartidor " + nombreRepartidor
                            + " notificado: nuevo pedido disponible de "
                            + ultimoPedido.getOrigen() + " a " + ultimoPedido.getDestino());
                }
            }
            case PEDIDO_CANCELADO -> {
                pedidoDisponible = false;
                ultimoPedido = null;
                System.out.println("Repartidor " + nombreRepartidor
                        + " notificado: pedido cancelado");
            }
            default -> {
                // Este observer no necesita reaccionar a todos los eventos.
            }
        }
    }

    private EventoEntrega convertirEvento(String evento) {
        try {
            return EventoEntrega.valueOf(evento);
        } catch (IllegalArgumentException | NullPointerException ex) {
            return null;
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
