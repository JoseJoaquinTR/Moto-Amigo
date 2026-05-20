package Main;

import AdminPresentacion.MenuAdminFORM;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.EventQueue;

/**
 *
 * @author Jesus Omar
 */
public class MainAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatLightLaf.setup();

        EventQueue.invokeLater(() -> {
            new MenuAdminFORM().setVisible(true);
        });
    }
    
}
