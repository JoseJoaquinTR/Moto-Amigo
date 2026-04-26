/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.IncidenteDTO;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;

public class FachadaNegocio implements IFachadaNegocio {


    /**
     * Lógica de negocio para rutas.
     */
    private final IRutaBO rutaBO;

    /**
     * Construye una nueva fachada con sus dependencias de negocio.
     *
     * @param rutaBO BO de rutas.
     */
    public FachadaNegocio(IRutaBO rutaBO) {
        this.rutaBO = rutaBO;
    }

    @Override
    public void registrarEmprendedor(EmprendedorDTO emprendedorDTO) throws NegocioException {
        EmprendedorBO.registrarEmprendedor(emprendedorDTO);
    }

    @Override
    public void registrarIncidente(IncidenteDTO incidenteDTO) throws NegocioException {
        IncidenteBO.registrarIncidente(incidenteDTO);
    }

    @Override
    public RutaResponseDTO calcularRuta(RutaRequestDTO rutaRequestDTO) throws NegocioException {
        return rutaBO.calcularRuta(rutaRequestDTO);
    }
}
