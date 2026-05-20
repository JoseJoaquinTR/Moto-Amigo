package Adapter;

import Enums.TipoNegocio;
import com.mycompany.Entidades.Negocio;
import com.mycompany.emprendedoresdto.NegocioDTO;
import enums.TipoNegocioDTO;

/**
 *
 * @author Jesus Omar
 */
public class AdapterNegocio {

    public AdapterNegocio() {
    }

    /**
     * Este metodo convierte un NegocioDTO a un Negocio de persistencia
     *
     * @param negocioDTO el negocioDTO que se convertira
     * @param idEmprendedor el id del emprendedor que esta asociado al negocio
     * @return una entidad de Negocio
     */
    public static Negocio negocioDTOANegocio(NegocioDTO negocioDTO, String idEmprendedor) {
        Negocio negocio = new Negocio();
        negocio.setIdEmprendedor(idEmprendedor);
        negocio.setNombre(negocioDTO.getNombre());
        negocio.setTipoNegocio(tipoNegocioDTOATipoNegocio(negocioDTO.getTipoNegocio()));
        negocio.setDireccion(AdapterDireccion.dtoADireccion(negocioDTO.getDireccion()));
        return negocio;
    }

    /**
     * Convierte una entidad de Negocio a NegocioDTO
     *
     * @param negocio
     * @return
     */
    public static NegocioDTO negocioANegocioDTO(Negocio negocio) {
        return new NegocioDTO(
                negocio.getNombre(),
                AdapterDireccion.direccionADTO(negocio.getDireccion()),
                tipoNegocioATipoNegocioDTO(negocio.getTipoNegocio())
        );
    }

    /**
     * Convierte un TipoNegocioDTO a un TipoNegocio de persistencia
     *
     * @param tipo el TipoNegocioDTO que se convertira
     * @return el TipoNegocio de persistencia
     */
    public static TipoNegocio tipoNegocioDTOATipoNegocio(TipoNegocioDTO tipo) {
        if (tipo.toString().equals("RESTAURANTE")) {
            return TipoNegocio.RESTAURANTE;
        } else if (tipo.toString().equals("DARK_KITCHEN")) {
            return TipoNegocio.DARK_KITCHEN;
        } else {
            return TipoNegocio.TIENDA_LOCAL;
        }
    }

    /**
     * Convierte un TipoNegocio de persistencia a DTO
     *
     * @param tipo el tipo que se convertira a DTO
     * @return el tipo de negocio DTO
     */
    public static TipoNegocioDTO tipoNegocioATipoNegocioDTO(TipoNegocio tipo) {
        if (tipo.toString().equals("RESTAURANTE")) {
            return TipoNegocioDTO.RESTAURANTE;
        } else if (tipo.toString().equals("DARK_KITCHEN")) {
            return TipoNegocioDTO.DARK_KITCHEN;
        } else {
            return TipoNegocioDTO.TIENDA_LOCAL;
        }
    }
}
