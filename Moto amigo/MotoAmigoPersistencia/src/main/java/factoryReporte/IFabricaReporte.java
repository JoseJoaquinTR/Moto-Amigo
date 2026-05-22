/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package factoryReporte;

import com.mycompany.Entidades.Reporte;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IFabricaReporte<T, R extends Reporte> {

    R crearReporte(T dto);
}
