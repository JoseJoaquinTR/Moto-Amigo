package com.mycompany.motoamigonegocio;

import Adapter.AdapterUbicacionAUbicacionDTO;
import com.mycompany.Entidades.Ubicacion;
import com.mycompany.infraestructura.IUbicacionDAO;
import com.mycompany.infraestructura.UbicacionDAO;
import com.mycompany.motoamigodto.UbicacionDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la lógica de negocio para búsqueda de ubicaciones.
 *
 * @author joset
 */
public class UbicacionBO implements IUbicacionBO {

    private final IUbicacionDAO dao = UbicacionDAO.getInstancia();
    private static UbicacionBO instancia;

    private UbicacionBO() {
    }

    /**
     * Obtiene la instancia única del BO.
     *
     * @return instancia de UbicacionBO.
     */
    public static synchronized UbicacionBO getInstancia() {
        if (instancia == null) {
            instancia = new UbicacionBO();
        }
        return instancia;
    }

    @Override
    public List<UbicacionDTO> buscarUbicacion(String texto) throws NegocioException {
        try {
            List<Ubicacion> entidades = dao.buscarUbicacion(texto.trim());
            return new AdapterUbicacionAUbicacionDTO().adaptarLista(entidades);
        } catch (Exception ex) {
            Logger.getLogger(UbicacionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al buscar ubicaciones: " + ex.getMessage(), ex);
        }
    }
}
