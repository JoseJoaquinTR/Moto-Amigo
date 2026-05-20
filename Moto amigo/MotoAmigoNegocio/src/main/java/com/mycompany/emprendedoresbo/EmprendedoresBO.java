package com.mycompany.emprendedoresbo;

import Adapter.AdapterCuentaBancaria;
import Adapter.AdapterCuentaUsuario;
import Adapter.AdapterDocumento;
import Adapter.AdapterEmprendedor;
import Adapter.AdapterImagen;
import Adapter.AdapterNegocio;
import Enums.EstatusEmprendedor;
import com.itextpdf.text.DocumentException;
import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.Entidades.Documento;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.Entidades.Imagen;
import com.mycompany.Entidades.Negocio;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import com.mycompany.emprendedoresdto.ImagenDTO;
import com.mycompany.emprendedoresdto.RegistroEmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.motoamigonegocio.Observers.IObservableEstatusEmprendedor;
import com.mycompany.motoamigonegocio.Observers.IObservadorEstatusEmprendedor;
import com.mycompany.motoamigonegocio.Observers.ObservadorEstatusEmprendedor;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import enums.EstatusEmprendedorDTO;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Implementación de la lógica de negocio para emprendedores.
 *
 * @author Jesus Omar
 */
public class EmprendedoresBO implements IEmprendedoresBO, IObservableEstatusEmprendedor {

    private static EmprendedoresBO instancia;
    private final IFachadaPersistencia persistencia;
    private final List<IObservadorEstatusEmprendedor> observadores;
    /**
     * Constructor privado para que solo se pueda crear la instancia dentro de
     * la clase, y para que solo haya una
     */
    private EmprendedoresBO() {
        this.persistencia = FachadaPersistencia.getInstancia();
        this.observadores = new ArrayList<>();
    }

    public static EmprendedoresBO getInstanciaConObserver() {
        EmprendedoresBO instancia = getInstancia();
        instancia.agregarObservador(new ObservadorEstatusEmprendedor());
        return instancia;
    }

    /**
     * Metodo para obetner la instancia de EmprendedoresBO, si no existe ninguna
     * accede al constructor privado para crearla y te la regresa
     *
     * @return la instancia de EmprendedoresBO
     */
    public static EmprendedoresBO getInstancia() {
        if (instancia == null) {
            instancia = new EmprendedoresBO();
        }
        return instancia;
    }

    /**
     * Metodo para registrar un nuevo emprendedor
     *
     * @param nuevoEmprendedorDTO la DTO del emprendedor que se va a registrar
     * @return regresa la DTO del emprendedor que se registro
     * @throws NegocioException
     */
    @Override
    public EmprendedorDTO registrarEmprendedor(RegistroEmprendedorDTO nuevoEmprendedorDTO) throws NegocioException {
        try {
            // primero hashea la contrasenia para mandarsela al adapter que convierte la dto a entidad
            // con la contrasenia ya hasheada
            String contraHasheada = BCrypt.hashpw(nuevoEmprendedorDTO.getEmprendedor().getCuentaUsuario().getContrasenia(), BCrypt.gensalt());
            // luego adaptamos el emprendedor y lo registramos
            Emprendedor emprendedor = AdapterEmprendedor.emprendedorDTOAEmprendedor(nuevoEmprendedorDTO.getEmprendedor(), contraHasheada);
            Emprendedor emprendedorRegistrado = persistencia.registrarEmprendedor(emprendedor);
            // luego adaptamos el negocio y lo registramos tambien
            Negocio negocio = AdapterNegocio.negocioDTOANegocio(nuevoEmprendedorDTO.getNegocio(), emprendedorRegistrado.getIdEmprendedor());
            persistencia.registrarNegocio(negocio);

            return AdapterEmprendedor.emprendedorAEmprendedorDTO(emprendedorRegistrado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el emprendedor", ex);
        }
    }

    /**
     * Actualiza el estatus de un emprendedor a ACTIVO, BAJA o RECHAZADO, este
     * es el metodo que usaria el administrador
     *
     * @param idEmprendedor el id del emprendedor al que se le actualizara el
     * estatus
     * @param estatus el nuevo estatus que tendra el emprendedor
     * @return regresa la DTO del emprendedor al que se le actualizo el estatus
     * @throws NegocioException
     */
    @Override
    public EmprendedorDTO actualizarEstatusEmprendedor(String idEmprendedor, EstatusEmprendedorDTO estatus) throws NegocioException {
        try {
            // primero adaptamos el estatus y luego lo actualizamos
            EstatusEmprendedor estatusNuevo = AdapterEmprendedor.estatusDTOAEstatus(estatus);
            Emprendedor emprendedorActualizado = persistencia.actualizarEstatusEmprendedor(idEmprendedor, estatusNuevo);
            // ya que se actualizo notificamos para activar o desactivar la cuenta
            notificarObservadores(idEmprendedor, estatusNuevo);
            return AdapterEmprendedor.emprendedorAEmprendedorDTO(emprendedorActualizado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar el estatus del emprendedor", ex);
        }
    }

    /**
     * Busca un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor que se quiere buscar
     * @return el emprendedor que coincide con el id que se mando
     * @throws NegocioException
     */
    @Override
    public EmprendedorDTO obtenerEmprendedorPorID(String idEmprendedor) throws NegocioException {
        try {
            Emprendedor emprendedor = persistencia.obtenerEmprendedorPorID(idEmprendedor);
            return AdapterEmprendedor.emprendedorAEmprendedorDTO(emprendedor);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener el emprendedor", ex);
        }
    }

    /**
     * Genera un reporte detallado de los emprendedores
     *
     * @return regresa los datos para el reporte PDF
     * @throws NegocioException
     */
    @Override
    public ReporteEmprendedoresDTO generarDatosReportePDF() throws NegocioException {
        try {
            List<Emprendedor> emprendedores = persistencia.consultarEmprendedores(null, null, null);
            List<EmprendedorDTO> listaReporte = new LinkedList<>();
            // contadores para la cantidad de los emprendedores con el mismo estatus
            int totalActivos = 0;
            int totalPendientes = 0;
            int totalRechazados = 0;
            int totalBaja = 0;
            // despues recorremos la lista para agregar los emprendadores e ir sumando
            // en los contadores
            for (Emprendedor e : emprendedores) {
                listaReporte.add(AdapterEmprendedor.emprendedorAEmprendedorDTO(e));
                EstatusEmprendedor estatus = e.getEstatus();
                if (estatus.equals(EstatusEmprendedor.ACTIVO)) {
                    totalActivos++;
                } else if (estatus.equals(EstatusEmprendedor.PENDIENTE)) {
                    totalPendientes++;
                } else if (estatus.equals(EstatusEmprendedor.RECHAZADO)) {
                    totalRechazados++;
                } else {
                    totalBaja++;
                }
            }

            return new ReporteEmprendedoresDTO(
                    "Reporte de Emprendedores",
                    new Date(),
                    listaReporte,
                    listaReporte.size(),
                    totalActivos,
                    totalPendientes,
                    totalRechazados,
                    totalBaja
            );
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar el reporte PDF", ex);
        }
    }

    /**
     * Busca la cuenta bancaria de un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener la
     * cuenta
     * @return la cuenta del emprendedor
     * @throws NegocioException
     */
    @Override
    public CuentaBancariaDTO obtenerCuentaBancariaPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        try {
            CuentaBancaria cuenta = persistencia.obtenerCuentaBancariaPorIdEmprendedor(idEmprendedor);
            CuentaBancariaDTO cuentaBancaria = AdapterCuentaBancaria.cuentaBancariaADTO(cuenta);
            return cuentaBancaria;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener la cuenta bancaria", ex);
        }
    }

    /**
     * Actualiza la imagen de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor al que se le actualizara la
     * imagen
     * @param imagen la nueva imagen
     * @return la imagen nueva
     * @throws NegocioException
     */
    @Override
    public ImagenDTO actualizarImagen(String idEmprendedor, ImagenDTO imagen) throws NegocioException {
        try {
            Imagen imagenNueva = persistencia.actualizarImagen(idEmprendedor, AdapterImagen.dtoAImagen(imagen));
            ImagenDTO imagenActualizada = AdapterImagen.imagenADTO(imagenNueva);
            return imagenActualizada;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la imagen", ex);
        }
    }

    /**
     * Obtiene la imagen de un emprendedor buscandola por su id
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener su
     * imagen
     * @return la imagen que tiene el emprendedor
     * @throws NegocioException
     */
    @Override
    public ImagenDTO obtenerImagenPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        try {
            Imagen imagen = persistencia.obtenerImagenPorIdEmprendedor(idEmprendedor);
            ImagenDTO imagenObtenida = AdapterImagen.imagenADTO(imagen);
            return imagenObtenida;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener la imagen", ex);
        }
    }

    /**
     * Actualiza el documento de la INE de un emprendedor
     *
     * @param idEmprendedor el id del emprendedor del que se quiere actualizar
     * su documento
     * @param documento el nuevo documento
     * @return el nuevo documento
     * @throws NegocioException
     */
    @Override
    public DocumentoDTO actualizarDocumento(String idEmprendedor, DocumentoDTO documento) throws NegocioException {
        try {
            Documento documentoNuevo = persistencia.actualizarDocumento(idEmprendedor, AdapterDocumento.dtoADocumento(documento));
            DocumentoDTO documentoActualizado = AdapterDocumento.documentoADTO(documentoNuevo);
            return documentoActualizado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar el documento", ex);
        }
    }

    /**
     * Obtiene el documento de un emprendedor por su id
     *
     * @param idEmprendedor el id del emprendedor del que se quiere obtener su
     * documento
     * @return
     * @throws NegocioException
     */
    @Override
    public DocumentoDTO obtenerDocumentoPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        try {
            Documento documento = persistencia.obtenerDocumentoPorIdEmprendedor(idEmprendedor);
            DocumentoDTO documentoObtenido = AdapterDocumento.documentoADTO(documento);
            return documentoObtenido;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener el documento", ex);
        }
    }

    /**
     * Busca la una cuenta por el correo
     *
     * @param correo el correo asociado a la cuenta
     * @return la CuentaUsuarioDTO con el idEmprendedor, EstatusEmprendedor,
     * idCuenta y el estado
     * @throws NegocioException
     */
    @Override
    public CuentaUsuarioSesionDTO buscarCuenta(String correo) throws NegocioException {
        try {
            Emprendedor emprendedor = persistencia.buscarCuentaPorCorreo(correo);
            CuentaUsuarioSesionDTO cuentaUsuario = AdapterCuentaUsuario.cuentaUsuarioADTO(emprendedor);
            return cuentaUsuario;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar la cuenta de usuario", ex);
        }
    }

    @Override
    public void agregarObservador(IObservadorEstatusEmprendedor observador) {
        observadores.add(observador);
    }

    @Override
    public void notificarObservadores(String idEmprendedor, EstatusEmprendedor estatus) {
        for (IObservadorEstatusEmprendedor observador : observadores) {
            observador.estatusEmprendedorActualizado(idEmprendedor, estatus);
        }
    }

    @Override
    public CuentaUsuarioSesionDTO iniciarSesion(String correo, String contrasenia) throws NegocioException {
        try {
            Emprendedor emprendedor = persistencia.buscarCuentaPorCorreo(correo);
            if (emprendedor == null) {
                return null;
            }
            if (!BCrypt.checkpw(contrasenia, emprendedor.getCuentaUsuario().getContrasenia())) {
                return null;
            }
            return AdapterCuentaUsuario.cuentaUsuarioADTO(emprendedor);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion", ex);
        }
    }

    @Override
    public List<EmprendedorDTO> consultarEmprendedores(String nombre, String rfc, EstatusEmprendedorDTO estatus) throws NegocioException {
        try {
            List<EmprendedorDTO> resultado = new LinkedList<>();
            for (Emprendedor e : persistencia.consultarEmprendedores(nombre, rfc, AdapterEmprendedor.estatusDTOAEstatus(estatus))) {
                resultado.add(AdapterEmprendedor.emprendedorAEmprendedorDTO(e));
            }
            return resultado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar los emprendedores", ex);
        }
    }

    @Override
    public boolean generarYDescargarReportePDF(ReporteEmprendedoresDTO reporte, String ruta) throws NegocioException {
        try {
            com.itextpdf.text.Document doc
                    = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(
                    doc, new FileOutputStream(ruta));
            doc.open();

            com.itextpdf.text.Font fuenteTitulo = new com.itextpdf.text.Font(
                    com.itextpdf.text.Font.FontFamily.HELVETICA, 18,
                    com.itextpdf.text.Font.BOLD);
            doc.add(new com.itextpdf.text.Paragraph(
                    "Reporte de Emprendedores", fuenteTitulo));
            doc.add(new com.itextpdf.text.Paragraph(
                    "Fecha: " + new java.util.Date()));
            doc.add(com.itextpdf.text.Chunk.NEWLINE);

            doc.add(new com.itextpdf.text.Paragraph(
                    "Total: " + reporte.getTotalEmprendedores()
                    + "  Activos: " + reporte.getTotalActivos()
                    + "  Pendientes: " + reporte.getTotalPendientes()
                    + "  Rechazados: " + reporte.getTotalRechazados()
                    + "  Baja: " + reporte.getTotalBaja()));
            doc.add(com.itextpdf.text.Chunk.NEWLINE);

            com.itextpdf.text.pdf.PdfPTable tabla
                    = new com.itextpdf.text.pdf.PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{3, 4, 2, 2, 2});

            com.itextpdf.text.Font fuenteHeader = new com.itextpdf.text.Font(
                    com.itextpdf.text.Font.FontFamily.HELVETICA, 11,
                    com.itextpdf.text.Font.BOLD,
                    com.itextpdf.text.BaseColor.WHITE);

            for (String header : new String[]{
                "Nombre", "Correo", "RFC", "Teléfono", "Estatus"}) {
                com.itextpdf.text.pdf.PdfPCell celda
                        = new com.itextpdf.text.pdf.PdfPCell(
                                new com.itextpdf.text.Phrase(header, fuenteHeader));
                celda.setBackgroundColor(new com.itextpdf.text.BaseColor(230, 72, 0));
                celda.setPadding(6);
                tabla.addCell(celda);
            }

            for (EmprendedorDTO e : reporte.getListaEmprendedores()) {
                tabla.addCell(e.getNombre() != null ? e.getNombre() : "");
                tabla.addCell(e.getCorreo() != null ? e.getCorreo() : "");
                tabla.addCell(e.getRfc() != null ? e.getRfc() : "");
                tabla.addCell(e.getTelefono() != null ? e.getTelefono() : "");
                tabla.addCell(e.getEstatus() != null ? e.getEstatus().name() : "");
            }

            doc.add(tabla);
            doc.close();
            return true;
        }catch(DocumentException ex){
            throw new NegocioException("Error al generar el PDF" + ex.getMessage());
        }catch(FileNotFoundException ex){
            throw new NegocioException("Error al generar el PDF" + ex.getMessage());
        }
    }
}
