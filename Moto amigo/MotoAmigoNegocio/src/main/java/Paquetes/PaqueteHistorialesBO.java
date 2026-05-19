package Paquetes;

import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.paquetesdto.PaqueteHistorialDTO;
import com.mycompany.paquetesdto.ReporteHistorialPaquetePDFDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author joset
 */
public class PaqueteHistorialesBO implements IPaqueteHistorialesBO {

    private static PaqueteHistorialesBO instancia;

    private final IFachadaPersistencia fachada;

    private PaqueteHistorialesBO() {
        this.fachada = FachadaPersistencia.getInstancia();
    }

    public static synchronized PaqueteHistorialesBO getInstancia() {
        if (instancia == null) {
            instancia = new PaqueteHistorialesBO();
        }
        return instancia;
    }

    @Override
    public ReporteHistorialPaquetePDFDTO generarHistorial(String idEmprendedor)
            throws NegocioException {
        if (idEmprendedor == null || idEmprendedor.isBlank()) {
            throw new NegocioException("El id del emprendedor es obligatorio.");
        }

        try {
            List<Paquete> paquetes = fachada.obtenerPaquetesPorEmprendedor(idEmprendedor);
            List<PaqueteHistorialDTO> historial = new ArrayList<>();

            int usosTotal = 0;
            for (Paquete p : paquetes) {
                int numeroUsos = 0;
                LocalDate ultimoUso = null;
                Float pesoPaquete = calcularPesoTotal(p);

                historial.add(new PaqueteHistorialDTO(
                        numeroUsos,
                        p.getNombre(),
                        ultimoUso,
                        pesoPaquete
                ));
                usosTotal += numeroUsos;
            }

            ReporteHistorialPaquetePDFDTO reporte = new ReporteHistorialPaquetePDFDTO();
            reporte.setTitulo("Historial de paquetes");
            reporte.setFecha(LocalDate.now());
            reporte.setNumeroPaquetesRegistrados(paquetes.size());
            reporte.setUsosTotal(usosTotal);
            reporte.setPaqueteHistorial(historial);
            return reporte;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar el historial de paquetes.", ex);
        }
    }

    @Override
    public boolean descargarPDF(ReporteHistorialPaquetePDFDTO reporte, String rutaDestino) throws NegocioException {
        if (reporte == null) {
            throw new NegocioException("El reporte no puede ser nulo.");
        }
        if (rutaDestino == null || rutaDestino.isBlank()) {
            throw new NegocioException("La ruta de destino es obligatoria.");
        }

        try (InputStream reporteStream = getClass().getResourceAsStream("/reportes/historial_paquetes.jrxml")) {
            if (reporteStream == null) {
                throw new NegocioException("No se encontró la plantilla del reporte.");
            }

            // Compilar la plantilla
            JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);

            // Preparar parámetros del reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO", reporte.getTitulo() != null ? reporte.getTitulo() : "motoAmigo");
            parametros.put("FECHA", reporte.getFecha() != null
                    ? reporte.getFecha().format(DateTimeFormatter.ofPattern("d/M/yyyy"))
                    : "");
            parametros.put("PAQUETES_REGISTRADOS", reporte.getNumeroPaquetesRegistrados());
            parametros.put("USOS_TOTAL", reporte.getUsosTotal());
            parametros.put("PESO_PROMEDIO", calcularPesoPromedio(reporte));

            // Preparar los datos de la tabla (los PaqueteHistorialDTO)
            List<Map<String, Object>> filas = new ArrayList<>();
            if (reporte.getPaqueteHistorial() != null) {
                for (PaqueteHistorialDTO h : reporte.getPaqueteHistorial()) {
                    Map<String, Object> fila = new HashMap<>();
                    fila.put("nombre", h.getNombre());
                    fila.put("numeroUsos", h.getNumeroUsos());
                    fila.put("ultimoUso", h.getUltimoUso() != null
                            ? h.getUltimoUso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            : null);
                    fila.put("pesoPaquete", h.getPesoPaquete());
                    filas.add(fila);
                }
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(filas);

            // Llenar la plantilla
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

            // Exportar a PDF en la ruta indicada
            File archivo = new File(rutaDestino);
            try (OutputStream out = new FileOutputStream(archivo)) {
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();
            }

            return true;
        } catch (JRException | java.io.IOException ex) {
            throw new NegocioException("Error al generar el PDF: " + ex.getMessage(), ex);
        }
    }

    /**
     * Calcula el peso promedio de los paquetes del historial. Devuelve una
     * cadena ya formateada con "kg" para mostrarse directo en el reporte.
     */
    private String calcularPesoPromedio(ReporteHistorialPaquetePDFDTO reporte) {
        if (reporte.getPaqueteHistorial() == null || reporte.getPaqueteHistorial().isEmpty()) {
            return "0.00 kg";
        }
        float total = 0f;
        int count = 0;
        for (PaqueteHistorialDTO h : reporte.getPaqueteHistorial()) {
            total += h.getPesoPaquete();
            count++;
        }
        float promedio = count > 0 ? total / count : 0f;
        return String.format("%.2f kg", promedio);
    }

    /**
     * Calcula el peso total del paquete sumando el pesoTotal de cada referencia
     * de producto. Si la lista está vacía, devuelve 0.
     */
    private Float calcularPesoTotal(Paquete paquete) {
        if (paquete == null || paquete.getProductos() == null) {
            return 0f;
        }
        float total = 0f;
        for (ProductosPaquete prodPaque : paquete.getProductos()) {
            if (prodPaque != null) {
                total += prodPaque.getPesoTotal();
            }
        }
        return total;
    }

}
