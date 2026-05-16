package com.mycompany.emprendedoresdto;

/**
 * Esta clase es la DTO para registrar a un
 * nuevo emprendedor
 * @author Jesus Omar
 */
public class NuevoEmprendedorDTO {
    
    private String nombre;
    private String correo;
    private String telefono;
    private String rfc;
    private byte[] fotoPerfil;
    private byte[] documentoIne;

    /**
     * Constructor que recibe todos los atributos del emprendedor
     * que sera registrado
     * @param nombre
     * @param correo
     * @param telefono
     * @param rfc
     * @param fotoPerfil
     * @param documentoIne 
     */
    public NuevoEmprendedorDTO(String nombre, String correo, String telefono, String rfc, byte[] fotoPerfil, byte[] documentoIne) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.fotoPerfil = fotoPerfil;
        this.documentoIne = documentoIne;
    }

    /**
     * 
     * @return el nombre del emprendedor 
     * que sera registrado
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * 
     * @return el correo del emprendedor
     * que sera registrado
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * 
     * @return el telefono del emprendedor
     * que sera registrado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * 
     * @return el rfc del emprendedor
     * que sera registrado
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * 
     * @return la foto de perfil del emprendedor
     * que sera registrado
     */
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * 
     * @return el documento de la identificacion
     * del emprendedor que sera registrado
     */
    public byte[] getDocumentoIne() {
        return documentoIne;
    }
    
}
