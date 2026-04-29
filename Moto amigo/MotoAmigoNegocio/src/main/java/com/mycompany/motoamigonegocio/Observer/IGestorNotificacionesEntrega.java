package com.mycompany.motoamigonegocio.Observer;

/**
 * Subject del patrón Observer para los eventos de entrega.
 */
public interface IGestorNotificacionesEntrega {

    void agregarObserver(INotificacionPedido observer);

    void eliminarObserver(INotificacionPedido observer);

    void notificar(String evento, Object datos);

    void limpiarObservers();

    int contarObservers();
}
