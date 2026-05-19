/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminPresentacion;

import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.MotivoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.fachada.FachadaNegocio;
import com.mycompany.motoamigonegocio.fachada.IFachadaNegocio;
import enums.Tipo;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ControlCUBloqueos {

    private List<MotivoDTO> motivosFiltroBloqueo = new LinkedList<>();
    private List<MotivoDTO> motivosDesbloqueo = new LinkedList<>();
    private List<RepartidorDTO> repartidoresBloqueo = new LinkedList<>();
    private List<MotivoDTO> motivosBloqueo = new LinkedList<>();
    private byte[] imagenEvidenciaBloqueo;
    private final IFachadaNegocio fachada;

    public ControlCUBloqueos() {
        this.fachada = FachadaNegocio.crear();
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
            modelo.addRow(new Object[]{
                repartidor.getNombre(),
                repartidor.getEstado(),
                repartidor.getNumBloqueos()
            });
        }
    }

    public void inicializarBloquearRepartidor(BloquearRepartidorFORM formBloq) {
        cargarComboRepartidoresActivos(formBloq);
        cargarComboMotivosBloqueo(formBloq);
        formBloq.getBtnSeleccionarArchivo().addActionListener(e -> seleccionarImagenBloqueo(formBloq));
    }

    private void cargarComboRepartidoresActivos(BloquearRepartidorFORM formBloq) {
        try {
            repartidoresBloqueo = fachada.obtenerRepartidoresActivos();
            formBloq.getCboRepartidores().removeAllItems();

            for (RepartidorDTO repartidor : repartidoresBloqueo) {
                formBloq.getCboRepartidores().addItem(repartidor.getNombre());
            }
        } catch (NegocioException ex) {
            mostrarError(formBloq, "Error al cargar repartidores: " + ex.getMessage());
        }
    }

    private void cargarComboMotivosBloqueo(BloquearRepartidorFORM formBloq) {
        try {
            motivosBloqueo = fachada.obtenerMotivos(Tipo.BLOQUEO);
            formBloq.getCboMotivos().removeAllItems();

            for (MotivoDTO motivo : motivosBloqueo) {
                formBloq.getCboMotivos().addItem(motivo.getMotivo());
            }
        } catch (NegocioException ex) {
            mostrarError(formBloq, "Error al cargar motivos: " + ex.getMessage());
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
            } catch (Exception ex) {
                mostrarError(formBloq, "Error al cargar imagen: " + ex.getMessage());
            }
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
        int indice = formBloq.getCboMotivos().getSelectedIndex();

        if (indice < 0 || indice >= motivosBloqueo.size()) {
            return null;
        }

        return motivosBloqueo.get(indice);
    }

    public byte[] getImagenEvidenciaBloqueo() {
        return imagenEvidenciaBloqueo;
    }

    private void mostrarError(java.awt.Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void inicializarDesbloqueoMasivo(DesbloqueoMasivoFORM formDesbloq) {
        cargarComboMotivosFiltros(formDesbloq);
        cargarComboMotivosDesbloqueo(formDesbloq);
    }

    private void cargarComboMotivosFiltros(DesbloqueoMasivoFORM formDesbloq) {
        try {
            motivosFiltroBloqueo = fachada.obtenerMotivos(Tipo.BLOQUEO);

            formDesbloq.getCboMotivosFiltros().removeAllItems();
            formDesbloq.getCboMotivosFiltros().addItem("Todos");

            for (MotivoDTO motivo : motivosFiltroBloqueo) {
                formDesbloq.getCboMotivosFiltros().addItem(motivo.getMotivo());
            }

        } catch (NegocioException ex) {
            mostrarError(formDesbloq, "Error al cargar motivos de bloqueo: " + ex.getMessage());
        }
    }

    private void cargarComboMotivosDesbloqueo(DesbloqueoMasivoFORM formDesbloq) {
        try {
            motivosDesbloqueo = fachada.obtenerMotivos(Tipo.DESBLOQUEO);

            formDesbloq.getCboMotivos().removeAllItems();

            for (MotivoDTO motivo : motivosDesbloqueo) {
                formDesbloq.getCboMotivos().addItem(motivo.getMotivo());
            }

        } catch (NegocioException ex) {
            mostrarError(formDesbloq, "Error al cargar motivos de desbloqueo: " + ex.getMessage());
        }
    }

    public MotivoDTO obtenerMotivoFiltroSeleccionado(DesbloqueoMasivoFORM formDesbloq) {

        int indice = formDesbloq.getCboMotivosFiltros().getSelectedIndex();

        if (indice <= 0) {
            return null;
        }

        return motivosFiltroBloqueo.get(indice - 1);
    }

    public MotivoDTO obtenerMotivoDesbloqueoSeleccionado(DesbloqueoMasivoFORM formDesbloq) {

        int indice = formDesbloq.getCboMotivos().getSelectedIndex();

        if (indice < 0 || indice >= motivosDesbloqueo.size()) {
            return null;
        }

        return motivosDesbloqueo.get(indice);
    }

    public Integer obtenerNumeroBloqueosSeleccionado(DesbloqueoMasivoFORM formDesbloq) {

        String valor = formDesbloq.getCboNumBloqueos().getSelectedItem().toString();

        return Integer.valueOf(valor);
    }

    public LocalDateTime obtenerFechaDesde(DesbloqueoMasivoFORM formDesbloq) {

        if (formDesbloq.getDtpFechaDesde().getDate() == null) {
            return null;
        }

        return formDesbloq.getDtpFechaDesde().getDate().atStartOfDay();
    }

    public LocalDateTime obtenerFechaHasta(DesbloqueoMasivoFORM formDesbloq) {

        if (formDesbloq.getDtpFechaHasta().getDate() == null) {
            return null;
        }

        return formDesbloq.getDtpFechaHasta().getDate().atTime(23, 59, 59);
    }

    public FiltrosDTO obtenerFiltrosDesbloqueo(DesbloqueoMasivoFORM formDesbloq) {

        FiltrosDTO filtros = new FiltrosDTO();

        filtros.setFechaDesde(obtenerFechaDesde(formDesbloq));

        filtros.setFechaHasta(obtenerFechaHasta(formDesbloq));

        filtros.setMotivo(obtenerMotivoFiltroSeleccionado(formDesbloq));

        filtros.setNumBloqueos(obtenerNumeroBloqueosSeleccionado(formDesbloq));

        return filtros;
    }

    public String obtenerDetallesDesbloqueo(DesbloqueoMasivoFORM formDesbloq) {
        return formDesbloq.getTxtDetalles().getText().trim();
    }

}
