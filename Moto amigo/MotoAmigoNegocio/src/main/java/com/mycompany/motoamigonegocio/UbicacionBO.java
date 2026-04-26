/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigonegocio;

import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigopersistencia.IUbicacionDAO;
import com.mycompany.motoamigopersistencia.UbicacionDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joset
 */
public class UbicacionBO implements IUbicacionBO {

    private final IUbicacionDAO dao = UbicacionDAO.getInstancia();
    private static UbicacionBO instancia;
    
    private UbicacionBO() {
    }

    public static synchronized UbicacionBO getInstancia() {
        if (instancia == null) {
            instancia = new UbicacionBO();
        }
        return instancia;
    }
    
    @Override
    public List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException{

        try {
            return dao.buscarUbicacion(texto.trim());
        } catch (Exception ex) {
            Logger.getLogger(UbicacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error"+ex.getMessage(),null);
        }
    }

}
