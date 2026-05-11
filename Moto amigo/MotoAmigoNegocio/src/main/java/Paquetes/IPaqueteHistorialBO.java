
package Paquetes;

import com.mycompany.motoamigodto.ReporteHistorialPaquetePDFDTO;
import com.mycompany.motoamigonegocio.NegocioException;

/**
 * Operaciones de negocio relacionadas con el historial de uso de paquetes.
 *
 * @author joset
 */
public interface IPaqueteHistorialBO {

    /**
     * Genera el reporte de historial de paquetes de un emprendedor: cuántas
     * veces se ha usado cada paquete, fecha del último uso y peso.
     *
     * @param idEmprendedor identificador del emprendedor.
     * @return reporte con la lista de paquetes y sus estadísticas de uso.
     * @throws NegocioException si falla la persistencia.
     */
    ReporteHistorialPaquetePDFDTO generarHistorial(String idEmprendedor) throws NegocioException;

    /**
     * Genera un archivo PDF a partir del reporte de historial.
     *
     * @param reporte reporte previamente generado.
     * @return true si el PDF se generó correctamente, false en caso contrario.
     * @throws NegocioException si ocurre un error al generar el PDF.
     */
    boolean descargarPDF(ReporteHistorialPaquetePDFDTO reporte) throws NegocioException;

}
