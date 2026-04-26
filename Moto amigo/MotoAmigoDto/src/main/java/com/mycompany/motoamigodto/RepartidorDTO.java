package com.mycompany.motoamigodto;

/**
 *
 * @author xiomi
 */
public class RepartidorDTO {

    private String nombre;
    private String telefono;
    private String correo;
    private String vehiculo;
    private String estado;

    public RepartidorDTO(String nombre, String telefono, String correo, String vehiculo, String estado) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public String getEstado() {
        return estado;
    }
}
