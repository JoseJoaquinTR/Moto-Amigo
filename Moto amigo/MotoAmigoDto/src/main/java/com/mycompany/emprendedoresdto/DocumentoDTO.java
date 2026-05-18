package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class DocumentoDTO {

    private byte[] documento;

    public DocumentoDTO() {
    }

    public DocumentoDTO(byte[] documento) {
        this.documento = documento;
    }

    public byte[] getDocumento() {
        return documento;
    }

}
