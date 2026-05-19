package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;

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
    public void cerrarSesion() {
        this.cuenta = null;
    }

}
