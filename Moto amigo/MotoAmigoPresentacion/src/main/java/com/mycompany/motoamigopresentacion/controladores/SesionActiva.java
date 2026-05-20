package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.motoamigodto.RepartidorDTO;

/**
 * Singleton que mantiene el emprendedor logueado durante toda la sesión de la
 * aplicación. Las pantallas y controles acceden a esta clase para saber qué
 * emprendedor está activo y filtrar sus datos.
 *
 * por ahora esta hardcodeado desde el main
 *
 * @author joset
 */
public class SesionActiva {

    private static SesionActiva instancia;

    private CuentaUsuarioSesionDTO cuenta;
    private RepartidorDTO repartidor;

    private SesionActiva() {
    }

    public static synchronized SesionActiva getInstancia() {
        if (instancia == null) {
            instancia = new SesionActiva();
        }
        return instancia;
    }

    public CuentaUsuarioSesionDTO getEmprendedor() {
        return cuenta;
    }

    public void setCuenta(CuentaUsuarioSesionDTO cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Atajo para obtener el id del emprendedor activo sin tener que pedir el
     * DTO completo. Devuelve null si no hay sesión activa.
     */
    public String getIdEmprendedor() {
        return cuenta != null ? cuenta.getIdEmprendedor() : null;
    }

    /**
     * Cierra la sesión activa. Útil cuando se implemente "Cerrar sesión".
     */
    public void cerrarSesionEmprendedor() {
        this.cuenta = null;
    }
    
    /**
     * Cierra la sesión activa. Útil cuando se implemente "Cerrar sesión".
     */
    public void cerrarSesionRepartidor() {
        this.repartidor = null;
    }
    
    public RepartidorDTO getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(RepartidorDTO repartidor) {
        this.repartidor = repartidor;
    }

    public String getIdRepartidor() {
        return repartidor != null ? repartidor.getId() : null;
    }

    public boolean esRepartidor() {
        return repartidor != null;
    }
}
