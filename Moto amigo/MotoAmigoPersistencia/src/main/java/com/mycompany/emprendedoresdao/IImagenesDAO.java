package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.Imagen;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface IImagenesDAO {
    Imagen actualizarImagen(String idEmprendedor, Imagen imagen)throws PersistenciaException;
    Imagen obtenerImagenPorIdEmprendedor(String idEmprendedor)throws PersistenciaException;
}
