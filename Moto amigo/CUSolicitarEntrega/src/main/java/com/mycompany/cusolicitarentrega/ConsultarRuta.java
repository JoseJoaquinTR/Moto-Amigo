
package com.mycompany.cusolicitarentrega;

import com.consultarruta.servicios.mapBox.MapBoxService;
import com.mycompany.motoamigodto.RutaRequestDTO;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigonegocio.FachadaNegocio;
import com.mycompany.motoamigonegocio.IFachadaNegocio;
import com.mycompany.motoamigonegocio.IRutaBO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.RutaBO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ConsultarRuta implements IConsultarRuta {

    private IFachadaNegocio fachada;

    public ConsultarRuta(IRutaBO negocio) {
        this.fachada = new FachadaNegocio(
        new RutaBO( MapBoxService.getInstance())
);
    }

    @Override
    public RutaResponseDTO consultarRuta(RutaRequestDTO rutaDTO) {
        try {
            return fachada.calcularRuta(rutaDTO);
        } catch (NegocioException ex) {
            Logger.getLogger(ConsultarRuta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
