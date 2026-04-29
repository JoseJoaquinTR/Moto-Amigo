package com.mycompany.motoamigonegocio.Observer;

/**
 * Observer de apoyo para el emprendedor.
 * Sirve para saber cuándo un repartidor aceptó la solicitud.
 */
public class EmprendedorObserver implements INotificacionPedido {

    private final String nombreEmprendedor;
    private boolean pedidoAceptado;
    private String nombreRepartidorAsignado;

    public EmprendedorObserver(String nombreEmprendedor) {
        this.nombreEmprendedor = nombreEmprendedor;
    }

    @Override
    public void actualizar(String evento, Object datos) {
        EventoEntrega eventoEntrega = convertirEvento(evento);
        if (eventoEntrega == null) {
            return;
        }

        switch (eventoEntrega) {
            case PEDIDO_ACEPTADO -> {
                pedidoAceptado = true;
                nombreRepartidorAsignado = datos != null ? datos.toString() : "Repartidor";
                System.out.println("Emprendedor " + nombreEmprendedor
                        + " notificado: pedido aceptado por " + nombreRepartidorAsignado);
            }
            case PEDIDO_ENTREGADO ->
                System.out.println("Emprendedor " + nombreEmprendedor
                        + " notificado: pedido entregado exitosamente");
            case PEDIDO_NO_COMPLETADO ->
                System.out.println("Emprendedor " + nombreEmprendedor
                        + " notificado: pedido no completado");
            case PEDIDO_CANCELADO -> {
                pedidoAceptado = false;
                nombreRepartidorAsignado = null;
                System.out.println("Emprendedor " + nombreEmprendedor
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

    public boolean isPedidoAceptado() {
        return pedidoAceptado;
    }

    public String getNombreRepartidorAsignado() {
        return nombreRepartidorAsignado;
    }
}
