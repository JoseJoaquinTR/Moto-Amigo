package com.mycompany.cuemprendedor;

import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.emprendedoresdto.DireccionDTO;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.ImagenDTO;
import com.mycompany.emprendedoresdto.NegocioDTO;
import com.mycompany.emprendedoresdto.RegistroEmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.EstatusEmprendedorDTO;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public interface ICURegistrarEmprendedorFachada {

    EmprendedorDTO registrarEmprendedor(RegistroEmprendedorDTO dto) throws NegocioException;

    EmprendedorDTO actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedorDTO estatus) throws NegocioException;

    EmprendedorDTO obtenerEmprendedorPorID(String idEmprendedor) throws NegocioException;

    List<EmprendedorDTO> consultarEmprendedores() throws NegocioException;

    ReporteEmprendedoresDTO generarDatosReportePDF() throws NegocioException;

    CuentaBancariaDTO obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    NegocioDTO obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    DireccionDTO obtenerDireccionPorIdNegocio(String idNegocio) throws NegocioException;

    DocumentoDTO actualizarDocumentoEmprendedor(String idEmprendedor, DocumentoDTO documento) throws NegocioException;

    DocumentoDTO obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    ImagenDTO actualizarImagen(String idEmprendedor, ImagenDTO imagen) throws NegocioException;

    ImagenDTO obtenerImagenPorIdEmprendedor(String idEmprendedor) throws NegocioException;

    DireccionDTO actualizarDireccion(String idNegocio, DireccionDTO direccion) throws NegocioException;

    CuentaUsuarioSesionDTO buscarCuenta(String correo) throws NegocioException;
}
