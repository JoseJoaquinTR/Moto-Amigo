package Adapter;

import com.mycompany.Entidades.Documento;
import com.mycompany.emprendedoresdto.DocumentoDTO;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class AdapterDocumento {

    public AdapterDocumento() {
    }

    /**
     * Metodo que adapta un DocumentoDTO a una entidad Documento
     *
     * @param documentoDTO el documentoDTO que se adaptara
     * @return la entidad de Documento
     */
    public static Documento dtoADocumento(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setIdDocumento(new ObjectId().toHexString());
        documento.setDocumento(documentoDTO.getDocumento());

        return documento;
    }

    /**
     * Metodo que adapta una entidad de Documento a documentoDTO
     *
     * @param documento el documento que se adaptara a DTO
     * @return el DocumentoDTO adaptado
     */
    public static DocumentoDTO documentoADTO(Documento documento) {
        return new DocumentoDTO(documento.getDocumento());
    }
}
