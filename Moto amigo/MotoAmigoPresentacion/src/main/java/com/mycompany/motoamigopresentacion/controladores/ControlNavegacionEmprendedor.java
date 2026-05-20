package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.cuemprendedor.CURegistrarEmprendedorFachada;
import com.mycompany.cuemprendedor.ICURegistrarEmprendedorFachada;
import com.mycompany.paquetespresentacion.FrmMenuPaquetes;
import com.mycompany.emprendedorpresentacion.FrmMenuPrincipalEmprendedor;
import com.mycompany.productospresentacion.FrmMenuProductos;
import javax.swing.JFrame;

/**
 *
 * @author joset
 */
public class ControlNavegacionEmprendedor {

    private static ControlNavegacionEmprendedor instancia;
    private final ICURegistrarEmprendedorFachada cu;
    private JFrame menuActivo;

    private ControlNavegacionEmprendedor() {
        this.cu = new CURegistrarEmprendedorFachada();
    }

    /**
     * Obtiene la instancia única del controlador de navegación.
     *
     * @return instancia de ControlNavegacionEmprendedor.
     */
    public static synchronized ControlNavegacionEmprendedor getInstancia() {
        if (instancia == null) {
            instancia = new ControlNavegacionEmprendedor();
        }
        return instancia;
    }

    /**
     * Registra el menú que se abre como el menú activo. Cada Frm de
     * menú principal debe llamar a este método al iniciarse.
     *
     * @param menu menú que se está mostrando.
     */
    public void registrarMenuActivo(JFrame menu) {
        this.menuActivo = menu;
    }

    /**
     * Cambia al menú principal del emprendedor. Cierra el menú actual y abre
     * FrmMenuPrincipalEmprendedor.
     */
    public void irAInicio() {
        if (menuActivo instanceof FrmMenuPrincipalEmprendedor) {
            return;
        }
        cerrarMenuActivo();
        FrmMenuPrincipalEmprendedor menu = new FrmMenuPrincipalEmprendedor();
        menu.setVisible(true);
    }

    /**
     * Cambia al menú de paquetes. Cierra el menú actual y abre FrmMenuPaquetes.
     */
    public void irAPaquetes() {
        if (menuActivo instanceof FrmMenuPaquetes) {
            return;
        }
        cerrarMenuActivo();
        FrmMenuPaquetes menu= new FrmMenuPaquetes();
        menu.setVisible(true);
    }

    /**
     * Cambia al menú de productos. Cierra el menú actual y abre
     * FrmMenuProductos.
     */
    public void irAProductos() {
        if (menuActivo instanceof FrmMenuProductos) {
            return;
        }
        cerrarMenuActivo();
        FrmMenuProductos menu= new FrmMenuProductos();
        menu.setVisible(true);
    }
    /**
     * Metodo auxiliar que sirve para cerrar 
     * el menu que se encuentre activo. 
     * 
     */
    private void cerrarMenuActivo() {
        if (menuActivo != null) {
            menuActivo.dispose();
            menuActivo = null;
        }
    }
}
