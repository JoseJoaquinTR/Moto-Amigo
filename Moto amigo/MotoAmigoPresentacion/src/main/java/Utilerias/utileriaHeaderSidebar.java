/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import panelesUtilerias.PanelHeader;
import panelesUtilerias.PanelSidebarEmprendedor;

/**
 *
 * @author joset
 */
public class utileriaHeaderSidebar {

    public static void aplicarHeaderYSidebar(JPanel panelPrincipal, String PantallaActiva) {
        PanelHeader header = new PanelHeader();
        panelPrincipal.add(header, new AbsoluteConstraints(0, 0, 1010, 80));

        PanelSidebarEmprendedor sidebar = new PanelSidebarEmprendedor();
        sidebar.setPantallaActiva(PantallaActiva);
        panelPrincipal.add(sidebar, new AbsoluteConstraints(0, 80, 180, 738));
    }
     public static void aplicarHeader(JPanel panelPrincipal) {
        PanelHeader header = new PanelHeader();
        panelPrincipal.add(header, new AbsoluteConstraints(0, 0, 1010, 80));

    }
    
}
