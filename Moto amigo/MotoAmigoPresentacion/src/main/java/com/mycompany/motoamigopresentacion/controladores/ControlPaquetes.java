
package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.paquetespresentacion.FrmMenuPaquetes;
import com.mycompany.productosdto.ProductoDTO;
import javax.swing.SwingUtilities;
import observerCambiosPaquete.IObservadorPaqueteCU;

/**
 *
 * @author joset
 */
public class ControlPaquetes implements IObservadorPaqueteCU {
 
    private static ControlPaquetes instancia;
 
    private FrmMenuPaquetes menuActivo;
 
    private ControlPaquetes() {
    }
 
    public static synchronized ControlPaquetes getInstancia() {
        if (instancia == null) {
            instancia = new ControlPaquetes();
        }
        return instancia;
    }
 
    /**
     * Registra el menú de paquetes activo para que el control pueda mandarle
     * notificaciones cuando reciba eventos del Observer. Si se pasa null, el
     * control deja de tener referencia al menú (se usa cuando el menú se
     * cierra).
     */
    public void setMenuActivo(FrmMenuPaquetes menu) {
        this.menuActivo = menu;
    }
 
    @Override
    public void paqueteCreado(PaqueteDTO paquete) {
        refrescarMenu();
    }
 
    @Override
    public void paqueteEditado(PaqueteDTO paquete) {
        refrescarMenu();
    }
 
    @Override
    public void paqueteEliminado(String idPaquete) {
        refrescarMenu();
    }
 
    @Override
    public void productoAgregadoAPaquete(String idPaquete, ProductoDTO producto) {
    }
 
    @Override
    public void productoQuitadoDePaquete(String idPaquete, ProductoDTO producto) {
    }
 
    /**
     * Refresca la tabla del menú activo si está visible. La actualización se
     * hace en el hilo de Swing para que sea seguro modificar la UI.
     */
    private void refrescarMenu() {
        if (menuActivo == null) {
            return;
        }
        SwingUtilities.invokeLater(() -> {
            if (menuActivo != null && menuActivo.isShowing()) {
                menuActivo.refrescarTablaPaquetes();
            }
        });
    }
 
}