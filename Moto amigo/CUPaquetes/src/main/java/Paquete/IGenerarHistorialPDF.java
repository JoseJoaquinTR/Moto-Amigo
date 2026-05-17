
package Paquete;

import com.mycompany.paquetesdto.ReporteHistorialPaquetePDFDTO;

/**
 *
 * @author joset
 */
public interface IGenerarHistorialPDF {

    /**
     * Genera un reporte con el historial de uso de los paquetes.
     *
     * @return reporte generado, o null si ocurre un error.
     */
    ReporteHistorialPaquetePDFDTO generar()throws PaqueteException;

    /**
     * Descarga el reporte indicado a un archivo PDF.
     *
     * @param reporte reporte a descargar.
     * @return true si se descargó correctamente, false en caso contrario.
     */
    boolean descargar(ReporteHistorialPaquetePDFDTO reporte)throws PaqueteException;
}
