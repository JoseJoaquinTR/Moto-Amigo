package com.mycompany.motoamigonegocio.Observer;

/**
 * Observer que reacciona a eventos relacionados con una entrega.
 */
public interface INotificacionPedido {

    void actualizar(String evento, Object datos);
}
