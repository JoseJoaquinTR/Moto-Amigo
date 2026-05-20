package Main;
import com.mycompany.emprendedorpresentacion.FrmMenuPrincipalEmprendedor;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.motoamigopresentacion.controladores.ControlPaquetes;
import com.mycompany.motoamigopresentacion.controladores.SesionActiva;
import java.awt.EventQueue;
import observerCambiosPaquete.ObserverCambiosPaquete;

/**
 * Punto de entrada para probar la aplicación como emprendedor.
 *
 * @author joset
 */
public class MainEmprendedor {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatLightLaf.setup();

        CuentaUsuarioSesionDTO cuentaMock = new CuentaUsuarioSesionDTO();
        cuentaMock.setIdEmprendedor("6a0d216d8655134c496f96d9"); // id real
        SesionActiva.getInstancia().setCuenta(cuentaMock);

        ObserverCambiosPaquete observerCU = ObserverCambiosPaquete.getInstancia();
        PaquetesBO.getInstancia().agregarObservador(observerCU);
        observerCU.agregarObservador(ControlPaquetes.getInstancia());

        EventQueue.invokeLater(() -> {
            new FrmMenuPrincipalEmprendedor().setVisible(true);
        });
    }
}
