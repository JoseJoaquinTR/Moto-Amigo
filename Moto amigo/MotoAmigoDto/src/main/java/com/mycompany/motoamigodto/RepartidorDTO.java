package com.mycompany.motoamigodto;

/**
 *
 * @author xiomi
 */
public class RepartidorDTO {
    
    private int idRepartidor;
    private String nombre;
    private String telefono;
    private String correo;
    private String vehiculo;
    private String estado;

    public RepartidorDTO(int idRepartidor, String nombre, String telefono, String correo,String vehiculo, String estado) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculo = vehiculo;
        this.estado = estado;
    }

    public RepartidorDTO() {
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
