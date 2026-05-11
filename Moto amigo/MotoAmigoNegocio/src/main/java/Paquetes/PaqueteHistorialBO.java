
package Paquetes;

import Paquetes.IPaqueteHistorialBO;
import com.mycompany.Entidades.Paquete;
import com.mycompany.Entidades.ProductosPaquete;
import com.mycompany.motoamigodto.PaqueteHistorialDTO;
import com.mycompany.motoamigodto.ReporteHistorialPaquetePDFDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class PaqueteHistorialBO implements IPaqueteHistorialBO {

    private static PaqueteHistorialBO instancia;

    private final IFachadaPersistencia fachada;

    private PaqueteHistorialBO() {
        this.fachada = FachadaPersistencia.getInstancia();
    }

    public static synchronized PaqueteHistorialBO getInstancia() {
        if (instancia == null) {
            instancia = new PaqueteHistorialBO();
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
                int pesoPaquete = calcularPesoTotal(p);

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
    public boolean descargarPDF(ReporteHistorialPaquetePDFDTO reporte) throws NegocioException {
        if (reporte == null) {
            throw new NegocioException("El reporte no puede ser nulo.");
        }
        return false;
    }

    /**
     * Calcula el peso total del paquete sumando el pesoTotal de cada referencia
     * de producto. Si la lista está vacía, devuelve 0.
     */
    private int calcularPesoTotal(Paquete paquete) {
        if (paquete == null || paquete.getProductos() == null) {
            return 0;
        }
        float total = 0f;
        for (ProductosPaquete prodPaque : paquete.getProductos()) {
            if (prodPaque != null) {
                total += prodPaque.getPesoTotal();
            }
        }
        return Math.round(total);
    }

}
