package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class ImagenDTO {
    
    private byte[] imagen;

    public ImagenDTO() {
    }

    public ImagenDTO(byte[] imagen) {
        this.imagen = imagen;
    }

    public byte[] getImagen() {
        return imagen;
    }
    
}
