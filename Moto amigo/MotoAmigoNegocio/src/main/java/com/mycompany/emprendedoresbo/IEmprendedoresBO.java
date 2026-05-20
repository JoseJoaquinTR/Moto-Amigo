package com.mycompany.emprendedoresbo;

import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.ImagenDTO;
import com.mycompany.emprendedoresdto.RegistroEmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.EstatusEmprendedorDTO;
import java.util.List;

/**
 * Operaciones de negocio relacionadas con emprendedores.
 *
 * @author Jesus Omar
 */
public interface IEmprendedoresBO {

    /**
     * Metodo para registrar un nuevo emprendedor
     *
     * @param nuevoEmprendedorDTO la DTO del emprendedor que se va a registrar
     * @return regresa la DTO del emprendedor que se registro
     * @throws NegocioException
     */
    EmprendedorDTO registrarEmprendedor(RegistroEmprendedorDTO nuevoEmprendedorDTO) throws NegocioException;


    /**
     * Actualiza el estatus de un emprendedor a ACTIVO, BAJA o RECHAZADO
     *
     * @param idEmprendedor el id del emprendedor al que se le actualizara el
     * estatus
     * @param estatus el nuevo estatus que tendra el emprendedor
     * @return regresa la DTO del emprendedor al que se le actualizo el estatus
     * @throws NegocioException
     */
    EmprendedorDTO actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedorDTO estatus) throws NegocioException;

    /**
     * Busca un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor que se quiere buscar
     * @return el emprendedor que coincide con el id que se mando
     * @throws NegocioException
     */
    EmprendedorDTO obtenerEmprendedorPorID(String idEmprendedor) throws NegocioException;

    /**
     * Consulta a los emprendedores que hay en la base de datos
     *
     * @return una lista con los emprendedores que estan en la base de datos
     * @throws NegocioException
     */
    List<EmprendedorDTO> consultarEmprendedores() throws NegocioException;

    /**
     * Hace un reporte de los emprendedores que estan en la base de datos
     *
     * @return
     * @throws NegocioException
     */
    ReporteEmprendedoresDTO generarDatosReportePDF() throws NegocioException;

    /**
     * Busca la cuenta bancaria de un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener la
     * cuenta
     * @return la cuenta del emprendedor
     * @throws NegocioException
     */
    CuentaBancariaDTO obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    /**
     * Actualiza la imagen de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor al que se le actualizara la
     * imagen
     * @param imagen la nueva imagen
     * @return la imagen nueva
     * @throws NegocioException
     */
    ImagenDTO actualizarImagen(String idEmprendedor, ImagenDTO imagen) throws NegocioException;

    /**
     * Obtiene la imagen de un emprendedor buscandola por su id
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener su
     * imagen
     * @return la imagen que tiene el emprendedor
     * @throws NegocioException
     */
    ImagenDTO obtenerImagenPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    /**
     * Actualiza el documento de la INE de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor del que se quiere actualizar
     * su documento
     * @param documento el nuevo documento
     * @return el nuevo documento
     * @throws NegocioException
     */
    DocumentoDTO actualizarDocumento(String idEmprendedor, DocumentoDTO documento) throws NegocioException;

    /**
     * Obtiene el documento de un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener su
     * documento
     * @return
     * @throws NegocioException
     */
    DocumentoDTO obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    /**
     * Busca la una cuenta por el correo
     *
     * @param correo el correo asociado a la cuenta
     * @return la CuentaUsuarioDTO con el idEmprendedor, EstatusEmprendedor,
     * idCuenta y el estado
     * @throws NegocioException
     */
    CuentaUsuarioSesionDTO buscarCuenta(String correo) throws NegocioException;

}
