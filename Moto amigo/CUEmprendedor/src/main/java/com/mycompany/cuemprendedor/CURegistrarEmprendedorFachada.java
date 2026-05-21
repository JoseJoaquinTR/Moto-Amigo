package com.mycompany.cuemprendedor;

import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.emprendedoresdto.DireccionDTO;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.ImagenDTO;
import com.mycompany.emprendedoresdto.NegocioDTO;
import com.mycompany.emprendedoresdto.NuevoEmprendedorDTO;
import com.mycompany.emprendedoresdto.RegistroEmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import enums.EstatusEmprendedorDTO;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class CURegistrarEmprendedorFachada implements ICURegistrarEmprendedorFachada {

    private static final int MAX_BYTES_IMAGEN = 5 * 1024 * 1024;
    private static final int MAX_BYTES_DOCUMENTO = 5 * 1024 * 1024;
    private static final int DIGITOS_TELEFONO = 10;
    private static final int DIGITOS_CODIGO_POSTAL = 5;
    private static final int MIN_DIGITOS_CUENTA = 10;
    private static final int MAX_DIGITOS_CUENTA = 18;
    private static final int MIN_CONTRASENIA = 8;
    private static final int MAX_CONTRASENIA = 16;
    private final IFachadaNegocio fachadaNegocio;

    public CURegistrarEmprendedorFachada() {
        this.fachadaNegocio = FachadaNegocio.crear();
    }

    /**
     * Valida a un nuevo emprendedor para que no se guarden datos incorrectos
     *
     * @param nuevoEmprendedor el emprendedor que se va a validar
     * @throws NegocioException
     */
    private void validarEmprendedor(NuevoEmprendedorDTO nuevoEmprendedor) throws NegocioException {
        if (nuevoEmprendedor == null) {
            throw new NegocioException("Los datos del emprendedor no pueden ser nulos");
        }
        if (nuevoEmprendedor.getNombre() == null || nuevoEmprendedor.getNombre().isBlank()) {
            throw new NegocioException("El nombre del emprendedor es obligatorio");
        }
        if (nuevoEmprendedor.getCorreo() == null || nuevoEmprendedor.getCorreo().isBlank()) {
            throw new NegocioException("El correo es obligatorio");
        }
        if (nuevoEmprendedor.getTelefono() == null || nuevoEmprendedor.getTelefono().isBlank()) {
            throw new NegocioException("El telefono es obligatorio");
        }
        if (nuevoEmprendedor.getRfc() == null || nuevoEmprendedor.getRfc().isBlank()) {
            throw new NegocioException("El RFC es obligatorio");
        }
        if (nuevoEmprendedor.getCuentaUsuario() == null) {
            throw new NegocioException("Los datos de la cuenta de usuario son obligatorios");
        }
        if (nuevoEmprendedor.getCuentaUsuario().getContrasenia() == null
                || nuevoEmprendedor.getCuentaUsuario().getContrasenia().isBlank()) {
            throw new NegocioException("La contraseña es obligatoria");
        }
        if (nuevoEmprendedor.getDocumentoINE() == null) {
            throw new NegocioException("El documento INE es obligatorio");
        }
        if (!nuevoEmprendedor.getNombre().matches("^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ\\s]+$")) {
            throw new NegocioException("El nombre solo puede tener letras y espacios");
        }
        if (!nuevoEmprendedor.getCorreo().matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            throw new NegocioException("El formato del correo es incorrecto");
        }
        if (!nuevoEmprendedor.getTelefono().matches("^\\d{" + DIGITOS_TELEFONO + "}$")) {
            throw new NegocioException("El telefono debe tener exactamente 10 digitos");
        }
        if (!nuevoEmprendedor.getRfc().matches("^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$")) {
            throw new NegocioException("El RFC no tiene un formato valido");
        }
        int longContrasenia = nuevoEmprendedor.getCuentaUsuario().getContrasenia().length();
        if (longContrasenia < MIN_CONTRASENIA || longContrasenia > MAX_CONTRASENIA) {
            throw new NegocioException("La contraseña debe tener entre 8 y 16 caracteres");
        }
        if (nuevoEmprendedor.getFotoPerfil() != null
                && nuevoEmprendedor.getFotoPerfil().getImagen() != null
                && nuevoEmprendedor.getFotoPerfil().getImagen().length > MAX_BYTES_IMAGEN) {
            throw new NegocioException("La imagen no puede pesar mas de 5MB");
        }
        if (nuevoEmprendedor.getDocumentoINE().getDocumento() != null
                && nuevoEmprendedor.getDocumentoINE().getDocumento().length > MAX_BYTES_DOCUMENTO) {
            throw new NegocioException("El documento no puede pesar mas de 5MB.");
        }
    }

    /**
     * Valida un nuevo negocio para que no se gurden datos incorrectos
     *
     * @param nuevoNegocio el negocio que se va a validar
     * @throws NegocioException
     */
    private void validarNegocio(NegocioDTO nuevoNegocio) throws NegocioException {
        if (nuevoNegocio == null) {
            throw new NegocioException("Los datos del negocio son obligatorios");
        }
        if (nuevoNegocio.getNombre() == null || nuevoNegocio.getNombre().isBlank()) {
            throw new NegocioException("El nombre del negocio es obligatorio");
        }
        if (nuevoNegocio.getTipoNegocio() == null) {
            throw new NegocioException("El tipo de negocio es obligatorio.");
        }
        if (nuevoNegocio.getDireccion() == null) {
            throw new NegocioException("La direccion del negocio es obligatoria");
        }
        if (!nuevoNegocio.getNombre().matches("^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ0-9\\s]+$")) {
            throw new NegocioException("El nombre del negocio tiene caracteres no permitidos");
        }

        validarDireccion(nuevoNegocio.getDireccion());
    }

    /**
     * Valida una nueva direccion para que no se manden datos incorrectos
     *
     * @param nuevaDireccion la direccion que se va a validar
     * @throws NegocioException
     */
    private void validarDireccion(DireccionDTO nuevaDireccion) throws NegocioException {
        if (nuevaDireccion.getCalle() == null || nuevaDireccion.getCalle().isBlank()) {
            throw new NegocioException("La calle es obligatoria");
        }
        if (nuevaDireccion.getNumero() == null || nuevaDireccion.getNumero().isBlank()) {
            throw new NegocioException("El numero de la direccion es obligatorio");
        }
        if (nuevaDireccion.getColonia() == null || nuevaDireccion.getColonia().isBlank()) {
            throw new NegocioException("La colonia es obligatoria");
        }
        if (nuevaDireccion.getCodigoPostal() == null || nuevaDireccion.getCodigoPostal().isBlank()) {
            throw new NegocioException("El codigo postal es obligatorio");
        }
        if (!nuevaDireccion.getNumero().matches("^\\d+$")) {
            throw new NegocioException("El numero de la direccion solo puede contener digitos");
        }
        if (!nuevaDireccion.getCodigoPostal().matches("^\\d{" + DIGITOS_CODIGO_POSTAL + "}$")) {
            throw new NegocioException("El codigo postal debe tener exactamente 5 digitos");
        }
    }

    /**
     * Valida una nueva cuenta bancaria para que no se guarden datos incorrectos
     *
     * @param nuevaCuentaBancaria la cuenta que se va a validar
     * @throws NegocioException
     */
    private void validarCuentaBancaria(CuentaBancariaDTO nuevaCuentaBancaria) throws NegocioException {
        if (nuevaCuentaBancaria == null) {
            throw new NegocioException("Los datos de la cuenta bancaria son obligatorios");
        }
        if (nuevaCuentaBancaria.getNumeroCuenta() == null || nuevaCuentaBancaria.getNumeroCuenta().isBlank()) {
            throw new NegocioException("El numero de cuenta es obligatorio");
        }
        if (nuevaCuentaBancaria.getNombreTitular() == null || nuevaCuentaBancaria.getNombreTitular().isBlank()) {
            throw new NegocioException("El nombre del titular es obligatorio");
        }
        if (nuevaCuentaBancaria.getInstitucionBancaria() == null) {
            throw new NegocioException("La institución bancaria es obligatoria");
        }
        if (!nuevaCuentaBancaria.getNumeroCuenta().matches("^\\d{" + MIN_DIGITOS_CUENTA
                + "," + MAX_DIGITOS_CUENTA + "}$")) {
            throw new NegocioException("El numero de cuenta debe tener entre 10 y 18 digitos");
        }
        if (!nuevaCuentaBancaria.getNombreTitular().matches("^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ\\s]+$")) {
            throw new NegocioException("El nombre del titular solo puede tener letras y espacios");
        }
    }

    @Override
    public EmprendedorDTO registrarEmprendedor(RegistroEmprendedorDTO nuevoEmprendedor) throws NegocioException {
        validarEmprendedor(nuevoEmprendedor.getEmprendedor());
        validarNegocio(nuevoEmprendedor.getNegocio());
        validarCuentaBancaria(nuevoEmprendedor.getCuentaBancaria());

        return fachadaNegocio.registrarEmprendedor(nuevoEmprendedor);
    }

    @Override
    public EmprendedorDTO actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedorDTO estatus) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        if (estatus == null) {
            throw new NegocioException("El estatus es obligatorio");
        }
        return fachadaNegocio.actualizarEstatusEmprendedor(idEmprendedor, estatus);
    }

    @Override
    public EmprendedorDTO obtenerEmprendedorPorID(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        return fachadaNegocio.obtenerEmprendedorPorID(idEmprendedor);
    }

    @Override
    public List<EmprendedorDTO> consultarEmprendedores(String nombre, String rfc, EstatusEmprendedorDTO estatus) throws NegocioException {
        return fachadaNegocio.consultarEmprendedores(nombre, rfc, estatus);
    }

    @Override
    public ReporteEmprendedoresDTO generarDatosReportePDF() throws NegocioException {
        return fachadaNegocio.generarDatosReportePDF();
    }

    @Override
    public CuentaBancariaDTO obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        return fachadaNegocio.obtenerCuentaBancariaPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public NegocioDTO obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        return fachadaNegocio.obtenerNegocioPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public DireccionDTO obtenerDireccionPorIdNegocio(String idNegocio) throws NegocioException {
        if (idNegocio == null || idNegocio.isBlank()) {
            throw new NegocioException("El id del negocio es obligatorio");
        }
        return fachadaNegocio.obtenerDireccionPorIdNegocio(idNegocio);
    }

    @Override
    public DocumentoDTO actualizarDocumentoEmprendedor(String idEmprendedor, DocumentoDTO documento) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        if (documento == null || documento.getDocumento() == null) {
            throw new NegocioException("El documento es obligatorio");
        }
        if (documento.getDocumento().length > MAX_BYTES_DOCUMENTO) {
            throw new NegocioException("El documento no puede pesar mas de 5MB");
        }
        return fachadaNegocio.actualizarDocumento(idEmprendedor, documento);
    }

    @Override
    public DocumentoDTO obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        return fachadaNegocio.obtenerDocumentoPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public ImagenDTO actualizarImagen(String idEmprendedor, ImagenDTO imagen) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        if (imagen != null && imagen.getImagen() != null
                && imagen.getImagen().length > MAX_BYTES_IMAGEN) {
            throw new NegocioException("La imagen no puede pesar mas de 5MB");
        }
        return fachadaNegocio.actualizarImagen(idEmprendedor, imagen);
    }

    @Override
    public ImagenDTO obtenerImagenPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio");
        }
        return fachadaNegocio.obtenerImagenPorIdEmprendedor(idEmprendedor);
    }

    @Override
    public DireccionDTO actualizarDireccion(String idNegocio, DireccionDTO direccion) throws NegocioException {
        if (idNegocio == null || idNegocio.isBlank()) {
            throw new NegocioException("El id del negocio es obligatorio");
        }
        validarDireccion(direccion);
        return fachadaNegocio.actualizarDireccion(idNegocio, direccion);
    }

    @Override
    public CuentaUsuarioSesionDTO buscarCuenta(String correo) throws NegocioException {
        if (correo == null || correo.isBlank()) {
            throw new NegocioException("El correo es obligatorio");
        }
        if (!correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            throw new NegocioException("El formato del correo es incorrecto");
        }
        return fachadaNegocio.buscarCuenta(correo);
    }

    @Override
    public CuentaUsuarioSesionDTO iniciarSesion(String correo, String contrasenia) throws NegocioException {
        if (correo == null || correo.isBlank()) {
            throw new NegocioException("El correo es obligatorio");
        }
        if (contrasenia == null || contrasenia.isBlank()) {
            throw new NegocioException("La contraseña es obligatoria");
        }
        return fachadaNegocio.iniciarSesion(correo, contrasenia);
    }

    @Override
    public boolean generarYDescargarPDF(ReporteEmprendedoresDTO reporte, String rutaDestino) throws NegocioException {
        return fachadaNegocio.generarYDescargarReportePDF(reporte, rutaDestino);
    }

}
