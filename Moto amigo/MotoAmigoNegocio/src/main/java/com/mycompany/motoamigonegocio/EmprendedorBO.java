package com.mycompany.motoamigonegocio;

import Adapter.AdapterEmprendedorAEmprendedorDTO;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigopersistencia.EmprendedoresDAO;
import com.mycompany.motoamigopersistencia.IEmprendedoresDAO;

/**
 * Implementación de la lógica de negocio para emprendedores.
 *
 * @author joset
 */
public class EmprendedorBO implements IEmprendedoresBO {

    private final IEmprendedoresDAO dao = EmprendedoresDAO.getInstance();

    @Override
    public EmprendedorDTO obtenerEmprendedorPorId(Long id) throws NegocioException {
        if (id == null) {
            throw new NegocioException("El identificador del emprendedor no puede ser nulo.");
        }
        Emprendedor emprendedor = dao.obtenerEmprendedorPorId(id);
        return new AdapterEmprendedorAEmprendedorDTO().adaptar(emprendedor);
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
