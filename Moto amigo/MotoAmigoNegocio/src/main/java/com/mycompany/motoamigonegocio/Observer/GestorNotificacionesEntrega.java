/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class GestorNotificacionesEntrega implements IGestorNotificacionesEntrega {

    private static GestorNotificacionesEntrega instancia;
    private List<INotificacionPedido> observers = new ArrayList<>();

    private GestorNotificacionesEntrega() {
    }

    public static GestorNotificacionesEntrega getInstance() {
        if (instancia == null) {
            instancia = new GestorNotificacionesEntrega();
        }
        return instancia;
    }

    @Override
    public void agregarObserver(INotificacionPedido observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(INotificacionPedido observer) {
        observers.remove(observer);
    }

    @Override
    public void notificar(String evento, Object datos) {
        for (INotificacionPedido observer : observers) {
            observer.actualizar(evento, datos);
        }
    }
}
