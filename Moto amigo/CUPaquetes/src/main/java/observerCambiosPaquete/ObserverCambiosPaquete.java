
package observerCambiosPaquete;

import Paquetes.IObservadorPaqueteBO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class ObserverCambiosPaquete implements IObservadorPaqueteBO, IObserverCambiosPaquete {

    private final List<IObservadorPaqueteCU> observadores;

    public ObserverCambiosPaquete() {
        this.observadores = new ArrayList<>();
    }

    @Override
    public void agregarObservador(IObservadorPaqueteCU observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void eliminarObservador(IObservadorPaqueteCU observador) {
        observadores.remove(observador);
    }

    @Override
    public void paqueteCreado(PaqueteDTO paquete) {
        for (IObservadorPaqueteCU observer : observadores) {
            observer.paqueteCreado(paquete);
        }
    }

    @Override
    public void paqueteEditado(PaqueteDTO paquete) {
        for (IObservadorPaqueteCU observer : observadores) {
            observer.paqueteEditado(paquete);
        }
    }

    @Override
    public void paqueteEliminado(String idPaquete) {
        for (IObservadorPaqueteCU observer : observadores) {
            observer.paqueteEliminado(idPaquete);
        }
    }

    @Override
    public void productoAgregadoAPaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaqueteCU observer : observadores) {
            observer.productoAgregadoAPaquete(idPaquete, producto);
        }
    }

    @Override
    public void productoQuitadoDePaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaqueteCU observer : observadores) {
            observer.productoQuitadoDePaquete(idPaquete, producto);
        }
    }
}