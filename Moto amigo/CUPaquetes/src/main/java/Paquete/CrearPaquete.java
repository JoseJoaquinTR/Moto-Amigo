package Paquete;

import Paquetes.PaquetesBO;
import com.mycompany.paquetesdto.NuevoPaqueteDTO;
import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import Paquetes.IPaquetesBO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import static enums.TamañoPaqueteDTO.CHICO;
import static enums.TamañoPaqueteDTO.GRANDE;
import static enums.TamañoPaqueteDTO.MEDIANO;

/**
 *
 * @author joset
 */
public class CrearPaquete implements ICrearPaquete {

    private final IPaquetesBO paqueteBO;
    private static final float PESO_MAX_CHICO = 10f;
    private static final float PESO_MAX_MEDIANO = 20f;
    private static final float PESO_MAX_GRANDE = 50f;
    private static final int PRODUCTOS_MAX_CHICO = 10;
    private static final int PRODUCTOS_MAX_MEDIANO = 25;
    private static final int PRODUCTOS_MAX_GRANDE = 50;
    private static final int CANTIDAD_MAX_POR_PRODUCTO = 50;

    public CrearPaquete() {
        this.paqueteBO = PaquetesBO.getInstancia();
    }

    @Override
    public PaqueteDTO crear(NuevoPaqueteDTO paquete) throws PaqueteException {

        try {
            validarNuevoPaquete(paquete);
            return paqueteBO.crearPaquete(paquete);
        } catch (NegocioException ex) {
            throw new PaqueteException(ex.getMessage(), ex);
        }
    }

    private void validarNuevoPaquete(NuevoPaqueteDTO paquete) throws NegocioException {
        if (paquete == null) {
            throw new NegocioException("El paquete a crear no puede ser nulo.");
        }
        if (paquete.getNombre() == null || paquete.getNombre().isBlank()) {
            throw new NegocioException("El nombre del paquete es obligatorio.");
        }
        if (paquete.getTamaño() == null) {
            throw new NegocioException("El tamaño del paquete es obligatorio.");
        }
        if (paquete.getProductos() == null || paquete.getProductos().isEmpty()) {
            throw new NegocioException("El paquete debe tener al menos un producto.");
        }
        validarLimitesPaquete(paquete);
    }

    private void validarLimitesPaquete(NuevoPaqueteDTO nuevo) throws NegocioException {
        if (nuevo.getProductos() == null) {
            return;
        }

        float pesoMax;
        int productosMax;
        String tamañoTexto;

        switch (nuevo.getTamaño()) {
            case CHICO -> {
                pesoMax = PESO_MAX_CHICO;
                productosMax = PRODUCTOS_MAX_CHICO;
                tamañoTexto = "CHICO";
            }
            case MEDIANO -> {
                pesoMax = PESO_MAX_MEDIANO;
                productosMax = PRODUCTOS_MAX_MEDIANO;
                tamañoTexto = "MEDIANO";
            }
            case GRANDE -> {
                pesoMax = PESO_MAX_GRANDE;
                productosMax = PRODUCTOS_MAX_GRANDE;
                tamañoTexto = "GRANDE";
            }
            default -> {
                return;
            }
        }

        if (nuevo.getProductos().size() > productosMax) {
            throw new NegocioException(
                    "Un paquete " + tamañoTexto + " admite un máximo de " + productosMax + " productos distintos."
            );
        }

        float pesoTotal = 0f;
        for (ProductosPaqueteDTO pp : nuevo.getProductos()) {
            if (pp.getCantidad() > CANTIDAD_MAX_POR_PRODUCTO) {
                throw new NegocioException(
                        "La cantidad por producto no puede exceder " + CANTIDAD_MAX_POR_PRODUCTO + " unidades."
                );
            }
            pesoTotal += pp.getPesoTotal();
        }

        if (pesoTotal > pesoMax) {
            throw new NegocioException(
                    "El peso total (" + String.format("%.2f", pesoTotal) + " kg) " + "excede el máximo permitido para un paquete " + tamañoTexto
                    + " (" + pesoMax + " kg)."
            );
        }
    }
}
