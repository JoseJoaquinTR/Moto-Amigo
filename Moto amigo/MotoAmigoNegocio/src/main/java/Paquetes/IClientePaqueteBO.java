
package Paquetes;

/**
 *
 * @author joset
 */
public interface IClientePaqueteBO {

    /**
     * crea un observador para recibir notificaciones de cambios en
     * paquetes.
     *
     * @param observador observador a registrar.
     */
    void agregarObservador(IObservadorPaqueteBO observador);

    /**
     * elimina un observador.
     *
     * @param observador observador a remover.
     */
    void eliminarObservador(IObservadorPaqueteBO observador);
}
