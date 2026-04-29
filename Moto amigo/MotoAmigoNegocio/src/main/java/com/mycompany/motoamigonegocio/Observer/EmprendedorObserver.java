/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio.Observer;

/**
 *
 * @author joset
 */
public class EmprendedorObserver implements INotificacionPedido {

    private String nombreEmprendedor;
    private boolean pedidoAceptado = false;
    private String nombreRepartidorAsignado;

    public EmprendedorObserver(String nombreEmprendedor) {
        this.nombreEmprendedor = nombreEmprendedor;
    }

    @Override
    public void actualizar(String evento, Object datos) {
        switch (evento) {
            case "PEDIDO_ACEPTADO" -> {
                pedidoAceptado = true;
                nombreRepartidorAsignado = (String) datos;
                System.out.println("Emprendedor " + nombreEmprendedor+ " notificado: pedido aceptado por " + nombreRepartidorAsignado);
            }
            case "PEDIDO_ENTREGADO" -> {
                System.out.println("Emprendedor " + nombreEmprendedor+ " notificado: pedido entregado exitosamente");
            }
            case "PEDIDO_NO_COMPLETADO" -> {
                System.out.println("Emprendedor " + nombreEmprendedor+ " notificado: pedido no completado");
            }
        }
    }

    public boolean isPedidoAceptado() {
        return pedidoAceptado;
    }

    public String getNombreRepartidorAsignado() {
        return nombreRepartidorAsignado;
    }
}
