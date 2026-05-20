
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.reportes;

import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.Tipo;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IMotivosBO {

    List<MotivoDTO> obetnerMotivos(Tipo tipo) throws NegocioException;

    boolean existeMotivo(MotivoDTO motivo, Tipo tipo) throws NegocioException;
}
