package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.cuemprendedor.CURegistrarEmprendedorFachada;
import com.mycompany.cuemprendedor.ICURegistrarEmprendedorFachada;
import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.emprendedoresdto.DireccionDTO;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.ImagenDTO;
import com.mycompany.emprendedoresdto.NegocioDTO;
import com.mycompany.emprendedoresdto.NuevoEmprendedorDTO;
import com.mycompany.emprendedoresdto.RegistroEmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.emprendedorpresentacion.PanelPaso1;
import com.mycompany.emprendedorpresentacion.PanelPaso2;
import com.mycompany.emprendedorpresentacion.PanelPaso3;
import com.mycompany.motoamigonegocio.NegocioException;
import enums.EstatusEmprendedorDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Omar
 */
public class ControlEmprendedor {

    private static ControlEmprendedor instancia;
    private final ICURegistrarEmprendedorFachada cu;

    /**
     * Constructor privado para usarlo solo en la clase y para que haya una sola
     * instancia
     */
    private ControlEmprendedor() {
        this.cu = new CURegistrarEmprendedorFachada();
    }

    /**
     * Regresa la instancia de ControlEmprendedor, si no hay ninguna accede al
     * constructor privado para crearla y la regresa
     *
     * @return
     */
    public static ControlEmprendedor getInstancia() {
        if (instancia == null) {
            instancia = new ControlEmprendedor();
        }
        return instancia;
    }

    public boolean iniciarSesion(String correo, String contrasenia) {
        try {
            CuentaUsuarioSesionDTO cuenta = cu.iniciarSesion(correo, contrasenia);

            if (cuenta == null) {
                JOptionPane.showMessageDialog(null,
                        "Correo o contraseña incorrectos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!cuenta.isActiva()) {
                String mensaje = switch (cuenta.getEstatusEmprendedor()) {
                    case PENDIENTE ->
                        "Tu cuenta no ha sido aprobada";
                    case RECHAZADO ->
                        "Tu cuenta fue rechazada";
                    case BAJA ->
                        "Dieron de baja tu cuenta";
                    default ->
                        "Tu cuenta no esta activada";
                };
                JOptionPane.showMessageDialog(null, mensaje,
                        "Cuenta inactiva", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            SesionActiva.getInstancia().setCuenta(cuenta);
            return true;

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public String registrarEmprendedor(
            PanelPaso1 paso1,
            PanelPaso2 paso2,
            PanelPaso3 paso3) {
        try {
            // Armar DTOs
            ImagenDTO imagen = null;
            byte[] fotoBytes = paso1.getFotoPerfil();
            if (fotoBytes != null) {
                imagen = new ImagenDTO(fotoBytes);
            }

            NuevoEmprendedorDTO nuevoEmprendedor = new NuevoEmprendedorDTO(
                    paso1.getNombre(),
                    paso1.getCorreo(),
                    paso1.getTelefono(),
                    paso3.getRfc(),
                    imagen,
                    new DocumentoDTO(paso3.getDocumentoIne()),
                    new CuentaBancariaDTO(
                            paso3.getNumeroCuenta(),
                            paso3.getNombreTitular(),
                            paso3.getInstitucionBancaria()
                    ),
                    new CuentaUsuarioDTO(
                            paso1.getCorreo(),
                            paso1.getContrasenia()
                    )
            );

            NegocioDTO negocio = new NegocioDTO(
                    paso2.getNombreNegocio(),
                    new DireccionDTO(
                            paso2.getCalle(),
                            paso2.getNumero(),
                            paso2.getColonia(),
                            paso2.getCodigoPostal()
                    ),
                    paso2.getTipoNegocio()
            );

            RegistroEmprendedorDTO registro = new RegistroEmprendedorDTO(
                    nuevoEmprendedor,
                    negocio,
                    nuevoEmprendedor.getCuentaBancaria()
            );

            EmprendedorDTO registrado = cu.registrarEmprendedor(registro);
            return registrado.getIdEmprendedor();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public List<EmprendedorDTO> consultarEmprendedores() {
        try {
            return cu.consultarEmprendedores();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlEmprendedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public EmprendedorDTO obtenerEmprendedorPorID(String id) {
        try {
            return cu.obtenerEmprendedorPorID(id);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlEmprendedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ReporteEmprendedoresDTO generarDatosReportePDF() {
        try {
            return cu.generarDatosReportePDF();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlEmprendedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean actualizarEstatusEmprendedor(String idEmprendedor,
            EstatusEmprendedorDTO estatus) {
        try {
            cu.actualizarEstatusEmprendedor(idEmprendedor, estatus);
            return true;
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar estatus: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public EstatusEmprendedorDTO verificarEstatus(String idEmprendedor) {
        try {
            EmprendedorDTO emprendedor = cu.obtenerEmprendedorPorID(idEmprendedor);
            if (emprendedor == null) {
                return null;
            }
            CuentaUsuarioSesionDTO cuenta = cu.iniciarSesion(
                    SesionActiva.getInstancia().getCuenta().getCorreo(),
                    null 
            );
            if (cuenta == null) {
                return null;
            }
            return cuenta.getEstatusEmprendedor();
        } catch (NegocioException ex) {
            return null;
        }
    }
}

