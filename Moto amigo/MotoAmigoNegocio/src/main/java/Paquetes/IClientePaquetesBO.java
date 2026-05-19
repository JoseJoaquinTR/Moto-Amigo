
package Paquetes;

/**
 *
 * @author joset
 */
public interface IClientePaquetesBO {

    /**
     * crea un observador para recibir notificaciones de cambios en
     * paquetes.
     *
     * @param observador observador a registrar.
     */
    void agregarObservador(IObservadorPaquetesBO observador);

    /**
     * elimina un observador.
     *
     * @param observador observador a remover.
     */
    void eliminarObservador(IObservadorPaquetesBO observador);
}
