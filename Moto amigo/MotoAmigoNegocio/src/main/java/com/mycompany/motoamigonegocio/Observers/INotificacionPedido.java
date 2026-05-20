package com.mycompany.motoamigonegocio.Observers;

/**
 * Observer que reacciona a eventos relacionados con una entrega.
 */
public interface INotificacionPedido {

    void actualizar(String evento, Object datos);
}
