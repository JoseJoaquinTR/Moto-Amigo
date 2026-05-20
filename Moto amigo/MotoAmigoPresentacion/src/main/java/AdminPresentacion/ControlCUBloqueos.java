package AdminPresentacion;

import com.github.lgooddatepicker.components.DatePicker;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteBloqueoDTO;
import com.mycompany.bloqueorepartidores.ReporteDesbloqueoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import com.mycompany.cubloqueos.*;
import enums.Tipo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author Carmen Andrea Lara Osuna
 */
public class ControlCUBloqueos {

    private final IFachadaNegocio fachada;
    private final IBloquearRepartidor bloquearRepartidorCU;
    private final IDesbloqueoMasivo desbloqueoMasivoCU;
    private final IGenerarPDFHistorialReportes generarPDFHistorial;
    private final List<RepartidorDTO> repartidoresBusqueda = new LinkedList<>();
    private final List<RepartidorDTO> repartidoresBloqueo = new LinkedList<>();
    private final List<MotivoDTO> motivosBloqueo = new LinkedList<>();
    private final List<MotivoDTO> motivosDesbloqueo = new LinkedList<>();
    private final List<ReporteBloqueoDTO> reportesBloqueo = new LinkedList<>();
    private final List<ReporteDesbloqueoDTO> reportesDesbloqueo = new LinkedList<>();
    private byte[] imagenEvidenciaBloqueo;

    private MenuAdminFORM menuAdmin;

    public ControlCUBloqueos() {
        this.fachada = FachadaNegocio.crear();
        this.bloquearRepartidorCU = BloquearRepartidor.getInstancia();
        this.desbloqueoMasivoCU = DesbloqueoMasivo.getInstancia();
        this.generarPDFHistorial = GenerarPDFHistorialReportes.getInstancia();
    }

    /**
     * Inicializa el menú principal del administrador y configura la navegación
     * entre pantallas.
     *
     * @param formMenu Pantalla principal del administrador.
     */
    public void inicializarMenuAdmin(MenuAdminFORM formMenu) {
        formMenu.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.menuAdmin = formMenu;

        formMenu.getBtnBloquearRepartidor()
                .addActionListener(e -> abrirConsultaRepartidores(formMenu));

        formMenu.getBtnDesbloqueoMasivo()
                .addActionListener(e -> abrirConsultaDesbloqueo(formMenu));

        formMenu.getBtnHistorialBloqueos()
                .addActionListener(e -> abrirHistorialBloqueos(formMenu));

        formMenu.getBtnHistorialDesbloqueos()
                .addActionListener(e -> abrirHistorialDesbloqueos(formMenu));
    }

    /**
     * Regresa al menú de administrador ocultando la pantalla actual.
     *
     * @param pantallaActual Pantalla que se cierra al regresar.
     */
    private void regresarAlMenu(java.awt.Window pantallaActual) {
        pantallaActual.dispose();
        if (menuAdmin != null) {
            menuAdmin.toFront();
            menuAdmin.requestFocus();
        }
    }

    private void abrirConsultaRepartidores(MenuAdminFORM formMenu) {
        ConsultarRepartidoresFORM formConsulta = new ConsultarRepartidoresFORM(ModoConsultaRepartidores.BLOQUEO);
        formConsulta.setLocationRelativeTo(formMenu);
        formConsulta.setVisible(true);
    }

    private void abrirConsultaDesbloqueo(MenuAdminFORM formMenu) {
        ConsultarRepartidoresFORM formConsulta = new ConsultarRepartidoresFORM(ModoConsultaRepartidores.DESBLOQUEO);
        formConsulta.setLocationRelativeTo(formMenu);
        formConsulta.setVisible(true);
    }

    private void abrirHistorialBloqueos(MenuAdminFORM formMenu) {
        HistorialBloqueosFORM formHistorial = new HistorialBloqueosFORM();
        formHistorial.setLocationRelativeTo(formMenu);
        formHistorial.setVisible(true);
    }

    private void abrirHistorialDesbloqueos(MenuAdminFORM formMenu) {
        HistorialDesbloqueosFORM formHistorial = new HistorialDesbloqueosFORM();
        formHistorial.setLocationRelativeTo(formMenu);
        formHistorial.setVisible(true);
    }

    private void abrirFormularioSegunModo(ConsultarRepartidoresFORM formConsulta, ModoConsultaRepartidores modo) {
        if (modo == ModoConsultaRepartidores.BLOQUEO) {
            BloquearRepartidorFORM formBloq = new BloquearRepartidorFORM();
            formBloq.setLocationRelativeTo(formConsulta);
            formBloq.setVisible(true);
        } else {
            DesbloqueoMasivoFORM formDesbloq = new DesbloqueoMasivoFORM();
            formDesbloq.setLocationRelativeTo(formConsulta);
            formDesbloq.setVisible(true);
        }
        formConsulta.dispose();
    }

    /**
     * Inicializa la pantalla de consulta de repartidores.
     *
     * @param formConsulta Pantalla de consulta de repartidores.
     * @param modo bloqueo o desbloqueo segun sea el caso
     */
    public void inicializarConsultarRepartidores(ConsultarRepartidoresFORM formConsulta, ModoConsultaRepartidores modo) {
        if (modo == ModoConsultaRepartidores.BLOQUEO) {
            formConsulta.getLblTitulo().setText("Bloquear Repartidor");
            formConsulta.getBtnBloquear().setText("Bloquear");
        } else {
            formConsulta.getLblTitulo().setText("Desbloquear Repartidor");
            formConsulta.getBtnBloquear().setText("Desbloquear");
        }

        formConsulta.getBtnRegresar().addActionListener(e -> regresarAlMenu(formConsulta));

        cargarTablaBusquedaRepartidores(formConsulta, "");
        formConsulta.getTxtNombre().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cargarTablaBusquedaRepartidores(formConsulta, formConsulta.getTxtNombre().getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cargarTablaBusquedaRepartidores(formConsulta, formConsulta.getTxtNombre().getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                cargarTablaBusquedaRepartidores(formConsulta, formConsulta.getTxtNombre().getText().trim());
            }
        });

        formConsulta.getBtnBloquear().addActionListener(e -> abrirFormularioSegunModo(formConsulta, modo));
    }

    private void cargarTablaBusquedaRepartidores(ConsultarRepartidoresFORM formConsulta, String nombre) {
        try {
            repartidoresBusqueda.clear();
            repartidoresBusqueda.addAll(buscarRepartidores(nombre));
            llenarTablaRepartidores(formConsulta.getTblRepartidores(), repartidoresBusqueda);
        } catch (NegocioException ex) {
            mostrarError(formConsulta, "Error al buscar repartidores: " + ex.getMessage());
        }
    }

    public List<RepartidorDTO> buscarRepartidores(String nombre) throws NegocioException {
        if (nombre == null || nombre.isBlank()) {
            return fachada.obtenerRepartidores();
        }
        return fachada.buscarRepartidoresPorNombre(nombre);
    }

    public void llenarTablaRepartidores(JTable tabla, List<RepartidorDTO> repartidores) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (RepartidorDTO repartidor : repartidores) {
            modelo.addRow(new Object[]{repartidor.getNombre(), repartidor.getEstado(), repartidor.getNumBloqueos()});
        }
    }

    /**
     * Inicializa la pantalla de bloqueo de repartidor.
     *
     * @param formBloq Pantalla de bloqueo.
     */
    public void inicializarBloquearRepartidor(BloquearRepartidorFORM formBloq) {
        cargarComboRepartidoresActivos(formBloq);
        cargarComboMotivos(formBloq.getCboMotivos(), motivosBloqueo, Tipo.BLOQUEO, false, formBloq);
        formBloq.getBtnSeleccionarArchivo().addActionListener(e -> seleccionarImagenBloqueo(formBloq));
        formBloq.getBtnBloquear().addActionListener(e -> generarReporteBloqueo(formBloq));
        formBloq.getBtnRegresar().addActionListener(e -> regresarAlMenu(formBloq));
    }

    private void cargarComboRepartidoresActivos(BloquearRepartidorFORM formBloq) {
        try {
            repartidoresBloqueo.clear();
            repartidoresBloqueo.addAll(fachada.obtenerRepartidoresActivos());
            formBloq.getCboRepartidores().removeAllItems();
            for (RepartidorDTO repartidor : repartidoresBloqueo) {
                formBloq.getCboRepartidores().addItem(repartidor.getNombre());
            }
        } catch (NegocioException ex) {
            mostrarError(formBloq, "Error al cargar repartidores: " + ex.getMessage());
        }
    }

    private void seleccionarImagenBloqueo(BloquearRepartidorFORM formBloq) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen de evidencia");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
        if (fileChooser.showOpenDialog(formBloq) == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = fileChooser.getSelectedFile();
                imagenEvidenciaBloqueo = Files.readAllBytes(archivo.toPath());
                formBloq.getBtnSeleccionarArchivo().setText(archivo.getName());
            } catch (IOException ex) {
                mostrarError(formBloq, "Error al cargar imagen: " + ex.getMessage());
            }
        }
    }

    private void generarReporteBloqueo(BloquearRepartidorFORM formBloq) {
        try {
            NuevoReporteBloqueoDTO dto = new NuevoReporteBloqueoDTO();
            dto.setRepartidor(obtenerRepartidorBloqueoSeleccionado(formBloq));
            dto.setMotivo(obtenerMotivoBloqueoSeleccionado(formBloq));
            dto.setDetalles(formBloq.getTxtDetalles().getText().trim());
            dto.setFechaHora(LocalDateTime.now());
            dto.setImagenEvidencia(imagenEvidenciaBloqueo);

            ReporteBloqueoDTO reporte = bloquearRepartidorCU.bloquearRepartidor(dto);
            ConfirmacionBloqueoFORM confirmacion = new ConfirmacionBloqueoFORM(reporte);
            confirmacion.setLocationRelativeTo(formBloq);
            confirmacion.setVisible(true);
            formBloq.dispose();
        } catch (NegocioException ex) {
            mostrarError(formBloq, ex.getMessage());
        }
    }

    public RepartidorDTO obtenerRepartidorBloqueoSeleccionado(BloquearRepartidorFORM formBloq) {
        int indice = formBloq.getCboRepartidores().getSelectedIndex();
        if (indice < 0 || indice >= repartidoresBloqueo.size()) {
            return null;
        }
        return repartidoresBloqueo.get(indice);
    }

    public MotivoDTO obtenerMotivoBloqueoSeleccionado(BloquearRepartidorFORM formBloq) {
        return obtenerMotivoSeleccionado(formBloq.getCboMotivos(), motivosBloqueo, false);
    }

    /**
     * Inicializa la pantalla de desbloqueo masivo.
     *
     * @param formDesbloq Pantalla de desbloqueo masivo.
     */
    public void inicializarDesbloqueoMasivo(DesbloqueoMasivoFORM formDesbloq) {
        cargarComboMotivos(formDesbloq.getCboMotivosFiltros(), motivosBloqueo, Tipo.BLOQUEO, true, formDesbloq);
        cargarComboMotivos(formDesbloq.getCboMotivos(), motivosDesbloqueo, Tipo.DESBLOQUEO, false, formDesbloq);
        formDesbloq.getBtnDesbloquear().addActionListener(e -> generarReporteDesbloqueoMasivo(formDesbloq));
        formDesbloq.getBtnRegresar().addActionListener(e -> regresarAlMenu(formDesbloq));
    }

    private void generarReporteDesbloqueoMasivo(DesbloqueoMasivoFORM formDesbloq) {
        try {
            NuevoReporteDesbloqueoDTO dto = new NuevoReporteDesbloqueoDTO();
            dto.setMotivo(obtenerMotivoDesbloqueoSeleccionado(formDesbloq));
            dto.setDetalles(formDesbloq.getTxtDetalles().getText().trim());
            dto.setFechaHora(LocalDateTime.now());

            ReporteDesbloqueoDTO reporte = desbloqueoMasivoCU.desbloqueoMasivo(obtenerFiltrosDesbloqueo(formDesbloq), dto);
            ConfirmacionDesbloqueoFORM confirmacion = new ConfirmacionDesbloqueoFORM(reporte);
            confirmacion.setLocationRelativeTo(formDesbloq);
            confirmacion.setVisible(true);
            formDesbloq.dispose();
        } catch (NegocioException ex) {
            mostrarError(formDesbloq, ex.getMessage());
        }
    }

    public FiltrosDTO obtenerFiltrosDesbloqueo(DesbloqueoMasivoFORM formDesbloq) {
        FiltrosDTO filtros = new FiltrosDTO();
        filtros.setFechaDesde(obtenerFechaDesde(formDesbloq.getDtpFechaDesde()));
        filtros.setFechaHasta(obtenerFechaHasta(formDesbloq.getDtpFechaHasta()));
        filtros.setMotivo(obtenerMotivoSeleccionado(formDesbloq.getCboMotivosFiltros(), motivosBloqueo, true));
        filtros.setNumBloqueos(obtenerNumeroBloqueosSeleccionado(formDesbloq.getCboNumBloqueos()));
        return filtros;
    }

    public MotivoDTO obtenerMotivoDesbloqueoSeleccionado(DesbloqueoMasivoFORM formDesbloq) {
        return obtenerMotivoSeleccionado(formDesbloq.getCboMotivos(), motivosDesbloqueo, false);
    }

    /**
     * Inicializa la pantalla de confirmación de bloqueo.
     *
     * @param formConfirmacion Pantalla de confirmación.
     * @param reporte Reporte generado.
     */
    public void inicializarConfirmacionBloqueo(ConfirmacionBloqueoFORM formConfirmacion, ReporteBloqueoDTO reporte) {
        llenarDatosConfirmacionBloqueo(formConfirmacion, reporte);
        formConfirmacion.getBtnTerminar().addActionListener(e -> regresarAlMenu(formConfirmacion));
    }

    private void llenarDatosConfirmacionBloqueo(ConfirmacionBloqueoFORM formConfirmacion, ReporteBloqueoDTO reporte) {
        if (reporte == null) {
            mostrarError(formConfirmacion, "No se recibió información del reporte.");
            return;
        }
        formConfirmacion.getLblMotivo().setText(reporte.getMotivo() != null ? reporte.getMotivo().getMotivo() : "");
        formConfirmacion.getTxtDetalles().setText(reporte.getDetalles() == null ? "" : reporte.getDetalles());
        formConfirmacion.getTxtDetalles().setEditable(false);
        formConfirmacion.getFormatFecha().setText(reporte.getFechaHora() != null ? reporte.getFechaHora().toLocalDate().toString() : "");
    }

    /**
     * Inicializa la pantalla de confirmación de desbloqueo.
     *
     * @param formConfirmacion Pantalla de confirmación.
     * @param reporte Reporte de desbloqueo.
     */
    public void inicializarConfirmacionDesbloqueo(ConfirmacionDesbloqueoFORM formConfirmacion, ReporteDesbloqueoDTO reporte) {
        llenarDatosConfirmacionDesbloqueo(formConfirmacion, reporte);
        formConfirmacion.getBtnTerminar().addActionListener(e -> regresarAlMenu(formConfirmacion));
    }

    private void llenarDatosConfirmacionDesbloqueo(ConfirmacionDesbloqueoFORM formConfirmacion, ReporteDesbloqueoDTO reporte) {
        if (reporte == null) {
            mostrarError(formConfirmacion, "No se recibió información del reporte.");
            return;
        }
        formConfirmacion.getLblMotivo().setText(reporte.getMotivo() != null ? reporte.getMotivo().getMotivo() : "");
        formConfirmacion.getTxtDetalles().setText(reporte.getDetalles() == null ? "" : reporte.getDetalles());
        formConfirmacion.getTxtDetalles().setEditable(false);
        formConfirmacion.getFormatFecha().setText(reporte.getFechaHora() != null ? reporte.getFechaHora().toLocalDate().toString() : "");
        formConfirmacion.getLblNumRepartidoresDesbloqueados().setText(String.valueOf(reporte.getNumRepartidoresDesbloqueados()));
    }

    /**
     * Inicializa la pantalla de historial de bloqueos.
     *
     * @param formHistorial Pantalla de historial de bloqueos.
     */
    public void inicializarHistorialBloqueos(HistorialBloqueosFORM formHistorial) {
        cargarComboMotivos(formHistorial.getCboMotivosFiltros1(), motivosBloqueo, Tipo.BLOQUEO, true, formHistorial);
        cargarTablaHistorialBloqueos(formHistorial);
        formHistorial.getCboMotivosFiltros1().addActionListener(e -> cargarTablaHistorialBloqueos(formHistorial));
        formHistorial.getDtpFechaDesde1().addDateChangeListener(e -> cargarTablaHistorialBloqueos(formHistorial));
        formHistorial.getDtpFechaHasta1().addDateChangeListener(e -> cargarTablaHistorialBloqueos(formHistorial));
        formHistorial.getBtnGenerarPDF().addActionListener(e -> generarPDFHistorialBloqueos(formHistorial));
        formHistorial.getBtnRegresar().addActionListener(e -> regresarAlMenu(formHistorial));
    }

    private FiltrosDTO obtenerFiltrosHistorialBloqueos(HistorialBloqueosFORM formHistorial) {
        FiltrosDTO filtros = new FiltrosDTO();
        filtros.setFechaDesde(obtenerFechaDesde(formHistorial.getDtpFechaDesde1()));
        filtros.setFechaHasta(obtenerFechaHasta(formHistorial.getDtpFechaHasta1()));
        filtros.setMotivo(obtenerMotivoSeleccionado(formHistorial.getCboMotivosFiltros1(), motivosBloqueo, true));
        return filtros;
    }

    private void cargarTablaHistorialBloqueos(HistorialBloqueosFORM formHistorial) {
        try {
            reportesBloqueo.clear();
            reportesBloqueo.addAll(fachada.consultarReportesBloqueos(obtenerFiltrosHistorialBloqueos(formHistorial)));
            llenarTablaHistorialBloqueos(formHistorial, reportesBloqueo);
        } catch (NegocioException ex) {
            mostrarError(formHistorial, ex.getMessage());
        }
    }

    private void llenarTablaHistorialBloqueos(HistorialBloqueosFORM formHistorial, List<ReporteBloqueoDTO> reportes) {
        DefaultTableModel modelo = (DefaultTableModel) formHistorial.getTblBloqueos().getModel();
        modelo.setRowCount(0);
        for (ReporteBloqueoDTO reporte : reportes) {
            String nombreRepartidor = reporte.getRepartidor() != null ? reporte.getRepartidor().getNombre() : "";
            String motivo = reporte.getMotivo() != null ? reporte.getMotivo().getMotivo() : "";
            modelo.addRow(new Object[]{reporte.getFechaHora(), nombreRepartidor, motivo});
        }
    }

    /**
     * Inicializa la pantalla de historial de desbloqueos.
     *
     * @param formHistorialDesbloq Pantalla de historial de desbloqueos.
     */
    public void inicializarHistorialDesbloqueos(HistorialDesbloqueosFORM formHistorialDesbloq) {
        cargarComboMotivos(formHistorialDesbloq.getCboMotivosFiltros(), motivosDesbloqueo, Tipo.DESBLOQUEO, true, formHistorialDesbloq);
        cargarTablaHistorialDesbloqueos(formHistorialDesbloq);
        formHistorialDesbloq.getCboMotivosFiltros().addActionListener(e -> cargarTablaHistorialDesbloqueos(formHistorialDesbloq));
        formHistorialDesbloq.getDtpFechaDesde().addDateChangeListener(e -> cargarTablaHistorialDesbloqueos(formHistorialDesbloq));
        formHistorialDesbloq.getDtpFechaHasta().addDateChangeListener(e -> cargarTablaHistorialDesbloqueos(formHistorialDesbloq));
        formHistorialDesbloq.getBtnGenerarPDF().addActionListener(e -> generarPDFHistorialDesbloqueos(formHistorialDesbloq));
        formHistorialDesbloq.getBtnRegresar().addActionListener(e -> regresarAlMenu(formHistorialDesbloq));
    }

    private FiltrosDTO obtenerFiltrosHistorialDesbloqueos(HistorialDesbloqueosFORM formHistorialDesbloq) {
        FiltrosDTO filtros = new FiltrosDTO();
        filtros.setFechaDesde(obtenerFechaDesde(formHistorialDesbloq.getDtpFechaDesde()));
        filtros.setFechaHasta(obtenerFechaHasta(formHistorialDesbloq.getDtpFechaHasta()));
        filtros.setMotivo(obtenerMotivoSeleccionado(formHistorialDesbloq.getCboMotivosFiltros(), motivosDesbloqueo, true));
        return filtros;
    }

    private void cargarTablaHistorialDesbloqueos(HistorialDesbloqueosFORM formHistorialDesbloq) {
        try {
            reportesDesbloqueo.clear();
            reportesDesbloqueo.addAll(fachada.consultarReportesDesbloqueos(obtenerFiltrosHistorialDesbloqueos(formHistorialDesbloq)));
            llenarTablaHistorialDesbloqueos(formHistorialDesbloq, reportesDesbloqueo);
        } catch (NegocioException ex) {
            mostrarError(formHistorialDesbloq, ex.getMessage());
        }
    }

    private void llenarTablaHistorialDesbloqueos(HistorialDesbloqueosFORM formHistorialDesbloq, List<ReporteDesbloqueoDTO> reportes) {
        DefaultTableModel modelo = (DefaultTableModel) formHistorialDesbloq.getTblDesbloqueos().getModel();
        modelo.setRowCount(0);
        for (ReporteDesbloqueoDTO reporte : reportes) {
            String motivo = reporte.getMotivo() != null ? reporte.getMotivo().getMotivo() : "";
            modelo.addRow(new Object[]{reporte.getFechaHora(), reporte.getNumRepartidoresDesbloqueados(), motivo});
        }
    }

    private void cargarComboMotivos(JComboBox<String> combo, List<MotivoDTO> listaMotivos, Tipo tipo, boolean incluirTodos, java.awt.Component parent) {
        try {
            listaMotivos.clear();
            listaMotivos.addAll(fachada.obtenerMotivos(tipo));
            combo.removeAllItems();
            if (incluirTodos) {
                combo.addItem("Todos");
            }
            for (MotivoDTO motivo : listaMotivos) {
                combo.addItem(motivo.getMotivo());
            }
        } catch (NegocioException ex) {
            mostrarError(parent, "Error al cargar motivos: " + ex.getMessage());
        }
    }

    private MotivoDTO obtenerMotivoSeleccionado(JComboBox<String> combo, List<MotivoDTO> listaMotivos, boolean tieneTodos) {
        int indice = combo.getSelectedIndex();
        if (tieneTodos) {
            if (indice <= 0) {
                return null;
            }
            return listaMotivos.get(indice - 1);
        }
        if (indice < 0 || indice >= listaMotivos.size()) {
            return null;
        }
        return listaMotivos.get(indice);
    }

    private Integer obtenerNumeroBloqueosSeleccionado(JComboBox<String> combo) {
        Object valor = combo.getSelectedItem();
        if (valor == null) {
            return null;
        }
        String texto = valor.toString();
        if (texto.equalsIgnoreCase("Todos")) {
            return null;
        }
        return Integer.valueOf(texto);
    }

    private LocalDateTime obtenerFechaDesde(DatePicker datePicker) {
        if (datePicker.getDate() == null) {
            return null;
        }
        return datePicker.getDate().atStartOfDay();
    }

    private LocalDateTime obtenerFechaHasta(DatePicker datePicker) {
        if (datePicker.getDate() == null) {
            return null;
        }
        return datePicker.getDate().atTime(23, 59, 59);
    }

    private void mostrarError(java.awt.Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarInfo(java.awt.Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void generarPDFHistorialBloqueos(HistorialBloqueosFORM formHistorial) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF de historial de bloqueos");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

        if (fileChooser.showSaveDialog(formHistorial) == JFileChooser.APPROVE_OPTION) {
            try {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                if (!ruta.toLowerCase().endsWith(".pdf")) {
                    ruta += ".pdf";
                }
                FiltrosDTO filtros = obtenerFiltrosHistorialBloqueos(formHistorial);
                generarPDFHistorial.descargarPDFHistorialBloqueos(filtros, ruta);
                mostrarInfo(formHistorial, "PDF generado correctamente.");
            } catch (NegocioException ex) {
                mostrarError(formHistorial, ex.getMessage());
            }
        }
    }

    private void generarPDFHistorialDesbloqueos(HistorialDesbloqueosFORM formHistorialDesbloq) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF de historial de desbloqueos");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

        if (fileChooser.showSaveDialog(formHistorialDesbloq) == JFileChooser.APPROVE_OPTION) {
            try {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                if (!ruta.toLowerCase().endsWith(".pdf")) {
                    ruta += ".pdf";
                }
                FiltrosDTO filtros = obtenerFiltrosHistorialDesbloqueos(formHistorialDesbloq);
                generarPDFHistorial.descargarPDFHistorialDesbloqueos(filtros, ruta);
                mostrarInfo(formHistorialDesbloq, "PDF generado correctamente.");
            } catch (NegocioException ex) {
                mostrarError(formHistorialDesbloq, ex.getMessage());
            }
        }
    }
}