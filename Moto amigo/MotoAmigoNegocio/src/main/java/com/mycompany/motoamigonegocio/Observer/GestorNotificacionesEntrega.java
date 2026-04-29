package com.mycompany.motoamigonegocio.Observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Subject central del patrón Observer.
 * Mantiene a los observers registrados y les avisa cuando ocurre un evento.
 */
public class GestorNotificacionesEntrega implements IGestorNotificacionesEntrega {

    private static GestorNotificacionesEntrega instancia;
    private final List<INotificacionPedido> observers;

    private GestorNotificacionesEntrega() {
        this.observers = new CopyOnWriteArrayList<>();
    }

    public static synchronized GestorNotificacionesEntrega getInstance() {
        if (instancia == null) {
            instancia = new GestorNotificacionesEntrega();
        }
        return instancia;
    }

    @Override
    public void agregarObserver(INotificacionPedido observer) {
        if (observer == null || observers.contains(observer)) {
            return;
        }
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(INotificacionPedido observer) {
        observers.remove(observer);
    }

    @Override
    public void notificar(String evento, Object datos) {
        if (evento == null || evento.isBlank()) {
            return;
        }

        for (INotificacionPedido observer : observers) {
            try {
                observer.actualizar(evento, datos);
            } catch (RuntimeException ex) {
                System.err.println("Error al notificar observer "
                        + observer.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }

    public void notificar(EventoEntrega evento, Object datos) {
        if (evento != null) {
            notificar(evento.name(), datos);
        }
    }

    @Override
    public void limpiarObservers() {
        observers.clear();
    }

    @Override
    public int contarObservers() {
        return observers.size();
    }
}
