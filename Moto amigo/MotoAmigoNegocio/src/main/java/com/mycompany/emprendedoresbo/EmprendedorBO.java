package com.mycompany.emprendedoresbo;

import Adapter.AdapterEmprendedorAEmprendedorDTO;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.emprendedoresdao.EmprendedoresDAO;
import com.mycompany.emprendedoresdao.IEmprendedoresDAO;

/**
 * Implementación de la lógica de negocio para emprendedores.
 *
 * @author joset
 */
public class EmprendedorBO implements IEmprendedoresBO {

    private final IEmprendedoresDAO dao = EmprendedoresDAO.getInstancia();

    @Override
    public EmprendedorDTO obtenerEmprendedorPorId(Long id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El identificador del emprendedor no puede ser nulo.");
        }
        return null;
    }

    @Override
    public Emprendedor registrarEmprendedor(EmprendedorDTO emprendedorDTO) throws NegocioException {
        if (emprendedorDTO == null) {
            throw new NegocioException("Los datos del emprendedor no pueden ser nulos.");
        }
        if (emprendedorDTO.getNombre() == null || emprendedorDTO.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del emprendedor es obligatorio.");
        }
        return null;
    }
}
