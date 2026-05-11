
package observerCambiosPaquete;

/**
 * 
 * @author joset
 */
public interface IObserverCambiosPaquete {

    /**
     * Crea un observador para recibir notificaciones de cambios.
     *
     * @param observador observador a registrar.
     */
    void agregarObservador(IObservadorPaqueteCU observador);

    /**
     * elimina un observador.
     *
     * @param observador observador a remover.
     */
    void eliminarObservador(IObservadorPaqueteCU observador);
}