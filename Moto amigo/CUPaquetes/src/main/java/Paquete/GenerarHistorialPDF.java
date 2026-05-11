
package Paquete;

import Paquetes.IPaqueteHistorialBO;
import Paquetes.PaqueteHistorialBO;
import com.mycompany.motoamigodto.ReporteHistorialPaquetePDFDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Caso de uso para generar y descargar el historial de paquetes.
 *
 * @author joset
 */
public class GenerarHistorialPDF implements IGenerarHistorialPDF {

    private final IPaqueteHistorialBO paqueteHistorialBO;

    public GenerarHistorialPDF() {
        this.paqueteHistorialBO = PaqueteHistorialBO.getInstancia();
    }

    @Override
    public ReporteHistorialPaquetePDFDTO generar() throws PaqueteException {
        try {
            return paqueteHistorialBO.generarHistorial(null);
        } catch (NegocioException ex) {
            throw new PaqueteException("Error al generar el historial de paquetes.", ex);
        }
    }

    @Override
    public boolean descargar(ReporteHistorialPaquetePDFDTO reporte) throws PaqueteException {
        try {
            return paqueteHistorialBO.descargarPDF(reporte);
        } catch (NegocioException ex) {
            throw new PaqueteException("Error al descargar el PDF del historial.", ex);
        }
    }
}