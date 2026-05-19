
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
     * @param idEmprendedor
     * @return reporte generado, o null si ocurre un error.
     * @throws Paquete.PaqueteException
     */
    ReporteHistorialPaquetePDFDTO generar(String idEmprendedor)throws PaqueteException;

    /**
     * Descarga el reporte indicado a un archivo PDF.
     *
     * @param reporte reporte a descargar.
     * @param rutaDestino
     * @return true si se descargó correctamente, false en caso contrario.
     * @throws Paquete.PaqueteException
     */
    boolean descargar(ReporteHistorialPaquetePDFDTO reporte, String rutaDestino)throws PaqueteException;
}
