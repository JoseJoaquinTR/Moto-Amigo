package observerCambiosPaquete;

import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.productosdto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import Paquetes.IObservadorPaquetesBO;

/**
 *
 * @author joset
 */
public class ObserverCambiosPaquete implements IObservadorPaquetesBO, IObserverCambiosPaquete {

    private static ObserverCambiosPaquete instancia;

    private final List<IObservadorPaqueteCU> observadores;

    private ObserverCambiosPaquete() {
        this.observadores = new ArrayList<>();
    }

    public static synchronized ObserverCambiosPaquete getInstancia() {
        if (instancia == null) {
            instancia = new ObserverCambiosPaquete();
            Paquetes.PaquetesBO.getInstancia().agregarObservador(instancia);
        }
        return instancia;
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
        for (IObservadorPaqueteCU observer : new ArrayList<>(observadores)) {
            observer.paqueteCreado(paquete);
        }
    }

    @Override
    public void paqueteEditado(PaqueteDTO paquete) {
        for (IObservadorPaqueteCU observer : new ArrayList<>(observadores)) {
            observer.paqueteEditado(paquete);
        }
    }

    @Override
    public void paqueteEliminado(String idPaquete) {
        for (IObservadorPaqueteCU observer : new ArrayList<>(observadores)) {
            observer.paqueteEliminado(idPaquete);
        }
    }

    @Override
    public void productoAgregadoAPaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaqueteCU observer : new ArrayList<>(observadores)) {
            observer.productoAgregadoAPaquete(idPaquete, producto);
        }
    }

    @Override
    public void productoQuitadoDePaquete(String idPaquete, ProductoDTO producto) {
        for (IObservadorPaqueteCU observer : new ArrayList<>(observadores)) {
            observer.productoQuitadoDePaquete(idPaquete, producto);
        }
    }
}
