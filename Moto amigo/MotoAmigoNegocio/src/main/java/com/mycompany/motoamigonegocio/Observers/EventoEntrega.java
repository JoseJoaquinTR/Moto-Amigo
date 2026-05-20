package com.mycompany.motoamigonegocio.Observers;

/**
 * Eventos válidos para el flujo de una entrega.
 * Usar este enum evita errores por escribir strings manualmente.
 */
public enum EventoEntrega {
    PEDIDO_DISPONIBLE,
    PEDIDO_ACEPTADO,
    PEDIDO_CANCELADO,
    PEDIDO_ENTREGADO,
    PEDIDO_NO_COMPLETADO,
    UBICACION_ACTUALIZADA
}
