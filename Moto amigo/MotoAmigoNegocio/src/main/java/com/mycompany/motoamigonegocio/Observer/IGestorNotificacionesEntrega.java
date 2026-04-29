/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio.Observer;

/**
 *
 * @author joset
 */
public interface IGestorNotificacionesEntrega {

    public void agregarObserver(INotificacionPedido observer);

    public void eliminarObserver(INotificacionPedido observer);

    public void notificar(String evento, Object datos);
}
