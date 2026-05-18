/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Motivo;
import enums.Tipo;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IMotivosDAO {

    List<Motivo> obtenerMotivos(Tipo tipoMotivo);

}
