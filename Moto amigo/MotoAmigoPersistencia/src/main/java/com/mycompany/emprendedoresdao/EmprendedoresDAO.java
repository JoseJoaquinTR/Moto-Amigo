/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.Emprendedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class EmprendedoresDAO implements IEmprendedoresDAO{

    private static EmprendedoresDAO instancia;

    private EmprendedoresDAO() {
    }
    
    public static EmprendedoresDAO getInstance() {
        if (instancia == null) {
            instancia = new EmprendedoresDAO();
        }
        return instancia;
    }
    
    @Override
    public Emprendedor obtenerEmprendedorPorId(Long id) {
        return obtenerEmprendedores()
            .stream()
            .filter(em -> em.getIdEmprendedor().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Emprendedor> obtenerEmprendedores() {
        return null;
    }
    
}
