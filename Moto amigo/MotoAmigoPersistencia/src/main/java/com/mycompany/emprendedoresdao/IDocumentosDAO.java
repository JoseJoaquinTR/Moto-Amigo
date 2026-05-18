package com.mycompany.emprendedoresdao;

import com.mycompany.Entidades.Documento;
import com.mycompany.motoamigopersistencia.PersistenciaException;

/**
 *
 * @author Jesus Omar
 */
public interface IDocumentosDAO {
    Documento actualizarDocumentoEmprendedor(String idEmprendedor, Documento documento)throws PersistenciaException;
    Documento obtenerDocumentoPorIdEmprendedor(String idEmprendedor)throws PersistenciaException;
}
